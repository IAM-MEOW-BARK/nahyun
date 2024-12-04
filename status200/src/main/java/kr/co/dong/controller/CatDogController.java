package kr.co.dong.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.dong.catdog.CartDTO;
import kr.co.dong.catdog.CatDogService;
import kr.co.dong.catdog.MemberDTO;
import kr.co.dong.catdog.MyDTO;
import kr.co.dong.catdog.OrderDTO;
import kr.co.dong.catdog.OrderDetailDTO;
import kr.co.dong.catdog.OrderItemDTO;
import kr.co.dong.catdog.OrderItemDetailDTO;
import kr.co.dong.catdog.ProductDTO;
import kr.co.dong.catdog.ReviewDTO;
import kr.co.dong.catdog.WishDTO;

@Controller
public class CatDogController {
	private static final Logger logger = LoggerFactory.getLogger(CatDogController.class);

	@Inject
	CatDogService catDogService;

	// 전체 상품 출력
	@GetMapping(value = "/home")
	public ModelAndView list(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		// 세션에서 사용자 ID 가져오기
		Map<String, Object> userMap = (Map<String, Object>) session.getAttribute("user");
		String user_id = userMap != null ? (String) userMap.get("user_id") : null;

		// 파라미터 맵 구성
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("user_id", user_id);

		// 카테고리별 상품 목록 조회
		param.put("product_category", 1);
		List<ProductDTO> list01 = catDogService.mainlist(param);
		param.put("product_category", 2);
		List<ProductDTO> list02 = catDogService.mainlist(param);
		param.put("product_category", 3);
		List<ProductDTO> list03 = catDogService.mainlist(param);
		param.put("product_category", 4);
		List<ProductDTO> list04 = catDogService.mainlist(param);
		param.put("product_category", 5);
		List<ProductDTO> list05 = catDogService.mainlist(param);

		// 뷰에 데이터 추가
		mav.addObject("list01", list01);
		mav.addObject("list02", list02);
		mav.addObject("list03", list03);
		mav.addObject("list04", list04);
		mav.addObject("list05", list05);

		mav.setViewName("home");
		return mav;
	}

	@PostMapping("/addWish")
	@ResponseBody
	public Map<String, String> addWish(@RequestParam("product_code") int productCode, HttpSession session) {
		Map<String, String> response = new HashMap<String, String>();
		Map<String, Object> userMap = (Map<String, Object>) session.getAttribute("user");
		String userId = userMap != null ? (String) userMap.get("user_id") : null;

		if (userId == null) {
			response.put("message", "로그인 후 이용해주세요.");
			return response;
		}

		try {
			catDogService.addWish(userId, productCode);
			response.put("message", "찜하기가 추가되었습니다.");
		} catch (Exception e) {
			response.put("message", "찜하기 추가 중 오류가 발생했습니다.");
		}
		return response;
	}

	@PostMapping("/deleteWish")
	@ResponseBody
	public Map<String, String> deleteWish(@RequestParam("product_code") int productCode, HttpSession session) {
		Map<String, String> response = new HashMap<String, String>();
		Map<String, Object> userMap = (Map<String, Object>) session.getAttribute("user");
		String userId = userMap != null ? (String) userMap.get("user_id") : null;

		if (userId == null) {
			response.put("message", "로그인 후 이용해주세요.");
			return response;
		}

		try {
			WishDTO wishDTO = new WishDTO();
			wishDTO.setUser_id(userId);
			wishDTO.setProduct_code(productCode);
			catDogService.deleteWish(wishDTO);
			response.put("message", "찜하기가 삭제되었습니다.");
		} catch (Exception e) {
			response.put("message", "찜하기 삭제 중 오류가 발생했습니다.");
		}
		return response;
	}

	@GetMapping(value = "/catdog-term")
	public String catdogTerm() {
		return "catdog-term";
	}

	@GetMapping(value = "/catdog-add-product-admin")
	public String catdogAddProductAdmin() {
		return "catdog-add-product-admin";
	}

