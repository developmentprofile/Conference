package com.n11.conference.service;

import org.springframework.stereotype.Service;

import com.n11.conference.dao.entity.Talk;
import com.n11.conference.dao.repo.TalkRepository;
import com.n11.conference.exception.types.EntityConflictException;
import com.n11.conference.service.model.TalkDTO;
import com.n11.conference.util.ApplicationProperties;
import com.n11.conference.util.TalkType;

@Service
public class TalkServiceImpl implements TalkService {

	private TalkRepository talkRepository;
	
	private TalkMapper talkMapper;

	private ApplicationProperties properties;
	
	public TalkServiceImpl(TalkRepository talkRepository, TalkMapper talkMapper, ApplicationProperties properties) {
		this.talkRepository = talkRepository;
		this.talkMapper = talkMapper;
		this.properties = properties;
	}

	@Override
	public void addTalk(TalkDTO talkDto) throws Exception {
		Talk talkEntity = talkMapper.toTalkEntity(talkDto);
		
		talkEntity.setTalkType(TalkType.getType(talkDto.getTalkType()));
		if (TalkType.LIGHTNING.getType().equalsIgnoreCase(talkEntity.getTalkType())) {
			talkEntity.setTalkTime(properties.getLightning());
		}
		
		Talk talk = talkRepository.findByTalkName(talkDto.getTalkName());
		if (talk != null)
			throw new EntityConflictException();
		
		talkRepository.save(talkEntity);
	}

}
