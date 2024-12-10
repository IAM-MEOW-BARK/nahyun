package kr.co.dong.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.dong.catdog.CartDTO;
import kr.co.dong.catdog.CatDogService;
import kr.co.dong.catdog.FaqDTO;
import kr.co.dong.catdog.MemberDTO;
import kr.co.dong.catdog.MyDTO;
import kr.co.dong.catdog.NoticeDTO;
import kr.co.dong.catdog.OrderDTO;
import kr.co.dong.catdog.OrderDetailDTO;
import kr.co.dong.catdog.OrderItemDTO;
import kr.co.dong.catdog.OrderItemDetailDTO;
import kr.co.dong.catdog.ProductDTO;
import kr.co.dong.catdog.QnaDTO;
import kr.co.dong.catdog.ReviewDTO;

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

		List<ProductDTO> list01 = catDogService.mainlist(1);
		List<ProductDTO> list02 = catDogService.mainlist(2);
		List<ProductDTO> list03 = catDogService.mainlist(3);
		List<ProductDTO> list04 = catDogService.mainlist(4);
		List<ProductDTO> list05 = catDogService.mainlist(5);

		// 뷰에 데이터 추가
		mav.addObject("list01", list01);
		mav.addObject("list02", list02);
		mav.addObject("list03", list03);
		mav.addObject("list04", list04);
		mav.addObject("list05", list05);

		mav.setViewName("home");
		return mav;
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

