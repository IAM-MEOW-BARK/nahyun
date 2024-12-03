package kr.co.dong.catdog;

import java.util.List;

public class OrderDTO {
	private String orderCode;
	private String userIdFk;
	private String orderedAt;
	private int productCost; // 총비용
	private int paymentStatus;
	private List<ProductDTO> productList; // 상품 리스트
	private List<ReviewDTO> reviews; // 리뷰 리스트

	private List<OrderItemDTO> orders;

	public List<OrderItemDTO> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderItemDTO> orders) {
		this.orders = orders;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getUserIdFk() {
		return userIdFk;
	}

	public void setUserIdFk(String userIdFk) {
		this.userIdFk = userIdFk;
	}

	public String getOrderedAt() {
		return orderedAt;
	}

	public void setOrderedAt(String orderedAt) {
		this.orderedAt = orderedAt;
	}

	public int getProductCost() {
		return productCost;
	}

	public void setProductCost(int productCost) {
		this.productCost = productCost;
	}

	public int getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(int paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public List<ProductDTO> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductDTO> productList) {
		this.productList = productList;
	}

	public List<ReviewDTO> getReviews() {
		return reviews;
	}

	public void setReviews(List<ReviewDTO> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return "OrderDTO [orderCode=" + orderCode + ", userIdFk=" + userIdFk + ", orderedAt=" + orderedAt
				+ ", productCost=" + productCost + ", paymentStatus=" + paymentStatus + ", productList=" + productList
				+ ", reviews=" + reviews + ", orders=" + orders + "]";
	}

}