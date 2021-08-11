package com.itwillbs.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itwillbs.domain.AlarmVO;
import com.itwillbs.domain.ItemVO;
import com.itwillbs.domain.MemberVO;
import com.itwillbs.domain.OrderItemVO;
import com.itwillbs.domain.PaymentVO;
import com.itwillbs.service.AlarmService;
import com.itwillbs.service.ItemService;
import com.itwillbs.service.MemberService;
import com.itwillbs.service.PaymentService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

/**
 * Handles requests for the application home page.
 */
@Controller
//@SessionAttributes("id")
public class PaymentController {

	@Inject
	private MemberService memberService;

	@Inject
	private ItemService itemService;

	@Inject
	private PaymentService paymentService;
	
	@Inject
	private AlarmService alarmService;

	private final String ACCESS_TOKEN_URL = "https://api.iamport.kr/users/getToken";
	private final String IMP_KEY = "3213919851287396";
	private final String IMP_SECRET = "2b5660328a36c4cf1551ede247b14faab9e9da9a82f175740e7e185de0933d612089a56ef3523ddd";
	private final String PAYMENT_INFO_URL = "https://api.iamport.kr/payments/";

	// 아임포트 엑세스 토큰 가져오기(아임포트서버호출)
	public String getAccessToken() throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();

		Map<String, Object> reqData = new HashMap<String, Object>();
		reqData.put("imp_key", IMP_KEY);
		reqData.put("imp_secret", IMP_SECRET);
		String jsonReqData = om.writeValueAsString(reqData);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<String> param = new HttpEntity<>(jsonReqData, headers);

		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> result = restTemplate.postForObject(ACCESS_TOKEN_URL, param, Map.class);
		String accessToken = (String) ((Map<String, Object>) result.get("response")).get("access_token");
		return accessToken;
	}

	// imp_uid로 아임포트 결제호출건 조회하기(아임포트서버호출)
	public Map<String, Object> getImpInfo(String imp_uid, String accessToken) {
		ObjectMapper om = new ObjectMapper();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.add("Authorization", "Bearer " + accessToken);
		HttpEntity<String> param = new HttpEntity<>(headers);

		RestTemplate restTemplate = new RestTemplate();

		Map<String, Object> result = restTemplate.postForObject(PAYMENT_INFO_URL + imp_uid, param, Map.class);

		return (Map<String, Object>) result.get("response");
	}

	// http://127.0.0.1:8080/syso/paymentComplete/my_0001
	@ResponseBody
	@RequestMapping(value = "/paymentComplete/{imp_uid}", method = RequestMethod.GET)
	public Map<String, Object> paymentByImpUid(Model model, Locale locale, HttpSession session,
			@PathVariable(value = "imp_uid") String imp_uid) throws IamportResponseException, IOException {
		Map<String, Object> resp = new HashMap<String, Object>();

		String iamPortAccessToken = getAccessToken();// 아임포트서버 호출을 위한 엑세스토큰 발행(가져오기)

		Map<String, Object> impInfo = getImpInfo(imp_uid, iamPortAccessToken);// imp_uid에 해당하는 결제정보 가져오기

//		if (((String) impInfo.get("status")).equals("paid")) {// 아임포트 결제 결과가 성공이면..
//
//			String merchant_uid = (String) impInfo.get("merchant_uid");
//
//			// 1. merchant_uid를 이용해 orderItem 테이블에서 orderItemIndex 가져오는 서비스 구현
//			// 2. item테이블에서 1번의 orderItemIndex에 해당하는 상품가격 가져오기.
//			int item_price = 10000;
//			if ((Integer) impInfo.get("amount") == item_price) {
//				resp.put("ok", true);
//			} else {
//				resp.put("ok", false);
//			}
//
//		} else {
//			resp.put("ok", false);
//		}
		
		resp.put("ok", true);

		return resp;
	}

//	@RequestMapping(value = "/order/payment", method = RequestMethod.GET)
//	public String payment(MemberVO vo, HttpSession session, Model model) {
//
//		String id = (String) session.getAttribute("id");
//
//		vo = memberService.getMember(id);
//
//		model.addAttribute("memVO", vo);
//
//		System.out.println("payment 매핑" + session.getAttribute("id"));
//
//		return "/payment";
//	}

