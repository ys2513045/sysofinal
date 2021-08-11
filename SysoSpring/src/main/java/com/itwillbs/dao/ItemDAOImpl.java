package com.itwillbs.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.ItemVO;
import com.itwillbs.domain.MemberVO;
import com.itwillbs.domain.ReportDTO;
import com.itwillbs.domain.likesVO;

@Repository
public class ItemDAOImpl implements ItemDAO {
	
	private static final String namespace ="com.itwillbs.itemMapper";
	private static final String namespace2 ="com.itwillbs.likesMapper";
	private static final String namespace3 ="com.itwillbs.reportMapper";
	private static final String namespace4 ="com.itwillbs.CommentMapper";

	
	@Inject
	private SqlSession sqlSession;

	@Override
	public List<ItemVO> getItem() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace +".getItem");
	}

	@Override
	public List<ItemVO> infiniteScrollDown(ItemVO itemVO) {
		
		return sqlSession.selectList(namespace+".infinite",itemVO);
	}

	@Override
	public List<ItemVO> getItemDetail(int item_idx) {
		
		return sqlSession.selectList(namespace+".getItemDetail",item_idx);
		
	}

	@Override
	public String getMemberID(int item_idx) {
		return sqlSession.selectOne(namespace+".getMemberID",item_idx);
	}


	@Override
	public List<ItemVO> getSameCategory(String item_category) {
		return sqlSession.selectList(namespace+".samecategory",item_category);
	}

	@Override
	public MemberVO getAuth(MemberVO vo) {
		return sqlSession.selectOne(namespace+".getAuth",vo);
	}
	
	@Override
	public void insertItem(ItemVO itemVO) {
		System.out.println("ItemDAOImpl - insertItem");
		sqlSession.insert(namespace+".insertItem", itemVO);
		
	}

	@Override
	public int increaseReadCount(int item_idx) {
		  System.out.println("daoImpl - increaseReadcount");
	      return sqlSession.update(namespace + ".increaseReadCount", item_idx);

		
	}

	@Override
	public List<String> getTagNames(int item_idx) {
	      return sqlSession.selectList(namespace + ".getTagNames", item_idx);
	}

	@Override
	public likesVO getLikesItem(likesVO vo) {
		   return sqlSession.selectOne(namespace2 + ".getLikesItem", vo);

		
	}

	@Override
	public int getLikesCount(likesVO lvo) {
	      System.out.println("dao - getLikesCount");
	      return sqlSession.selectOne(namespace2 + ".getLikesCount", lvo);

	}

	@Override
	   public int changeToRed(likesVO lVO) {
	      System.out.println("DAO - changeToRed");
	      return sqlSession.insert(namespace2 + ".changeToRed", lVO);
	   }
	
	@Override
	   public int changeToGray(likesVO lVO) {
	      // TODO Auto-generated method stub
	      System.out.println("DAO - changeToGray");
	      
	      return sqlSession.delete(namespace2 + ".changeToGray", lVO);
	   }
	
	@Override
	   public int decreaseLikesCount(ItemVO ivo) {
	      // TODO Auto-generated method stub
	      System.out.println("DAO - increaseLikesCount");
	      return sqlSession.update(namespace + ".decreaseLikesCount", ivo);
	   }
	
	
	@Override
	   public int increaseLikesCount(ItemVO ivo) {
	      // TODO Auto-generated method stub
	      System.out.println("DAO - increaseLikesCount");
	      return sqlSession.update(namespace + ".increaseLikesCount", ivo);
	   }

	@Override
	   public String getCategoryName(int category_idx) {
	      // TODO Auto-generated method stub
	      return sqlSession.selectOne(namespace + ".getCategoryName", category_idx);
	   }
		
	
	@Override
	public List<ItemVO> getKeyword(String keyword) {
		return sqlSession.selectList(namespace+".getKeyword",keyword);
	}
	
	

	@Override
	public List<ItemVO> getItemCategory(String item_category) {
		return sqlSession.selectList(namespace + ".getItemCategory", item_category);
	}
	
	@Override
	public Integer getMaxIdx(ItemVO itemVO) {
		return sqlSession.selectOne(namespace+".getMaxIdx");
	}
	

	@Override
	public void insertHash2(ItemVO itemVO) {
		
		sqlSession.insert(namespace+".insertHash2",itemVO);
		
	}

	@Override
	   public ItemVO getItem(int item_idx) {
	      return sqlSession.selectOne(namespace+".getItemInfo", item_idx);
	   }
	
	
	@Override
	   public List<ItemVO> getMyItemList(String member_id) {
	      return sqlSession.selectList(namespace+".getMyItemList", member_id);
	   }
	
	@Override
	   public String getItemSubject(int item_idx) {
	      return sqlSession.selectOne(namespace+".getItemSubject", item_idx);
	   }
	
	   @Override
	   public List<ItemVO> getRelatedItem(ItemVO ivo) {
	      // TODO Auto-generated method stub
	      
	      
	      return sqlSession.selectList(namespace + ".getRelatedItem", ivo);
	   }
	   
	   @Override
	   public List<ItemVO> getShopItemPhoto(ItemVO ivo) {
	      // TODO Auto-generated method stub
	      return sqlSession.selectList(namespace + ".getShopItemPhoto", ivo);
	   }
	   
	   
	   @Override
		public List<ItemVO> getSellList(ItemVO vo2) {
			return sqlSession.selectList(namespace+".getSellList", vo2);
		}

	@Override
	public void insertItemReport(ReportDTO dto) {
		
		sqlSession.insert(namespace3+".insertItemReport",dto);
		
	}

	@Override
	public List<ItemVO> getBuyList(ItemVO vo2) {
		return sqlSession.selectList(namespace+".getBuyList", vo2);
	}
	
	@Override
	public List<ItemVO> myItemList(String member_id) {
		return sqlSession.selectList(namespace+".myItemList", member_id);
	}
	
	
	@Override
	public void updatePro(ItemVO vo) {
		System.out.println("ItemDAOImpl-updatePro");
		sqlSession.update(namespace+".updatePro", vo);
	}

	@Override
	public int getCommentCount(int item_idx) {
		return sqlSession.selectOne(namespace+".commnetCount",item_idx);
	}
	   
	@Override
	public void timeUpdate(ItemVO vo) {
		sqlSession.update(namespace+".timeUpdate",vo);
		
	}
	
	@Override
	public void deletePro(int item_idx) {
		System.out.println("ItemDAOImpl-deletePro");
		sqlSession.delete(namespace+".deletePro", item_idx);
		
	}

	@Override
	public void updateItemState(ItemVO itemVO) {
		sqlSession.update(namespace+".updateItemState",itemVO);
		
	}

	@Override
	public String getMemberNickname(String member_id) {
		return sqlSession.selectOne(namespace+".getMemberNickname",member_id);
	}

	@Override
	public void changeItemState(ItemVO vo) {
		sqlSession.update(namespace+".changeItemState",vo);
		
	}
	
	
	@Override
    public void insertShopReport(ReportDTO dto) {
        sqlSession.insert(namespace3+".insertShopReport",dto);
    }

    @Override
    public void insertShopReviewReport(ReportDTO dto) {
        sqlSession.insert(namespace3+".insertShopReviewReport",dto);
    }

    @Override
    public void insertCommentReport(ReportDTO rvo) {
       // TODO Auto-generated method stub
       sqlSession.insert(namespace4 + ".insertCommentReport", rvo);
    }

	@Override
	public List<String> getSellKing() {
		return sqlSession.selectList(namespace+".getSellKing");
	}
	
	@Override
	public int getSaleCount(String member_id) {
		return sqlSession.selectOne(namespace+".getSaleCount", member_id);
	}
	
	
	

}
