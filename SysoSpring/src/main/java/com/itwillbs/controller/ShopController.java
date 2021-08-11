package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.domain.ItemVO;
import com.itwillbs.domain.MemberVO;
import com.itwillbs.domain.ReportDTO;
import com.itwillbs.domain.ShopReviewVO;
import com.itwillbs.domain.ShopVO;
import com.itwillbs.service.ItemService;
import com.itwillbs.service.LikesService;
import com.itwillbs.service.MemberService;
import com.itwillbs.service.ShopReviewService;
import com.itwillbs.service.ShopService;

@Controller
public class ShopController {
	@Inject
	private ItemService itemService;
	@Inject
	private MemberService memberService;
	@Inject
	private LikesService likesService;
	@Inject
	private ShopReviewService shopReviewService;
	@Inject
	private ShopService shopService;

	@RequestMapping(value = "/shop", method = RequestMethod.GET)
	public String main(@RequestParam String nickname, HttpSession session, Model model) {

		if (session.getAttribute("id") == null) {

			model.addAttribute("msg", "회원가입 후 이용하실수 있습니다.");
			return "./product/msg_auth";

		} else {

			MemberVO vo = new MemberVO();
			vo.setMember_id((String) session.getAttribute("id"));
			vo = itemService.getAuth(vo);
			if (vo != null) {
				System.out.println("authStatus : " + vo.getMember_authStatus());

				int authStatus = vo.getMember_authStatus();

				if (authStatus > 0) {

					String member_id = memberService.getId(nickname);
					// 상점 정보
					ShopVO shop = shopService.getShop(member_id);

					// 상품
					List<ItemVO> shopItemList = itemService.getMyItemList(member_id);

					shop.setShop_itemCount(shopItemList.size());

					// 찜
					List<ItemVO> likesList = likesService.memberLikesList(member_id);

					shop.setShop_likesCount(likesList.size());

					// 리뷰
					List<ShopReviewVO> shopRivewList = shopReviewService.getShopReviewList(member_id);
					shop.setShop_shopReviewCount(shopRivewList.size());

					model.addAttribute("nickname", nickname);
					model.addAttribute("shop", shop);
					model.addAttribute("shopItemList", shopItemList);
					model.addAttribute("likesList", likesList);
					model.addAttribute("shopRivewList", shopRivewList);

					return "./product/my_products";

				} else {
					model.addAttribute("msg", "접근 권한이 없습니다. 이메일 인증을 완료해주세요.");

					return "./product/msg_auth";
				}

			} else {
				model.addAttribute("msg", "접근 권한이 없습니다. 이메일 인증을 완료해주세요.");

				return "./product/msg_auth";
			}
		}

	}

	@RequestMapping(value = "/shop/updateIntro", method = RequestMethod.POST)
	@ResponseBody
	public void shopIntro(@RequestParam String member_id, @RequestParam String intro) {
		ShopVO shop = new ShopVO();
		shop.setMember_id(member_id);
		shop.setShop_intro(intro);

		shopService.updateShopIntro(shop);
	}

	@RequestMapping(value = "/report_shop", method = RequestMethod.POST)
	public String report_shop(@RequestParam String member_id, @RequestParam String report_content, Model model,
			HttpSession session) {
		ReportDTO dto = new ReportDTO();
		dto.setShop_member_id(member_id);
		dto.setMember_id((String) session.getAttribute("id"));
		dto.setReport_content(report_content);

		itemService.insertShopReport(dto);
		model.addAttribute("msg", "신고해주셔서 감사합니다 고객님의 신고는 모두가 즐겁게 이용할 수 있는 쾌적한 시소마켓을 만드는 데에 큰 도움이 됩니다.");
		return "./product/msg_auth";
	}

	@RequestMapping(value = "/report_shopReview", method = RequestMethod.POST)
	public String report_item(@RequestParam int shopReview_idx, @RequestParam String report_content, Model model,
			HttpSession session) {
		ReportDTO dto = new ReportDTO();
		dto.setShopReview_idx(shopReview_idx);
		dto.setMember_id((String) session.getAttribute("id"));
		dto.setReport_content(report_content);

		itemService.insertShopRivewReport(dto);
		model.addAttribute("msg", "신고해주셔서 감사합니다 고객님의 신고는 모두가 즐겁게 이용할 수 있는 쾌적한 시소마켓을 만드는 데에 큰 도움이 됩니다.");
		return "./product/msg_auth";
	}
	
	
	@RequestMapping(value = "/shopReview", method = RequestMethod.GET)
    public String shopReviewForm(@RequestParam int item_idx, HttpSession session, Model model) {

        String member_id = (String)session.getAttribute("id");
        ShopReviewVO shopReview = new ShopReviewVO();
        shopReview.setItem_idx(item_idx);
        shopReview.setShopReview_id(member_id);

        ShopReviewVO vo = shopReviewService.getShopRiview(shopReview);

        if(vo != null) {
            model.addAttribute("msg", "이미 작성된 상점리뷰 입니다!");
            return "./product/msg_auth";
        } 

        String item_subject = itemService.getItemSubject(item_idx);
        model.addAttribute("item_subject", item_subject);
        model.addAttribute("item_idx", item_idx);

        return "product/shopReviewForm";
    }

    @RequestMapping(value = "/shopReview/insert", method = RequestMethod.POST)
    public String shopReviewPro(ServletRequest request, HttpSession session, Model model) {


        String member_id = (String)session.getAttribute("id");
        int item_idx = Integer.parseInt(request.getParameter("item_idx"));
        int shopReview_star = Integer.parseInt(request.getParameter("shopReview_star"));
        String shopReview_content = request.getParameter("shopReview_content");

        ShopReviewVO shopReview = new ShopReviewVO();
        shopReview.setShopReview_star(shopReview_star);
        shopReview.setItem_idx(item_idx);
        shopReview.setShopReview_content(shopReview_content);
        shopReview.setShopReview_id(member_id);

        shopReviewService.insertShopReview(shopReview);


        return "redirect:/home";
    }
	
	
	

}
