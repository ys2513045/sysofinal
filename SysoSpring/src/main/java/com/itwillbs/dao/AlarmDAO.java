package com.itwillbs.dao;

import java.util.List;

import com.itwillbs.domain.AlarmVO;

public interface AlarmDAO {

	public List<AlarmVO> getAlarm(String member_id);

	public List<AlarmVO> getAlarm2(String member_id);

	public void insertOrderAlarm(AlarmVO alarmVO);
		
}
