package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.ItemVO;
import com.itwillbs.domain.ShopVO;

public interface ShopService {

	ShopVO getShop(String member_id);
	
	String getShopIntro(String member_id);

	void updateShopIntro(ShopVO shop);

	List<ShopVO> getShopInfo(ItemVO ivo);

}
