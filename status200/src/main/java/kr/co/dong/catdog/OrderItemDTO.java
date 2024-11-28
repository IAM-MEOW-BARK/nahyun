package kr.co.dong.catdog;

public class OrderItemDTO {
	private int order_item_code;
	private int order_code;
	private int product_id;
	private String product_name;
	private int cart_quantity;
	private int order_status;
	private int order_return;
	private int product_price;

	public int getOrder_item_code() {
		return order_item_code;
	}

	public void setOrder_item_code(int order_item_code) {
		this.order_item_code = order_item_code;
	}

	public int getOrder_code() {
		return order_code;
	}

	public void setOrder_code(int order_code) {
		this.order_code = order_code;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getCart_quantity() {
		return cart_quantity;
	}

	public void setCart_quantity(int cart_quantity) {
		this.cart_quantity = cart_quantity;
	}

	public int getOrder_status() {
		return order_status;
	}

	public void setOrder_status(int order_status) {
		this.order_status = order_status;
	}

	public int getOrder_return() {
		return order_return;
	}

	public void setOrder_return(int order_return) {
		this.order_return = order_return;
	}

	public int getProduct_price() {
		return product_price;
	}

	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}

	@Override
	public String toString() {
		return "OrderItemDTO [order_item_code=" + order_item_code + ", order_code=" + order_code + ", product_id="
				+ product_id + ", product_name=" + product_name + ", cart_quantity=" + cart_quantity + ", order_status="
				+ order_status + ", order_return=" + order_return + ", product_price=" + product_price + "]";
	}

}