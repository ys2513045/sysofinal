package com.itwillbs.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.itwillbs.dao.ChatDAO;
import com.itwillbs.domain.ItemVO;
import com.itwillbs.domain.MessageDTO;
import com.itwillbs.service.ItemService;
import com.itwillbs.util.DateUtil;
import com.itwillbs.util.TimeGap;

@Controller
public class ChatController {
	@Inject
	private ChatDAO dao;

	@RequestMapping(value = "/chatList", method = RequestMethod.GET)
	public ModelAndView chatList(ModelAndView mv, HttpSession session) {

		if (session.getAttribute("id") != null) {
			String send_nick = (String) session.getAttribute("id");
			MessageDTO dto = new MessageDTO();
			dto.setSend_nick(send_nick);
			
			
			List<MessageDTO> chatList = dao.getChatList(dto);
			TimeGap.messageDateFormat(chatList);
			mv.addObject("chatList", chatList);
			mv.setViewName("./chat/chatList");
			return mv;

		} else {
			mv.addObject("msg", "로그인 후 이용하실수 있습니다.");
			mv.setViewName("./product/msg_auth");
			return mv;
		}

	}

	@RequestMapping(value = "/chatting", method = RequestMethod.GET)
	public ModelAndView chatting(@RequestParam String item_idx, ModelAndView mv, HttpSession session) {
		if (session.getAttribute("id") != null) {
		
		String recv_nick = dao.getNickname(Integer.parseInt(item_idx));
		String send_nick = (String) session.getAttribute("id");
		MessageDTO msg = new MessageDTO();
		msg.setSend_nick(send_nick);
		msg.setRecv_nick(recv_nick);
		
		
		List<MessageDTO> msgList = dao.getMessage(msg);
		TimeGap.messageDateFormat(msgList);


		System.out.println("msgList :" + msgList);
		if (msgList.isEmpty()) {
			int room = (int)(Math.random() * 10000000) + 1;
			msg.setRoom(room);
			msg.setContent(send_nick + "님이 대화를 시작하셨습니다.");
			dao.insertRoom(msg);
			msgList = dao.getMessage(msg);
			mv.addObject("msgList", msgList);

		} else {
			mv.addObject("msgList", msgList);

		}

		mv.setViewName("./chat/chatting");

		return mv;
		
		}else {
			mv.addObject("msg", "로그인 후 이용하실수 있습니다.");
			mv.setViewName("./product/msg_auth");
			return mv;
		}
	}

	@RequestMapping(value = "/chattingFromList", method = RequestMethod.GET)
	public ModelAndView chatting(@RequestParam int room, ModelAndView mv) {

		List<MessageDTO> msgList = dao.getMessage(room);

		mv.addObject("msgList", msgList);
		mv.setViewName("./chat/chatting-2");

		return mv;

	}

	@RequestMapping(value = "/insertMsg", method = RequestMethod.POST)
	public @ResponseBody MessageDTO insertMsg(@RequestBody MessageDTO dto) {

		dao.insertMessage(dto);
//		dao.insertAlarm(dto);
		MessageDTO msgList = dao.getMessageOne(dto.getSend_nick());

		return msgList;
	}

}
