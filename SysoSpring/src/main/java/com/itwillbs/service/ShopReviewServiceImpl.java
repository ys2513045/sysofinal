package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.dao.ItemDAO;
import com.itwillbs.dao.MemberDAO;
import com.itwillbs.dao.ShopReviewDAO;
import com.itwillbs.domain.ShopReviewVO;
import com.itwillbs.util.TimeGap;

@Service
public class ShopReviewServiceImpl implements ShopReviewService {

	@Inject
	private ShopReviewDAO dao;
	@Inject
	private ItemDAO itemDAO;
	@Inject
	private MemberDAO memberDAO;

	@Override
	public List<ShopReviewVO> getShopReviewList(String shop_member_id) {
		List<ShopReviewVO> shopReviewList = dao.getShopReviewList(shop_member_id);
		TimeGap.setShopReviewDateFormat(shopReviewList);
		
		for(ShopReviewVO shopReview : shopReviewList) {
			shopReview.setShopReview_nickname(memberDAO.getNickname(shopReview.getShopReview_id()));
			shopReview.setItem_subject(itemDAO.getItemSubject(shopReview.getItem_idx()));
		}
		
		return shopReviewList;
	}
	
	@Override
	   public List<ShopReviewVO> getPreReview(String shop_member_id) {
	      // TODO Auto-generated method stub
	      List<ShopReviewVO> shopReviewList = dao.getPreReview(shop_member_id);
	      for(int i = 0; i < shopReviewList.size(); i++) {
	         String id = shopReviewList.get(i).getShopReview_id();
	         shopReviewList.get(i).setShopReview_nickname(memberDAO.transMember_id(id));
	      }
	      
	      TimeGap.setShopReviewDateFormat(shopReviewList);
	      
	      if(shopReviewList.size() > 0) {
	         System.out.println("상점리뷰 1개이상가져가요");
	         
	      }
	      System.out.println("상점리뷰 service 단");
	      return shopReviewList;
	   }

	@Override
    public void insertShopReview(ShopReviewVO shopReview) {
        shopReview.setShop_member_id(itemDAO.getMemberID(shopReview.getItem_idx()));

        dao.insertShopReview(shopReview);
    }

    @Override
    public ShopReviewVO getShopRiview(ShopReviewVO shopReivew) {
        return dao.getShopReview(shopReivew);
    }
	
	
}
