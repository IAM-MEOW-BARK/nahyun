package kr.co.dong.catdog;

public class MyDTO {

	private String orderedAt; // 주문 일자
	private String orderCode; // 주문 코드
	private String firstProductName; // 대표 상품 이름
	private int totalPrice; // 총 결제 금액
	private int order_status; // 결제 상태

	public String getOrderedAt() {
		return orderedAt;
	}

	public void setOrderedAt(String orderedAt) {
		this.orderedAt = orderedAt;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getFirstProductName() {
		return firstProductName;
	}

	public void setFirstProductName(String firstProductName) {
		this.firstProductName = firstProductName;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getOrder_status() {
		return order_status;
	}

	public void setOrder_status(int order_status) {
		this.order_status = order_status;
	}

	@Override
	public String toString() {
		return "MyDTO [orderedAt=" + orderedAt + ", orderCode=" + orderCode + ", firstProductName=" + firstProductName
				+ ", totalPrice=" + totalPrice + ", order_status=" + order_status + "]";
	}

}
