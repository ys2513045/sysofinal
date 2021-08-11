package com.itwillbs.domain;

import java.sql.Timestamp;

public class OrderItemVO {
	private int orderItem_idx;
	private int item_idx;
	private String member_id;
	private boolean orderItem_isOffline;
	private int orderItem_quantity;
	private int orderItem_state;
	private Timestamp orderItem_date;
	private String orderItem_merchantID;
	
	
	


	public String getOrderItem_merchantID() {
		return orderItem_merchantID;
	}
	public void setOrderItem_merchantID(String orderItem_merchantID) {
		this.orderItem_merchantID = orderItem_merchantID;
	}
	public int getOrderItem_idx() {
		return orderItem_idx;
	}
	public void setOrderItem_idx(int orderItem_idx) {
		this.orderItem_idx = orderItem_idx;
	}
	public int getItem_idx() {
		return item_idx;
	}
	public void setItem_idx(int item_idx) {
		this.item_idx = item_idx;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public boolean isOrderItem_isOffline() {
		return orderItem_isOffline;
	}
	public void setOrderItem_isOffline(boolean orderItem_isOffline) {
		this.orderItem_isOffline = orderItem_isOffline;
	}
	public int getOrderItem_quantity() {
		return orderItem_quantity;
	}
	public void setOrderItem_quantity(int orderItem_quantity) {
		this.orderItem_quantity = orderItem_quantity;
	}
	public int getOrderItem_state() {
		return orderItem_state;
	}
	public void setOrderItem_state(int orderItem_state) {
		this.orderItem_state = orderItem_state;
	}
	public Timestamp getOrderItem_date() {
		return orderItem_date;
	}
	public void setOrderItem_date(Timestamp orderItem_date) {
		this.orderItem_date = orderItem_date;
	}
	
	
	
	
	
}
