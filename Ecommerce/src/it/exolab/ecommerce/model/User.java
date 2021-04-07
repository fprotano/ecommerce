package it.exolab.ecommerce.model;

public class User {
	
	private long id;
	private String email;
	private String password;
	private String name;
	private String surname;
	private String phone;
	private String address;
	private long did;
	private Discount discount;
	private String session_id;
	
	
	
	
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public Discount getDiscount() {
		if(discount==null)
			discount = new Discount();
		return discount;
	}
	public void setDiscount(Discount discount) {
		this.discount = discount;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getDid() {
		return did;
	}
	public void setDid(long did) {
		this.did = did;
	}
	public User(String email, String password, String name, String surname, String phone, String address, int did,
			Discount discount) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.address = address;
		this.did = did;
		this.discount = discount;
	}
	
	public User() {
		super();
		
	}
	
	public String getFullname() {
		return this.name+", "+this.surname;
	}
	
	
}
