package kr.co.dong.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.dong.catdog.CatDogService;
import kr.co.dong.catdog.MemberDTO;
import kr.co.dong.catdog.ProductDTO;
import kr.co.dong.catdog.WishDTO;

@Controller
public class CatDogController {
	private static final Logger logger = LoggerFactory.getLogger(CatDogController.class);
		
	@Inject
	CatDogService catDogService;  
	
	// 전체 상품 출력
	@GetMapping(value = "/home")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		List<ProductDTO> list01 = catDogService.list(1);
		mav.addObject("list01", list01);
		List<ProductDTO> list02 = catDogService.list(2);
		mav.addObject("list02", list02);
		List<ProductDTO> list03 = catDogService.list(3);
		mav.addObject("list03", list03);
		List<ProductDTO> list04 = catDogService.list(4);
		mav.addObject("list04", list04);
		List<ProductDTO> list05 = catDogService.list(5);
		mav.addObject("list05", list05);
		mav.setViewName("home");
		return mav;
	}
	
	@GetMapping(value="/catdog-term")
	public String catdogTerm(){
		return "catdog-term";
	}
	
	@GetMapping(value="/catdog-add-product-admin")
	public String catdogAddProductAdmin(){
		return "catdog-add-product-admin";
	}
	
	@GetMapping(value="/catdog-product-list-admin")
	public String catdogProductListAdmin(){
		return "catdog-product-list-admin";
	}
	
	// 로그인
	
	@GetMapping(value = "/catdog-login")
	public String login() {
		logger.info("login view 이동");
		return "catdog-login";
	}
	
	@PostMapping(value = "/catdog-login")
	public String login(@RequestParam Map<String, Object> map, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		request.setCharacterEncoding("UTF-8");

		Map user = catDogService.login(map);

		if (user == null) {
			logger.info("실패");
			return "redirect:catdog-login"; // prefix suffix 이용해서 이동
		} else {
			logger.info("성공");
			session.setAttribute("user", user);

			Integer userAuth = (Integer) user.get("user_auth");

			if (userAuth == 1) {
				logger.info("관리자 계정으로 로그인");
				return "redirect:/catdog-user-list-admin";
			} else if (userAuth == 0) {
				logger.info("일반 사용자 계정으로 로그인");
				return "redirect:/home";
			} else {
				logger.warn("알 수 없는 USER_AUTH 값: " + userAuth);
				return "redirect:/catdog-login";
			}
		}
	}	
		
	// 로그아웃
	   @GetMapping(value="/catdog-logout")
	   public String logout(HttpSession session, RedirectAttributes rttr) {
	      session.invalidate(); // 세션에 저장되어 있는 정보 삭제
	      rttr.addFlashAttribute("msg", "로그아웃 성공"); // 1회성 저장
	      return "redirect:/";
	   }
	
	@GetMapping(value="/catdog-add-user-admin")
	public String catdogAddUserAdmin(){
		return "catdog-add-user-admin";
	}
	
	@GetMapping(value="/catdog-find-id")
	public String catdogFindId(){
		return "catdog-find-id";
	}
	
	@GetMapping(value="/catdog-find-pw")
	public String catdogFindPw(){
		return "catdog-find-pw";
	}
	
	@GetMapping(value="/catdog-main")
	public String catDogMain(){
		return "catdog-main";
	}
	
	@GetMapping(value="/catdog-payment")
	public String catDogPayment(){
		return "catdog-payment";
	}
	
	// 일반 유저 회원가입
	@PostMapping(value="/catdog-signup")
	public String signup(MemberDTO member, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		int r = catDogService.create(member);
		
		return "redirect:/";
	}
	
	// 관리자 회원가입
	@PostMapping(value="/catdog-add-user-admin")
	public String adminSignup(MemberDTO member, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		int r = catDogService.create(member);
		
		return "redirect:/catdog-user-list-admin";
	}
	
	// 페이지 이동
	
	@GetMapping("/mypage")
	public String mypage() {
		return "mypage";
	}
	
	@GetMapping("/totalOrder")
	public String totalOrder() {
		return "totalOrder";
	}

	@GetMapping("/detailOrder")
	public String detailOrder() {
		return "detailOrder";
	}
	
	@GetMapping("/totalWish")
	public String totalWish() {
		return "wish";
	}
	
	@GetMapping("/cart")
	public String cart() {
		return "cart";
	}
	
	@GetMapping("/reviewPop")
	public String reviewPop() {
		return "reviewPop";
	}
	
	@GetMapping("/cartPop")
	public String cartPop() {
		return "cartPop";
	}
	
	@GetMapping("/updateProfile")
	public String updateProfile() {
		return "updateProfile";
	}
	
	@GetMapping("/deleteUser")
	public String deleteUser() {
		return "deleteUser";
	}
	
	@GetMapping("/deleteWish")
	public String deleteWish(@RequestParam("wishDTO")WishDTO wishDTO, RedirectAttributes rttr) throws Exception {
		
		int r = catDogService.deleteWish(wishDTO);
		
		if (r>0) {
			rttr.addFlashAttribute("msg", "글 삭제 성공.");
			return "redirect:totalWish";
		}
		
		return "redirect: totalWish";
		
	}
	
}