//	@GetMapping("/totalOrder")
//	public String totalOrder(HttpSession session, Model model,
//	        @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
//	        @RequestParam(value = "pageListNum", defaultValue = "1") int pageListNum) throws Exception {
//
//	    Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
//	    if (user == null) {
//	        return "catdog-login";
//	    }
//
//	    model.addAttribute("user_name", user.get("name"));
//	    model.addAttribute("user_id", user.get("user_id"));
//
//	    int pageSize = 10; // 한 페이지당 게시글 수
//	    int pageListSize = 5; // 한 번에 표시할 페이지 수
//
//	    // 전체 게시글 수
//	    int totalList = 50;
//	    int totalPage = (int) Math.ceil((double) totalList / pageSize);
//
//	    // 현재 페이지에서 가져올 데이터의 시작 인덱스 계산
//	    int start = (pageNum - 1) * pageSize;
//
//	    // 현재 페이지 번호 목록의 시작과 끝
//	    int startPage = (pageListNum - 1) * pageListSize + 1;
//	    int endPage = Math.min(startPage + pageListSize - 1, totalPage);
//
//	    List<MyDTO> myOrders = catDogService.getMyOrders((String) user.get("user_id"));
//	    model.addAttribute("myOrders", myOrders);
//	    model.addAttribute("totalPage", totalPage); // 전체 페이지 수
//	    model.addAttribute("currentPage", pageNum); // 현재 페이지 번호
//	    model.addAttribute("pageListNum", pageListNum); // 1~10, 11~20 ...
//	    model.addAttribute("startPage", startPage); // 페이지 nav 시작 번호
//	    model.addAttribute("endPage", endPage); // 페이지 nav 끝 번호
//
//	    System.out.println(myOrders);
//	    return "totalOrder";
//	}

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
	public String processOrder(HttpSession session, RedirectAttributes rttr) throws Exception {
	    Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
	    if (user == null) {
	        return "redirect:/catdog-login";
	    }

	    String userId = (String) user.get("user_id");

	    // Order 생성
	    OrderDTO orderDTO = new OrderDTO();
	    orderDTO.setUser_id_fk(userId);
	    orderDTO.setPayment_status(0);

	    String orderCode = catDogService.addOrder(orderDTO);
	    orderDTO.setOrder_code(orderCode);

	    // Cart 정보를 기반으로 OrderItems 생성 및 저장
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

	    // Redirect 시 사용할 orderCode 설정
	    return "redirect:/catdog-payment?orderCode=" + orderCode;
	}
	
	
	// 결제 페이지 회원
	@GetMapping("catdog-payment")
	public String paymentMember(@RequestParam("orderCode") String orderCode, 
	                            Model model, HttpSession session) throws Exception {
	    // 회원 정보 확인
	    Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
	    if (user == null) {
	        return "redirect:/catdog-login";
	    }

	    // 회원 정보 모델에 추가
	    String userId = (String) user.get("user_id");
	    MemberDTO memberInfo = catDogService.getMember(userId);
	    model.addAttribute("paymentMember", memberInfo);

	    // 주문 정보 및 주문 아이템 조회
	    OrderDetailDTO orderInfo = catDogService.getOrderDetail(orderCode);
	    List<OrderItemDTO> orderItems = catDogService.getOrderInfo(orderCode);
	    int totalCost = catDogService.getTotalCost(orderCode);

	    // 모델에 데이터 추가
	    model.addAttribute("orderInfo", orderInfo);
	    model.addAttribute("orderItems", orderItems);
	    model.addAttribute("totalCost", totalCost);

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
		model.addAttribute("user_id", user.get("user_id"));
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

	// 지혜 언니

	// 상품 상세페이지
	@RequestMapping(value = "/productDetail", method = RequestMethod.GET)
	public String productDetail(@RequestParam("product_code") int product_code, Model model) {

		// 배송 예정일
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);

		if (hour < 15) {
			calendar.add(Calendar.DATE, 1);
		} else {
			calendar.add(Calendar.DATE, 2);
		}

		Date delivery = calendar.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM월 dd일(E)"); // 날짜와 요일 형식

		String deliveryDate = dateFormat.format(delivery);

		// 1. 상품 상세 정보
		ProductDTO productDTO = catDogService.productDetail(product_code);

		// 2. 리뷰 리스트 (최신 5개)
		List<ReviewDTO> getReview = catDogService.getReview(product_code);
		// 3. Q&A 리스트 (최신 5개)
		List<QnaDTO> getQna = catDogService.getQna(product_code);
		// 4. 상품 코드에 해당하는 게시글 개수 가져오기
		int product_reviewTotal = catDogService.product_reviewTotal(product_code);
		int product_qnaTotal = catDogService.product_qnaTotal(product_code);

		model.addAttribute("productDetail", productDTO);
		model.addAttribute("getReview", getReview);
		model.addAttribute("getQna", getQna);
		model.addAttribute("product_reviewTotal", product_reviewTotal);
		model.addAttribute("product_qnaTotal", product_qnaTotal);
		model.addAttribute("deliveryDate", deliveryDate);
		return "/productDetail";
	}

	// 카테고리 리스트

	@RequestMapping(value = "/categoryList", method = RequestMethod.GET)
	public String categoryList(

			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageListNum", defaultValue = "1") int pageListNum,
			@RequestParam(value = "product_category") int product_category, Model model) {

		int pageSize = 12;
		int pageListSize = 10;

		int totalPost = catDogService.categoryTotalPost(product_category);
		int totalPage = (int) Math.ceil((double) totalPost / pageSize);
		int start = (pageNum - 1) * pageSize;
		int startPage = (pageListNum - 1) * pageListSize + 1;
		int endPage = Math.min(startPage + pageListSize - 1, totalPage);

		System.out.println("totalPost: " + totalPost);
		System.out.println("totalPage: " + totalPage);
		System.out.println("start: " + start);
		System.out.println("pageSize: " + pageSize);

		List<ProductDTO> categoryList = catDogService.categoryList(start, pageSize, product_category);

		System.out.println("categoryList in Controller: " + categoryList);

		model.addAttribute("totalPage", totalPage);
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("pageListNum", pageListNum);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("productCategory", product_category);
		model.addAttribute("categoryList", categoryList);

		return "categoryList";
	}

	// 공지사항 리스트
	@RequestMapping(value = "noticeList", method = RequestMethod.GET)
	public ModelAndView noticeList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageListNum", defaultValue = "1") int pageListNum, HttpSession session) {

		int pageSize = 10; // 한 페이지당 게시글 수
		int pageListSize = 10; // 한 번에 표시할 페이지 수

		// 세션에서 사용자 정보 가져오기
		Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
		int user_auth = (user != null && user.get("user_auth") != null) ? (int) user.get("user_auth") : 0;

		// 전체 게시글 수
		int totalPost = catDogService.noticeTotalPost();
		int totalPage = (int) Math.ceil((double) totalPost / pageSize);

		// 현재 페이지에서 가져올 데이터의 시작 인덱스 계산
		int start = (pageNum - 1) * pageSize;

		// 현재 페이지 번호 목록의 시작과 끝
		int startPage = (pageListNum - 1) * pageListSize + 1;
		int endPage = Math.min(startPage + pageListSize - 1, totalPage);

		ModelAndView mav = new ModelAndView();
		mav.addObject("noticeList", catDogService.noticeList(start, pageSize)); // 게시글 목록
		mav.addObject("totalPage", totalPage); // 전체 페이지 수
		mav.addObject("currentPage", pageNum); // 현재 페이지 번호
		mav.addObject("pageListNum", pageListNum); // 1~10, 11~20 ...
		mav.addObject("startPage", startPage); // 페이지 네비게이션 시작
		mav.addObject("endPage", endPage); // 페이지 네비게이션 끝
		mav.addObject("user_auth", user_auth); // 사용자 권한
		mav.setViewName("noticeList");
		return mav;
	}

	// 리뷰 리스트
	@RequestMapping(value = "reviewList", method = RequestMethod.GET)
	public ModelAndView reviewList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageListNum", defaultValue = "1") int pageListNum) {

		int pageSize = 10; // 한 페이지당 게시글 수
		int pageListSize = 10; // 한 번에 표시할 페이지 수

		// 전체 게시글 수
		int totalPost = catDogService.reviewTotalPost();
		int totalPage = (int) Math.ceil((double) totalPost / pageSize);

		// 현재 페이지에서 가져올 데이터의 시작 인덱스 계산
		int start = (pageNum - 1) * pageSize;

		// 현재 페이지 번호 목록의 시작과 끝
		int startPage = (pageListNum - 1) * pageListSize + 1;
		int endPage = Math.min(startPage + pageListSize - 1, totalPage);

		ModelAndView mav = new ModelAndView();
		mav.addObject("reviewList", catDogService.reviewList(start, pageSize)); // 게시글 목록
		mav.addObject("totalPage", totalPage); // 전체 페이지 수
		mav.addObject("currentPage", pageNum); // 현재 페이지 번호
		mav.addObject("pageListNum", pageListNum);
		mav.addObject("startPage", startPage); // 페이지 네비게이션 시작
		mav.addObject("endPage", endPage); // 페이지 네비게이션 끝
		mav.setViewName("reviewList");
		return mav;
	}

	// 리뷰 상세조회
	@RequestMapping(value = "/reviewDetail", method = RequestMethod.GET)
	public String reviewDetail(@RequestParam("review_no") int review_no, Model model) {
		ReviewDTO reviewDTO = catDogService.reviewDetail(review_no);
		catDogService.reviewUpdateReadCnt(review_no);
		model.addAttribute("reviewDetail", reviewDTO);

		return "/reviewDetail";
	}

	// Q&A 리스트
	@RequestMapping(value = "qnaList", method = RequestMethod.GET)
	public ModelAndView qnaList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageListNum", defaultValue = "1") int pageListNum, HttpSession session) {

		int pageSize = 10; // 한 페이지당 게시글 수
		int pageListSize = 10; // 한 번에 표시할 페이지 수

		// 세션에서 사용자 정보 가져오기
		Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
		int user_auth = (user != null && user.get("user_auth") != null) ? (int) user.get("user_auth") : 0;

		// 전체 게시글 수
		int totalPost = catDogService.qnaTotalPost();
		int totalPage = (int) Math.ceil((double) totalPost / pageSize);

		// 현재 페이지에서 가져올 데이터의 시작 인덱스 계산
		int start = (pageNum - 1) * pageSize;

		// 현재 페이지 번호 목록의 시작과 끝
		int startPage = (pageListNum - 1) * pageListSize + 1;
		int endPage = Math.min(startPage + pageListSize - 1, totalPage);

		ModelAndView mav = new ModelAndView();
		mav.addObject("qnaList", catDogService.qnaList(start, pageSize)); // 게시글 목록
		mav.addObject("totalPage", totalPage); // 전체 페이지 수
		mav.addObject("currentPage", pageNum); // 현재 페이지 번호
		mav.addObject("pageListNum", pageListNum);
		mav.addObject("startPage", startPage); // 페이지 네비게이션 시작
		mav.addObject("endPage", endPage); // 페이지 네비게이션 끝
		mav.addObject("user_auth", user_auth);
		mav.setViewName("qnaList");
		return mav;
	}

	// FAQ 리스트
	@RequestMapping(value = "faqList", method = RequestMethod.GET)
	public ModelAndView faqList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageListNum", defaultValue = "1") int pageListNum,
			@RequestParam(value = "faq_division", required = false) Integer faq_division, HttpSession session) {
		int pageSize = 10; // 한 페이지당 게시글 수
		int pageListSize = 10; // 한 번에 표시할 페이지 수

		// 세션에서 사용자 정보 가져오기
		Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
		int user_auth = (user != null && user.get("user_auth") != null) ? (int) user.get("user_auth") : 0;

		int totalPost;
		List<FaqDTO> faqList;

		if (faq_division == null) {
			// 전체 게시글 수 및 리스트 가져오기
			totalPost = catDogService.faqTotalPost();
			faqList = catDogService.faqList((pageNum - 1) * pageSize, pageSize);
		} else {
			// 특정 구분에 해당하는 게시글 수 및 리스트 가져오기
			totalPost = catDogService.faqTotalPostDivision(faq_division);
			faqList = catDogService.faqListDivision((pageNum - 1) * pageSize, pageSize, faq_division);
		}

		// 총 페이지 계산
		int totalPage = (int) Math.ceil((double) totalPost / pageSize);

		// 현재 페이지 번호 목록의 시작과 끝
		int startPage = (pageListNum - 1) * pageListSize + 1;
		int endPage = Math.min(startPage + pageListSize - 1, totalPage);

		ModelAndView mav = new ModelAndView();
		mav.addObject("faqList", faqList); // 게시글 목록
		mav.addObject("totalPage", totalPage); // 전체 페이지 수
		mav.addObject("currentPage", pageNum); // 현재 페이지 번호
		mav.addObject("pageListNum", pageListNum); // 현재 페이지 리스트 번호
		mav.addObject("startPage", startPage); // 페이지 네비게이션 시작
		mav.addObject("endPage", endPage); // 페이지 네비게이션 끝
		mav.addObject("selectedDivision", faq_division);
		mav.addObject("user_auth", user_auth);
		mav.setViewName("faqList");
		return mav;
	}

	// FAQ 수정
	@RequestMapping(value = "/faqUpdate", method = RequestMethod.GET)
	public String faqUpdate(@RequestParam("faq_no") int faq_no, Model model) {
		// FAQ 번호에 해당하는 데이터를 가져옴
		FaqDTO faqDTO = catDogService.faqDetail(faq_no);
		model.addAttribute("faqUpdate", faqDTO);
		return "/faqUpdate"; // 수정 폼으로 이동
	}

	@RequestMapping(value = "/faqUpdate", method = RequestMethod.POST)
	public String faqUpdate(FaqDTO faqDTO, RedirectAttributes attr, HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("UTF-8");
		// 수정 실행
		int r = catDogService.faqUpdate(faqDTO);

		if (r > 0) {
			attr.addFlashAttribute("msg", "FAQ가 성공적으로 수정되었습니다.");
		} else {
			attr.addFlashAttribute("msg", "FAQ 수정에 실패하였습니다.");
		}
		return "redirect:/faqList"; // 수정 후 FAQ 리스트로 이동
	}

	// FAQ 삭제
	@RequestMapping(value = "/faqDelete", method = RequestMethod.POST)
	public String faqDelete(@RequestParam("faq_no") int faq_no, RedirectAttributes redirectAttributes) {
		// 삭제 실행
		int result = catDogService.faqDelete(faq_no);
		if (result > 0) {
			redirectAttributes.addFlashAttribute("message", "FAQ가 성공적으로 삭제되었습니다.");
		} else {
			redirectAttributes.addFlashAttribute("message", "FAQ 삭제에 실패하였습니다.");
		}
		return "redirect:/faqList"; // 삭제 후 FAQ 리스트로 이동
	}

	// 공지사항 상세조회
	@RequestMapping(value = "/noticeDetail", method = RequestMethod.GET)
	public String noticeDetail(@RequestParam("notice_no") int notice_no, Model model, HttpSession session) {
		NoticeDTO noticeDTO = catDogService.noticeDetail(notice_no);
		catDogService.noticeUpdateReadCnt(notice_no);
		model.addAttribute("noticeDetail", noticeDTO);

		// 세션에서 사용자 권한 가져오기
		Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
		int user_auth = (user != null && user.get("user_auth") != null) ? (int) user.get("user_auth") : 0;

		model.addAttribute("noticeDetail", noticeDTO);
		model.addAttribute("user_auth", user_auth);

		return "/noticeDetail";
	}

	@RequestMapping(value = "backToList", method = RequestMethod.GET)
	public String backToList(@RequestParam("notice_no") int notice_no, Model model) {
		int totalPost = catDogService.noticeTotalPost();
		int no = totalPost - notice_no + 1;
		int pageSize = 10; // 해당 게시판을 호출할 때 사용한 pageSize
		int pageListSize = 10; // 해당 게시판을 호출할 때 사용한 pageListSize
		int pageNUM = (no / pageSize) + 1;
		int pageListNUM = (no / (pageSize * pageListSize)) + 1;

		return "redirect:noticeList?pageNUM=" + pageNUM + "&pageListNUM=" + pageListNUM;
	}

	@RequestMapping(value = "/qnaDetail", method = RequestMethod.GET)
	public String qnaDetail(@RequestParam(value = "qna_no") int qna_no, HttpSession session, Model model,
			RedirectAttributes rttr) {

		// 사용자 권한 확인
		Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
		int user_auth = (user != null && user.get("user_auth") != null) ? (int) user.get("user_auth") : 0;

		// Q&A 데이터 가져오기
		QnaDTO qnaDTO = catDogService.qnaDetail(qna_no);
		if (qnaDTO == null) {
			rttr.addFlashAttribute("error", "Q&A 정보를 찾을 수 없습니다.");
			return "redirect:/qnaList";
		}

		// 비밀글 접근 권한 확인
		if (qnaDTO.getQna_secret() == 1) {
			if (user_auth != 1) { // 관리자가 아닌 경우
				Boolean hasAccess = (Boolean) session.getAttribute("qnaAccess_" + qna_no);
				if (hasAccess == null || !hasAccess) {
					rttr.addFlashAttribute("error", "비밀글에 접근 권한이 없습니다.");
					return "redirect:/qnaList";
				}
			}
		}

		// Q&A 상세 데이터 전달
		model.addAttribute("qnaDetail", qnaDTO);
		model.addAttribute("user_auth", user_auth);
		return "/qnaDetail";
	}

	@RequestMapping(value = "/validatePassword", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> validatePassword(@RequestBody Map<String, Object> payload, HttpSession session) {
		int qna_no = Integer.parseInt(payload.get("qna_no").toString());
		String qna_pwd = payload.get("qna_pwd").toString();

		QnaDTO qnaDTO = catDogService.qnaDetail(qna_no); // qna_pwd ?
		Map<String, Object> response = new HashMap<>();

		if (qnaDTO != null && qna_pwd.equals(qnaDTO.getQna_pwd())) {
			session.setAttribute("qnaAccess_" + qna_no, true); // 세션에 접근 권한 저장
			response.put("success", true);
		} else {
			response.put("success", false);
		}

		return response;
	}

	// 공지사항 작성
	@RequestMapping(value = "/noticeRegister", method = RequestMethod.GET)
	public String noticeRegister() {
		return "/noticeRegister";
	}

	@RequestMapping(value = "/noticeRegister", method = RequestMethod.POST)
	public String noticeRegister(NoticeDTO noticeDTO, HttpServletRequest request, RedirectAttributes rttr)
			throws Exception {
		request.setCharacterEncoding("UTF-8");

		int r = catDogService.noticeRegister(noticeDTO);

		if (r > 0) {
			rttr.addFlashAttribute("msg", "추가에 성공하였습니다."); // 세션저장
		}
		return "redirect:/noticeList";
	}

	// 공지사항 수정
	@RequestMapping(value = "/noticeUpdate", method = RequestMethod.GET)
	public String noticeUpdate(@RequestParam("notice_no") int notice_no, Model model) {
		NoticeDTO noticeDTO = catDogService.noticeDetail(notice_no);
		model.addAttribute("noticeUpdate", noticeDTO);

		return "/noticeUpdate";
	}

	@RequestMapping(value = "/noticeUpdate", method = RequestMethod.POST)
	public String noticeUpdate(NoticeDTO noticeDTO, RedirectAttributes attr, HttpServletRequest request)
			throws Exception {
		request.setCharacterEncoding("UTF-8");

		int r = catDogService.noticeUpdate(noticeDTO);

		if (r > 0) {
			attr.addFlashAttribute("msg", "수정에 성공 하였습니다.");
			return "redirect:noticeList";
		}
		return "redirect:/noticeUpdate?notice_no=" + noticeDTO.getNotice_no();
	}

	// 공지사항 삭제
	@RequestMapping(value = "/noticeDelete", method = RequestMethod.GET)
	public String noticeDelete(@RequestParam("notice_no") int notice_no, RedirectAttributes rttr) {
		int r = catDogService.noticeDelete(notice_no);

		if (r > 0) {
			rttr.addFlashAttribute("msg", "글삭제에 성공하였습니다.");
			return "redirect:noticeList";
		}
		return "redirect:/noticeDetail?notice_no=" + notice_no;
	}

	// Q&A 작성
	@RequestMapping(value = "/qnaRegister", method = RequestMethod.GET)
	public String qnaRegister(HttpSession session, RedirectAttributes rttr) {
		// 세션에서 사용자 정보 가져오기
		Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
		if (user == null) {
			rttr.addFlashAttribute("error", "로그인이 필요합니다.");
			return "redirect:/catdog-login"; // 로그인 페이지로 리다이렉트
		}

		return "/qnaRegister"; // 로그인된 사용자라면 작성 페이지로 이동
	}

	@RequestMapping(value = "/qnaRegister", method = RequestMethod.POST)
	public String qnaRegister(QnaDTO qnaDTO, HttpServletRequest request, HttpSession session, RedirectAttributes rttr)
			throws Exception {

		request.setCharacterEncoding("UTF-8"); // 요청 인코딩 설정

		// 세션에서 사용자 정보 가져오기
		Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
		if (user == null) {
			rttr.addFlashAttribute("error", "로그인이 필요합니다.");
			return "redirect:/catdog-login"; // 로그인 페이지로 리다이렉트
		}

		// user_id 설정
		String userId = (String) user.get("user_id");
		qnaDTO.setUser_id(userId);

		// 비밀글 여부에 따른 비밀번호 처리
		if (qnaDTO.getQna_secret() == 0) {
			qnaDTO.setQna_pwd(null); // 공개글인 경우 비밀번호 제거
		}

		// Q&A 등록 처리
		int result = catDogService.qnaRegister(qnaDTO);

		// 등록 성공 여부 확인 및 메시지 설정
		if (result > 0) {
			rttr.addFlashAttribute("msg", "문의글이 성공적으로 등록되었습니다.");
		} else {
			rttr.addFlashAttribute("msg", "문의글 등록에 실패하였습니다.");
		}

		// Q&A 리스트 페이지로 리다이렉트
		return "redirect:/qnaList";
	}

	// Q&A 수정
	@RequestMapping(value = "/qnaUpdate", method = RequestMethod.GET)
	public String qnaUpdate(@RequestParam("qna_no") int qna_no, Model model) {
		QnaDTO qnaDTO = catDogService.qnaDetail(qna_no);

		model.addAttribute("qnaUpdate", qnaDTO);
		return "/qnaUpdate";
	}

	@RequestMapping(value = "/qnaUpdate", method = RequestMethod.POST)
	public String qnaUpdate(QnaDTO qnaDTO, RedirectAttributes attr, HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("UTF-8");

		int r = catDogService.qnaUpdate(qnaDTO);

		if (r > 0) {
			attr.addFlashAttribute("msg", "수정에 성공 하였습니다.");
			return "redirect:/qnaDetail?qna_no=" + qnaDTO.getQna_no();
		}
		return "redirect:/qnaUpdate?qna_no=" + qnaDTO.getQna_no();
	}

	// Q&A 삭제
	@RequestMapping(value = "/qnaDelete", method = RequestMethod.GET)
	public String qnaDelete(@RequestParam("qna_no") int qna_no, RedirectAttributes rttr) {
		int r = catDogService.qnaDelete(qna_no);

		if (r > 0) {
			rttr.addFlashAttribute("msg", "글삭제에 성공하였습니다.");
			return "redirect:qnaList";
		}
		return "redirect:/qnaDetail?qna_no=" + qna_no;
	}

	@RequestMapping(value = "/qnaReplyDetail", method = RequestMethod.GET)
	public String qnaReplyDetail(@RequestParam(value = "qna_no") int qna_no, HttpSession session, Model model,
			RedirectAttributes rttr) {

		// 사용자 권한 확인
		Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
		int user_auth = (user != null && user.get("user_auth") != null) ? (int) user.get("user_auth") : 0;

		// Q&A 데이터 가져오기
		QnaDTO qnaDTO = catDogService.qnaReplyDetail(qna_no);
		if (qnaDTO == null) {
			rttr.addFlashAttribute("error", "Q&A 정보를 찾을 수 없습니다.");
			return "redirect:/qnaList";
		}

		// 비밀글 접근 권한 확인
		if (qnaDTO.getQna_secret() == 1) {
			if (user_auth != 1) { // 관리자가 아닌 경우
				Boolean hasAccess = (Boolean) session.getAttribute("qnaAccess_" + qna_no);
				if (hasAccess == null || !hasAccess) {
					rttr.addFlashAttribute("error", "비밀글에 접근 권한이 없습니다.");
					return "redirect:/qnaList";
				}
			}
		}

		// Q&A 상세 데이터 전달
		model.addAttribute("qnaDetail", qnaDTO);
		// model.addAttribute("user_auth", user_auth);
		return "/qnaReplyDetail";
	}

	// Q&A 답변 작성
	@RequestMapping(value = "/qnaReply", method = RequestMethod.GET)
	public String qnaReply(@RequestParam("qna_no") int qna_no, Model model) {
		QnaDTO qnaDTO = catDogService.qnaDetail(qna_no);

		model.addAttribute("qnaReply", qnaDTO);
		return "/qnaReply";
	}

	@RequestMapping(value = "/qnaReply", method = RequestMethod.POST)
	public String qnaReply(QnaDTO qnaDTO, RedirectAttributes attr, HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("UTF-8");

		int r = catDogService.qnaReply(qnaDTO);

		if (r > 0) {
			attr.addFlashAttribute("msg", "답변이 작성되었습니다.");
			return "redirect:/qnaDetail?qna_no=" + qnaDTO.getQna_no();
		}
		return "redirect:/qnaReply?qna_no=" + qnaDTO.getQna_no();
	}

	// Q&A 답변 수정
	@RequestMapping(value = "/qnaReplyUpdate", method = RequestMethod.GET)
	public String qnaReplyUpdate(@RequestParam("qna_no") int qna_no, Model model) {
		QnaDTO qnaDTO = catDogService.qnaDetail(qna_no);

		model.addAttribute("qnaReplyUpdate", qnaDTO);
		return "/qnaReplyUpdate";
	}

	@RequestMapping(value = "/qnaReplyUpdate", method = RequestMethod.POST)
	public String qnaReplyUpdate(QnaDTO qnaDTO, RedirectAttributes attr, HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("UTF-8");

		int r = catDogService.qnaReplyUpdate(qnaDTO);

		if (r > 0) {
			attr.addFlashAttribute("msg", "수정에 성공 하였습니다.");
			return "redirect:/qnaDetail?qna_no=" + qnaDTO.getQna_no();
		}
		return "redirect:/qnaUpdate?qna_no=" + qnaDTO.getQna_no();
	}

	// Q&A 답변 삭제
	@RequestMapping(value = "/qnaReplyDelete", method = RequestMethod.GET)
	public String qnaReplyClear(@RequestParam("qna_no") int qna_no, RedirectAttributes redirectAttributes) {
		try {
			catDogService.qnaReplyDelete(qna_no);
			redirectAttributes.addFlashAttribute("msg", "Q&A 답변이 삭제되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", "답변 삭제 중 오류가 발생했습니다.");
		}
		return "redirect:/qnaList";
	}

	@PostMapping(value = "/addCart")
	public String addToCart(@ModelAttribute CartDTO cartDTO, HttpSession session,
			RedirectAttributes redirectAttributes) {
		Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");

		// 로그인 여부 확인
		if (user == null || user.get("user_id") == null) {
			redirectAttributes.addFlashAttribute("error", "로그인이 필요합니다.");
			return "redirect:/catdog-login";
		}

		// 세션에서 사용자 ID 가져오기
		String userId = (String) user.get("user_id");
		cartDTO.setUser_id(userId); // CartDTO에 사용자 ID 설정

		try {
			// 장바구니 추가
			catDogService.addCart(cartDTO);
			redirectAttributes.addFlashAttribute("message", "장바구니에 추가되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", "장바구니 추가 중 오류가 발생했습니다.");
		}
		return "redirect:/cart";
	}

	// -------------------------------------------------------------------------------------
//	@PostMapping("/regReview")
//	@ResponseBody
//	public String regReview(HttpSession session, Model model)
//			throws Exception {
//		Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
//		model.addAttribute("user_name", user.get("name"));
//		model.addAttribute("user_id", user.get("user_id"));		
//		
//		ReviewDTO reviewDTO -
//		
//		System.out.println("ReviewDTO: " + reviewDTO);
//	
//		reviewDTO.setProduct_code()
//		
////		if (!reviewImg.isEmpty()) {
////			String fileName = UUID.randomUUID() + "_" + reviewImg.getOriginalFilename();
////			Path uploadPath = Paths.get("uploads/review-images");
////			if (!Files.exists(uploadPath)) {
////				Files.createDirectories(uploadPath);
////			}
////			reviewImg.transferTo(uploadPath.resolve(fileName).toFile());
////			reviewDTO.setReview_img(fileName);
////		}
//		catDogService.regReview(reviewDTO);
//		return "리뷰가 등록되었습니다.";
//	}

//	@PostMapping("/addWish")
//	@ResponseBody
//	public Map<String, String> addWish(@RequestParam("product_code") int productCode, HttpSession session) {
//		Map<String, String> response = new HashMap<String, String>();
//		Map<String, Object> userMap = (Map<String, Object>) session.getAttribute("user");
//		String userId = userMap != null ? (String) userMap.get("user_id") : null;
//
//		if (userId == null) {
//			response.put("message", "로그인 후 이용해주세요.");
//			return response;
//		}
//
//		try {
//			catDogService.addWish(userId, productCode);
//			response.put("message", "찜하기가 추가되었습니다.");
//		} catch (Exception e) {
//			response.put("message", "찜하기 추가 중 오류가 발생했습니다.");
//		}
//		return response;
//	}
//
//	@PostMapping("/deleteWish")
//	@ResponseBody
//	public Map<String, String> deleteWish(@RequestParam("product_code") int productCode, HttpSession session) {
//		Map<String, String> response = new HashMap<String, String>();
//		Map<String, Object> userMap = (Map<String, Object>) session.getAttribute("user");
//		String userId = userMap != null ? (String) userMap.get("user_id") : null;
//
//		if (userId == null) {
//			response.put("message", "로그인 후 이용해주세요.");
//			return response;
//		}
//
//		try {
//			WishDTO wishDTO = new WishDTO();
//			wishDTO.setUser_id(userId);
//			wishDTO.setProduct_code(productCode);
//			catDogService.deleteWish(wishDTO);
//			response.put("message", "찜하기가 삭제되었습니다.");
//		} catch (Exception e) {
//			response.put("message", "찜하기 삭제 중 오류가 발생했습니다.");
//		}
//		return response;
//	}

}