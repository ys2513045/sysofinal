package com.itwillbs.service;



import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.itwillbs.domain.QnaVO;

public interface QnaService {

	public ModelAndView getQnaList(HttpServletRequest request);

	public int getQnaCount();

	public void insertQna(QnaVO qnaVO);

	public void insertQnaReply(QnaVO qnaVO);
	
	public void deleteQnaReply(QnaVO qnaVO);

	public void deleteQna(QnaVO qnaVO);

	public void updateQna(QnaVO qnaVO);

	
	
	

//	public QnaVO getQna(int num);



}