	@GetMapping(value = "/catdog-product-list-admin")
	public String catdogProductListAdmin() {
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
		logger.info("아이디 :" + map.get("user_ID"));
		logger.info("이름 :" + map.get("name"));
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
				System.out.println("로그인 완료" + user);
				return "redirect:/home";
			} else {
				logger.warn("알 수 없는 USER_AUTH 값: " + userAuth);
				return "redirect:/catdog-login";
			}
		}
	}

	// 로그아웃
	@GetMapping(value = "/catdog-logout")
	public String logout(HttpSession session, RedirectAttributes rttr) {
		session.invalidate(); // 세션에 저장되어 있는 정보 삭제
		rttr.addFlashAttribute("msg", "로그아웃 성공"); // 1회성 저장
		return "redirect:/";
	}

	@GetMapping(value = "/catdog-add-user-admin")
	public String catdogAddUserAdmin() {
		return "catdog-add-user-admin";
	}

	@GetMapping(value = "/catdog-find-id")
	public String catdogFindId() {
		return "catdog-find-id";
	}

	@GetMapping(value = "/catdog-find-pw")
	public String catdogFindPw() {
		return "catdog-find-pw";
	}

	@GetMapping(value = "/catdog-main")
	public String catDogMain() {
		return "catdog-main";
	}

	@GetMapping(value = "/catdog-payment")
	public String catDogPayment() {
		return "catdog-payment";
	}

	// 일반 유저 회원가입
	@PostMapping(value = "/catdog-signup")
	public String signup(MemberDTO member, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		request.setCharacterEncoding("utf-8");

		int r = catDogService.create(member);

		return "redirect:/";
	}

	// 관리자 회원가입
	@PostMapping(value = "/catdog-add-user-admin")
	public String adminSignup(MemberDTO member, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		request.setCharacterEncoding("utf-8");

		int r = catDogService.create(member);

		return "redirect:/catdog-user-list-admin";
	}

	@GetMapping("/mypage")
	public String mypage(HttpSession session, Model model) throws Exception {
		Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
		if (user == null) {
			return "redirect:/catdog-login";
		}

		model.addAttribute("user_name", user.get("name"));
		model.addAttribute("user_id", user.get("user_id"));

		List<MyDTO> myOrders = catDogService.getMyOrders((String) user.get("user_id"));
		model.addAttribute("myOrders", myOrders);

		System.out.println(myOrders);

		return "mypage";
	}

	@GetMapping("/detailOrder")
	public String detailOrder(@RequestParam("order_code") String order_code, Model model) throws Exception {
		System.out.println("전달 받은 order_code = " + order_code);

		OrderDetailDTO orderDetail = catDogService.getOrderDetail(order_code); // orderDetail에 order_code 전달
		System.out.println(orderDetail);

		model.addAttribute("orderDetail", orderDetail); // jsp 사용할 데이터

		List<OrderItemDetailDTO> orderItemDetail = catDogService.getOrderItemDetail(order_code);
		System.out.println(orderItemDetail);
		model.addAttribute("orderItemDetail", orderItemDetail);

		int totalCost = catDogService.getTotalCost(order_code);
		model.addAttribute("totalCost", totalCost);

		return "detailOrder"; // 상세 페이지
	}

	@GetMapping("/totalOrder")
	public String totalOrder(HttpSession session, Model model) throws Exception {
		Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
		if (user == null) {
			return "redirect:/catdog-login";
		}
		model.addAttribute("user_name", user.get("name"));
		model.addAttribute("user_id", user.get("user_id"));

		List<MyDTO> myOrders = catDogService.getMyOrders((String) user.get("user_id"));
		model.addAttribute("myOrders", myOrders);

		System.out.println(myOrders);
		return "totalOrder";
	}

	@GetMapping("/totalWish")
	public String totalWish() {
		return "wish";
	}

