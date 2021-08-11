package com.itwillbs.domain;

import java.sql.Timestamp;

public class QnaVO {
	
	// qna & qna_reply
	private int qna_idx;
	private String member_id;
	private String qna_subject;
	private String qna_content;
	private String qna_file;
	private Timestamp qna_date;
	private int qnaReply_idx;
	private String qnaReply_content;
	private Timestamp qnaReply_date;
	public String getQna_reply() {
		return qna_reply;
	}
	public void setQna_reply(String qna_reply) {
		this.qna_reply = qna_reply;
	}
	private String qna_reply;
	
	private int qna_re_ref;
	private int qna_re_lev;
	private int qna_re_seq;
	
	
	//qna & qna_reply
	
	public int getQna_idx() {
		return qna_idx;
	}
	public void setQna_idx(int qna_idx) {
		this.qna_idx = qna_idx;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getQna_subject() {
		return qna_subject;
	}
	public void setQna_subject(String qna_subject) {
		this.qna_subject = qna_subject;
	}
	public String getQna_content() {
		return qna_content;
	}
	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}
	public String getQna_file() {
		return qna_file;
	}
	public void setQna_file(String qna_file) {
		this.qna_file = qna_file;
	}
	public Timestamp getQna_date() {
		return qna_date;
	}
	public void setQna_date(Timestamp qna_date) {
		this.qna_date = qna_date;
	}
	public int getQnaReply_idx() {
		return qnaReply_idx;
	}
	public void setQnaReply_idx(int qnaReply_idx) {
		this.qnaReply_idx = qnaReply_idx;
	}
	public String getQnaReply_content() {
		return qnaReply_content;
	}
	public void setQnaReply_content(String qnaReply_content) {
		this.qnaReply_content = qnaReply_content;
	}
	public Timestamp getQnaReply_date() {
		return qnaReply_date;
	}
	public void setQnaReply_date(Timestamp qnaReply_date) {
		this.qnaReply_date = qnaReply_date;
	}
	public int getQna_re_ref() {
		return qna_re_ref;
	}
	public void setQna_re_ref(int qna_re_ref) {
		this.qna_re_ref = qna_re_ref;
	}
	public int getQna_re_lev() {
		return qna_re_lev;
	}
	public void setQna_re_lev(int qna_re_lev) {
		this.qna_re_lev = qna_re_lev;
	}
	public int getQna_re_seq() {
		return qna_re_seq;
	}
	public void setQna_re_seq(int qna_re_seq) {
		this.qna_re_seq = qna_re_seq;
	}
	
	
}
