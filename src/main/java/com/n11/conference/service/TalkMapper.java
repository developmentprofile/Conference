package com.n11.conference.service;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.n11.conference.controller.request.AddTalkRequest;
import com.n11.conference.dao.entity.Talk;
import com.n11.conference.service.model.TalkDTO;

@Mapper(componentModel = "spring")
public interface TalkMapper {
	
	TalkMapper INSTANCE = Mappers.getMapper(TalkMapper.class);

	TalkDTO toTalkDTO(Talk talk);
	
	Talk toTalkEntity(TalkDTO talkDto);
	
	TalkDTO toTalkDTO(AddTalkRequest talkRequest);
	
	List<TalkDTO> toTalkDTO(List<Talk> talkEntityList);
}
