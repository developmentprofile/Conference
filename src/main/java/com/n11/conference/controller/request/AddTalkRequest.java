package com.n11.conference.controller.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AddTalkRequest {

	@NotNull
	private String talkName;
	
	@NotNull
	private String talkType;

	@Min(value = 1)
	@Max(value = 180)
	private Integer talkTime;

	public AddTalkRequest() {}

	public String getTalkName() {
		return talkName;
	}

	public void setTalkName(String talkName) {
		this.talkName = talkName;
	}

	public String getTalkType() {
		return talkType;
	}

	public void setTalkType(String talkType) {
		this.talkType = talkType;
	}

	public Integer getTalkTime() {
		return talkTime;
	}

	public void setTalkTime(Integer talkTime) {
		this.talkTime = talkTime;
	}

	
}
