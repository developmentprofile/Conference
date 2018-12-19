package com.n11.conference.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Talk")
public class Talk {
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private long id;
	
	@Column(name = "talk_name", nullable = false, unique = true)
	private String talkName;
	
	@Column(name = "talk_time", nullable = false)
	private Integer talkTime;
	
	@Column(name = "talk_type", nullable = false)
	private String talkType;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTalkName() {
		return talkName;
	}

	public void setTalkName(String talkName) {
		this.talkName = talkName;
	}

	public Integer getTalkTime() {
		return talkTime;
	}

	public void setTalkTime(Integer talkTime) {
		this.talkTime = talkTime;
	}

	public String getTalkType() {
		return talkType;
	}

	public void setTalkType(String talkType) {
		this.talkType = talkType;
	}

	@Override
	public String toString() {
		return "Talk [id=" + id + ", talkName=" + talkName + ", talkTime=" + talkTime + ", talkType=" + talkType + "]";
	}

	public Talk(String talkName, Integer talkTime, String talkType) {
		super();
		this.talkName = talkName;
		this.talkTime = talkTime;
		this.talkType = talkType;
	}

	public Talk() {
		super();
	}
	
}
