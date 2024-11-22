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
	public List<ProductDTO> list01() {
		// TODO Auto-generated method stub
		return catDogDAO.list01();
	}
	// (나현 추가) 카테고리 별 보기
	@Override
	public List<ProductDTO> list02() {
		// TODO Auto-generated method stub
		return catDogDAO.list02();
	}
	// (나현 추가) 카테고리 별 보기
	@Override
	public List<ProductDTO> list03() {
		// TODO Auto-generated method stub
		return catDogDAO.list03();
	}
	// (나현 추가) 카테고리 별 보기
	@Override
	public List<ProductDTO> list04() {
		// TODO Auto-ProductDTO method stub
		return catDogDAO.list04();
	}
	// (나현 추가) 카테고리 별 보기
	@Override
	public List<ProductDTO> list05() {
		// TODO Auto-generated method stub
		return catDogDAO.list05();
	}

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