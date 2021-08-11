package com.itwillbs.service;

import com.itwillbs.domain.MemberVO;

public interface MemberService {
	
	public void memberJoinProcess(MemberVO vo);

	public void updateAuthstatus(MemberVO vo);

	public MemberVO checkDup(String member_id);

	public MemberVO userCheck(MemberVO vo);

	public MemberVO findPw(MemberVO vo);

	public void kakaoMember(MemberVO vo);

	public MemberVO checkEmail(MemberVO vo);

	public MemberVO alreadyDone(MemberVO vo);

	public String getId(String nickname);

	String updateNickname(String nickname, String member_id);

	public void memberInsertShopId(MemberVO vo);

	public MemberVO getMember(String id);

	public void updateMember(MemberVO vo);

	void usePoint(MemberVO vo);

	public MemberVO checkEmailDup(String member_email);

	public void memberFindProcess(MemberVO memberInfo);

	public MemberVO checkNicknameDup(String member_nickname);
}
