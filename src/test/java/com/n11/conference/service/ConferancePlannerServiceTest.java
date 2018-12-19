package com.n11.conference.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.n11.conference.dao.entity.Talk;
import com.n11.conference.dao.repo.TalkRepository;
import com.n11.conference.service.model.TalkDTO;
import com.n11.conference.service.model.TimeTable;
import com.n11.conference.util.ApplicationProperties;

@RunWith(SpringRunner.class)
public class ConferancePlannerServiceTest {
	
	private ConferancePlanner scheduleConferanceService;
	
	@Mock
	TalkRepository talkRepository;
	
	@Mock
	TalkMapper talkMapper;
	
	@Mock
	TimeOperationsService timeOperationsService;
	
	@Mock
	ApplicationProperties applicationProperties;

	@Before
    public void setUp() {
		scheduleConferanceService = new ConferancePlannerImpl(talkRepository, talkMapper, timeOperationsService, applicationProperties);
    }
	
	@Test
	public void schedule() {
		
		List<Talk> list = new ArrayList<>();
		list.add(new Talk("A", 30, "OTHER"));
		list.add(new Talk("B", 60, "OTHER"));
		list.add(new Talk("C", 90, "OTHER"));
		list.add(new Talk("D", 60, "OTHER"));
		list.add(new Talk("E", 30, "OTHER"));
		list.add(new Talk("F", 45, "OTHER"));
		list.add(new Talk("G", 75, "OTHER"));
		list.add(new Talk("H", 90, "OTHER"));
		
		List<TalkDTO> dtoList = new ArrayList<>();
		dtoList.add(new TalkDTO("A", 30, "OTHER"));
		dtoList.add(new TalkDTO("B", 60, "OTHER"));
		dtoList.add(new TalkDTO("C", 90, "OTHER"));
		dtoList.add(new TalkDTO("D", 60, "OTHER"));
		dtoList.add(new TalkDTO("E", 30, "OTHER"));
		dtoList.add(new TalkDTO("F", 45, "OTHER"));
		dtoList.add(new TalkDTO("G", 75, "OTHER"));
		dtoList.add(new TalkDTO("H", 90, "OTHER"));
		
		when(talkRepository.findAll()).thenReturn(list);
		when(talkMapper.toTalkDTO(list)).thenReturn(dtoList);
		when(timeOperationsService.getBeforeLaunchTime()).thenReturn(180);
		when(timeOperationsService.getAfterLaunchTime()).thenReturn(240);
		when(applicationProperties.getLunchEnd()).thenReturn("13:00");
		when(applicationProperties.getConfStart()).thenReturn("09:00");
		
		TimeTable timeTable = scheduleConferanceService.scheduleConferance();
		
		assertEquals(timeTable.getTrackList().size(), 2);
	}
}
