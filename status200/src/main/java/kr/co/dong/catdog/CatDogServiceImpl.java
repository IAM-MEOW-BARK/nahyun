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

	@Override
	public int updateCartQuantity(CartDTO cartDTO) throws Exception {
		return catDogDAO.updateCartQuantity(cartDTO);
	}

}