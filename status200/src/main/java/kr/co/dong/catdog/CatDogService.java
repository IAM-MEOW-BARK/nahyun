package kr.co.dong.catdog;

import java.util.List;
import java.util.Map;

public interface CatDogService {
	// 로그인
	public Map login(Map<String, Object> map);

	// 회원가입
	public int create(MemberDTO meber) throws Exception;

	// (나현) 전체 목록 가져오는 메소드
	public List<ProductDTO> list01();
	// (나현) 전체 목록 가져오는 메소드
	public List<ProductDTO> list02();
	// (나현) 전체 목록 가져오는 메소드
	public List<ProductDTO> list03();
	// (나현) 전체 목록 가져오는 메소드
	public List<ProductDTO> list04();
	// (나현) 전체 목록 가져오는 메소드
	public List<ProductDTO> list05();

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