package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.dao.ItemDAO;
import com.itwillbs.dao.MemberDAO;
import com.itwillbs.dao.ShopDAO;
import com.itwillbs.dao.ShopReviewDAO;
import com.itwillbs.domain.ItemVO;
import com.itwillbs.domain.ShopVO;
import com.itwillbs.util.TimeGap;

@Service
public class ShopServiceImpl implements ShopService {
	
	@Inject
	private ShopDAO dao;
	
	@Inject
	   private ShopReviewDAO reviewdao;
	
	
	@Inject
	   private MemberDAO mdao;
	
	@Inject
	private ItemDAO itemDAO; 

	@Override
	public ShopVO getShop(String member_id) {
		ShopVO shop = dao.getShop(member_id);
		TimeGap.setShopDateFormat(shop);
		shop.setShop_saleCount(itemDAO.getSaleCount(member_id));
		
		return shop;
	}

//	@Override
//	public ShopVO getShop(String member_id) {
//		ShopVO shop = dao.getShop(member_id);
//		TimeGap.setShopDateFormat(shop);
//		return shop;
//	}
	
	@Override
	public String getShopIntro(String member_id) {
		return dao.getShopIntro(member_id);
	}

	@Override
	public void updateShopIntro(ShopVO shop) {
		dao.updateShopIntro(shop);
	}
	
	@Override
	   public List<ShopVO> getShopInfo(ItemVO Ivo) {
	      // TODO Auto-generated method stub
	      System.out.println("svc - getShopInfo");

	      List<ShopVO> sList = dao.getShopInfo(Ivo.getMember_id());

	      String shopId = Ivo.getMember_id();
	      sList.get(0).setMember_nickName(mdao.transMember_id(shopId));
	      sList.get(0).setShop_reviewCount(reviewdao.getreviewCount(shopId));

	      return sList;
	   }

}
