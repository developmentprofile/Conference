package com.n11.conference.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.n11.conference.controller.request.AddTalkRequest;
import com.n11.conference.controller.response.AddTalkResponse;
import com.n11.conference.service.ConferancePlanner;
import com.n11.conference.service.TalkMapper;
import com.n11.conference.service.TalkService;
import com.n11.conference.service.model.TalkDTO;
import com.n11.conference.service.model.TimeTable;

@CrossOrigin
@RestController
public class TalkRestController {

	TalkService talkService;
	
	TalkMapper talkMapper;
	
	ConferancePlanner conferancePlanner;
	
	public TalkRestController(TalkService talkService, TalkMapper talkMapper, ConferancePlanner conferancePlanner) {
		this.talkMapper = talkMapper;
		this.talkService = talkService;
		this.conferancePlanner = conferancePlanner;
	}

	@PostMapping("/talk")
	@CrossOrigin
	@ResponseStatus(HttpStatus.CREATED)
	public AddTalkResponse addTalk(@Valid @RequestBody AddTalkRequest talkRequest) throws Exception {
		
		TalkDTO talkDto = talkMapper.toTalkDTO(talkRequest);
		talkService.addTalk(talkDto);
		
		AddTalkResponse response = new AddTalkResponse();
		return response;
	}
	
	@RequestMapping(
			  value = "/schedule", 
			  produces = "application/json", 
			  method = {RequestMethod.POST}) 
	@ResponseStatus(HttpStatus.OK)
	public TimeTable getSchedule() throws Exception {
		return conferancePlanner.scheduleConferance();
	}
	
}
