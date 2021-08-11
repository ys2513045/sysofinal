package com.itwillbs.controller;


import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.itwillbs.domain.QnaVO;
import com.itwillbs.service.QnaService;

@Controller
public class QnaController {
	
	@Inject
	private QnaService service;
	
	// qna ==================================================
	
	// 고객센터 페이지 불러오기
	@RequestMapping(value = "/qnaList", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) {
		System.out.println("컨트롤러 1");
		ModelAndView mv = service.getQnaList(request);
		mv.setViewName("/customer_center/center");
		return mv;
	}

	
	// qna 문의 입력
	@RequestMapping(value = "/qnaWritePro", method = RequestMethod.POST)
	public String insertQna(ModelAndView mv, QnaVO qnaVO, HttpServletRequest request) {
		System.out.println("컨트롤러 2");
		service.insertQna(qnaVO);
		return "redirect:/qnaList";
	}

	
	// qna 답글 입력 및 수정
	@RequestMapping(value = "/insertQnaReply", method = RequestMethod.POST)
	public @ResponseBody String insertQnaReply(QnaVO qnaVO, HttpServletRequest request) {
		System.out.println("컨트롤러 3");
		
		service.insertQnaReply(qnaVO);
		
		// ========================================
		ModelAndView mv = service.getQnaList(request);
		// ========================================
		
		return "답글 입력 및 수정";
	}
	
	// qna 답글 삭제 
	@RequestMapping(value = "/deleteQnaReply", method = RequestMethod.POST)
	public @ResponseBody String deleteQnaReply(QnaVO qnaVO, HttpServletRequest request, HttpSession session) {
		System.out.println("컨트롤러 4");
		service.deleteQnaReply(qnaVO);
		
		// ========================================
		ModelAndView mv = service.getQnaList(request);
		// ========================================
		
		return "답글 삭제";
	}
	
	// qna 문의 삭제
	@RequestMapping(value = "/qnaDelete", method = RequestMethod.POST)
	public @ResponseBody String qnaDelete(QnaVO qnaVO, HttpServletRequest request, HttpSession session) {
		System.out.println("컨트롤러 5");
		service.deleteQna(qnaVO);
		
		// ========================================
		ModelAndView mv = service.getQnaList(request);
		// ========================================
		
		return "문의 삭제";
	}

	// qna 문의 수정
	@RequestMapping(value = "/qnaUpdate", method = RequestMethod.POST)
	public @ResponseBody String update(QnaVO qnaVO, HttpServletRequest request, HttpSession session) {
		System.out.println("컨트롤러 6");
		service.updateQna(qnaVO);
		
		// ========================================
		ModelAndView mv = service.getQnaList(request);
		// ========================================
		
		return "문의 수정";
	}
	
	
	// =============================
	
//	@RequestMapping(value = "/qna/qnaContent", method = RequestMethod.GET)
//	public String content(Model model,HttpServletRequest request) {
//		System.out.println("컨트롤러 11");
//		int num=Integer.parseInt(request.getParameter("num"));
//		QnaVO qna =service.getQna(num);
//		model.addAttribute("qna",qna);
//		return "/qna/qnaContent";
//	}
	
	
//	@RequestMapping(value = "/qna/qnaUpdatePro", method = RequestMethod.GET)
//	public String updatePro(QnaVO qnaVO,HttpSession session,Model model,HttpServletRequest request) {
//		System.out.println("컨트롤러 13");
//		String name = request.getParameter("name");
//		String member = (String)session.getAttribute("member");
//		
//		int num = Integer.parseInt(request.getParameter("num"));
//		qnaVO.setQna_idx(num);
//		qnaVO.setMember_id(request.getParameter("name"));
//		qnaVO.setQna_subject(request.getParameter("subject"));
//		qnaVO.setQna_content(request.getParameter("content"));
//		
//		if(name.equals(member)) {
//			service.updateQna(qnaVO);
//			return "redirect:/qna/qnaList";
//		}else {
//			model.addAttribute("msg","수정 권한이 없습니다.");
//			return "/qna/msg";
//		}
//		
//	}

	
//	@RequestMapping(value = "/qna/qnaWrite", method = RequestMethod.GET)
//	public String qnaWrite(Model model, HttpSession session) {
//		System.out.println("컨트롤러 15");
//		session.setAttribute("id", "tester2");
//		String member_id = (String)session.getAttribute("id");
//		model.addAttribute(member_id);
//		return "/qna/qnaWriteForm";
//	}
	
	
	
//	@RequestMapping(value = "/qna/qnaReply", method = RequestMethod.GET)
//	public String qnaReply(QnaVO qnaVO, Model model) {
//		System.out.println("컨트롤러 16");
//		model.addAttribute(qnaVO);
//		return "/qna/qnaReplyForm";
//	}
	
	
} // =======================================================
