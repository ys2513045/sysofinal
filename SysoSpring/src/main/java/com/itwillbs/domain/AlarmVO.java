package com.itwillbs.domain;

import java.sql.Timestamp;

public class AlarmVO {
	
	private int alarm_idx;
	private String member_id;
	private String alarm_content;
	private Timestamp alarm_date;
	private String member_nickname;
	private int orderItem_idx;
	
	public int getOrderItem_idx() {
		return orderItem_idx;
	}
	public void setOrderItem_idx(int orderitem_idx) {
		this.orderItem_idx = orderitem_idx;
	}
	public String getMember_nickname() {
		return member_nickname;
	}
	public void setMember_nickname(String member_nickname) {
		this.member_nickname = member_nickname;
	}
	public int getAlarm_idx() {
		return alarm_idx;
	}
	public void setAlarm_idx(int alarm_idx) {
		this.alarm_idx = alarm_idx;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getAlarm_content() {
		return alarm_content;
	}
	public void setAlarm_content(String alarm_content) {
		this.alarm_content = alarm_content;
	}
	public Timestamp getAlarm_date() {
		return alarm_date;
	}
	public void setAlarm_date(Timestamp alarm_date) {
		this.alarm_date = alarm_date;
	}
	
	
	
}
