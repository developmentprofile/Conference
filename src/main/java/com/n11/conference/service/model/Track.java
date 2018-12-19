package com.n11.conference.service.model;

import java.util.ArrayList;

public class Track {
	private Integer id;
	private ArrayList<Session> sessionList;
	
	public ArrayList<Session> getSessionList() {
		return sessionList;
	}
	public void setSessionList(ArrayList<Session> sessionList) {
		this.sessionList = sessionList;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
