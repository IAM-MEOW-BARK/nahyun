package kr.co.dong.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
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
import kr.co.dong.catdog.PaymentDTO;
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

		// 세션에서 사용자 정보 가져오기
		Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
		String user_id = null;

		if (user != null && user.get("user_id") != null) {
			user_id = (String) user.get("user_id");
		}

		// 파라미터 맵 구성
		Map<String, Object> param = new HashMap<>();
		if (user_id != null) {
			param.put("user_id", user_id);
		}

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

	// 결제 페이지 회원
	@GetMapping(value = "catdog-payment")
	public String paymentMember(@RequestParam("user_id") String user_id, @RequestParam("order_code") String order_code,
			Model model, HttpSession session) throws Exception {
		// 회원 정보 가져오기
		Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");

		if (user == null) {
			System.out.println("세션에 사용자가 없습니다.");
			return "redirect:/catdog-login";
		}

		// 회원 정보
		PaymentDTO pdto = catDogService.getMember((String) user.get("user_id"));
		model.addAttribute("paymentMember", pdto);

		System.out.println("Session user: " + session.getAttribute("user"));

		// order_code로 주문 정보 가져오기
		OrderDTO orderInfo = catDogService.getOrderInfo(order_code);

		model.addAttribute("orderInfo", orderInfo);
		System.out.println("orderInfo :::" + orderInfo);
		System.out.println("주문 코드:::: " + order_code);

		// 총 금액
		int totalPrice = catDogService.getTotalCost(order_code);
		model.addAttribute("totalPrice", totalPrice);

		return "catdog-payment"; // 뷰 이름 반환
	}

	// 결제
	@PostMapping("/processPayment")
	public String processPayment(@RequestParam("name") String name, @RequestParam("phone_num") String phone_num,
			@RequestParam("zipcode") String zipcode, @RequestParam("address") String address,
			@RequestParam("detailaddress") String detailaddress, HttpSession session, Model model) {
		Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");

		Object userIdObj = user.get("user_id");
		System.out.println("user_id 값: " + userIdObj);
		System.out.println("user_id 타입: " + (userIdObj != null ? userIdObj.getClass().getName() : "null"));

		String user_id = (String) user.get("user_id");
		if (user_id == null || user_id.isEmpty()) {
			System.out.println("에러1");
			return "redirect:/catdog-login";
		}

		// product_code를 데이터베이스에서 조회
		List<Integer> product_code = catDogService.getProductCodeByUserId(user_id);
		if (product_code == null) {
			model.addAttribute("errorMessage", "Product code를 찾을 수 없습니다.");
			System.out.println("에러2");
			return "catdog-payment";
		}

		try {
			catDogService.updateAddress(user_id, name, phone_num, zipcode, address, detailaddress);
			catDogService.updatePaymentStatus(user_id);
			catDogService.deleteOrderItems(user_id, product_code); // product_code 전달

			return "redirect:/";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "결제 처리 중 오류가 발생했습니다.");
			System.out.println("errorMessage" + "결제 처리 중 오류가 발생했습니다.");
			System.out.println("에러3");
			return "catdog-payment";
		}
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

		if (cartInfo.isEmpty()) {
			model.addAttribute("isCartEmpty", true);
		} else {
			model.addAttribute("isCartEmpty", false);
			model.addAttribute("cartInfo", cartInfo);
			System.out.println("cartInfo = " + cartInfo);
			session.setAttribute("cartInfo", cartInfo); // post할 세션
			model.addAttribute("cartCost", catDogService.getCartCost(userId));
		}
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
	public ResponseEntity<Integer> checkReview(@RequestParam int product_code, @RequestParam String user_id)
			throws Exception {
		ReviewDTO reviewDTO = new ReviewDTO();
		reviewDTO.setProduct_code(product_code);
		reviewDTO.setUser_id(user_id);
		int myReview = catDogService.isReview(reviewDTO);
		return ResponseEntity.ok(myReview);
	}

	@GetMapping("/reviewPop")
	public String reviewPop(@RequestParam int product_code, @RequestParam String user_id, Model model)
			throws Exception {
		ProductDTO product = catDogService.getProductByCode(product_code);

		System.out.println("product 가져오셈" + product);
		// 모델에 데이터 추가
		model.addAttribute("product_name", product.getProduct_name());
		model.addAttribute("product_code", product_code);
		model.addAttribute("user_id", user_id);
		model.addAttribute("thumbnail_img", product.getThumbnail_img());

		System.out.println("썸네일 이름" + product.getThumbnail_img());

		return "reviewPop";
	}

	@GetMapping("/checkPW")
	public String checkPW(Model model, HttpSession session) throws Exception {
		Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
		if (user == null) {
			return "redirect:/catdog-login";
		}
		model.addAttribute("userID", user.get("user_id"));
		return "checkPW";
	}

	@PostMapping("/checkPW")
	public String chekcPW(@RequestParam Map<String, Object> map, HttpServletRequest request, HttpSession session,
			Model model) throws Exception {
		request.setCharacterEncoding("UTF-8");

		System.out.println(map);

		Map user = catDogService.login(map);

		if (user == null) {
			logger.info("비밀번호가 틀립니다.");
			model.addAttribute("errorMessage", "비밀번호가 틀립니다.");
			model.addAttribute("user_id", map.get("user_id"));
			return "/checkPW";
		}
		logger.info("회원 조회 뿅");

		session.setAttribute("name", user.get("name"));
		session.setAttribute("user_id", user.get("user_id"));
		session.setAttribute("phone_num", user.get("phone_num"));
		session.setAttribute("zipcode", user.get("zipcode"));
		session.setAttribute("address", user.get("address"));
		session.setAttribute("detailaddress", user.get("detailaddress"));

		session.setAttribute("password", map.get("password"));
		
		System.out.println(user);

		return "redirect:/updateProfile";
	}

	@GetMapping("/updateProfile")
	public String updateProfile(HttpSession session, Model model) {
		// 세션에서 사용자 정보를 가져와 모델에 추가
		model.addAttribute("name", session.getAttribute("name"));
		model.addAttribute("user_id", session.getAttribute("user_id"));
		model.addAttribute("phone_num", session.getAttribute("phone_num"));
		model.addAttribute("zipcode", session.getAttribute("zipcode"));
		model.addAttribute("address", session.getAttribute("address"));
		model.addAttribute("detailaddress", session.getAttribute("detailaddress"));

		return "updateProfile"; // updateProfile.jsp 렌더링
	}

	@PostMapping("/updateProfile")
	public String updateProfile(@ModelAttribute MemberDTO memberDTO, HttpSession session, HttpServletRequest request,
			Model model, RedirectAttributes redirectAttributes) throws Exception {

		request.setCharacterEncoding("UTF-8");

		// 세션에서 현재 비밀번호 가져오기
		String currentPW = (String) session.getAttribute("password");
		
		System.out.println("지금 비번이 머꼬???????" + currentPW);

		// 새 비밀번호가 비어있는지 확인
		if (memberDTO.getPassword() == null || memberDTO.getPassword().isEmpty()) {
			// 새 비밀번호가 null 이거나 empty하다면
			System.out.println(memberDTO.getPassword());
			memberDTO.setPassword(currentPW);
		}

		model.addAttribute(memberDTO);
		System.out.println("===== 프로필 업데이트 할겨 ===== ");
		System.out.println(memberDTO);
		catDogService.updateProfile(memberDTO);
		System.out.println("===== 프로필 업데이트 된겨 ===== ");
		
		 // 플래시 메시지 추가
	    redirectAttributes.addFlashAttribute("successMessage", "회원 정보가 성공적으로 수정되었습니다.");

		
		return "redirect:/mypage";
	}

	@GetMapping("/deleteUser")
	public String deleteUser() {
		return "deleteUser";
	}

	@GetMapping("/getProductInfo")
	@ResponseBody
	public ProductDTO getProductInfo(@RequestParam int product_code) throws Exception {
		return catDogService.getProductByCode(product_code);
	}

	@PostMapping("/regReview")
	@ResponseBody
	public String regReview(@ModelAttribute ReviewDTO reviewDTO, @RequestParam("review_img") MultipartFile reviewImg)
			throws Exception {
		if (!reviewImg.isEmpty()) {
			String fileName = UUID.randomUUID() + "_" + reviewImg.getOriginalFilename();
			Path uploadPath = Paths.get("uploads/review-images");
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			reviewImg.transferTo(uploadPath.resolve(fileName).toFile());
			reviewDTO.setReview_img(fileName);
		}
		catDogService.regReview(reviewDTO);
		return "리뷰가 등록되었습니다.";
	}

}