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
	public List<ProductDTO> mainlist(int product_category) {
		return catDogDAO.mainlist(product_category);
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
	public int getCartCost(String user_id) throws Exception {
		return catDogDAO.getCartCost(user_id);
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

	@Override
	public int getTotalCost(String order_code) throws Exception {
		return catDogDAO.getTotalCost(order_code);
	}

	@Override
	public List<OrderItemDetailDTO> getOrderItemDetail(String order_code) throws Exception {
		return catDogDAO.getOrderItemDetail(order_code);
	}

	@Override
	public List<MyDTO> getMyOrders(String user_id) throws Exception {
		return catDogDAO.getMyOrders(user_id);
	}

	@Override
	public String addOrder(OrderDTO orderDTO) throws Exception {
		// 랜덤 코드 생성
		String orderCode = generateOrderCode();
		orderDTO.setOrder_code(orderCode);
		catDogDAO.addOrder(orderDTO);

		// 데이터베이스 삽입
		return orderCode;
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

	@Override
	public int updateCartQuantity(CartDTO cartDTO) throws Exception {
		return catDogDAO.updateCartQuantity(cartDTO);
	}

	@Override
	public int isReview(ReviewDTO reviewDTO) throws Exception {
		// TODO Auto-generated method stub
		return catDogDAO.isReview(reviewDTO);
	}

	@Override
	public int regReview(ReviewDTO reviewDTO) throws Exception {
		// TODO Auto-generated method stub
		return catDogDAO.regReview(reviewDTO);
	}

	@Override
	public ProductDTO getProductByCode(int product_code) throws Exception {
		return catDogDAO.getProductByCode(product_code);
	}

	@Override
	public int updateProfile(MemberDTO memberDTO) throws Exception {
		return catDogDAO.updateProfile(memberDTO);
	}

	@Override
	public OrderDTO getOrderInfo(String order_code) throws Exception {
		return catDogDAO.getOrderInfo(order_code);
	}

	@Override
	public PaymentDTO getMember(String user_id) throws Exception {
		return catDogDAO.getMember(user_id);
	}

	@Override
	public List<String> getOrderCodeByUserId(String user_id) {
		// TODO Auto-generated method stub
		return catDogDAO.getOrderCodeByUserId(user_id);
	}

	@Override
	public void updateAddress(String user_id, String name, String phone_num, String zipcode, String address,
			String detailaddress) {
		catDogDAO.updateAddress(user_id, name, phone_num, zipcode, address, detailaddress);
	}

	@Override
	public void updatePaymentStatus(String user_id) {
		catDogDAO.updatePaymentStatus(user_id);
	}

	@Override
	public void deleteOrderItems(String user_id, List<Integer> product_code) {
		catDogDAO.deleteOrderItems(user_id, product_code);
	}

	@Override
	public List<Integer> getProductCodeByUserId(String user_id) {
		return catDogDAO.getProductCodeByUserId(user_id);
	}

	@Override
	public List<MyDTO> getOrderList(int start, int pageSize, String user_id) {
		return catDogDAO.getOrderList(start, pageSize, user_id);
	}
}