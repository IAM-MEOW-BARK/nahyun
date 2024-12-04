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

	public int deleteCart(CartDTO cartDTO) throws Exception;

	public List<OrderDTO> getRecentOrder(String user_id) throws Exception;

	public List<OrderDTO> detailOrder(String order_code) throws Exception;

	public OrderDetailDTO getOrderDetail(String order_code) throws Exception;

	public int getTotalCost(String order_code) throws Exception;

	public List<OrderItemDetailDTO> getOrderItemDetail(String order_code) throws Exception;

	public List<MyDTO> getMyOrders(String user_id) throws Exception;

	public String addOrder(OrderDTO orderDTO) throws Exception;

	public void addOrderItems(List<OrderItemDTO> orderItems) throws Exception;

	public int updateCartQuantity(CartDTO cartDTO) throws Exception;

	public int isReview(ReviewDTO reviewDTO) throws Exception;

	public int regReview(ReviewDTO reviewDTO) throws Exception;

	public ProductDTO getProductByCode(int product_code) throws Exception;
}