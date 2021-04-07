package it.exolab.ecommerce.model;

public class Discount {
	
	private long id;
	private String coupon;
	private int discount;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCoupon() {
		return coupon;
	}
	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	public Discount(String coupon, int discount) {
		super();
		
		this.coupon = coupon;
		this.discount = discount;
	}
	
	public Discount() {
		super();
		
	
	}

}
