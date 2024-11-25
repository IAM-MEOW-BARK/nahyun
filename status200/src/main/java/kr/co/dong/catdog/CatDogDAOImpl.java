package kr.co.dong.catdog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class CatDogDAOImpl implements CatDogDAO{
	
	@Inject SqlSession sqlSession;	
	
	private static final String namespace ="kr.co.dong.catdogMapper";

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
	public List<ProductDTO> mainlist(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".mainlist", param);
	}
	
	@Override
	public List<String> getUserWish(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".getUserWish", user_id);
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
	
	/*
	 * @Override public int addWish(String user_id, int product_code) throws
	 * Exception { // TODO Auto-generated method stub return 0; }
	 * 
	 * @Override public int deleteWish(WishDTO wishDTO) throws Exception { // TODO
	 * Auto-generated method stub return sqlSession.delete(namespace+".deleteWish",
	 * wishDTO); }
	 */

	@Override
	public int addCart(CartDTO cartDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCart(int product_id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ProductDTO> getWish(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateProfile(MemberDTO memberDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteUser(String user_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OrderDTO> getRecentOrders(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDTO> getAllOrders(String user_id, String order_code) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDTO getOrderDetail(int order_code) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductDTO> getTotalProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addProduct(ProductDTO productDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateProduct(ProductDTO productDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteProduct(int product_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemberDTO> getTotalMember() {
		// TODO Auto-generated method stub
		return null;
	}





	
}