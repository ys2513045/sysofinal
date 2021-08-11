package com.itwillbs.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.ShopReviewVO;

@Repository
public class ShopReviewDAOImpl implements ShopReviewDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace ="com.itwillbs.shopReviewMapper";

	@Override
	public List<ShopReviewVO> getShopReviewList(String shop_member_id) {
		return sqlSession.selectList(namespace+".getShopReviewList", shop_member_id);
	}
	
	@Override
	   public Integer getreviewCount(String member_id) {
	      // TODO Auto-generated method stub
	      return sqlSession.selectOne(namespace + ".getreviewCount", member_id);
	   }
	
	@Override
	   public List<ShopReviewVO> getPreReview(String shop_member_id) {
	      // TODO Auto-generated method stub
	      return sqlSession.selectList(namespace + ".getPreReview", shop_member_id);
	   }
	
	@Override
    public void insertShopReview(ShopReviewVO shopReview) {
        sqlSession.insert(namespace+".insertShopReview", shopReview);
    }

    @Override
    public ShopReviewVO getShopReview(ShopReviewVO shopReivew) {
        return sqlSession.selectOne(namespace+".getShopReview", shopReivew);
    }
	
	
	
}
