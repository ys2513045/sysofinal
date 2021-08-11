package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.CommentVO;

public interface CommentService {

   public List<CommentVO> getComment(CommentVO cvo);


   public List<CommentVO> insertComment(CommentVO cvo);


public List<CommentVO> deleteComment(CommentVO cvo);
   
   

}