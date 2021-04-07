package it.exolab.ecommerce.model;

public class Item {
	private long id;
	private String title;
	private String description;
	private float price;
	private int quantity;
	private String picture;
	private long cid;
	private Category category;
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public long getCid() {
		return cid;
	}
	public void setCid(long cid) {
		this.cid = cid;
	}
	public Item(String title, String description, float price, int quantity, String picture, long cid) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.picture = picture;
		this.cid = cid;
	}
	
	public Item() {
		super();
		
	}
	
}
