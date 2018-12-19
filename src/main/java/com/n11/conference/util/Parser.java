package com.n11.conference.util;

public class Parser {

	private static final String TIME_DELIM = ":";
	public static int getHour(String time) {
		return parse(time, 0);
	}
	
	public static int getMinute(String time) {
		return parse(time, 1);
	}
	
	private static int parse(String timeString, int index) {
		String[] splits = timeString.split(TIME_DELIM);
		return Integer.valueOf(splits[index]);
	}
}
