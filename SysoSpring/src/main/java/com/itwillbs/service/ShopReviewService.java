package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.ShopReviewVO;

public interface ShopReviewService {

	List<ShopReviewVO> getShopReviewList(String shop_member_id);

	List<ShopReviewVO> getPreReview(String shop_member_id);

	ShopReviewVO getShopRiview(ShopReviewVO shopReview);

	void insertShopReview(ShopReviewVO shopReview);

}
