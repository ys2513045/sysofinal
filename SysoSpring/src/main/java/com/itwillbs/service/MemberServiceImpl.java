package com.itwillbs.service;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;
import javax.mail.MessageRemovedException;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwillbs.dao.MemberDAO;
import com.itwillbs.domain.MemberVO;
import com.itwillbs.util.MailUtils;
import com.itwillbs.util.TempKey;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAO dao;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	@Transactional
	public void memberJoinProcess(MemberVO vo){
		
		
		
		System.out.println("member service");
			if((vo.getMember_inviteID() != "") || (vo.getMember_inviteID().equals("추천인아이디"))){
				String selectName = dao.getMemberID(vo.getMember_inviteID());
				System.out.println("selectName :" + selectName);
				if(selectName != null) {
					vo.setMember_point(3000);
					dao.memberJoin2(vo);
					dao.updatePoint(vo.getMember_inviteID());
				}else {
					dao.memberJoin(vo);

				}
			}else {
				dao.memberJoin(vo);
			}
			
		String authkey = new TempKey().getKey(15,false);
		
		vo.setMember_authKey(authkey);
		dao.updateAuthkey(vo);
		
		try {
			MailUtils sendMail = new MailUtils(mailSender);
			
			sendMail.setSubject("[SYSO MARKET] 회원가입 이메일 인증");
			sendMail.setText(new StringBuffer().append("<!DOCTYPE html>")
					.append("<html>")
					.append("<head>")
					.append("</head>")
					.append("<body>")
					.append(				" <div" 																																																	+ 
							"	style=\"font-family: 'Apple SD Gothic Neo', 'sans-serif' !important; width: 400px; height: 600px; border-top: 4px solid #396afc; margin: 100px auto; padding: 30px 0; box-sizing: border-box;\">"		+ 
							"	<h1 style=\"margin: 0; padding: 0 5px; font-size: 28px; font-weight: 400;\">"																															+ 
							"		<span style=\"font-size: 15px; margin: 0 0 10px 3px;\">Team SYSO</span><br />"																													+ 
							"		<span style=\"color: #396afc \">메일인증</span> 안내입니다."																																				+ 
							"	</h1>\n"																																																+ 
							"	<p style=\"font-size: 16px; line-height: 26px; margin-top: 50px; padding: 0 5px;\">"																													+ 
							vo.getMember_name()																																																+
							"		님 안녕하세요.<br />"																																													+ 
							"		SYSO MARKET 에 가입해 주셔서 진심으로 감사드립니다.<br />"																																						+ 
							"		아래 <b style=\"color: #396afc \">'메일 인증'</b> 버튼을 클릭하여 회원가입을 완료해 주세요.<br />"																													+ 
							"		감사합니다."																																															+ 
							"	</p>"																																																	+ 
							"	<a style=\"color: #FFF; text-decoration: none; text-align: center;\""																																	+
							"	href=\"http://localhost:8080/syso/joinconfirm?member_id="+ vo.getMember_id() +"&member_email="+vo.getMember_email()+"&member_authKey="+vo.getMember_authKey()+ "\" target=\"_blank\">"														+ 
							"		<p"																																																	+
							"			style=\"display: inline-block; width: 210px; height: 45px; margin: 30px 5px 40px; background: #396afc; line-height: 45px; vertical-align: middle; font-size: 16px;\">"							+ 
							"			메일 인증</p>"																																														+ 
							"	</a>"																																																	+
							"	<div style=\"border-top: 1px solid #DDD; padding: 5px;\"></div>"																																		+
							" </div>"
					)
					.append("</body>")
					.append("</html>")
					.toString());
			sendMail.setFrom("kysclient@gmail.com", "관리자");
			sendMail.setTo(vo.getMember_email());
			sendMail.send();
			
			
			
		} catch (MessageRemovedException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	@Transactional
	@Override
	public void memberFindProcess(MemberVO vo) {
String authkey = new TempKey().getKey(15,false);
		
		vo.setMember_password(authkey);
		dao.updatePassword(vo);
		
		try {
			MailUtils sendMail = new MailUtils(mailSender);
			
			sendMail.setSubject("[SYSO MARKET] 임시비밀번호 발급");
			sendMail.setText(new StringBuffer().append("<!DOCTYPE html>")
					.append("<html>")
					.append("<head>")
					.append("</head>")
					.append("<body>")
					.append(				" <div" 																																																	+ 
							"	style=\"font-family: 'Apple SD Gothic Neo', 'sans-serif' !important; width: 400px; height: 600px; border-top: 4px solid #396afc; margin: 100px auto; padding: 30px 0; box-sizing: border-box;\">"		+ 
							"	<h1 style=\"margin: 0; padding: 0 5px; font-size: 28px; font-weight: 400;\">"																															+ 
							"		<span style=\"font-size: 15px; margin: 0 0 10px 3px;\">Team SYSO</span><br />"																													+ 
							"		<span style=\"color: #396afc \">임시 비밀번호 발급 </span> 안내입니다."																																				+ 
							"	</h1>\n"																																																+ 
							"	<p style=\"font-size: 16px; line-height: 26px; margin-top: 50px; padding: 0 5px;\">"																													+ 
							vo.getMember_name()																																																+
							"		님 안녕하세요.<br />"																																													+ 
							"		SYSO MARKET을 이용  해주셔서 진심으로 감사드립니다.<br />"																																						+ 
							"	</p>"																																																	+ 
							"	회원님의 임시 비밀 번호는 " + "<span style=\"color:#396afc; padding:10px;\">" + vo.getMember_password() + "</span>입니다."																																																	+
							"	<div style=\"border-top: 1px solid #DDD; padding: 5px;\"></div>"																																		+
							" </div>"
					)
					.append("</body>")
					.append("</html>")
					.toString());
			sendMail.setFrom("kysclient@gmail.com", "관리자");
			sendMail.setTo(vo.getMember_email());
			sendMail.send();
			
			
			
		} catch (MessageRemovedException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public void updateAuthstatus(MemberVO vo) {
		
		dao.updateAuthstatus(vo);
		
	}

	@Override
	public MemberVO checkDup(String member_id) {
	
		
		return dao.checkDup(member_id);
	}

	@Override
	   public MemberVO userCheck(MemberVO vo) {
	      // TODO Auto-generated method stub
	      System.out.println("serviceImpl - userCheck");
	      return dao.userCheck(vo);
	   }

	@Override
	public MemberVO findPw(MemberVO vo) {
		return dao.findPw(vo);
	}

	@Override
	public void kakaoMember(MemberVO vo) {
		dao.kakaoMember(vo);
		
	}

	@Override
	public MemberVO checkEmail(MemberVO vo) {
		return dao.checkEmail(vo);
	}

	@Override
	public MemberVO alreadyDone(MemberVO vo) {
		return dao.alreadyDone(vo);
	}

	@Override
	   public String getId(String member_nickname) {
	      return dao.getId(member_nickname);
	   }

	@Override
	   public String updateNickname(String nickname, String member_id) {
	      MemberVO vo = dao.checkNickname(nickname);
	      
	      if(vo == null) {
	         MemberVO vo2 = new MemberVO();
	         vo2.setMember_nickname(nickname);
	         vo2.setMember_id(member_id);
	         
	         dao.updateNickname(vo2);
	         return nickname;
	         
	      } else {
	         return null;
	      }
	   }

	@Override
	public void memberInsertShopId(MemberVO vo) {
		dao.memberInsertShopId(vo);
		
	}

	@Override
	public MemberVO getMember(String id) {
		return dao.getMember(id);
	}

	@Override
	public void updateMember(MemberVO vo) {
		dao.updateMember(vo);
	
	}
	
	@Override
	public void usePoint(MemberVO vo) { // 포인트 사용 시 차감
		dao.usePoint(vo);
	}

	@Override
	public MemberVO checkEmailDup(String member_email) {
		return dao.checkEmailDup(member_email);
	}

	@Override
	public MemberVO checkNicknameDup(String member_nickname) {
		return dao.checkNicknameDup(member_nickname);
	}



}
