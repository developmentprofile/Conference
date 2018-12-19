package com.n11.conference.service.model;

public class Session {
	private TalkDTO talk;
	private String startTime;
	private String endTime;
	
	public TalkDTO getTalk() {
		return talk;
	}
	public void setTalk(TalkDTO talk) {
		this.talk = talk;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
}
