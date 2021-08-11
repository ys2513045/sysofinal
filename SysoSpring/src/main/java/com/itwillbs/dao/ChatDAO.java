package com.itwillbs.dao;

import java.sql.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MessageDTO;

@Repository
public class ChatDAO {
	
private static final String namespace ="com.itwillbs.itemMapper";
private static final String namespace2 = "com.itwillbs.alarmMapper";
	
	@Inject
	private SqlSession sqlSession;

	public String getNickname(int item_idx) {
		
		String member_id = sqlSession.selectOne(namespace+".getNickname",item_idx);
		
		return member_id;
	}


	public List<MessageDTO> getMessage(MessageDTO msg) {
		
		return sqlSession.selectList(namespace+".getMsg",msg);
	}
	
	public List<MessageDTO> getMessage(int room) {
		return sqlSession.selectList(namespace+".getMsg2",room);
	}


	public void insertRoom(MessageDTO msg) {
		sqlSession.insert(namespace+".insertRoom",msg);
		
	}


	public void insertMessage(MessageDTO dto) {
		sqlSession.insert(namespace+".insertMessage",dto);
		
	}


	public MessageDTO getMessageOne(String send_nick) {
		return sqlSession.selectOne(namespace+".getMsgOne",send_nick);
	}


	public List<MessageDTO> getChatList(MessageDTO dto) {

		return sqlSession.selectList(namespace+".getChatList",dto);
	}


//	public void insertAlarm(MessageDTO dto) {
//		
//		sqlSession.insert(namespace2+".insertAlarm",dto);
//		
//	}




	
}
