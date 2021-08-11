package com.itwillbs.domain;

import java.sql.Timestamp;

public class ShopReviewVO {

	private int shopReview_idx;
	private String shop_member_id;
	private int item_idx;
	private String item_subject;
	private String shopReview_id;
	private String shopReview_nickname;
	private int shopReview_star;
	private String shopReview_content;
	private Timestamp shopReview_date;
	private String shopReview_date_format;
	
	public int getShopReview_idx() {
		return shopReview_idx;
	}
	public void setShopReview_idx(int shopReview_idx) {
		this.shopReview_idx = shopReview_idx;
	}
	public String getShop_member_id() {
		return shop_member_id;
	}
	public void setShop_member_id(String shop_member_id) {
		this.shop_member_id = shop_member_id;
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
	public String getShopReview_id() {
		return shopReview_id;
	}
	public void setShopReview_id(String shopReview_id) {
		this.shopReview_id = shopReview_id;
	}
	public String getShopReview_nickname() {
		return shopReview_nickname;
	}
	public void setShopReview_nickname(String shopReview_nickname) {
		this.shopReview_nickname = shopReview_nickname;
	}
	public int getShopReview_star() {
		return shopReview_star;
	}
	public void setShopReview_star(int shopReview_star) {
		this.shopReview_star = shopReview_star;
	}
	public String getShopReview_content() {
		return shopReview_content;
	}
	public void setShopReview_content(String shopReview_content) {
		this.shopReview_content = shopReview_content;
	}
	public Timestamp getShopReview_date() {
		return shopReview_date;
	}
	public void setShopReview_date(Timestamp shopReview_date) {
		this.shopReview_date = shopReview_date;
	}
	public String getShopReview_date_format() {
		return shopReview_date_format;
	}
	public void setShopReview_date_format(String shopReview_date_format) {
		this.shopReview_date_format = shopReview_date_format;
	}
	
}
