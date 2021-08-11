package com.itwillbs.dao;

import java.util.List;

import com.itwillbs.domain.ShopVO;

public interface ShopDAO {

	String getShopIntro(String member_id);

	ShopVO getShop(String member_id);

	void updateShopIntro(ShopVO shop);

	List<ShopVO> getShopInfo(String member_id);
	
	void updateShopItemCount(String member_id);


}
