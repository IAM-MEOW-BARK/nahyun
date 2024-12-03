package kr.co.dong.catdog;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class CatDogServiceImpl implements CatDogService {
	@Inject
	private CatDogDAO catDogDAO;

	@Override
	public Map login(Map<String, Object> map) {
		return catDogDAO.login(map);
	}

	@Override
	public int create(MemberDTO meber) throws Exception {
		return catDogDAO.create(meber);
	}

	// (나현 추가) 카테고리 별 보기
	@Override
	public List<ProductDTO> mainlist(Map<String, Object> param) {
		return catDogDAO.mainlist(param);
	}

	@Override
	public int deleteWish(WishDTO wishDTO) throws Exception {
		return catDogDAO.deleteWish(wishDTO);
	}

	@Override
	public int addWish(String user_id, int product_code) throws Exception {
		return catDogDAO.addWish(user_id, product_code);
	}

	@Override
	public List<String> getUserWish(String user_id) throws Exception {
		return catDogDAO.getUserWish(user_id);
	}

	@Override
	public List<CartDTO> getCartInfo(String user_id) throws Exception {
		return catDogDAO.getCartInfo(user_id);
	}

	@Override
	public List<CartDTO> getCartItem(String user_id) throws Exception {
		return catDogDAO.getCartItem(user_id);
	}

	@Override
	public int deleteCart(CartDTO cartDTO) throws Exception {
		return catDogDAO.deleteCart(cartDTO);
	}

	@Override
	public List<OrderDTO> getRecentOrder(String user_id) throws Exception {

		return catDogDAO.getRecentOrders(user_id);
	}

	@Override
	public List<OrderDTO> detailOrder(String order_code) throws Exception {
		return catDogDAO.getDetailOrders(order_code);
	}

	@Override
	public OrderDetailDTO getOrderDetail(String order_code) throws Exception {
		return catDogDAO.getOrderDetail(order_code); // DAO 호출
	}

	public int getTotalCost(String order_code) throws Exception {
		return catDogDAO.getTotalCost(order_code);
	}

	public List<OrderItemDetailDTO> getOrderItemDetail(String order_code) throws Exception {
		return catDogDAO.getOrderItemDetail(order_code);
	}

	public List<MyDTO> getMyOrders(String user_id) throws Exception {
		return catDogDAO.getMyOrders(user_id);
	}

	@Override
	public String addOrder(OrderDTO orderDTO) throws Exception {
		// 랜덤 코드 생성
		String orderCode = generateOrderCode();
		orderDTO.setOrder_code(orderCode);

		// 데이터베이스 삽입
		return catDogDAO.addOrder(orderDTO);
	}

	private String generateOrderCode() {
		// UUID를 이용하여 랜덤 코드 생성
		String oc = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"))
				+ UUID.randomUUID().toString().substring(0, 8).toUpperCase();
		return oc;
	}

	@Override
	public void addOrderItems(List<OrderItemDTO> orderItems) throws Exception {
		catDogDAO.addOrderItems(orderItems);
	}

//	@Override
//	public OrderDTO getOrderDetail(int order_code) throws Exception {
//		
//		OrderDTO orderInfo = catDogDAO.getOrderInfo(order_code);
//		List<ProductDTO> orderItems = catDogDAO.getOrderItems(order_code);
//		List<ReviewDTO> reviews = catDogDAO.getReview(order_code);
//		
//		int totalCost = 0;
//		for (ProductDTO item : orderItems) {
//		    totalCost += item.getProduct_price() * item.getOrder_quantity();
//		}
//		
//		orderInfo.setProduct_cost(totalCost);
//		orderInfo.setReviews(reviews);
//		
//		return orderInfo;
//	}

	/*
	 * 주문 상세보기 관련
	 * 
	 * @Override public OrderDTO getOrderInfo(int order_code) throws Exception {
	 * return catDogDAO.getOrderItems(order_code); }
	 * 
	 * @Override public OrderDTO getOrderItems(int order_code) throws Exception {
	 * return catDogDAO.getOrderItems(order_code); }
	 * 
	 * @Override public ReviewDTO getReview(int order_code) throws Exception {
	 * return catDogDAO.getReview(order_code); }
	 * 
	 */
	/*
	 * @Override public List<MemberDTO> getTotalMember() { // TODO Auto-generated
	 * method stub return catDogDAO.getTotalMember(); }
	 * 
	 * @Override public int deleteUser(String user_id) { // TODO Auto-generated
	 * method stub return catDogDAO.deleteUser(user_id); }
	 * 
	 * @Override public int deleteUsers(List<String> userIds) { return
	 * catDogDAO.deleteUsers(userIds); }
	 * 
	 * @Override public int getMemberByEmail(String user_id) throws Exception { //
	 * TODO Auto-generated method stub return catDogDAO.getMemberByEmail(user_id); }
	 * 
	 * @Override public List<BoardReply> detail1(int bno) { // TODO Auto-generated
	 * method stub return boardDAO.detail1(bno); }
	 */

}