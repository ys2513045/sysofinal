package com.itwillbs.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.AlarmVO;

@Repository
public class AlarmDAOImpl implements AlarmDAO {

	private static final String namespace ="com.itwillbs.alarmMapper";

	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<AlarmVO> getAlarm(String member_id) {
		return sqlSession.selectList(namespace + ".getAlarm", member_id);
	}

	@Override
	public List<AlarmVO> getAlarm2(String member_id) {
		return sqlSession.selectList(namespace+".getAlarm2",member_id);
	}

	@Override
	public void insertOrderAlarm(AlarmVO alarmVO) {
		sqlSession.insert(namespace+".insertOrderAlarm",alarmVO);

	}


}
