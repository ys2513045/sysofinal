package com.itwillbs.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


@Repository
public class LikesDAOImpl implements LikesDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = "com.itwillbs.likesMapper";


	@Override
	public List<Integer> memberLikesList(String member_id) {
		return sqlSession.selectList(namespace+".memberLikesList", member_id);
	}
	
	
	

}
