package kr.co.dong.catdog;

public class ProductDTO {
	private int product_code;
	private String product_name;
	private int product_category;
	private int product_price;
	private String thumbnail_img;
	private String product_img;
	private String product_info;
	private String product_regdate;
	private String product_update;
	private int wish_no;
	private int order_quantity;

	public int getProduct_code() {
		return product_code;
	}

	public void setProduct_code(int product_code) {
		this.product_code = product_code;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getProduct_category() {
		return product_category;
	}

	public void setProduct_category(int product_category) {
		this.product_category = product_category;
	}

	public int getProduct_price() {
		return product_price;
	}

	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}

	public String getThumbnail_img() {
		return thumbnail_img;
	}

	public void setThumbnail_img(String thumbnail_img) {
		this.thumbnail_img = thumbnail_img;
	}

	public String getProduct_img() {
		return product_img;
	}

	public void setProduct_img(String product_img) {
		this.product_img = product_img;
	}

	public String getProduct_info() {
		return product_info;
	}

	public void setProduct_info(String product_info) {
		this.product_info = product_info;
	}

	public String getProduct_regdate() {
		return product_regdate;
	}

	public void setProduct_regdate(String product_regdate) {
		this.product_regdate = product_regdate;
	}

	public String getProduct_update() {
		return product_update;
	}

	public void setProduct_update(String product_update) {
		this.product_update = product_update;
	}

	
	// 필요해서 추가해봄
	public int getWish_no() {
		return wish_no;
	}

	public void setWish_no(int wish_no) {
		this.wish_no = wish_no;
	}

	public int getOrder_quantity() {
		return order_quantity;
	}

	public void setOrder_quantity(int order_quantity) {
		this.order_quantity = order_quantity;
	}

	@Override
	public String toString() {
		return "ProductDTO [product_code=" + product_code + ", product_name=" + product_name + ", product_category="
				+ product_category + ", product_price=" + product_price + ", thumbnail_img=" + thumbnail_img
				+ ", product_img=" + product_img + ", product_info=" + product_info + ", product_regdate="
				+ product_regdate + ", product_update=" + product_update + ", wish_no=" + wish_no + "]";
	}

}