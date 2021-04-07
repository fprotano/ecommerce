package it.exolab.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;

import it.exolab.ecommerce.model.Cart;

public class CartDTO {

	
	private float total;
	private float cartTotal;
	private float discountTotal;
	
	
	public float getDiscountTotal() {
		return discountTotal;
	}
	public void setDiscountTotal(float discountTotal) {
		this.discountTotal = discountTotal;
	}
	public float getCartTotal() {
		return cartTotal;
	}
	public void setCartTotal(float cartTotal) {
		this.cartTotal = cartTotal;
	}
	private List<Cart> rows = new ArrayList<Cart>();
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public List<Cart> getRows() {
		return rows;
	}
	public void setRows(List<Cart> rows) {
		this.rows = rows;
	}
	
	
	
}
