package com.itwillbs.domain;

import java.sql.Timestamp;

public class ShopVO {

	private String member_id;
	private String shop_intro;
	private String shop_profilePhoto;
	private int shop_itemCount;
	private int shop_saleCount;
	private int shop_likesCount;
	private int shop_shopReviewCount;
	private Timestamp shop_date;
	private String shop_date_format;
	private String member_nickName;
	private int shop_reviewCount;
	
	public int getShop_reviewCount() {
		return shop_reviewCount;
	}
	public void setShop_reviewCount(int shop_reviewCount) {
		this.shop_reviewCount = shop_reviewCount;
	}
	public String getMember_nickName() {
		return member_nickName;
	}
	public void setMember_nickName(String member_nickName) {
		this.member_nickName = member_nickName;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getShop_intro() {
		return shop_intro;
	}
	public void setShop_intro(String shop_intro) {
		this.shop_intro = shop_intro;
	}
	public String getShop_profilePhoto() {
		return shop_profilePhoto;
	}
	public void setShop_profilePhoto(String shop_profilePhoto) {
		this.shop_profilePhoto = shop_profilePhoto;
	}
	public int getShop_itemCount() {
		return shop_itemCount;
	}
	public void setShop_itemCount(int shop_itemCount) {
		this.shop_itemCount = shop_itemCount;
	}
	public int getShop_saleCount() {
		return shop_saleCount;
	}
	public void setShop_saleCount(int shop_saleCount) {
		this.shop_saleCount = shop_saleCount;
	}
	public int getShop_likesCount() {
		return shop_likesCount;
	}
	public void setShop_likesCount(int shop_likesCount) {
		this.shop_likesCount = shop_likesCount;
	}
	public int getShop_shopReviewCount() {
		return shop_shopReviewCount;
	}
	public void setShop_shopReviewCount(int shop_shopReviewCount) {
		this.shop_shopReviewCount = shop_shopReviewCount;
	}
	public Timestamp getShop_date() {
		return shop_date;
	}
	public void setShop_date(Timestamp shop_date) {
		this.shop_date = shop_date;
	}
	public String getShop_date_format() {
		return shop_date_format;
	}
	public void setShop_date_format(String shop_date_format) {
		this.shop_date_format = shop_date_format;
	}

}
