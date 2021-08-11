package com.itwillbs.dao;

import java.util.List;

import com.itwillbs.domain.ItemVO;
import com.itwillbs.domain.MemberVO;
import com.itwillbs.domain.ReportDTO;
import com.itwillbs.domain.likesVO;

public interface ItemDAO {
	
	public List<ItemVO> getItem() throws Exception;

	public ItemVO getItem(int integer);
	
	
	public List<ItemVO> infiniteScrollDown(ItemVO itemVO);

	public List<ItemVO> getItemDetail(int item_idx);

	public String getMemberID(int item_idx);

	public List<ItemVO> getSameCategory(String item_category);

	public MemberVO getAuth(MemberVO vo);
	
	public void insertItem(ItemVO itemVO);

	public int increaseReadCount(int item_idx);

	public List<String> getTagNames(int item_idx);

	public likesVO getLikesItem(likesVO vo);

	public int getLikesCount(likesVO lvo);

	public int changeToRed(likesVO lVO);

	public int changeToGray(likesVO lVO);

	public int decreaseLikesCount(ItemVO ivo);

	public int increaseLikesCount(ItemVO ivo);

	public String getCategoryName(int category_idx);
	
	public List<ItemVO> getKeyword(String keyword);
	public List<ItemVO> getItemCategory(String item_category);


	Integer getMaxIdx(ItemVO itemVO);

	public void insertHash2(ItemVO itemVO);

	public List<ItemVO> getMyItemList(String member_id);
	
	public String getItemSubject(int item_idx);

	public List<ItemVO> getRelatedItem(ItemVO ivo);

	public List<ItemVO> getShopItemPhoto(ItemVO ivo);

	public List<ItemVO> getSellList(ItemVO vo2);

	public void insertItemReport(ReportDTO dto);

	public List<ItemVO> getBuyList(ItemVO vo2);

	public List<ItemVO> myItemList(String member_id);

	public void updatePro(ItemVO vo);

	public int getCommentCount(int item_idx);

	public void timeUpdate(ItemVO vo);

	public void deletePro(int item_idx);

	public void updateItemState(ItemVO itemVO);

	public String getMemberNickname(String member_id);

	public void changeItemState(ItemVO vo);

	public void insertShopReport(ReportDTO dto);

	public void insertShopReviewReport(ReportDTO dto);

	public void insertCommentReport(ReportDTO rvo);

	public List<String> getSellKing();
	
	public int getSaleCount(String member_id);
	





	
}
