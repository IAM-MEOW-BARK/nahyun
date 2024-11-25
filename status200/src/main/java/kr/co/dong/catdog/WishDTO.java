package kr.co.dong.catdog;

public class WishDTO {

	private int wish_no;
	private String user_id;
	private int product_code;
	
	public int getWish_no() {
		return wish_no;
	}
	public void setWish_no(int wish_no) {
		this.wish_no = wish_no;
	}
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
	@Override
	public String toString() {
		return "WishDTO [wish_no=" + wish_no + ", user_id=" + user_id + ", product_code=" + product_code + "]";
	}
	

}