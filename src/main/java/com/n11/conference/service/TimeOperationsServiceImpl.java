package com.n11.conference.service;

import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

import com.n11.conference.util.ApplicationProperties;
import com.n11.conference.util.Parser;

@Service
public class TimeOperationsServiceImpl implements TimeOperationsService {

	ApplicationProperties properties;

	public TimeOperationsServiceImpl(ApplicationProperties properties) {
		this.properties = properties;
	}

	public void setProperties(ApplicationProperties properties) {
		this.properties = properties;
	}

	@Override
	public int getTotalTime() {
		return getBeforeLaunchTime() + getAfterLaunchTime();
	}

	@Override
	public int getBeforeLaunchTime() {
		DateTime lunchBegin = new DateTime().withHourOfDay(Parser.getHour(properties.getLunchStart()))
				.withMinuteOfHour(Parser.getMinute(properties.getLunchStart())).withSecondOfMinute(0);

		DateTime confStart = new DateTime().withHourOfDay(Parser.getHour(properties.getConfStart()))
				.withMinuteOfHour(Parser.getMinute(properties.getConfStart())).withSecondOfMinute(0);

		Minutes firstPart = Minutes.minutesBetween(confStart, lunchBegin);
		return firstPart.getMinutes();
	}

	@Override
	public int getAfterLaunchTime() {
		DateTime confEnd = new DateTime().withHourOfDay(Parser.getHour(properties.getConfEnd()))
				.withMinuteOfHour(Parser.getMinute(properties.getConfEnd())).withSecondOfMinute(0);

		DateTime lunchEnd = new DateTime().withHourOfDay(Parser.getHour(properties.getLunchEnd()))
				.withMinuteOfHour(Parser.getMinute(properties.getLunchEnd())).withSecondOfMinute(0);

		Minutes secondPart = Minutes.minutesBetween(lunchEnd, confEnd);
		return secondPart.getMinutes();
	}

	@Override
	public DateTime getTime(String time) {
		DateTime dateTime = new DateTime().withHourOfDay(Parser.getHour(time)).withMinuteOfHour(Parser.getMinute(time))
				.withSecondOfMinute(0);

		return dateTime;
	}

	public DateTime addTime(DateTime timeStart, int add) {
		DateTime addedTime = timeStart.plusMinutes(add);
		return addedTime;
	}

	@Override
	public String getTimeInHHMM(DateTime time) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");
		return time.toString(formatter);
	}

	@Override
	public boolean isAvailableForNetwork(String lastTalkEndTime) {
		DateTime endTime = getTime(lastTalkEndTime);
		DateTime networkStartTime = getTime(properties.getNetworkStart());
		DateTime networkEndTime = getTime(properties.getNetworkEnd());

		if ((endTime.isEqual(networkStartTime) || endTime.isAfter(networkStartTime))
				&& endTime.isBefore(networkEndTime))
			return true;

		return false;
	}

	@Override
	public int getTimeInMinutesBetween(String tOne, String tTwo) {
		DateTime tOneDT = new DateTime().withHourOfDay(Parser.getHour(tOne))
				.withMinuteOfHour(Parser.getMinute(tOne)).withSecondOfMinute(0);

		DateTime tTwoDT = new DateTime().withHourOfDay(Parser.getHour(tTwo))
				.withMinuteOfHour(Parser.getMinute(tTwo)).withSecondOfMinute(0);

		Minutes firstPart = Minutes.minutesBetween(tOneDT, tTwoDT);
		return firstPart.getMinutes();
	}

}
