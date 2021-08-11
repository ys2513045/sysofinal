package com.itwillbs.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.dao.CommentDAO;
import com.itwillbs.dao.MemberDAO;
import com.itwillbs.domain.CommentVO;
import com.itwillbs.util.TimeGap;

@Service
public class CommentServiceImpl implements CommentService {

   @Inject
   private CommentDAO dao;
   
   @Inject
   private MemberDAO mdao;

   @Override
   public List<CommentVO> getComment(CommentVO cvo) {
      // TODO Auto-generated method stub

      System.out.println("svc - getComment");

      List<CommentVO> cList = dao.getComment(cvo);

      // item_idx 에 맞는 댓글의 개수만큼 각 댓글의 member_id에 맞는
      // member_nickname 을 구해와서 cList의 member_id 에 저장
      String cNames = null;

      // 현재시간 - 저장되어있는시간 => String XXX_date_format 에 저장

      for (int i = 0; i < cList.size(); i++) {

         cNames = cList.get(i).getMember_id();
         cList.get(i).setMember_nickname(mdao.transMember_id(cNames));

         Timestamp comment_date = cList.get(i).getComment_date();
         cList.get(i).setComment_date_format(TimeGap.getTimeGap(comment_date));

      }

      return cList;
      
   }


   @Override
   public List<CommentVO> insertComment(CommentVO cvo) {
      // TODO Auto-generated method stub
      System.out.println("svc - insertComment");
      // 해야할작업 1. 댓글 입력(날짜 지정)
      // 해야할 작업 2. 넣은 댓글 가져오기(가장 최신 댓글 1개 뽑기)
      int insertCount = 0;
      insertCount = dao.insertComment(cvo);

      if (insertCount > 0) {
         System.out.println("입력된 댓글 개수 : " + insertCount);
      } else {
         System.out.println("댓글 INSERT 실패");
      }

      String member_id = mdao.transMember_id(cvo.getMember_id());

      List<CommentVO> cList = dao.getInsertComment(cvo);
      System.out.println(cList.get(0).getComment_date());
      System.out.println(cList.size());
      Timestamp timestamp = cList.get(0).getComment_date();
      
      if (cList != null) {

         cList.get(0).setMember_nickname(member_id);
         cList.get(0).setComment_date_format(TimeGap.getTimeGap(timestamp));

      }

      return cList;
   }
   
   @Override
   public List<CommentVO> deleteComment(CommentVO cvo) {
      // TODO Auto-generated method stub
      
      System.out.println("svc - deleteComment");
      
      // 삭제하기
      int deleteCount = 0;
      deleteCount = dao.deleteComment(cvo);
      
      List<CommentVO> cList = null;
      
      if(deleteCount == 1) {
         System.out.println("댓글삭제 1개");
      }else {
         System.out.println("삭제된 댓글수 - " + deleteCount);
      }
      
      cList = dao.getComment(cvo);
      
      String cNames = null;
      
      
      for (int i = 0; i < cList.size(); i++) {

         cNames = cList.get(i).getMember_id();
         cList.get(i).setMember_nickname(mdao.transMember_id(cNames));

         Timestamp comment_date = cList.get(i).getComment_date();
         cList.get(i).setComment_date_format(TimeGap.getTimeGap(comment_date));

      }
      
      return cList;
   }
   

}