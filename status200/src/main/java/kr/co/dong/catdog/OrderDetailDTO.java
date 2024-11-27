package kr.co.dong.catdog;

import java.util.List;

public class OrderDetailDTO {
	// 주문 정보
	private String orderCode;
	private String userId;
	private String orderedAt;

	// 배송지 정보
	private String zipcode;
	private String address;
	private String detailAddress;

	// 상품 리스트
	private List<OrderItemDTO> orderItems;

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderedAt() {
		return orderedAt;
	}

	public void setOrderedAt(String orderedAt) {
		this.orderedAt = orderedAt;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public List<OrderItemDTO> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemDTO> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		return "OrderDetailDTO [orderCode=" + orderCode + ", userId=" + userId + ", orderedAt=" + orderedAt
				+ ", zipcode=" + zipcode + ", address=" + address + ", detailAddress=" + detailAddress + ", orderItems="
				+ orderItems + "]";
	}

}
