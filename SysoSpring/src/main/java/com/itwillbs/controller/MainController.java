package com.itwillbs.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.itwillbs.domain.ItemVO;
import com.itwillbs.domain.MemberVO;
import com.itwillbs.service.ItemService;
import com.itwillbs.service.MemberService;
import com.itwillbs.util.TimeGap;

@Controller
public class MainController {

	@Inject
	private ItemService service;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model, HttpSession session) throws Exception {
		String member_id = (String)session.getAttribute("id");
		String member_nickname = service.getMemberNickname(member_id);
		String sellKing = service.getMemberNickname(service.getSellKing().get(0));
		model.addAttribute("member_nickname",member_nickname);
		model.addAttribute("sellKing",sellKing);
		return "index";
	}

	@RequestMapping(value = "/infiniteScrollDown", method = RequestMethod.POST)
	public @ResponseBody List<ItemVO> infiniteScrollDownPost(@RequestBody ItemVO itemVO) {

		List<ItemVO> list = service.infiniteScrollDown(itemVO);
		
		TimeGap.setDateFormat(list);

		return list;
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {

		return "./member/joinForm";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {

		return "./member/loginForm";
	}

	@RequestMapping(value = "/item", method = RequestMethod.GET)
	public String myItem(HttpSession session) {
		if (session.getAttribute("member_id") != null) {

			return "./product/write_from";
		} else {
			return "./product/badway";
		}

	}

	@RequestMapping(value = "/socketChat", method = RequestMethod.GET)
	public String example(HttpSession session) {

		return "./chat/socketChat";

	}
	
	@RequestMapping(value = "/kakaoTest", method = RequestMethod.GET)
	public String test(HttpSession session) {

		return "kakao";

	}
	
	

//    400 에러 	
//    (1) mapper id가 틀린 경우
//
//    (2) Parameter와 bean의 field명이  틀린 경우
//
//    (3) sql.xml에서 정의된 namespace와 DAO에서 호출하는 namespace가 다를 경우
//
//    (4) mapper가 정의가 되어 있지 않거나 Spelling이 틀린 경우
//
//    (5) mapper에 정의된 namespace 명칭이 같은 Application 내에 중복 될 경우

}
