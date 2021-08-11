package com.itwillbs.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.PageBean;
import com.itwillbs.domain.QnaVO;

@Repository
public class QnaDAOImpl implements QnaDAO {
	
	private static final String namespace ="com.itwillbs.qnaMapper";

	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<QnaVO> getQnaList(PageBean pb) {
		System.out.println("dao 1");
		return sqlSession.selectList(namespace + ".getQnaList", pb);
	}

	@Override
	public int getQnaCount() {
		System.out.println("dao 1-1");
		return sqlSession.selectOne(namespace + ".getQnaCount");	
	}

	@Override
	public void insertQna(QnaVO qnaVO) {
		System.out.println("dao 2");
		sqlSession.insert(namespace + ".insertQna", qnaVO);	
	}
	
	@Override
	public void insertQnaReply(QnaVO qnaVO) {
		System.out.println("dao 3");
		sqlSession.update(namespace+".insertQnaReply", qnaVO);
	}
	
	@Override
	public void deleteQnaReply(QnaVO qnaVO) {
		System.out.println("dao 4");
		sqlSession.update(namespace+".deleteQnaReply", qnaVO);
	}
	
	@Override
	public void deleteQna(QnaVO qnaVO) {
		System.out.println("dao 5");
		sqlSession.delete(namespace + ".deleteQna", qnaVO);
	}

	@Override
	public void updateQna(QnaVO qnaVO) {
		System.out.println("dao 6");
		sqlSession.update(namespace + ".updateQna", qnaVO);
	}
	
	
	
//	@Override
//	public QnaVO getQna(int num) {
//		System.out.println("dao 21");
//		return sqlSession.selectOne(namespace + ".getQna", num);
//	}
//
//	@Override
//	public Integer getMaxNum() {
//		System.out.println("dao 13");
//		return sqlSession.selectOne(namespace+".getMaxNum");
//	}
//
//	@Override
//	public void updateReseq(QnaVO qnaVO) {
//		System.out.println("dao 14");
//		sqlSession.update(namespace+".updateReseq", qnaVO);
//	}


	
}
