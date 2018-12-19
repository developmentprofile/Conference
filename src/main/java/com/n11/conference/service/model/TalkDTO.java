package com.n11.conference.service.model;

public class TalkDTO {

	private String talkName;
	
	private String talkType;
	
	private Integer talkTime;

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

	public TalkDTO(String talkName, Integer talkTime, String talkType) {
		this.talkName = talkName;
		this.talkType = talkType;
		this.talkTime = talkTime;
	}

	public TalkDTO() {

	}
	
}
