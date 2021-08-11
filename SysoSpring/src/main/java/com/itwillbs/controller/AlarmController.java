package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.itwillbs.domain.AlarmVO;
import com.itwillbs.service.AlarmService;

@Controller
public class AlarmController {
	
	@Inject
	AlarmService service;
	
	@RequestMapping(value = "/alarm/getAlarm", method = RequestMethod.POST)
	public @ResponseBody List<AlarmVO> getAlarm(@RequestParam String member_id, AlarmVO alarmVO) {
		System.out.println("들어옴");
//		List<AlarmVO> alarmList=service.getAlarm(member_id);
		List<AlarmVO> alarmList = service.getAlarm2(member_id);
		return alarmList;
	}
	
	
	
//	 =============== 확인용 ===============
//	@RequestMapping(value = "/order_complete", method = RequestMethod.GET)
//	public ModelAndView order_complete(@RequestParam String member_id, int orderitem_idx, ModelAndView mv) {
//		
//		System.out.println("확인1 : " + member_id);
//		System.out.println("확인2 : " + orderitem_idx);
//		mv.setViewName("redirect:/order_complete2");
//		return mv;
//	}
	
		
}
