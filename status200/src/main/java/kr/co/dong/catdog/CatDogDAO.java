package kr.co.dong.catdog;

import java.util.List;
import java.util.Map;

public interface CatDogDAO {
	// 로그인
	public Map login(Map<String, Object> map);

	// 회원가입
	public int create(MemberDTO meber) throws Exception;

	// 이메일 중복
	public MemberDTO getMemberByEmail(String user_id) throws Exception;

	// 회원 정보 찾기 (아이디)
	public int findId(String name, String phone_num) throws Exception;

	// 회원 정보 찾기 (비밀번호)
	public int findPw(String user_id, String name, String phone_num) throws Exception;

	// 메인페이지 제품 리스트 출력
	public List<ProductDTO> mainlist(int product_category);

	// 메인페이지 찜한 상품
	public List<String> getUserWish(String user_id) throws Exception;

	// 찜하기 추가
	public int addWish(String user_id, int product_code) throws Exception;

	// 찜하기 삭제
	public int deleteWish(WishDTO wishDTO) throws Exception;

	// 장바구니 추가
	public int addCart(CartDTO cartDTO) throws Exception;

	// 장바구니 삭제
	public int deleteCart(CartDTO cartDTO) throws Exception;
	
	//장바구니 수량 변경
	public int updateCartQuantity(CartDTO cartDTO) throws Exception;

	// 찜한 상품 리스트 조회
	public List<ProductDTO> getWish(String user_id) throws Exception;

	// 회원 정보 수정
	public int updateProfile(MemberDTO memberDTO);

	// 회원 탈퇴
	public int deleteUser(String user_id);

	// 장바구니 정보
	public List<CartDTO> getCartInfo(String user_id) throws Exception;

	// 장바구니 상품 정보
	public List<CartDTO> getCartItem(String user_id) throws Exception;

	// 장바구니 총 가격
	public int getCartCost(String user_id) throws Exception;

	// 정보 확인
	public OrderDetailDTO getOrderDetail(String order_code) throws Exception;

	public List<OrderItemDetailDTO> getOrderItemDetail(String order_code) throws Exception;

	public List<MyDTO> getMyOrders(String user_id) throws Exception;

	// 최근 주문 내역 (최신 5개 등 제한)
	public List<OrderDTO> getRecentOrders(String user_id) throws Exception;

	// 상세 주문 내역
	public List<OrderDTO> getDetailOrders(String order_code) throws Exception;

	// 주문 총 결제액
	public int getTotalCost(String order_code) throws Exception;

	// 전체 주문 내역
	public List<OrderDTO> getAllOrders(String user_id, String order_code) throws Exception;

	// 주문 기본 정보
	public List<OrderItemDTO> getOrderInfo(String order_code) throws Exception;
	
    public List<String> getOrderCodeByUserId(String user_id);

	// 주문 상품 상세 정보
	public List<ProductDTO> getOrderItems(String order_code) throws Exception;

	// 주문 상품 후기 정보
	public List<ReviewDTO> getReview(String order_code) throws Exception;

	// 주문하기 (주문 정보 생성)
	public String addOrder(OrderDTO orderDTO) throws Exception;
	
	// 주문하기 (주문 아이템 생성)
	public void addOrderItems(List<OrderItemDTO> orderItems) throws Exception;

	public int isReview(ReviewDTO reviewDTO) throws Exception;
	
	public int regReview(ReviewDTO reviewDTO) throws Exception;
	
	public ProductDTO getProductByCode(int product_code) throws Exception;
	
	// 단일 회원 정보
    public MemberDTO getMember(String user_id);
    
 // 상품 결제
    public List<PaymentDTO> productPayment(String user_id);
    void updateAddress(String user_id, String name, String phone_num, String zipcode, String address, String detailaddress);
    void updatePaymentStatus(String user_id);
    public void deleteOrderItems(String user_id, List<Integer> product_code);
    public List<Integer> getProductCodeByUserId(String user_id);	
    
    public List<MyDTO> getOrderList(int start, int pageSize, String user_id);
	
}