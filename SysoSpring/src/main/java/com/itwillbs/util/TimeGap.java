package com.itwillbs.util;

import java.sql.Timestamp;
import java.util.List;

import com.itwillbs.domain.ItemVO;
import com.itwillbs.domain.MessageDTO;
import com.itwillbs.domain.ShopReviewVO;
import com.itwillbs.domain.ShopVO;

public class TimeGap {
   
   private TimeGap() {
      // 생성자 잠금
   }
   
   private static Timestamp timestamp = new Timestamp(System.currentTimeMillis());


   public static String getTimeGap(Timestamp time) {

         String timeGapStr = "";

         long timegap = (timestamp.getTime() - time.getTime()) / 1000;
         
         long year = 60 * 60 * 24 * 30 * 12;

         if((timegap/60) < 1) {
            timeGapStr = "방금전";
         } else if((timegap/(60 * 60)) < 1) {
            timeGapStr = (timegap/60 )+ "분전";
         } else if((timegap / (60 * 60 * 24)) < 1) {
            timeGapStr = (timegap/(60 * 60)) + "시간전";
         } else if(timegap / (60 * 60 * 24 * 30) < 1) {
            timeGapStr = (timegap / (60 * 60 * 24)) + "일전";
         } else if((timegap / year) < 1) {
            timeGapStr = (timegap / (60 * 60 * 24 * 30)) + "달전";
         } else{
            timeGapStr = (timegap / year) + "년전";
         }

         return timeGapStr;
      }
   
   public static void setDateFormat(List<ItemVO> list) {
      for(ItemVO item : list) {
         Timestamp date = item.getItem_date();
         item.setItem_date_format(getTimeGap(date));
      }
   }
   public static void messageDateFormat(List<MessageDTO> list) {
	      for(MessageDTO message : list) {
	         Timestamp date = message.getSend_time();
	       message.setMessage_date_format(getTimeGap(date));
	      }
	   }
   
   public static void setItemDateFormat(List<ItemVO> list) {
	      for(ItemVO item : list) {
	         Timestamp date = item.getItem_date();
	         item.setItem_date_format(getTimeGap(date));
	      }
	   }
	   
	   public static void setShopReviewDateFormat(List<ShopReviewVO> list) {
	      for(ShopReviewVO shopReivew : list) {
	         Timestamp date = shopReivew.getShopReview_date();
	         
	         shopReivew.setShopReview_date_format(getTimeGap(date));
	      }
	   }
	   
	   public static void setShopDateFormat(ShopVO shop) {
	      String timeGapStr = "";
	      Timestamp time = shop.getShop_date();

	      long timegap = (timestamp.getTime() - time.getTime()) / 1000;
	      timeGapStr = (timegap / (60 * 60 * 24)) + "일전";
	      
	      shop.setShop_date_format(timeGapStr);
	   }
   

   
   

}
