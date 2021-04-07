package it.exolab.ecommerce.model;

import java.util.Date;
import java.util.List;

public class UserOrder {

	private long id;
	private String order_no;
	private long uid;
	private long discount;
	private float total;
	private long uosid;
	private Date updated_at;
	private User user;
	private List<UserOrderRow> rows;
	
	
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public long getUosid() {
		return uosid;
	}
	public void setUosid(long uosid) {
		this.uosid = uosid;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public long getDiscount() {
		return discount;
	}
	public void setDiscount(long discount) {
		this.discount = discount;
	}
	public List<UserOrderRow> getRows() {
		return rows;
	}
	public void setRows(List<UserOrderRow> rows) {
		this.rows = rows;
	}
	
	public boolean getIsConfermato() {
		return this.uosid==1;
	}
	
	public boolean getIsPresoInCarico() {
		return this.uosid==2;
	}
	public boolean getIsSpedito() {
		return this.uosid==3;
	}
	public boolean getIsConsegnato() {
		return this.uosid==4;
	}
	
}
