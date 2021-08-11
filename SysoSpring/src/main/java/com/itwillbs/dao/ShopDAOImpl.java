package com.itwillbs.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.ShopVO;

@Repository
public class ShopDAOImpl implements ShopDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace ="com.itwillbs.shopMapper";
	
	@Override
	public ShopVO getShop(String member_id) {
		return sqlSession.selectOne(namespace+".getShop", member_id);
	}

	@Override
	public String getShopIntro(String member_id) {
		return sqlSession.selectOne(namespace+".getShopIntro", member_id);
	}

	@Override
	public void updateShopIntro(ShopVO shop) {
		sqlSession.update(namespace+".updateShopIntro", shop);
	}
	
	@Override
	   public List<ShopVO> getShopInfo(String member_id) {
	      // TODO Auto-generated method stub
	      
	      
	      return sqlSession.selectList(namespace + ".getShopInfo", member_id);
	   }
	
	@Override
	public void updateShopItemCount(String member_id) {
		sqlSession.update(namespace+".updateShopItemCount", member_id);
	}

}
