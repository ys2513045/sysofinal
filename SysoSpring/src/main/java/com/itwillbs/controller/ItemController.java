package com.itwillbs.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.itwillbs.domain.CommentVO;
import com.itwillbs.domain.ItemVO;
import com.itwillbs.domain.MemberVO;
import com.itwillbs.domain.ReportDTO;
import com.itwillbs.domain.ShopReviewVO;
import com.itwillbs.domain.ShopVO;
import com.itwillbs.domain.likesVO;
import com.itwillbs.service.CommentService;
import com.itwillbs.service.ItemService;
import com.itwillbs.service.ShopReviewService;
import com.itwillbs.service.ShopService;
import com.itwillbs.util.TimeGap;

@Controller
public class ItemController {
	
	
	@Inject
	private ItemService service;
	
	@Inject
	private CommentService cService;
	
	@Inject
	private ShopService sService;
	
	@Inject
	private ShopReviewService reviewService;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	

	@RequestMapping(value = "/itemDetail", method = RequestMethod.GET)
	   public String itemDetail(@RequestParam int item_idx, Model model, HttpSession session, HttpServletRequest request,
	         HttpServletResponse response) throws Exception {
	      System.out.println("세션값 : " + session.getAttribute("id")); // 세션값 받아와짐
	      // 1. 조회수 (쿠키) 있는지 판별================
	      // 2. 없으면 생성, 있으면 무시(?)
	      String cookie_idx = Integer.toString(item_idx);

	      Cookie cookie = null;
	      Cookie[] cookies = null;

	     
	      cookies = request.getCookies();

	      boolean isContainRead = false;
	      if (cookies != null) {

	         for (int i = 0; i < cookies.length; i++) {

	            cookie = cookies[i];
	            System.out.println(cookie.getName());
	            System.out.println(cookie.getValue());
	            if (cookie.getValue().equals(cookie_idx)) {

	               isContainRead = true;

	            }

	         }

	      } else {

	         isContainRead = false;

	      }

	      if (isContainRead) {

	         System.out.println("해당하는 쿠키값이 있습니다.");

	      } else {

	         System.out.println("해당하는 쿠키값이 없습니다.");
	         service.increaseReadCount(item_idx); // 조회수 증가
	         // 쿠키값 지정
	         cookie = new Cookie("isRead" + cookie_idx, cookie_idx);
	         cookie.setMaxAge(60 * 60 * 24); // 24시간
	         response.addCookie(cookie);

	      }
	      // ==================================== 조회수 (쿠키) 끝

	      // item_idx에 맞는 item정보를 전부 가져옴(+ 서비스단에서 처리해놓음)
	      String member_id = (String) session.getAttribute("id");
	      List<ItemVO> list = service.getItemDetail(item_idx);
	      model.addAttribute("list", list);

	      // 해시태그 ===============================
	      List<String> hashtag_names = service.getTagNames(item_idx);
	      if (hashtag_names != null) {
	         model.addAttribute("tagNames", hashtag_names);

	      }
	      // =============================== 해시태그

	      // 찜기능 ===============================
	      // member_id 랑 item_idx 를 가져가서 있는지 조회
	      likesVO vo = new likesVO();
	     
	      vo.setItem_idx(item_idx);
	      vo.setMember_id(member_id);
	      likesVO vo2 = service.getLikesItem(vo);
	      int likesCount = service.getLikesCount(vo);

	      // Model 객체에 저장
	      if (vo2 != null) {
	         // 값이 있으면 == 찜이 눌러져 있으면
	         model.addAttribute("isLike", "like");
	         System.out.println("찜 눌려져있어요");
	      } else {
	         model.addAttribute("isLike", "notLike");
	         System.out.println("찜 안눌려져 있어요");
	      }

	      model.addAttribute("likesCount", likesCount);
	      // =============================== 찜기능 끝

	      // 댓글 List 들고오기 ==========================
	      CommentVO cvo = new CommentVO();

	      cvo.setItem_idx(item_idx);
	      List<CommentVO> cList = cService.getComment(cvo);

	      model.addAttribute("cList", cList);
	      // ===================================== 댓글 끝
	      
	      // 연관상품 item_img랑 item_idx 들고오기
	      // rList
	      int rNum = list.get(0).getCategory_idx(); // 보여줄 상품의 카테고리
	      ItemVO ivo = new ItemVO();
	      ivo.setCategory_idx(rNum);
	      ivo.setItem_idx(item_idx);
	      
	      List<ItemVO> rList = null;
	      rList = service.getRelatedItem(ivo);
	      
	      model.addAttribute("rList", rList);
	      
	      
	      // ====================================== 연관상품 끝
	      // 상점정보 ===================================
	      ItemVO Ivo = new ItemVO();
	      Ivo.setMember_id(list.get(0).getMember_id());
	      Ivo.setItem_idx(item_idx);
	      List<ShopVO> sList = sService.getShopInfo(Ivo);
	      model.addAttribute("sList", sList);
	      
	      // 상점에 있는 제품 중 랜덤으로 2개 뽑아 저장하기
	      List<ItemVO> shopItemPhotoList = service.getShopItemPhoto(Ivo);
	      
	      model.addAttribute("pList", shopItemPhotoList);
	      System.out.println(shopItemPhotoList.size() + "개 가져왔음");
	      // =================================== 상점정보 끝
	      // 상점리뷰 =====================================
	      List<ShopReviewVO> reviewList = null;
	      String shop_member_id = sList.get(0).getMember_id();
	      reviewList = reviewService.getPreReview(shop_member_id);
	      
	      model.addAttribute("reviewList", reviewList);
	      // ==================================== 상점리뷰 끝

	      return "./product/product_detail";
	   }
	   
