package com.n11.conference.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.n11.conference.dao.entity.Talk;

public interface TalkRepository extends JpaRepository<Talk, Long>{
	
	Talk findByTalkName(String talkName);
	
}
