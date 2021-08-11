package com.itwillbs.domain;

import java.sql.Timestamp;

public class CommentVO {
   
   private int comment_idx;
   private int item_idx;
   private String member_id;
   private String comment_content;
   private Timestamp comment_date;
   private String member_nickname;
   
   public String getMember_nickname() {
	return member_nickname;
}
public void setMember_nickname(String member_nickname) {
	this.member_nickname = member_nickname;
}
private String comment_date_format;
   
   public String getComment_date_format() {
      return comment_date_format;
   }
   public void setComment_date_format(String comment_date_format) {
      this.comment_date_format = comment_date_format;
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
   public String getMember_id() {
      return member_id;
   }
   public void setMember_id(String member_id) {
      this.member_id = member_id;
   }
   public String getComment_content() {
      return comment_content;
   }
   public void setComment_content(String comment_content) {
      this.comment_content = comment_content;
   }
   public Timestamp getComment_date() {
      return comment_date;
   }
   public void setComment_date(Timestamp comment_date) {
      this.comment_date = comment_date;
   }
   
}