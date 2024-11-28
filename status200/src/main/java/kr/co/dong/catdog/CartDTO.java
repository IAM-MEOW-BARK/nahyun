package kr.co.dong.catdog;

public class CartDTO {
	private String user_id;
	private int product_code;	
	private int cart_quantity;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getProduct_code() {
		return product_code;
	}
	public void setProduct_code(int product_code) {
		this.product_code = product_code;
	}
	public int getCart_quantity() {
		return cart_quantity;
	}
	public void setCart_quantity(int cart_quantity) {
		this.cart_quantity = cart_quantity;
	}
	@Override
	public String toString() {
		return "CartDTO [user_id=" + user_id + ", product_icode=" + product_code + ", cart_quantity=" + cart_quantity + "]";
	}
}