	   // 찜버튼 클릭시 동작
	   // 찜이 안되어 있는 경우 item/changeToRed mapping
	   //
	   @RequestMapping(value = "/item/changeToRed", method = RequestMethod.POST)
	   public @ResponseBody int changeToRed(@RequestParam String member_id, int item_idx) throws Exception {

	      // likesVO에 값 저장
	      likesVO lVO = new likesVO();
	      lVO.setItem_idx(item_idx);
	      lVO.setMember_id(member_id);

	      service.changeToRed(lVO);


	      int likesCount = service.getLikesCount(lVO);
	      // 값을 리턴
	      return likesCount;
	   }

	   // 찜버튼 클릭시 동작
	   // 찜이 되어 있는 경우 item/changeToGray mapping
	   @RequestMapping(value = "/item/changeToGray", method = RequestMethod.POST)
	   public @ResponseBody int changeToGray(@RequestParam String member_id, int item_idx) throws Exception {

	      likesVO lvo = new likesVO();
	      
	      lvo.setItem_idx(item_idx);
	      lvo.setMember_id(member_id);

	      service.changeToGray(lvo);

//	      ItemVO ivo = new ItemVO();
//	      ivo.setItem_idx(item_idx);
	//
//	      service.decreaseLikesCount(ivo);

	      int likesCount = service.getLikesCount(lvo);

	      return likesCount;

	   }

	   // item/insertComment
	   @RequestMapping(value = "/item/insertComment", method = RequestMethod.POST)
	   public @ResponseBody List<CommentVO> insertComment(@RequestBody CommentVO cvo) throws Exception {
		  
		   
	      System.out.println("itemcontroller /item/insertComment");
	      System.out.println("item_idx : " + cvo.getMember_id());
	      System.out.println("member_id : " + cvo.getItem_idx());

	      List<CommentVO> cList = null;
	      
	    		  

	      cList = cService.insertComment(cvo);

	      return cList;
	   }
	   
