package kr.co.dong.catdog;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class CatDogServiceImpl implements CatDogService {
	@Inject
	private CatDogDAO catDogDAO;

	@Override
	public Map login(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return catDogDAO.login(map);
	}

	@Override
	public int create(MemberDTO meber) throws Exception {
		// TODO Auto-generated method stub
		return catDogDAO.create(meber);
	}

	// (나현 추가) 카테고리 별 보기
	@Override
	public List<ProductDTO> mainlist(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return catDogDAO.mainlist(param);
	}

	@Override
	public int deleteWish(WishDTO wishDTO) throws Exception {
		// TODO Auto-generated method stub
		return catDogDAO.deleteWish(wishDTO);
	}

	@Override
	public int addWish(String user_id, int product_code) throws Exception {
		// TODO Auto-generated method stub
		return catDogDAO.addWish(user_id, product_code);
	}
	
	@Override
	public List<String> getUserWish(String user_id) throws Exception {
		return catDogDAO.getUserWish(user_id);
	}
	
	@Override
	public OrderDTO getOrderDetail(int order_code) throws Exception {
		
		OrderDTO orderInfo = catDogDAO.getOrderInfo(order_code);
		List<ProductDTO> orderItems = catDogDAO.getOrderItems(order_code);
		List<ReviewDTO> reviews = catDogDAO.getReview(order_code);
		
		int totalCost = 0;
		for (ProductDTO item : orderItems) {
		    totalCost += item.getProduct_price() * item.getOrder_quantity();
		}
		
		orderInfo.setProduct_cost(totalCost);
		orderInfo.setReviews(reviews);
		
		return orderInfo;
	}
	
	/* 주문 상세보기 관련
	@Override
	public OrderDTO getOrderInfo(int order_code) throws Exception {
		return catDogDAO.getOrderItems(order_code);
	}
	@Override
	public OrderDTO getOrderItems(int order_code) throws Exception {
		return catDogDAO.getOrderItems(order_code);
	}
	@Override
	public ReviewDTO getReview(int order_code) throws Exception {
		return catDogDAO.getReview(order_code);
	}
	 * 
	 * 	*/
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