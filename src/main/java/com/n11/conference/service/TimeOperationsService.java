package com.n11.conference.service;

import org.joda.time.DateTime;

public interface TimeOperationsService {
	public int getTotalTime();
	
	public int getBeforeLaunchTime();
	
	public int getAfterLaunchTime();
	
	public DateTime getTime(String time);
	
	public DateTime addTime(DateTime timeStart, int add);
	
	public String getTimeInHHMM(DateTime time);

	public boolean isAvailableForNetwork(String lastTalkEndTime);
	
	public int getTimeInMinutesBetween(String tOne, String tTwo);
	
}
