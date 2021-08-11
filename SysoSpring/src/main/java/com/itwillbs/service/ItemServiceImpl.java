package com.itwillbs.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.dao.ItemDAO;
import com.itwillbs.dao.ShopDAO;
import com.itwillbs.domain.ItemVO;
import com.itwillbs.domain.MemberVO;
import com.itwillbs.domain.ReportDTO;
import com.itwillbs.domain.likesVO;
import com.itwillbs.util.TimeGap;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Inject
	private ItemDAO dao;
	
	@Inject
	private ShopDAO shopDao;

	@Override
	public List<ItemVO> getItem() throws Exception {
		return dao.getItem();
	}

	@Override
	public List<ItemVO> infiniteScrollDown(ItemVO itemVO) {
		return dao.infiniteScrollDown(itemVO);
	}

	@Override
	public List<ItemVO> getItemDetail(int item_idx) {
		
		
		
		List<ItemVO> list = dao.getItemDetail(item_idx);
	      
	      if(list != null) {
	         
	      int category_idx = list.get(0).getCategory_idx();
	      String category_name = dao.getCategoryName(category_idx);
	      list.get(0).setCategory_name(category_name);
	      
	      // ---- XX분 전 ----
	      Timestamp item_date = list.get(0).getItem_date();
	      list.get(0).setItem_date_format(TimeGap.getTimeGap(item_date));
	      
	      }
		
			
			return list;
			
		
	}

	@Override
	public String getMemberID(int item_idx) {
		return dao.getMemberID(item_idx);
	}


	@Override
	public List<ItemVO> getSameCategory(String item_category) {
		return dao.getSameCategory(item_category);
	}

	@Override
	public MemberVO getAuth(MemberVO vo) {
		return dao.getAuth(vo);
	}

	@Override
	public void insertItem(ItemVO itemVO,String[] hashList) {
		System.out.println("ItemServiceImpl - insertItem");
		
		Calendar cal = Calendar.getInstance();
		
		
		if(itemVO.isItem_isDeliveryFree()) {
			itemVO.setItem_charge(0);
		} else {
			itemVO.setItem_charge(2500);
		}
		
		itemVO.setItem_date(new Timestamp(System.currentTimeMillis()));
		Timestamp timestamp = itemVO.getItem_date();
		cal.setTime(new Date(timestamp.getTime()));
		cal.add(Calendar.MONTH, 1);
		
		Date nowDate = new Date(cal.getTimeInMillis());
		
		itemVO.setItem_endDate(nowDate);
		
		dao.insertItem(itemVO);
		int maxIdx = dao.getMaxIdx(itemVO);
		itemVO.setItem_idx(maxIdx);
//		dao.insertHash(itemVO, maxIdx);
		for(int i = 0; i < hashList.length; i++) {
			
			itemVO.setHashtag_name(hashList[i]);
			dao.insertHash2(itemVO);
		}
		shopDao.updateShopItemCount(itemVO.getMember_id());

		
	}

	@Override
	public void increaseReadCount(int item_idx) {
	      int updateCount = 0;
	      updateCount = dao.increaseReadCount(item_idx);
	      System.out.println("updateCount = " + updateCount);

	}

	@Override
	public List<String> getTagNames(int item_idx) {
		
	    System.out.println("svc - getTagNames");
	      return dao.getTagNames(item_idx);
		
	}

	@Override
	public likesVO getLikesItem(likesVO vo) {
		
		  return dao.getLikesItem(vo);
	}

	@Override
	public int getLikesCount(likesVO lvo) {
		
		   System.out.println("service - getLikesCount");
		      return dao.getLikesCount(lvo);

	}

	@Override
	   public void changeToRed(likesVO lVO) {
	      // TODO Auto-generated method stub
	      System.out.println("service - changeToRed()");
	      int item_idx = lVO.getItem_idx();
	      
	      int insertCount = dao.changeToRed(lVO);
	      
	      ItemVO ivo = new ItemVO();
	      
	      ivo.setItem_idx(item_idx);
	      int updateCount = dao.increaseLikesCount(ivo);
	      
	      
	      
	      // 확인 식 =================================
	      System.out.println("likes 테이블에 수정된 컬럼수 : " + insertCount);
	      // ================================= 확인 식 끝
	      if (updateCount > 1) {
	         System.out.println("item_likesCount - 수정된 컬럼수 : " + updateCount);
	      } else if (updateCount == 1) {
	         System.out.println("item_likesCount - 수정된 컬럼수 : " + updateCount);
	      } else {
	         System.out.println("item_likesCount - 수정된 컬럼수 : " + updateCount);
	      }
	      
	      
	      
	      
	      
	      
	   }   //    method
	
	
	@Override
	   public void changeToGray(likesVO lVO) {
	      // TODO Auto-generated method stub
	      // TODO Auto-generated method stub
	      System.out.println("service - changeToGray()");
	      int insertCount = 0;
	      int item_idx = lVO.getItem_idx();

	      insertCount = dao.changeToGray(lVO);
	      
	      ItemVO ivo = new ItemVO();
	      ivo.setItem_idx(item_idx);
	      
	      int updateCount = dao.decreaseLikesCount(ivo);
	      
	      // 확인 식 =========================================
	      if (insertCount > 1) {
	         System.out.println("likes 테이블에 수정된 컬럼수 2개이상");
	      } else if (insertCount == 1) {
	         System.out.println("likes 테이블에 추가된 컬럼수 1개");
	      } else {
	         System.out.println("likes 테이블에 추가된 컬럼수 0개");
	      }
	      if (updateCount > 1) {
	         System.out.println("item_likesCount - 수정된 컬럼수 : " + updateCount);
	      } else if (updateCount == 1) {
	         System.out.println("item_likesCount - 수정된 컬럼수 : " + updateCount);
	      } else {
	         System.out.println("item_likesCount - 수정된 컬럼수 : " + updateCount);
	      }
	      // 확인식 끝 =========================================
	      
	   }  // method
	
		
	@Override
	public List<ItemVO> getKeyword(String keyword) {
		keyword = keyword.replaceAll(" ", "");
		return dao.getKeyword(keyword);
	}

	@Override
	public List<ItemVO> getItemCategory(String item_category) {
		List<ItemVO> categoryList = null;
		
		categoryList = dao.getItemCategory(item_category);
		
		return categoryList;
	}

	@Override
	public void insertHash(ItemVO itemVO) {
		
		dao.insertHash2(itemVO);
		
	}


	@Override
	   public List<ItemVO> getMyItemList(String member_id) {
	      List<ItemVO> list = dao.getMyItemList(member_id);
	      TimeGap.setItemDateFormat(list);
	      
	      return list;
	   }
	
	   @Override
	   public List<ItemVO> getRelatedItem(ItemVO ivo) {
	      // TODO Auto-generated method stub
//	      System.out.println("svc - getRelatedItem");
	      List<ItemVO> rList = null;
	      
	      rList = dao.getRelatedItem(ivo);
//	      System.out.println(rList.get(0).getItem_img());
	      return rList;
	   }
	   
	   
	   @Override
	   public List<ItemVO> getShopItemPhoto(ItemVO ivo) {
	      // TODO Auto-generated method stub
	      System.out.println("svc - getShopItemPhoto");
	      List<ItemVO> list = null;
	      
	      list = dao.getShopItemPhoto(ivo);
	      
	      
	      return list;
	   }

	@Override
	public List<ItemVO> getSellList(ItemVO vo2) {
		return  dao.getSellList(vo2);

	}

	@Override
	public void insertItemReport(ReportDTO dto) {
		 dao.insertItemReport(dto);
		
	}

	@Override
	public List<ItemVO> getBuyList(ItemVO vo2) {
		// TODO Auto-generated method stub
		return dao.getBuyList(vo2);
		}
	
	@Override
	public List<ItemVO> myItemList(String member_id) {
		 List<ItemVO> myItemList =  dao.myItemList(member_id);
		 
		 for(int i = 0; i < myItemList.size(); i++) {
			  int item_idx = myItemList.get(i).getItem_idx();
			   myItemList.get(i).setComment_count(dao.getCommentCount(item_idx));
		 }
		 
		 return myItemList;
	}
	
	@Override
	   public ItemVO getUpdateItem(int item_idx) {
	      ItemVO item = dao.getItem(item_idx);
	      item.setCategory_name(dao.getCategoryName(item.getCategory_idx()));
	      
	      List<String> hashtagList = dao.getTagNames(item_idx);
	      String hashtagName = "";
	      for(int i=0; i<hashtagList.size(); i++) {
	         hashtagName = hashtagList.get(i) + " ";
	      }
	      item.setHashtag_name(hashtagName);
	      
	      return item;
	   }
	   
	@Override
	public void updatePro(ItemVO vo) {
		dao.updatePro(vo);
		
	}
	
	@Override
	public void timeUpdate(ItemVO vo) {
		dao.timeUpdate(vo);
		
	}
	
	@Override
	public void deletePro(int item_idx) {
		dao.deletePro(item_idx);
		
	}

	@Override
	public void updateItemState(ItemVO itemVO) {
		dao.updateItemState(itemVO);
		
	}

	@Override
	public String getMemberNickname(String member_id) {
		return dao.getMemberNickname(member_id);
	}

	@Override
	public void changeItemState(ItemVO vo) {
		
		dao.changeItemState(vo);
		
	}
	
	@Override
    public void insertShopReport(ReportDTO dto) {
        dao.insertShopReport(dto);
    }

    @Override
    public void insertShopRivewReport(ReportDTO dto) {
        dao.insertShopReviewReport(dto);
    }

	@Override
    public String getItemSubject(int item_idx) {
        return dao.getItemSubject(item_idx);
    }
	
	@Override
	   public void insertCommentReport(ReportDTO rvo) {
	      // TODO Auto-generated method stub
	      dao.insertCommentReport(rvo);
	   }

	@Override
	public List<String> getSellKing() {
		return dao.getSellKing();
	}
	
	  
	




}