//	@RequestMapping(value = "/point/chargeComplete", method = RequestMethod.GET)
//	public @ResponseBody String chargeComplete(Long amount) {
//		System.out.println(amount);
//
//		PaymentVO pay_vo = new PaymentVO();
//		pay_vo.setPayment_idx(0)
//		
//		int id = UserService.getIdFromAuth();
//
//
//		return "/point/chargeComplete";
//	}

	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String order(HttpSession session, Model model, HttpServletRequest request) throws Exception {
//		1. 세션 값가져오기
//		String id = (String) session.getAttribute("id");
////	2. 세션값에 해당하는 member정보를 조회
//		MemberVO vo = memberService.getMember(id);
////	3. Model vo, vo 데이터 담아 가기
//		model.addAttribute("vo", vo);
		// 1,2,3 한 번에 처리
		model.addAttribute("vo", memberService.getMember((String) session.getAttribute("id")));

		// item_idx 값에 맞는 itemVO List로 들고오기
		int item_idx = Integer.parseInt(request.getParameter("item_idx"));

		List<ItemVO> olist = itemService.getItemDetail(item_idx);
		model.addAttribute("olist", olist);

		return "/order/order";
	}

	   @RequestMapping(value = "/orderpro", method = RequestMethod.POST)
	   public String orderpro(HttpServletRequest request, HttpSession session, ItemVO itemVO, AlarmVO alarmVO) throws Exception {

	      // <----------- 주문 작성 시 회원 정보 수정 -------------->
	      String id = (String) session.getAttribute("id");
	      
	      MemberVO vo = memberService.getMember(id);
	      
	      vo.setMember_name((request.getParameter("member_name")));
	      vo.setMember_zipcode((request.getParameter("member_zipcode")));
	      vo.setMember_address((request.getParameter("member_address")));
	      vo.setMember_addressDetail((request.getParameter("member_addressDetail")));
	      vo.setMember_phone((request.getParameter("member_phone")));
	      memberService.updateMember(vo);
	      
	      // >-------------------------------------------------<

	      // <---------------- 주문 작성 시 oderItem table 삽입 ------------------->
	      // OrderItem_idx 생성하기 -> 날짜를 포함해서 구별하기 쉽게하려고 UUID 안씀 
	      Calendar cal = Calendar.getInstance();
	      int m = Integer.parseInt(new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1));
	      int md = Integer.parseInt(m + new DecimalFormat("00").format(cal.get(Calendar.DATE)));
	      int subNum = 0;

	      for (int i = 1; i <= 10; i++) {
	         subNum += ((int) (Math.random() * 99999)+1);
	      } 

	      int orderItem_idx = md + subNum;
	      
	      // >------------------------------------------------------------------<

	      // <---------- 구매 후 item_state 1로 변경 ------------->
	      itemVO.setItem_state(1);
	      itemService.updateItemState(itemVO);
	      // >-------------------------------------------------<
	            
	      // <-------- form에서 저장받은 값을 db의 orderItem table에 저장 ------------->
	      OrderItemVO oi_vo = new OrderItemVO();
	      oi_vo.setOrderItem_idx(orderItem_idx);
	      oi_vo.setItem_idx(Integer.parseInt(request.getParameter("item_idx")));
	      oi_vo.setMember_id(id);
	      
	      boolean item_isDeliveryFree = itemVO.isItem_isDeliveryFree();
	      	      
	      oi_vo.setOrderItem_isOffline(item_isDeliveryFree);
	      oi_vo.setOrderItem_state(1);
	      // date는 PaymentServiceImpl에서 작업
	      
	      // ---------------- 아임포트 결제 위한 merchant_uid 생성-----------------
	      String merchant_uid = "_muid_" + subNum;
	      oi_vo.setOrderItem_merchantID(Integer.parseInt(request.getParameter("item_idx")) + merchant_uid);
	      // ------------------------------------------------------------------

	      paymentService.insertOrder(oi_vo);

	      // >-------------------------------------------------------------------<

	      // <----------- 주문 작성 시 payment table 삽입 ------------>
	      // 직거래 : 주문작성 페이지 -> 아임포트 X -> orderItem,payment 테이블 삽입 후 order_complete 페이지 이동
	      // 온라인 결제 : 주문작성 페이지 -> 아임포트 O -> orderItem,payment 테이블 삽입 후 order_complete 페이지
	      // 이동

	      // payment_idx를 위한 랜덤값 생성
	      int subNum2 = 0;
	      for (int i = 1; i <= 10; i++) {
	         subNum2 += ((int) (Math.random() * 999999)+1);
	      }
	      int payment_idx = md + subNum2;

	      // form에서 저장받은 payment 값을 db의 payment table에 저장
	      PaymentVO p_vo = new PaymentVO();
	      p_vo.setPayment_idx(payment_idx);
	      p_vo.setOrderitem_idx(orderItem_idx);
	      p_vo.setPayment_price(Integer.parseInt(request.getParameter("payment_price")));
	      
	      
	      // date는 PaymentServiceImpl에서 작업
	      paymentService.insertPayment(p_vo);
	      // >-----------------------------------------------------<

	      // <------------ 포인트 사용한 만큼 차감 --------------------->
	      int have_Point = vo.getMember_point();
	      
	      // use_Point에 아무것도 넣지 않았을 때 0으로 입력되어 member_point 계산
	      String use_Point = request.getParameter("use_Point");
	      if(use_Point == null || use_Point.trim().equals("")){
	    	  use_Point = "0";
	      }
  
	      int member_point = have_Point - Integer.parseInt(use_Point);

	      vo.setMember_point(member_point);

	      memberService.usePoint(vo);
	      // >-----------------------------------------------------<

	      //<--------------- 결제 완료 시 판매자에게 알람 ----------------->
	      alarmVO.setOrderItem_idx(orderItem_idx);	
	      alarmService.insertOrderAlarm(alarmVO);
	      // >-----------------------------------------------------<


	      // /order/complete 가상주소 이동
	      return "redirect:/orderComplete?item_idx="+request.getParameter("item_idx");
	   }

	@RequestMapping(value = "/orderComplete", method = RequestMethod.GET)
    public String orderComplete(OrderItemVO order_vo, Model model,@RequestParam int item_idx) throws Exception {
		
       
        System.out.println(item_idx);
        model.addAttribute("item_idx", item_idx);

        return "/order/orderComplete";
    }

}
