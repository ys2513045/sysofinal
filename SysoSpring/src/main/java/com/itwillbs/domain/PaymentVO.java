package com.itwillbs.domain;

import java.sql.Timestamp;

public class PaymentVO {
	private int payment_idx;
	private int orderitem_idx;
	private int payment_price;
	private String payment_method;
	private Timestamp payment_date;
	
	
	public int getPayment_idx() {
		return payment_idx;
	}
	public void setPayment_idx(int payment_idx) {
		this.payment_idx = payment_idx;
	}
	public int getOrderitem_idx() {
		return orderitem_idx;
	}
	public void setOrderitem_idx(int orderitem_idx) {
		this.orderitem_idx = orderitem_idx;
	}
	public int getPayment_price() {
		return payment_price;
	}
	public void setPayment_price(int payment_price) {
		this.payment_price = payment_price;
	}
	public String getPayment_method() {
		return payment_method;
	}
	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}
	public Timestamp getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(Timestamp payment_date) {
		this.payment_date = payment_date;
	}
	
	
	
	
	
	
	
	
}
