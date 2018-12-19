package com.n11.conference.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.n11.conference.dao.entity.Talk;
import com.n11.conference.dao.repo.TalkRepository;
import com.n11.conference.service.model.Session;
import com.n11.conference.service.model.TalkDTO;
import com.n11.conference.service.model.TimeTable;
import com.n11.conference.service.model.Track;
import com.n11.conference.util.ApplicationProperties;
import com.n11.conference.util.TalkType;

@Service
public class ConferancePlannerImpl implements ConferancePlanner{

	private TalkRepository talkRepository;
	
	private TalkMapper talkMapper;
	
	private TimeOperationsService timeOperationsService;
	
	private ApplicationProperties applicationProperties;
	
	public ConferancePlannerImpl(TalkRepository talkRepository, TalkMapper talkMapper, TimeOperationsService timeOperationsService, ApplicationProperties applicationProperties) {
		this.talkRepository = talkRepository;
		this.talkMapper = talkMapper;
		this.timeOperationsService = timeOperationsService;
		this.applicationProperties = applicationProperties;
	}
	
	@Override
	public TimeTable scheduleConferance() {
		TimeTable timeTable = new TimeTable();
		ArrayList<Track> trackList = new ArrayList<>();
		
		List<Talk> talkList = talkRepository.findAll();
		List<TalkDTO> talkDtoList = talkMapper.toTalkDTO(talkList);
		talkDtoList.sort(Comparator.comparing(TalkDTO::getTalkTime));
	
		int id = 1;
		while (!talkDtoList.isEmpty()) {
			Track track = new Track();
			track.setId(id++);
			
			ArrayList<TalkDTO> selectedTalkList = getPlannedTalksForInterval(talkDtoList, timeOperationsService.getBeforeLaunchTime());
			track.setSessionList(generateSession(getPlannedTalksForInterval(talkDtoList, timeOperationsService.getBeforeLaunchTime()),
					applicationProperties.getConfStart()));
			removeSelectedTalks(talkDtoList, selectedTalkList);
			
			if (!talkDtoList.isEmpty()) {
				track.getSessionList().add(addMealSessionIfAvailable());
			}
			selectedTalkList.clear();
			
			selectedTalkList = getPlannedTalksForInterval(talkDtoList, timeOperationsService.getAfterLaunchTime());
			track.getSessionList().addAll(generateSession(selectedTalkList, applicationProperties.getLunchEnd()));
			removeSelectedTalks(talkDtoList, selectedTalkList);
			
			addNetworkSessionIfAvailable(track);
			
			trackList.add(track);
		}
		
		timeTable.setTrackList(trackList);
		return timeTable;
	}
	
	private void removeSelectedTalks(List<TalkDTO> talkDtoList, List<TalkDTO> selectedTalkList) {
		ListIterator<TalkDTO> talkDtoIterator = talkDtoList.listIterator();
		
		while(talkDtoIterator.hasNext()){
			TalkDTO talkDtoIteratorItem = talkDtoIterator.next();
			
			ListIterator<TalkDTO> selectedTalksIterator = selectedTalkList.listIterator();
			while(selectedTalksIterator.hasNext()) {
				TalkDTO selectedTalkDtoIterator = selectedTalksIterator.next();
				if (talkDtoIteratorItem.getTalkName().equals(selectedTalkDtoIterator.getTalkName()))
					talkDtoIterator.remove();
			}
		}
	}
	
	private ArrayList<Session> generateSession(List<TalkDTO> talkDtoList, String timeStart) {
		ArrayList<Session> sessions = new ArrayList<>();
		DateTime startTime = timeOperationsService.getTime(timeStart);
		for (TalkDTO talk : talkDtoList) {
			Session session = new Session();
			session.setStartTime(timeOperationsService.getTimeInHHMM(startTime));
			DateTime endTime = timeOperationsService.addTime(startTime, talk.getTalkTime());
			session.setEndTime(timeOperationsService.getTimeInHHMM(endTime));
			startTime = timeOperationsService.addTime(startTime, talk.getTalkTime());
			session.setTalk(talk);
			sessions.add(session);
		}
		
		return sessions;
	}
	
	private ArrayList<TalkDTO> getPlannedTalksForInterval(List<TalkDTO> talkDtoList, int interval) {
		TalkDTO[] talkDtoArray = talkDtoList.toArray(new TalkDTO[talkDtoList.size()]);
		ArrayList<TalkDTO> selectedTalkList = (ArrayList<TalkDTO>) new GetAvailableTalks(talkDtoArray, interval).solve();
		return selectedTalkList;
	}
	
	private void addNetworkSessionIfAvailable(Track track) {
		if (track != null) {
			if (track.getSessionList() != null) {
				Session lastSession = track.getSessionList().get(track.getSessionList().size() -1 );
				if (timeOperationsService.isAvailableForNetwork(lastSession.getEndTime())) {
					Session networkSession = new Session();
					networkSession.setStartTime(lastSession.getEndTime());
					networkSession.setEndTime(applicationProperties.getNetworkEnd());
					TalkDTO network = new TalkDTO();
					network.setTalkType(TalkType.NETWORKING.getType());
					network.setTalkName(TalkType.NETWORKING.getType());
					network.setTalkTime(timeOperationsService.getTimeInMinutesBetween(networkSession.getStartTime(), networkSession.getEndTime()));
					networkSession.setTalk(network);
					track.getSessionList().add(networkSession);
				}
			}
		}
	}
	
	private Session addMealSessionIfAvailable() {
		Session newSession = new Session();
		TalkDTO meal = new TalkDTO();
		meal.setTalkType(TalkType.MEAL.getType());
		meal.setTalkName(TalkType.MEAL.getType());
		meal.setTalkTime(timeOperationsService.getTimeInMinutesBetween(applicationProperties.getLunchStart(), applicationProperties.getLunchEnd()));
		newSession.setTalk(meal);
		newSession.setStartTime(applicationProperties.getLunchStart());
		newSession.setEndTime(applicationProperties.getLunchEnd());
		return newSession;
	}

}
