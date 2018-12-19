package com.n11.conference.util;

public enum TalkType {
	LIGHTNING("LIGHTNING"), OTHER("OTHER"), NETWORKING("NETWORKING"), MEAL("MEAL");
	
	private String type;
	
	public static String getType(String value){
        for (TalkType type : TalkType.values()) {
            if (value.equalsIgnoreCase(type.getType())) {
                return type.getType();
            }
        }
        return OTHER.getType();
    }
	
	TalkType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
