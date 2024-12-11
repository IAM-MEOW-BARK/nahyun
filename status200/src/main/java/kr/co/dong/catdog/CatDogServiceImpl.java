package kr.co.dong.catdog;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
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

	// 전체 주문 조회
	@Override
	public List<MyDTO> getMyOrders(String user_id) throws Exception {
		Map<String, Object> params = new HashMap<>();
		params.put("user_id", user_id);
		params.put("limit", null); // LIMIT 조건 없이 전체 조회
		return catDogDAO.getMyOrders(params);
	}

	// 최근 5건 조회
	@Override
	public List<MyDTO> getRecentOrders(String user_id) throws Exception {
		Map<String, Object> params = new HashMap<>();
		params.put("user_id", user_id);
		params.put("limit", 5); // 최근 5건만 조회
		return catDogDAO.getMyOrders(params);
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
	public int isReview(int productCode, String userId) throws Exception {
		return catDogDAO.isReview(productCode, userId);
	}

	@Override
	public int regReview(ReviewDTO reviewDTO) throws Exception {

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
	public List<OrderItemDTO> getOrderInfo(String order_code) throws Exception {
		return catDogDAO.getOrderInfo(order_code);
	}

	@Override
	public MemberDTO getMember(String user_id) throws Exception {
		return catDogDAO.getMember(user_id);
	}

	@Override
	public List<String> getOrderCodeByUserId(String user_id) {

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

	// 지혜 언니

	@Override
	public int addCart(CartDTO cartDTO) throws Exception {

		return catDogDAO.addCart(cartDTO);
	}

	@Override
	public ProductDTO productDetail(int product_code) {

		return catDogDAO.productDetail(product_code);
	}

	@Override
	public List<ReviewDTO> getReview(int product_code) {

		return null;
	}

	@Override
	public List<QnaDTO> getQna(int product_code) {

		return null;
	}

	@Override
	public int product_reviewTotal(int product_code) {

		return 0;
	}

	@Override
	public int product_qnaTotal(int product_code) {

		return 0;
	}

	@Override
	public List<ProductDTO> categoryList(int start, int pageSize, int product_category) {

		return catDogDAO.categoryList(start, pageSize, product_category);
	}

	@Override
	public int categoryTotalPost(int product_category) {

		return catDogDAO.categoryTotalPost(product_category);
	}

	@Override
	public List<NoticeDTO> noticeList(int start, int pageSize) {

		return catDogDAO.noticeList(start, pageSize);
	}

	@Override
	public int noticeTotalPost() {

		return catDogDAO.noticeTotalPost();
	}

	@Override
	public List<ReviewDTO> reviewList(int start, int pageSize) {

		return catDogDAO.reviewList(start, pageSize);
	}

	@Override
	public int reviewTotalPost() {

		return catDogDAO.reviewTotalPost();
	}

	@Override
	public List<QnaDTO> qnaList(int start, int pageSize) {

		return catDogDAO.qnaList(start, pageSize);
	}

	@Override
	public int qnaTotalPost() {

		return catDogDAO.qnaTotalPost();
	}

	@Override
	public List<FaqDTO> faqList(int start, int pageSize) {

		return catDogDAO.faqList(start, pageSize);
	}

	@Override
	public int faqTotalPost() {

		return catDogDAO.faqTotalPost();
	}

	@Override
	public List<FaqDTO> faqListDivision(int start, int pageSize, int faq_division) {

		return catDogDAO.faqListDivision(start, pageSize, faq_division);
	}

	@Override
	public int faqTotalPostDivision(int faq_division) {

		return catDogDAO.faqTotalPostDivision(faq_division);
	}

	@Override
	public NoticeDTO noticeDetail(int notice_no) {

		return catDogDAO.noticeDetail(notice_no);
	}

	@Override
	public ReviewDTO reviewDetail(int review_no) {

		return catDogDAO.reviewDetail(review_no);
	}

	@Override
	public QnaDTO qnaDetail(int qna_no) {

		return catDogDAO.qnaDetail(qna_no);
	}

	@Override
	public FaqDTO faqDetail(int faq_no) {

		return catDogDAO.faqDetail(faq_no);
	}

	@Override
	public int noticeRegister(NoticeDTO noticeDTO) {

		return catDogDAO.noticeRegister(noticeDTO);
	}

	@Override
	public int noticeUpdate(NoticeDTO noticeDTO) {

		return catDogDAO.noticeUpdate(noticeDTO);
	}

	@Override
	public int noticeDelete(int notice_no) {

		return catDogDAO.noticeDelete(notice_no);
	}

	@Override
	public int qnaRegister(QnaDTO qnaDTO) {

		return catDogDAO.qnaRegister(qnaDTO);
	}

	@Override
	public int qnaUpdate(QnaDTO qnaDTO) {

		return catDogDAO.qnaUpdate(qnaDTO);
	}

	@Override
	public int qnaDelete(int qna_no) {

		return catDogDAO.qnaDelete(qna_no);
	}

	@Override
	public QnaDTO qnaReplyDetail(int qna_no) {

		return catDogDAO.qnaReplyDetail(qna_no);
	}

	@Override
	public int qnaReply(QnaDTO qnaDTO) {

		return catDogDAO.qnaReply(qnaDTO);
	}

	@Override
	public int qnaReplyUpdate(QnaDTO qnaDTO) {

		return catDogDAO.qnaReplyUpdate(qnaDTO);
	}

	@Override
	public int qnaReplyDelete(int qna_no) {

		return catDogDAO.qnaReplyDelete(qna_no);
	}

//		@Override
//		public List<ProductDTO> productList(int start, int pageSize) {
//	
//			return catDogDAO.productList(start, pageSize);
//		}
	@Override
	public List<ProductDTO> productSearch(String keyword) {

		return catDogDAO.productSearch(keyword);
	}

	@Override
	public int productTotal() {

		return catDogDAO.productTotal();
	}

	@Override
	public int faqRegister(FaqDTO faqDTO) {

		return catDogDAO.faqRegister(faqDTO);
	}

	@Override
	public int noticeUpdateReadCnt(int notice_no) {

		return catDogDAO.noticeUpdateReadCnt(notice_no);
	}

	@Override
	public int reviewUpdateReadCnt(int review_no) {

		return catDogDAO.reviewUpdateReadCnt(review_no);
	}

	@Override
	public int faqUpdate(FaqDTO faqDTO) {

		return catDogDAO.faqUpdate(faqDTO);
	}

	@Override
	public int faqDelete(int faq_no) {

		return catDogDAO.faqDelete(faq_no);
	}

}