package com.n11.conference.repo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.n11.conference.dao.entity.Talk;
import com.n11.conference.dao.repo.TalkRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql({"talk.sql"})
public class RepositoryTest {
	
	@Autowired
	TalkRepository talkRepository;
	
	@Test
	public void whenFindByName_thenReturnTalk() {
		Talk talk = talkRepository.findByTalkName("T1");
		assertEquals(talk.getTalkType(), "OTHER");
	
	}
	
	@Test
	public void whenFindByName_thenReturnEmptyObject() {
		Talk talk = talkRepository.findByTalkName("T3");
		assertEquals(talk, null);
	}
	

}
