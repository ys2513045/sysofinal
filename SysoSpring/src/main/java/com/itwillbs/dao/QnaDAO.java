package com.itwillbs.dao;

import java.util.List;

import com.itwillbs.domain.PageBean;
import com.itwillbs.domain.QnaVO;

public interface QnaDAO {

	public List<QnaVO> getQnaList(PageBean pb);

	public int getQnaCount();

	public void insertQna(QnaVO qnaVO);

	public void insertQnaReply(QnaVO qnaVO);
	
	public void deleteQnaReply(QnaVO qnaVO);
	
	public void deleteQna(QnaVO qnaVO);

	public void updateQna(QnaVO qnaVO);
	
	
//	public QnaVO getQna(int num);
//
//	public Integer getMaxNum();
//
//	public void updateReseq(QnaVO qnaVO);



}
