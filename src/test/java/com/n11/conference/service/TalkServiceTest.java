package com.n11.conference.service;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.n11.conference.dao.entity.Talk;
import com.n11.conference.dao.repo.TalkRepository;
import com.n11.conference.exception.types.EntityConflictException;
import com.n11.conference.service.model.TalkDTO;
import com.n11.conference.util.ApplicationProperties;

@RunWith(SpringRunner.class)
public class TalkServiceTest {
	
	private TalkService talkService;
	
	@Mock
	TalkRepository talkRepository;
	
	@Mock
	TalkMapper talkMapper;
	
	@Mock
	ApplicationProperties applicationProperties;

	@Before
    public void setUp() {
		talkService = new TalkServiceImpl(talkRepository, talkMapper, applicationProperties);
    }
	
	@Test(expected = EntityConflictException.class)
	public void addTalk() throws Exception {
		
		TalkDTO talkDTO = new TalkDTO("TALKSERVICETEST", 30, "OTHER");
		Talk talkEntity = new Talk("TALKSERVICETEST", 30, "OTHER");
		
		when(talkMapper.toTalkEntity(talkDTO)).thenReturn(talkEntity);
		when(talkRepository.findByTalkName("TALKSERVICETEST")).thenReturn(talkEntity);
	
		talkService.addTalk(talkDTO);
	}
}
