package it.exolab.ecommerce.model;

public class Category {
	private long id;
	private String title;
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
	public Category() {
		super();
		
	}
	public Category(String title) {
		super();
		this.title = title;
	}
	
	
}