	   @RequestMapping(value = "/report_item", method = RequestMethod.POST)
	   public String report_item(@RequestParam int item_idx,@RequestParam String report_content , Model model, HttpSession session) {
		  ReportDTO dto = new ReportDTO();
		  dto.setItem_idx(item_idx);
		  dto.setMember_id((String)session.getAttribute("id"));
		  dto.setReport_content(report_content);
		  
		  service.insertItemReport(dto);

				model.addAttribute("msg", "신고해주셔서 감사합니다 고객님의 신고는 모두가 즐겁게 이용할 수 있는 쾌적한 시소마켓을 만드는 데에 큰 도움이 됩니다.");
				return "./product/msg_auth";
			
	   }
	   
	   @RequestMapping(value = "/report_comment", method = RequestMethod.POST)
       public String report_comment(@RequestParam int comment_idx, @RequestParam String report_content, @RequestParam int item_idx, Model model, HttpSession session) {
          System.out.println("__________________ " + item_idx +"_________________-");
          System.out.println(comment_idx + ": comment_idx");
          System.out.println(report_content);
          
          ReportDTO rvo = new ReportDTO();
          rvo.setComment_idx(comment_idx);
          rvo.setMember_id((String)session.getAttribute("id"));
          rvo.setReport_content(report_content);
          
          service.insertCommentReport(rvo);
          
          model.addAttribute("msg", "신고해주셔서 감사합니다. 고객님의 신고는 모두가 즐겁게 이용할 수 있는 쾌적한 시소마켓을 만드는 데에 큰 도움이 됩니다.");
          model.addAttribute("item_idx", item_idx);
          return "./product/msg";
       }
	   
	   
	   


	@RequestMapping(value = "/sell", method = RequestMethod.GET)
	public String sell(HttpSession session, Model model, ModelAndView mv) throws Exception {

		if (session.getAttribute("id") == null) {

			model.addAttribute("msg", "회원가입 후 이용하실수 있습니다.");
			return "./product/msg_auth";
		
			
		} else {

			MemberVO vo = new MemberVO();
			ItemVO vo2 = new ItemVO();
			vo2.setMember_id((String)session.getAttribute("id"));
			vo.setMember_id((String) session.getAttribute("id"));
			vo = service.getAuth(vo);
			if (vo != null) {
				System.out.println("authStatus : " + vo.getMember_authStatus());
				
				int authStatus = vo.getMember_authStatus();

				if (authStatus > 0) {
					String member_id = (String) session.getAttribute("id");
					List<ItemVO> sellList = service.getSellList(vo2);
					List<ItemVO> buyList = service.getBuyList(vo2);
					List<ItemVO> list = service.myItemList(member_id);
					
					
					model.addAttribute("list",list);
					model.addAttribute("sellList", sellList);
					model.addAttribute("buyList", buyList);
					model.addAttribute("user",vo.getMember_id());
					model.addAttribute("nickName",vo.getMember_nickname());
					
					
					
					return "./product/write_form";
				} else {
					model.addAttribute("msg", "접근 권한이 없습니다. 이메일 인증을 완료해주세요.");
					
					return "./product/msg_auth";
				}

			} else {
				model.addAttribute("msg", "접근 권한이 없습니다. 이메일 인증을 완료해주세요.");

				return "./product/msg_auth";
			}
		}

	} //

//	@RequestMapping(value = "/myshop", method = RequestMethod.GET)
//	public String myshop(HttpSession session, Model model) throws Exception {
//
//		if (session.getAttribute("id") == null) {
//
//			model.addAttribute("msg", "회원가입 후 이용하실수 있습니다.");
//			return "./product/msg_auth";
//
//		} else {
//
//			MemberVO vo = new MemberVO();
//			vo.setMember_id((String) session.getAttribute("id"));
//			vo = service.getAuth(vo);
//			if (vo != null) {
//				System.out.println("authStatus : " + vo.getMember_authStatus());
//
//				int authStatus = vo.getMember_authStatus();
//				
//				if (authStatus > 0) {
//					// id, pass 불일치
//					// 메시지 뿌리고 뒤로이동
//					return "./product/my_products";
//					
//				} else {
//					model.addAttribute("msg", "접근 권한이 없습니다. 이메일 인증을 완료해주세요.");
//					
//					return "./product/msg_auth";
//				}
//
//			} else {
//				model.addAttribute("msg", "접근 권한이 없습니다. 이메일 인증을 완료해주세요.");
//
//				return "./product/msg_auth";
//			}
//		}
//
//	} //
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String selectItemList(@RequestParam String keyword, Model model) throws Exception {
		System.out.println("itemcontroller/ search");
		System.out.println("keyword : " + keyword);
		
		ItemVO vo = new ItemVO();
		vo.setItem_subject(keyword);
		List<ItemVO> list = service.getKeyword(keyword);
	
		ItemVO hashvo = new ItemVO();
		hashvo.setHashtag_name(keyword);
		List<ItemVO> list2 = service.getKeyword(keyword);
		TimeGap.setDateFormat(list);
		TimeGap.setDateFormat(list2);



		model.addAttribute("searchList", list);
		model.addAttribute("searchList", list2);
		model.addAttribute("keyword", keyword);
		return "./product/search";
		
	}
	
