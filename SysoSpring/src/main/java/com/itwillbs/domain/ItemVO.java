package com.itwillbs.domain;

import java.sql.Date;
import java.sql.Timestamp;

public class ItemVO {
	private String member_id;
	private int item_idx;
	private String item_subject;
	private String item_detail;
	private String item_img;
	private int item_price;
	private int item_count;
	private boolean item_isUsed;
	private boolean item_isExchange;
	private String item_region;
	private boolean item_isDirect;
	private boolean item_isDeliveryFree;
	private int item_charge;
	private int item_readcount;
	private Date item_startDate;
	private java.util.Date item_endDate;
	private int item_state;
	private int startNum;
	private int endNum;
	private Timestamp item_date;
	private int item_likesCount;
	private int item_category;
	private String item_hashtag;
	private String item_date_format;
	private String category_name;
	private String hashtag_name;
	private int simple_endDate;
	
	private Timestamp orderItem_date;
	private String member_nickname;
	private int comment_count;
	
	
	
	public int getComment_count() {
		return comment_count;
	}
	public void setComment_count(int comment_count) {
		this.comment_count = comment_count;
	}
	public Timestamp getOrderItem_date() {
		return orderItem_date;
	}
	public void setOrderItem_date(Timestamp orderItem_date) {
		this.orderItem_date = orderItem_date;
	}
	public String getMember_nickname() {
		return member_nickname;
	}
	public void setMember_nickname(String member_nickname) {
		this.member_nickname = member_nickname;
	}
	public int getSimple_endDate() {
		return simple_endDate;
	}
	public void setSimple_endDate(int simple_endDate) {
		this.simple_endDate = simple_endDate;
	}
	public String getHashtag_name() {
		return hashtag_name;
	}
	public void setHashtag_name(String hashtag_name) {
		this.hashtag_name = hashtag_name;
	}
	public int getCategory_idx() {
		return category_idx;
	}
	public void setCategory_idx(int category_idx) {
		this.category_idx = category_idx;
	}
	private int category_idx;
	
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getItem_date_format() {
		return item_date_format;
	}
	public void setItem_date_format(String item_date_format) {
		this.item_date_format = item_date_format;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getItem_idx() {
		return item_idx;
	}
	public void setItem_idx(int item_idx) {
		this.item_idx = item_idx;
	}
	public String getItem_subject() {
		return item_subject;
	}
	public void setItem_subject(String item_subject) {
		this.item_subject = item_subject;
	}
	public String getItem_detail() {
		return item_detail;
	}
	public void setItem_detail(String item_detail) {
		this.item_detail = item_detail;
	}
	public String getItem_img() {
		return item_img;
	}
	public void setItem_img(String item_img) {
		this.item_img = item_img;
	}
	public int getItem_price() {
		return item_price;
	}
	public void setItem_price(int item_price) {
		this.item_price = item_price;
	}
	public int getItem_count() {
		return item_count;
	}
	public void setItem_count(int item_count) {
		this.item_count = item_count;
	}
	public boolean isItem_isUsed() {
		return item_isUsed;
	}
	public void setItem_isUsed(boolean item_isUsed) {
		this.item_isUsed = item_isUsed;
	}
	public boolean isItem_isExchange() {
		return item_isExchange;
	}
	public void setItem_isExchange(boolean item_isExchange) {
		this.item_isExchange = item_isExchange;
	}
	public String getItem_region() {
		return item_region;
	}
	public void setItem_region(String item_region) {
		this.item_region = item_region;
	}
	public boolean isItem_isDirect() {
		return item_isDirect;
	}
	public void setItem_isDirect(boolean item_isDirect) {
		this.item_isDirect = item_isDirect;
	}
	public boolean isItem_isDeliveryFree() {
		return item_isDeliveryFree;
	}
	public void setItem_isDeliveryFree(boolean item_isDeliveryFree) {
		this.item_isDeliveryFree = item_isDeliveryFree;
	}
	public int getItem_charge() {
		return item_charge;
	}
	public void setItem_charge(int item_charge) {
		this.item_charge = item_charge;
	}
	public int getItem_readcount() {
		return item_readcount;
	}
	public void setItem_readcount(int item_readcount) {
		this.item_readcount = item_readcount;
	}
	public Date getItem_startDate() {
		return item_startDate;
	}
	public void setItem_startDate(Date item_startDate) {
		this.item_startDate = item_startDate;
	}
	
	public java.util.Date getItem_endDate() {
		return item_endDate;
	}
	public void setItem_endDate(java.util.Date item_endDate) {
		this.item_endDate = item_endDate;
	}
	public int getItem_state() {
		return item_state;
	}
	public void setItem_state(int item_state) {
		this.item_state = item_state;
	}
	public int getStartNum() {
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	public int getEndNum() {
		return endNum;
	}
	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}
	public Timestamp getItem_date() {
		return item_date;
	}
	public void setItem_date(Timestamp item_date) {
		this.item_date = item_date;
	}
	public int getItem_likesCount() {
		return item_likesCount;
	}
	public void setItem_likesCount(int item_likesCount) {
		this.item_likesCount = item_likesCount;
	}
	public int getItem_category() {
		return item_category;
	}
	public void setItem_category(int item_category) {
		this.item_category = item_category;
	}
	public String getItem_hashtag() {
		return item_hashtag;
	}
	public void setItem_hashtag(String item_hashtag) {
		this.item_hashtag = item_hashtag;
	}
	


	
	
	
	
	
	
	
}
