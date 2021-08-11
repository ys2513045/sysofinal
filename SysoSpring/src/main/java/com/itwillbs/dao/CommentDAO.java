package com.itwillbs.dao;

import java.util.List;

import com.itwillbs.domain.CommentVO;

public interface CommentDAO {

   public List<CommentVO> getComment(CommentVO cvo);

   public String getCommentName(String member_id);

   public int insertComment(CommentVO cvo);

   public List<CommentVO> getInsertComment(CommentVO cvo);

public int deleteComment(CommentVO cvo);

}