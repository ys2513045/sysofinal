package com.itwillbs.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MemberVO;



@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace ="com.itwillbs.itemMapper";
	private static final String namespace2 ="com.itwillbs.memberMapper";
	private static final String namespace3 ="com.itwillbs.paymentMapper";




	@Override
	public void memberJoin(MemberVO vo) {
			sqlSession.insert(namespace+".join",vo);
	}
	@Override
	public void memberJoin2(MemberVO vo) {
			sqlSession.insert(namespace+".join2",vo);
	}
	@Override
	public String getMemberID(String member_inviteID) {
		return sqlSession.selectOne(namespace+".getMemberID2",member_inviteID);
	}
	@Override
	public void updatePoint(String member_inviteID) {
		sqlSession.update(namespace+".updatePoint",member_inviteID);
		
	}

	@Override
	public void updateAuthkey(MemberVO vo) {
		
		System.out.println("맵퍼 인증키 업데이트");
		sqlSession.update(namespace+".authkey", vo);
	}


	@Override
	public void updateAuthstatus(MemberVO vo) {
		System.out.println("맵퍼 인증 완료 업데이트");
		sqlSession.update(namespace+".authstatus", vo);

	}


	@Override
	public MemberVO checkDup(String member_id) {
		
		System.out.println("DAO 중복확인");
		
		return sqlSession.selectOne(namespace+".checkdup",member_id);
		
	}
	
	@Override
	   public MemberVO userCheck(MemberVO vo) {
	      System.out.println("DAO - userCheck");
	      
	      return sqlSession.selectOne(namespace + ".userCheck", vo);
	   }


	@Override
	public MemberVO findPw(MemberVO vo) {
		
		return sqlSession.selectOne(namespace+".findPw",vo);
	}
	@Override
	public void kakaoMember(MemberVO vo) {
		
		sqlSession.insert(namespace+".kakaoMember",vo);
		
	}
	@Override
	public MemberVO checkEmail(MemberVO vo) {
		return sqlSession.selectOne(namespace+".checkEmail",vo);
	}
	@Override
	public MemberVO alreadyDone(MemberVO vo) {
		return sqlSession.selectOne(namespace+".alreadyDone",vo);
	}
	
	@Override
	   public String getId(String member_nickname) {
	      return sqlSession.selectOne(namespace2+".getId", member_nickname);
	   }


	   @Override
	   public MemberVO checkNickname(String nickname) {
	      return sqlSession.selectOne(namespace2+".checkNickname", nickname);
	   }


	   @Override
	   public void updateNickname(MemberVO vo) {
	      sqlSession.update(namespace2+".updateNickname", vo);
	   }
	
	   @Override
	   public String getNickname(String member_id) {
	      return sqlSession.selectOne(namespace2+".getNickname", member_id);
	   }
	   
	   @Override
	   public String transMember_id(String member_id) {
	      // TODO Auto-generated method stub
	      return sqlSession.selectOne(namespace2 + ".transMemberid", member_id);
	   }
	@Override
	public void memberInsertShopId(MemberVO vo) {
	      sqlSession.insert(namespace2 + ".insertShopId", vo);

		
	}
	@Override
	public MemberVO getMember(String id) {
		return sqlSession.selectOne(namespace2+".getMember", id);
	}
	
	@Override
	public void updateMember(MemberVO vo) { // 주문폼 작성 시 회원 정보 수정
		sqlSession.update(namespace3+".updateMember", vo);
		
	}
	@Override
	public void usePoint(MemberVO vo) { // 포인트 사용 시 차감
		System.out.println("MemberDAOImpl_usePoint");
		sqlSession.update(namespace3+".usePoint", vo);
		
	}
	@Override
	public MemberVO checkEmailDup(String member_email) {
		return sqlSession.selectOne(namespace2+".checkEmaildup",member_email);
	}
	@Override
	public void updatePassword(MemberVO vo) {
		sqlSession.update(namespace2+".updatePassword",vo);
		
	}
	@Override
	public MemberVO checkNicknameDup(String member_nickname) {
		return sqlSession.selectOne(namespace2+".checkNicknamedup",member_nickname);
	}
	   




}