//	@GetMapping("/cart")
//	public String cart(@RequestParam("user_id") String user_id, HttpSession session, Model model) throws Exception {
//		Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
//		if (user == null) {
//			return "redirect:/catdog-login";
//		}
//		model.addAttribute("user_name", user.get("name"));
//		model.addAttribute("user_id", user.get("user_id"));
//
//		List<CartDTO> cartInfo = catDogService.getCartInfo(user_id);
//		model.addAttribute("cartInfo", cartInfo);
//		System.out.println("cartInfo = " + cartInfo);
//
//		return "cart";
//	}

	@GetMapping("/cart")
	public String cart(HttpSession session, Model model) throws Exception {
		Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
		if (user == null) {
			return "redirect:/catdog-login";
		}
		String userId = (String) user.get("user_id");
		model.addAttribute("user_name", user.get("name"));
		model.addAttribute("user_id", userId);

		List<CartDTO> cartInfo = catDogService.getCartInfo(userId);
		model.addAttribute("cartInfo", cartInfo);
		System.out.println("cartInfo = " + cartInfo);
		session.setAttribute("cartInfo", cartInfo); // post할 세션

		return "cart";
	}

	@PostMapping("/cart")
	public String processOrder(HttpSession session, HttpServletRequest request, RedirectAttributes rttr, Model model)
			throws Exception {
		Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
		if (user == null) {
			return "redirect:/catdog-login";
		}
		String userId = (String) user.get("user_id");
		model.addAttribute("user_name", user.get("name"));
		model.addAttribute("user_id", userId);

		OrderDTO orderDTO = new OrderDTO();

		orderDTO.setUser_id_fk(userId);
		orderDTO.setPayment_status(0);
		String orderCode = catDogService.addOrder(orderDTO);
		orderDTO.setOrder_code(orderCode);

		List<CartDTO> cartItems = catDogService.getCartInfo(userId);

		List<OrderItemDTO> orderItems = new ArrayList<>();
		for (CartDTO cartItem : cartItems) {
			OrderItemDTO orderItem = new OrderItemDTO();
			orderItem.setOrder_code(orderCode);
			orderItem.setProduct_code(cartItem.getProduct_code());
			orderItem.setProduct_name(cartItem.getProduct_name());
			orderItem.setThumbnail_img(cartItem.getThumbnail_img());
			orderItem.setProduct_price(cartItem.getProduct_price());
			orderItem.setCart_quantity(cartItem.getCart_quantity());
			orderItem.setOrder_quantity(cartItem.getCart_quantity());
			orderItem.setTotal_price(cartItem.getCart_quantity() * cartItem.getProduct_price());
			orderItems.add(orderItem);
		}
		catDogService.addOrderItems(orderItems);

		model.addAttribute("orderDTO", orderDTO);
		model.addAttribute("orderItems", orderItems);

		System.out.println("~~~~~~~~ orderDTO ~~~~~~~ = " + orderDTO);
		System.out.println("~~~~~~~~ orderItems ~~~~~~~ = " + orderItems);

		OrderDetailDTO orderInfo = catDogService.getOrderDetail(orderCode);
		System.out.println("~~~~~~~~ orderInfo ~~~~~~~ = " + orderInfo);
		model.addAttribute("orderInfo", orderInfo);

		int totalCost = catDogService.getTotalCost(orderCode);
		model.addAttribute("totalCost", totalCost);

		System.out.println("  💛💛💛💛💛💛💛💛💛💛 orderDTO: " + orderDTO);
		System.out.println("  💛💛💛💛💛💛💛💛💛💛 OrderItems: " + orderItems);
		System.out.println("  💛💛💛💛💛💛💛💛💛💛 totalCost: " + totalCost);

		return "/catdog-payment";
	}

	@PostMapping("/cart/update")
	public String updateCartQuantity(CartDTO cartDTO) throws Exception {

		System.out.println("업데이트 아직인겨 = " + cartDTO);
		System.out.println("업데이트 아직이여 = " + cartDTO.getCart_quantity());
		catDogService.updateCartQuantity(cartDTO);
		System.out.println("업데이트 눌럿슈 = " + cartDTO);
		System.out.println("업데이트 햇슈 = " + cartDTO.getCart_quantity());
		return "redirect:/cart";
	}

	@PostMapping("/cart/delete")
	@ResponseBody
	public String deleteCart(CartDTO cartDTO) throws Exception {
		System.out.println("뭐 가져온겨???????? " + cartDTO);
		int result = catDogService.deleteCart(cartDTO);
		return result > 0 ? "success" : "failure";
	}

	@PostMapping("/checkReview")
	public  ResponseEntity<Integer> checkReview(@RequestParam int product_code, @RequestParam String user_id) throws Exception {
		ReviewDTO reviewDTO = new ReviewDTO();
		reviewDTO.setProduct_code(product_code);
		reviewDTO.setUser_id(user_id);
		int myReview = catDogService.isReview(reviewDTO);
		return ResponseEntity.ok(myReview);
	}

	@GetMapping("/reviewPop")
	public String reviewPop(@RequestParam int product_code, @RequestParam String user_id, Model model) throws Exception {
	    ProductDTO product = catDogService.getProductByCode(product_code);

	    // 모델에 데이터 추가
	    model.addAttribute("product_name", product.getProduct_name());
	    model.addAttribute("product_code", product_code);
	    model.addAttribute("user_id", user_id);
	    
		return "reviewPop";
	}

	@GetMapping("/updateProfile")
	public String updateProfile() {
		return "updateProfile";
	}

	@GetMapping("/deleteUser")
	public String deleteUser() {
		return "deleteUser";
	}

}