	@RequestMapping(value = "/itemCategory", method = RequestMethod.GET)
	public String Category(HttpServletRequest request, Model model) {
		List<ItemVO> categoryList = service.getItemCategory(request.getParameter("item_category"));
		TimeGap.setDateFormat(categoryList);
		model.addAttribute("searchList", categoryList);

		return "./product/search";
	}
	
	
	
	
//	@RequestMapping(value = "/item/insert", method = RequestMethod.GET)
//	public String insert(HttpServletRequest request) {
//		System.out.println("/item/insertForm");
//		
//		ItemVO itemVO = new ItemVO();
//		itemVO.setMember_id("tester1");
//		HttpSession session = request.getSession();
//		
//		
//		
//		return "/product/insertForm";
//	}
	
	@RequestMapping(value = "/item/insertPro", method = RequestMethod.POST)
	public String insertPro(HttpServletRequest request, MultipartFile item_img, HttpSession session) throws ParseException {
		System.out.println("/item/insertPro");
		
		UUID uid = UUID.randomUUID();
		String fileName = uid.toString()+"_"+item_img.getOriginalFilename();
		
		System.out.println("fileName = " + fileName);
		String member_id = (String) session.getAttribute("id");
		
		File target = new File(uploadPath, fileName);
		if (!new File(uploadPath).exists()) {
            new File(uploadPath).mkdirs();
        }
		try {
			FileCopyUtils.copy(item_img.getBytes(), target);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(fileName);
		
		ItemVO itemVO = new ItemVO();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		itemVO.setMember_id(member_id);
		itemVO.setCategory_idx(Integer.parseInt(request.getParameter("category_idx")));
		itemVO.setItem_subject(request.getParameter("item_subject"));
		itemVO.setItem_detail(request.getParameter("item_detail"));
		itemVO.setItem_img(fileName);
		itemVO.setItem_price(Integer.parseInt(request.getParameter("item_price")));
		itemVO.setItem_count(Integer.parseInt(request.getParameter("item_count")));
		itemVO.setItem_isUsed(Boolean.parseBoolean(request.getParameter("item_isUsed")));
		itemVO.setItem_isExchange(Boolean.parseBoolean(request.getParameter("item_isExchange")));
		itemVO.setItem_region(request.getParameter("item_region"));
		itemVO.setItem_isDirect(Boolean.parseBoolean(request.getParameter("item_isDirect")));
		itemVO.setItem_isDeliveryFree(Boolean.parseBoolean(request.getParameter("item_isDeliveryFree")));
		itemVO.setItem_state(0);
		
		itemVO.setItem_endDate(sdf.parse(request.getParameter("item_endDate")));
		itemVO.setSimple_endDate(Integer.parseInt(request.getParameter("simple_endDate")));
		
		
		itemVO.setHashtag_name(request.getParameter("hashtag_name"));
		
		String hash = request.getParameter("hashtag_name");
		
		String hashList[] = hash.split(" ");
		
		
		service.insertItem(itemVO,hashList);
		
		
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	   public String update(@RequestParam int item_idx, Model model) {
	      
	      ItemVO item = service.getUpdateItem(item_idx);
	      model.addAttribute("item", item);
	      
	      return "product/updateForm";
	   }
	
	
	   @RequestMapping(value = "/updatePro", method = RequestMethod.POST)
	   public String updatePro(MultipartFile item_img, HttpServletRequest request) throws IOException {
	      System.out.println("/item/updatePro");
	      ItemVO itemVO = new ItemVO();
	      itemVO.setItem_idx(Integer.parseInt(request.getParameter("item_idx")));
	      itemVO.setCategory_idx(Integer.parseInt(request.getParameter("item_category")));
	      itemVO.setItem_subject(request.getParameter("item_subject"));
	      itemVO.setItem_detail(request.getParameter("item_detail"));
	      itemVO.setItem_price(Integer.parseInt(request.getParameter("item_price")));
	      itemVO.setItem_count(Integer.parseInt(request.getParameter("item_count")));
	      itemVO.setItem_isUsed(Boolean.parseBoolean(request.getParameter("item_isUsed")));
	      itemVO.setItem_isExchange(Boolean.parseBoolean(request.getParameter("item_isExchange")));
	      itemVO.setItem_region(request.getParameter("item_region"));
	      itemVO.setItem_isDirect(Boolean.parseBoolean(request.getParameter("item_isDirect")));
	      itemVO.setItem_isDeliveryFree(Boolean.parseBoolean(request.getParameter("item_isDeliveryFree")));
	      itemVO.setItem_state(0);
	      System.out.println(itemVO.getItem_price());
	      service.updatePro(itemVO);
	      
	      itemVO.setHashtag_name(request.getParameter("hashtag_name"));
	      service.insertHash(itemVO);
	         
	      return "redirect:/sell";

	   }
	
	@RequestMapping(value = "/deletePro", method = RequestMethod.GET)
	public String deletePro(@RequestParam int item_idx) {
		System.out.println("/item/deletePro");
		
		service.deletePro(item_idx);
		
		
		
		return "redirect:/sell#tabs-2";
	}
	
	
	
	@RequestMapping(value = "/timeUpdate", method = RequestMethod.GET)
	public String up(@RequestParam int item_idx, ItemVO vo, HttpServletRequest request) {
		System.out.println("/item/timeUpdate");
		
		
		vo.setItem_date(new Timestamp(System.currentTimeMillis())); 
		vo.setItem_idx(Integer.parseInt(request.getParameter("item_idx")));
		
		service.timeUpdate(vo);
		
		return "redirect:/sell#tabs-2";
	}
	
    @RequestMapping(value = "/item/deleteComment", method = RequestMethod.POST)
    public @ResponseBody List<CommentVO> deleteComment(@RequestBody CommentVO cvo) throws Exception {

       System.out.println("itemcontroller /item/deleteComment");
       System.out.println("댓글쓴 아이디이면서 로그인되어있는 아이디 : " + cvo.getMember_id());
       System.out.println("item_idx : " + cvo.getItem_idx());
       System.out.println("comment_idx : " + cvo.getComment_idx());
       List<CommentVO> cList = null;
       
       cList = cService.deleteComment(cvo);

       return cList;
    }
    
    @RequestMapping(value = "/changeItemState", method = RequestMethod.POST)
    public @ResponseBody int deleteComment(@RequestBody ItemVO vo) {
    
    	System.out.println("상태변경");
    	service.changeItemState(vo);
    
    	return 1;
    	
    	
    	
    }
	
	
	

}
