package kr.co.dong.catdog;

import java.util.List;
import java.util.Map;

public interface CatDogService {
	// 로그인
	public Map login(Map<String, Object> map);

	// 회원가입
	public int create(MemberDTO meber) throws Exception;

	// (나현) 전체 목록 가져오는 메소드
	public List<ProductDTO> mainlist(int product_category);

	public int deleteWish(WishDTO wishDTO) throws Exception;

	public int addWish(String user_id, int product_code) throws Exception;

	public List<String> getUserWish(String user_id) throws Exception;

	public List<CartDTO> getCartInfo(String user_id) throws Exception;

	public List<CartDTO> getCartItem(String user_id) throws Exception;

	public int getCartCost(String user_id) throws Exception;

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

	public int updateProfile(MemberDTO memberDTO) throws Exception;

	public List<OrderItemDTO> getOrderInfo(String order_code) throws Exception;

	public MemberDTO getMember(String user_id) throws Exception;

	// 결제 오더 정보
	public List<String> getOrderCodeByUserId(String user_id);

	// 결제
	public void updateAddress(String user_id, String name, String phone_num, String zipcode, String address,
			String detailaddress);

	public void updatePaymentStatus(String user_id);

	public void deleteOrderItems(String user_id, List<Integer> product_code);

	public List<Integer> getProductCodeByUserId(String user_id);

	public List<MyDTO> getOrderList(int start, int pageSize, String user_id);

	// 지혜 언니
	// 장바구니 추가
	public int addCart(CartDTO cartDTO) throws Exception;

	// 상품 상세정보
	public ProductDTO productDetail(int product_code);

	public List<ReviewDTO> getReview(int product_code);

	public List<QnaDTO> getQna(int product_code);

	public int product_reviewTotal(int product_code);

	public int product_qnaTotal(int product_code);

	// 카테고리별 리스트
	public List<ProductDTO> categoryList(int start, int pageSize, int product_category);

	public int categoryTotalPost(int product_category);

	// 공지사항 게시판 리스트
	public List<NoticeDTO> noticeList(int start, int pageSize);

	public int noticeTotalPost();

	// 리뷰 게시판 리스트
	public List<ReviewDTO> reviewList(int start, int pageSize);

	public int reviewTotalPost();

	// Q&A 게시판 리스트
	public List<QnaDTO> qnaList(int start, int pageSize);

	public int qnaTotalPost();

	// FAQ 게시판 리스트
	public List<FaqDTO> faqList(int start, int pageSize);

	public int faqTotalPost();

	// FAQ 구분 리스트
	public List<FaqDTO> faqListDivision(int start, int pageSize, int faq_division);

	public int faqTotalPostDivision(int faq_division);

	// 공지사항 상세보기
	public NoticeDTO noticeDetail(int notice_no);

	public int noticeUpdateReadCnt(int notice_no);

	// 리뷰 상세보기
	public ReviewDTO reviewDetail(int review_no);

	public int reviewUpdateReadCnt(int review_no);

	// Q&A 상세보기
	public QnaDTO qnaDetail(int qna_no);

	// FAQ 상세 조회
	public FaqDTO faqDetail(int faq_no);

	// 공지사항 글 작성
	public int noticeRegister(NoticeDTO noticeDTO);

	// 공지사항 글 수정
	public int noticeUpdate(NoticeDTO noticeDTO);

	// 공지사항 글 삭제
	public int noticeDelete(int notice_no);

	// Q&A 답변 조회
	public QnaDTO qnaReplyDetail(int qna_no);

	// Q&A 글 작성
	public int qnaRegister(QnaDTO qnaDTO);

	// Q&A 글 수정
	public int qnaUpdate(QnaDTO qnaDTO);

	// Q&A 글 삭제
	public int qnaDelete(int qna_no);

	// Q&A 답변 작성
	public int qnaReply(QnaDTO qnaDTO);

	// Q&A 답변 수정
	public int qnaReplyUpdate(QnaDTO qnaDTO);

	// Q&A 답변 삭제
	public int qnaReplyDelete(int qna_no);

	// 상품 검색
	// public List<ProductDTO> productList(int start, int pageSize);
	public List<ProductDTO> productSearch(String keyword);

	public int productTotal();

	// FAQ 글 작성
	public int faqRegister(FaqDTO faqDTO);

	// FAQ 글 수정
	public int faqUpdate(FaqDTO faqDTO);

	// FAQ 글 삭제
	public int faqDelete(int faq_no);
}
