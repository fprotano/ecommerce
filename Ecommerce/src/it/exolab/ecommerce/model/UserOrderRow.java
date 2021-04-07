package it.exolab.ecommerce.model;

public class UserOrderRow {

	private long id;
	private long iid;
	private long uoid;
	private float price;
	private int quantity;
	private UserOrder order;
	private Item item;
	
	
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public UserOrder getOrder() {
		return order;
	}
	public void setOrder(UserOrder order) {
		this.order = order;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIid() {
		return iid;
	}
	public void setIid(long iid) {
		this.iid = iid;
	}
	public long getUoid() {
		return uoid;
	}
	public void setUoid(long uoid) {
		this.uoid = uoid;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
