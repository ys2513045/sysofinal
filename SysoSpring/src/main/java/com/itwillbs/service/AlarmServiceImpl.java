package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.dao.AlarmDAO;
import com.itwillbs.dao.MemberDAO;
import com.itwillbs.domain.AlarmVO;

@Service
public class AlarmServiceImpl implements AlarmService {
	
	@Inject
	AlarmDAO alarmDAO;
	
	@Inject
	MemberDAO mDAO;
	
	@Override
	public List<AlarmVO> getAlarm(String member_id) {
		return alarmDAO.getAlarm(member_id);
	}

	@Override
	public List<AlarmVO> getAlarm2(String member_id) {
		
		List<AlarmVO> list =  alarmDAO.getAlarm2(member_id);
		for(int i = 0; i < list.size(); i++) {
			list.get(i).setMember_nickname(mDAO.transMember_id(member_id));
		
		}
		return list;
	}

	@Override
	public void insertOrderAlarm(AlarmVO alarmVO) {
		alarmDAO.insertOrderAlarm(alarmVO);
		
	}

	
}
