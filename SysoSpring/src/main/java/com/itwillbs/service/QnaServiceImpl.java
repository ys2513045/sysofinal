package com.itwillbs.service;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.itwillbs.dao.QnaDAO;
import com.itwillbs.domain.PageBean;
import com.itwillbs.domain.QnaVO;

@Service
public class QnaServiceImpl implements QnaService {
	
	@Inject
	private QnaDAO qnaDAO;
	
	@Override
	public ModelAndView getQnaList(HttpServletRequest request) {
		System.out.println("서비스 1");
		
		ModelAndView mv = new ModelAndView();
		PageBean pb=new PageBean();
		String pageNum=request.getParameter("pageNum");
		if(pageNum==null) {
			pageNum="1";
		}
		pb.setPageNum(pageNum);
		
		int pageSize=10;
		pb.setPageSize(pageSize);
		
		pb.setCurrentPage(Integer.parseInt(pb.getPageNum()));
		pb.setStartRow((pb.getCurrentPage()-1)*pb.getPageSize()+1-1);

		List<QnaVO> qnaList=qnaDAO.getQnaList(pb);
		pb.setCount(getQnaCount());
		
		mv.addObject("qnaList", qnaList);
		mv.addObject("pb",pb);

		return mv;
	}
	
	@Override
	public int getQnaCount() {
		System.out.println("서비스 1-1");
		return qnaDAO.getQnaCount();
	}

	@Override
	public void insertQna(QnaVO qnaVO) {
		System.out.println("서비스 2");
//		if(qnaDAO.getMaxNum()==null) {
//			qnaVO.setQna_idx(1);
//			qnaVO.setQna_re_ref(1);
//		}else {
//			qnaVO.setQna_idx(qnaDAO.getMaxNum()+1);
//			qnaVO.setQna_re_ref(qnaDAO.getMaxNum()+1);
//		}
		
//		adminVO.setReadcount(0);
		qnaVO.setQna_date(new Timestamp(System.currentTimeMillis()));
		
//		qnaVO.setQna_re_lev(0);
//		qnaVO.setQna_re_seq(0);
		qnaDAO.insertQna(qnaVO);
	}
	
	
	@Override
	public void insertQnaReply(QnaVO qnaVO) {
		System.out.println("서비스 3");
		qnaDAO.insertQnaReply(qnaVO);
	}
	
	@Override
	public void deleteQnaReply(QnaVO qnaVO) {
		System.out.println("서비스 4");
		qnaDAO.deleteQnaReply(qnaVO);
	}
	
	@Override
	public void deleteQna(QnaVO qnaVO) {
		System.out.println("서비스 5");
		qnaDAO.deleteQna(qnaVO);
	}
	
	@Override
	public void updateQna(QnaVO qnaVO) {
		System.out.println("서비스 6");
		qnaDAO.updateQna(qnaVO);
	}
	
	
	
	
	

//	@Override
//	public QnaVO getQna(int num) {
//		System.out.println("서비스 13");
//		QnaVO qnaVO = qnaDAO.getQna(num);
//		
//		if(qnaVO != null) {
//			qnaVO.setQna_content(qnaVO.getQna_content().replace("\r\n", "<br>"));
//		}
//		System.out.println("줄바꿈 확인중 : " + qnaVO.getQna_content());
//		return qnaVO;
//	}

	
}
