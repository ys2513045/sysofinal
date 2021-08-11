package com.itwillbs.domain;

import java.sql.Timestamp;

public class ReportDTO {

	private int report_idx;
	private String member_id;
	private String report_content;
	private int comment_idx;
	private int shopReview_idx;
	private int item_idx;
	private String shop_member_id;
	private int report_state;
	private Timestamp report_date;
	
	
	public int getShopReview_idx() {
		return shopReview_idx;
	}
	public void setShopReview_idx(int shopReview_idx) {
		this.shopReview_idx = shopReview_idx;
	}
	public int getReport_idx() {
		return report_idx;
	}
	public void setReport_idx(int report_idx) {
		this.report_idx = report_idx;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getReport_content() {
		return report_content;
	}
	public void setReport_content(String report_content) {
		this.report_content = report_content;
	}
	public int getComment_idx() {
		return comment_idx;
	}
	public void setComment_idx(int comment_idx) {
		this.comment_idx = comment_idx;
	}
	public int getItem_idx() {
		return item_idx;
	}
	public void setItem_idx(int item_idx) {
		this.item_idx = item_idx;
	}
	public String getShop_member_id() {
		return shop_member_id;
	}
	public void setShop_member_id(String shop_member_id) {
		this.shop_member_id = shop_member_id;
	}
	public int getReport_state() {
		return report_state;
	}
	public void setReport_state(int report_state) {
		this.report_state = report_state;
	}
	public Timestamp getReport_date() {
		return report_date;
	}
	public void setReport_date(Timestamp report_date) {
		this.report_date = report_date;
	}
	
	
	
	
	
	
	
	
}
