package kr.co.dong.catdog;

import java.util.List;
import java.util.Map;

public interface CatDogService {
	// 로그인
	public Map login(Map<String, Object> map);

	// 회원가입
	public int create(MemberDTO meber) throws Exception;

	// (나현) 전체 목록 가져오는 메소드
	public List<ProductDTO> mainlist(Map<String, Object> param);
	
	public int deleteWish(WishDTO wishDTO) throws Exception;
	
	public int addWish(String user_id, int product_code) throws Exception;
	
	public List<String> getUserWish(String user_id) throws Exception;
	
	public List<CartDTO> getCartInfo(String user_id) throws Exception; 
	
	public List<CartDTO> getCartItem(String user_id) throws Exception;
	
	public List<OrderDTO> getRecentOrder(String user_id) throws Exception;
	
	public List<OrderDTO> detailOrder(String order_code) throws Exception;
	
	public OrderDetailDTO getOrderDetail(String order_code) throws Exception;
	
	public int getTotalCost(String order_code) throws Exception;
	
	public List<OrderItemDetailDTO> getOrderItemDetail(String order_code) throws Exception;
	
	public List<MyDTO> getMyOrders(String user_id) throws Exception;
	
	/* 주문 상세보기 관련
	 * 
	 * public OrderDTO getOrderInfo(int order_code) throws Exception;
	 * 
	 * public OrderDTO getOrderItems(int order_code) throws Exception;
	 * 
	 * public ReviewDTO getReview(int order_code) throws Exception;
	 */
	
	
	/*
	 * // (우진) 이메일 중복 체크 public int getMemberByEmail(String user_id) throws
	 * Exception;
	 * 
	 * // (우진) 전체 회원 리스트 public List<MemberDTO> getTotalMember();
	 * 
	 * // (우진) 회원 탈퇴 public int deleteUser(String user_id);
	 * 
	 * public int deleteUsers(List<String> userIds);
	 */
}