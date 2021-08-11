package com.itwillbs.dao;

import com.itwillbs.domain.MemberVO;

public interface MemberDAO {

	public void memberJoin(MemberVO vo);

	public void updateAuthkey(MemberVO vo);

	public void updateAuthstatus(MemberVO vo);

	public MemberVO checkDup(String member_id);

	public MemberVO userCheck(MemberVO vo);

	public MemberVO findPw(MemberVO vo);

	public String getMemberID(String member_inviteID);

	void memberJoin2(MemberVO vo);

	public void updatePoint(String member_inviteID);

	public void kakaoMember(MemberVO vo);

	public MemberVO checkEmail(MemberVO vo);

	public MemberVO alreadyDone(MemberVO vo);

	public String getId(String member_nickname);

	public void updateNickname(MemberVO vo2);

	public MemberVO checkNickname(String nickname);
	
	public String getNickname(String member_id);

	public String transMember_id(String shopId);

	public void memberInsertShopId(MemberVO vo);

	public MemberVO getMember(String id);

	public void updateMember(MemberVO vo);

	public void usePoint(MemberVO vo);

	public MemberVO checkEmailDup(String member_email);

	public void updatePassword(MemberVO vo);

	public MemberVO checkNicknameDup(String member_nickname);
	
	
	
	
	

}
