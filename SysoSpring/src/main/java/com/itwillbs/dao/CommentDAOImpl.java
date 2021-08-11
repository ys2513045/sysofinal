package com.itwillbs.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.CommentVO;

@Repository
public class CommentDAOImpl implements CommentDAO {
   
   private static final String namespace ="com.itwillbs.CommentMapper";
   
   @Inject
   private SqlSession sqlSession;

   @Override
   public List<CommentVO> getComment(CommentVO cvo) {
      // TODO Auto-generated method stub
      return sqlSession.selectList(namespace + ".getComment", cvo);
   }

   @Override
   public String getCommentName(String member_id) {
      // TODO Auto-generated method stub
      return sqlSession.selectOne(namespace + ".getCommentName", member_id);
   }

   @Override
   public int insertComment(CommentVO cvo) {
      // TODO Auto-generated method stub
      return sqlSession.insert(namespace + ".insertComment", cvo);
   }

   @Override
   public List<CommentVO> getInsertComment(CommentVO cvo) {
      // TODO Auto-generated method stub
      return sqlSession.selectList(namespace + ".getInsertComment", cvo);
   }
   
   @Override
   public int deleteComment(CommentVO cvo) {
      // TODO Auto-generated method stub
      return sqlSession.delete(namespace + ".deleteComment", cvo);
   }

}