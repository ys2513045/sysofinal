package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.ItemVO;
import com.itwillbs.domain.MemberVO;
import com.itwillbs.domain.ReportDTO;
import com.itwillbs.domain.likesVO;

public interface ItemService {
	public List<ItemVO> getItem() throws Exception;

	public List<ItemVO> infiniteScrollDown(ItemVO itemVO);

	public List<ItemVO> getItemDetail(int item_idx);

	public String getMemberID(int item_idx);


	public List<ItemVO> getSameCategory(String item_category);

	public MemberVO getAuth(MemberVO vo);
	
	public void insertItem(ItemVO itemVO, String[] hashList);

	public void increaseReadCount(int item_idx);

	public List<String> getTagNames(int item_idx);

	public likesVO getLikesItem(likesVO vo);

	public int getLikesCount(likesVO vo);

	public void changeToRed(likesVO lVO);

	public void changeToGray(likesVO lvo);
	
	public List<ItemVO> getKeyword(String keyword);
	
	public List<ItemVO> getItemCategory(String item_category);

	public void insertHash(ItemVO itemVO);

	public List<ItemVO> getMyItemList(String member_id);

	public List<ItemVO> getRelatedItem(ItemVO ivo);

	public List<ItemVO> getShopItemPhoto(ItemVO ivo);

	public List<ItemVO> getSellList(ItemVO vo2);

	public void insertItemReport(ReportDTO dto);

	public List<ItemVO> getBuyList(ItemVO vo2);

	public List<ItemVO> myItemList(String member_id);

	public ItemVO getUpdateItem(int item_idx);

	public void updatePro(ItemVO itemVO);

	public void deletePro(int item_idx);

	public void timeUpdate(ItemVO vo);

	public void updateItemState(ItemVO itemVO);

	public String getMemberNickname(String member_id);

	public void changeItemState(ItemVO vo);

	public void insertShopReport(ReportDTO dto);

	public void insertShopRivewReport(ReportDTO dto);

	String getItemSubject(int item_idx);

	public void insertCommentReport(ReportDTO rvo);

	public List<String> getSellKing();





	
	
}
