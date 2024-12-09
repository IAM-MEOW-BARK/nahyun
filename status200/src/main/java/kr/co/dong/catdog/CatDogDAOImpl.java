package kr.co.dong.catdog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class CatDogDAOImpl implements CatDogDAO {

	@Inject
	SqlSession sqlSession;

	private static final String namespace = "kr.co.dong.catdogMapper";

	@Override
	public Map login(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".login", map);
	}

	@Override
	public int create(MemberDTO meber) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert(namespace + ".sign-up", meber);
	}

	@Override
	public MemberDTO getMemberByEmail(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findId(String name, int phone_num) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int findPw(String user_id, String name, int phone_num) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	// 나현 수정
	@Override
	public List<ProductDTO> mainlist(int product_category) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".mainlist", product_category);
	}

	@Override
	public List<String> getUserWish(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".getUserWish", user_id);
	}

	@Override
	public int addWish(String user_id, int product_code) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("user_id", user_id);
		param.put("product_code", product_code);
		return sqlSession.insert(namespace + ".addWish", param);
	}

	@Override
	public int deleteWish(WishDTO wishDTO) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("user_id", wishDTO.getUser_id());
		param.put("product_code", wishDTO.getProduct_code());
		return sqlSession.delete(namespace + ".deleteWish", param);
	}

	// 장바구니 정보
	public List<CartDTO> getCartInfo(String user_id) throws Exception {
		return sqlSession.selectList(namespace + ".getCartInfo", user_id);
	}

	// 장바구니 상품 정보
	public List<CartDTO> getCartItem(String user_id) throws Exception {
		return sqlSession.selectList(namespace + ".getCartItem", user_id);
	}

	// 수정한 거
	public OrderDetailDTO getOrderDetail(String order_code) throws Exception {
		return sqlSession.selectOne(namespace + ".getOrderDetail", order_code);
	}

	public List<OrderItemDetailDTO> getOrderItemDetail(String order_code) throws Exception {
		return sqlSession.selectList(namespace + ".getOrderItemDetail", order_code);
	}

	public List<OrderDTO> getDetailOrders(String order_code) throws Exception {
		return sqlSession.selectList(namespace + ".getDetailOrder", order_code);
	}

	// 주문 기본 정보
	@Override
	public OrderDTO getOrderInfo(String order_code) throws Exception {
		return sqlSession.selectOne(namespace + ".getOrderInfo", order_code);
	}

	// 주문 상품 상세 정보
	@Override
	public List<ProductDTO> getOrderItems(String order_code) throws Exception {
		return sqlSession.selectList(namespace + ".getOrderItems", order_code);
	}

	@Override
	public int getCartCost(String user_id) throws Exception {
		return sqlSession.selectOne(namespace + ".getCartCost", user_id);
	}

	// 주문 총 결제액
	public int getTotalCost(String order_code) throws Exception {
		return sqlSession.selectOne(namespace + ".getTotalCost", order_code);
	}

	// 주문 상품 후기 정보
	@Override
	public List<ReviewDTO> getReview(String order_code) throws Exception {
		return sqlSession.selectList(namespace + ".getReview", order_code);
	}

	@Override
	public int addCart(CartDTO cartDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateCartQuantity(CartDTO cartDTO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update(namespace + ".updateCartQuantity", cartDTO);
	}

	@Override
	public int deleteCart(CartDTO cartDTO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete(namespace + ".deleteCart", cartDTO);
	}

	@Override
	public List<ProductDTO> getWish(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateProfile(MemberDTO memberDTO) {
		// TODO Auto-generated method stub
		return sqlSession.update(namespace + ".updateProfile", memberDTO);
	}

	@Override
	public int deleteUser(String user_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<MyDTO> getMyOrders(String user_id) throws Exception {
		return sqlSession.selectList(namespace + ".getMyOrders", user_id);
	}

	@Override
	public List<OrderDTO> getRecentOrders(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".getRecentOrders", user_id);
	}

	@Override
	public List<OrderDTO> getAllOrders(String user_id, String order_code) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addOrder(OrderDTO orderDTO) throws Exception {
		sqlSession.insert(namespace + ".addOrder", orderDTO);
		return orderDTO.getOrder_code(); // MyBatis에서 반환된 order_code 사용
	}

	@Override
	public void addOrderItems(List<OrderItemDTO> orderItems) throws Exception {
		sqlSession.insert(namespace + ".addOrderItems", orderItems);
	}

	@Override
	public int isReview(ReviewDTO reviewDTO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".isReview", reviewDTO);
	}

	@Override
	public int regReview(ReviewDTO reviewDTO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert(namespace + ".regReview", reviewDTO);
	}

	@Override
	public ProductDTO getProductByCode(int product_code) throws Exception {
		return sqlSession.selectOne(namespace + ".getProductByCode", product_code);

	}

	@Override
	public PaymentDTO getMember(String user_id) {
		return sqlSession.selectOne(namespace + ".getMember", user_id);
	}

	@Override
	public List<PaymentDTO> productPayment(String user_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".productPayment", user_id);
	}

	@Override
	public void updateAddress(String user_id, String name, String phone_num, String zipcode, String address,
			String detailaddress) {
		Map<String, Object> params = new HashMap<>();
		params.put("user_id", user_id);
		params.put("name", name);
		params.put("phone_num", phone_num);
		params.put("zipcode", zipcode);
		params.put("address", address);
		params.put("detailaddress", detailaddress);
		sqlSession.update(namespace + ".updateAddress", params);
	}

	@Override
	public void updatePaymentStatus(String user_id) {
		sqlSession.update(namespace + ".updatePaymentStatus", user_id);
	}

	@Override
	   public void deleteOrderItems(String user_id, List<Integer> product_code) {
	      // TODO Auto-generated method stub
	      Map<String, Object> params = new HashMap<>();
	       params.put("user_id", user_id);
	       params.put("product_code", product_code);

	       sqlSession.delete(namespace + ".deleteOrderItems", params);
	   }

	@Override
	public List<Integer> getProductCodeByUserId(String user_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".getProductCodeByUserId", user_id);
	}

	@Override
	public List<String> getOrderCodeByUserId(String user_id) {
		return sqlSession.selectList(namespace + ".getOrderCodeByUserId", user_id);
	}

}