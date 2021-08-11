package com.itwillbs.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.itwillbs.domain.ItemVO;
import com.itwillbs.domain.MemberVO;
import com.itwillbs.service.ItemService;
import com.itwillbs.service.MemberService;
import com.itwillbs.util.*;

@Controller
public class MemberController {
	
//	private kakao_restapi kakao;
	
	kakao_restapi kakao = new kakao_restapi();

	@Inject
	private MemberService service;
	
	@Inject
	private ItemService itemservice;

	@RequestMapping(value = "/joinPro", method = RequestMethod.POST)
	public ModelAndView join(@ModelAttribute("vo") MemberVO vo, ModelAndView mv) {
		System.out.println(vo.getMember_inviteID());
		service.memberJoinProcess(vo);
		service.memberInsertShopId(vo);
		
		mv.setViewName("./member/certification");
		mv.addObject("vo", vo.getMember_name());
		return mv;
	}
	
	@RequestMapping(value = "/findPw", method = RequestMethod.POST)
	public @ResponseBody MemberVO insertMsg(@RequestBody MemberVO vo) {

		MemberVO memberInfo = service.findPw(vo);
		service.memberFindProcess(memberInfo);
		
		return memberInfo;
	}

//	@RequestMapping(value = "/certification", method = RequestMethod.GET)
//	public String cert(){
//		System.out.println("MainController /join");
//				
//		return "./member/certification";
//	}
	    
	
	    @RequestMapping(value="/oauth")
	    public String login(@RequestParam("code") String code, HttpSession session, Model model) {
	        String access_Token = kakao.getAccessToken(code);
	        HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
	        System.out.println("login Controller : " + userInfo);
	        String member_id = (String) userInfo.get("member_email");
	        String member_nickname = (String) userInfo.get("member_nickname");
	        String member_password = (String)userInfo.get("member_id");
	        String member_email = (String) userInfo.get("member_email");

	        
	        
	        if (userInfo.get("member_email") != null) {
  	
	        	MemberVO vo = new MemberVO();
	        	vo.setMember_id(member_id);
	        	vo.setMember_nickname(member_nickname);
	        	vo.setMember_password(member_password);
	        	vo.setMember_email(member_email);
	        	MemberVO vo3 = new MemberVO();
	        	vo3.setMember_id(member_id);
	        	vo3 = service.alreadyDone(vo);
	        	if(vo3 == null) {
	        	MemberVO vo2 = service.checkEmail(vo);
	        	
	        	if(vo2 == null) {
	        		
	        		service.kakaoMember(vo);
	        		service.memberInsertShopId(vo);
	        		session.setAttribute("id", vo.getMember_id());
	        		session.setAttribute("access_Token", access_Token);
	        		String member_nickname2 = itemservice.getMemberNickname(member_id);
	        		model.addAttribute("member_nickname",member_nickname2);
	        		session.setAttribute("nickname", member_nickname2);
		
	        		return "/index";
	        	}else {
				model.addAttribute("msg", "이미 SYSO 회원이시군요 !");
				return "./member/msg/msg";

	        	}
	        	}else {
	        		session.setAttribute("id", vo.getMember_id());
	        		session.setAttribute("access_Token", access_Token);
	        		String member_nickname2 = itemservice.getMemberNickname(member_id);
	        		model.addAttribute("member_nickname",member_nickname2);
	        		session.setAttribute("nickname", member_nickname2);
	        		return "/index";
	        	}
	        }else {
				model.addAttribute("msg", "카카오 로그인시 이메일 정보 제공 동의는 필수입니다.");

	        	return "./member/msg/msg";
	        }
	    }

	    
	    
	

	@RequestMapping(value = "/joinconfirm", method = RequestMethod.GET)
	public String emailConfirm(@ModelAttribute("vo") MemberVO vo, Model model) throws Exception {

		vo.setMember_authStatus(1); // authstatus를 1로,, 권한 업데이트
		service.updateAuthstatus(vo);

		model.addAttribute("name", vo.getMember_name());

		return "./member/joinconfirm";
	}

	@RequestMapping(value = "/loginPro", method = RequestMethod.POST)
	public String loginPro(MemberVO vo, HttpSession session, Model model) {

		MemberVO vo2 = service.userCheck(vo);

		if (vo2 != null) {
			// id, pass 일치시
			// session 값 생성
			session.setAttribute("id", vo2.getMember_id());
			System.out.println("닉네임" + vo2.getMember_nickname());
			session.setAttribute("nickname", vo2.getMember_nickname());
			return "redirect:/home";

		} else {
			// id, pass 불일치
			// 메시지 뿌리고 뒤로이동
			model.addAttribute("msg", "입력하신 정보는 틀립니다.");

			return "./member/msg/msg";

		}

	}

	@RequestMapping(value = "/member/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/home";
	}

	@RequestMapping(value = "/checkDup", method = RequestMethod.POST)
	public @ResponseBody int checkDup(@RequestBody MemberVO vo) {
		System.out.println("중복확인 들어옴");
		System.out.println(vo.getMember_id());
		int cnt = 0;
		
		MemberVO vo2 = service.checkDup(vo.getMember_id());
		if (vo2 == null) {
			cnt = 0;
		} else {
			cnt = 1;
		}

		return cnt;
	}
	
	@RequestMapping(value = "/checkEmailDup", method = RequestMethod.POST)
	public @ResponseBody int checkEmailDup(@RequestBody MemberVO vo) {
		System.out.println("이메일 중복확인 들어옴");
		System.out.println(vo.getMember_email());
		int cnt = 0;
		
		MemberVO vo2 = service.checkEmailDup(vo.getMember_email());
		if (vo2 == null) {
			cnt = 0;
		} else {
			cnt = 1;
		}

		return cnt;
	}
	
	@RequestMapping(value = "/checkNicknameDup", method = RequestMethod.POST)
	public @ResponseBody int checkNicknameDup(@RequestBody MemberVO vo) {
		System.out.println("닉네임 중복확인 들어옴");
		System.out.println(vo.getMember_nickname());
		int cnt = 0;
		
		MemberVO vo2 = service.checkNicknameDup(vo.getMember_nickname());
		if (vo2 == null) {
			cnt = 0;
		} else {
			cnt = 1;
		}

		return cnt;
	}
	
	
	
	
	@RequestMapping(value = "/member/updateNickname", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	   @ResponseBody
	   public String updateNickname(String nickname, String member_id, HttpSession session) {
	      session.setAttribute("nickname", nickname);
	      String result = service.updateNickname(nickname, member_id);
	      System.out.println(result);
	      return result;
	   }
	
	
	

} // class