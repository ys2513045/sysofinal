package com.itwillbs.domain;

import java.sql.Date;
import java.sql.Timestamp;

public class MessageDTO {

	private int no;
	private String send_nick;
	private String recv_nick;
	private Timestamp send_time;
	private String message_date_format;

	

	
	public String getMessage_date_format() {
		return message_date_format;
	}
	public void setMessage_date_format(String message_date_format) {
		this.message_date_format = message_date_format;
	}
	public Timestamp getSend_time() {
		return send_time;
	}
	public void setSend_time(Timestamp send_time) {
		this.send_time = send_time;
	}
	private String content;
	private int room;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getSend_nick() {
		return send_nick;
	}
	public void setSend_nick(String send_nick) {
		this.send_nick = send_nick;
	}
	public String getRecv_nick() {
		return recv_nick;
	}
	public void setRecv_nick(String recv_nick) {
		this.recv_nick = recv_nick;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getRoom() {
		return room;
	}
	public void setRoom(int room) {
		this.room = room;
	}
}
