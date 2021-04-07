package it.exolab.ecommerce.crud;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.exolab.ecommerce.exception.EntityNotFoundError;
import it.exolab.ecommerce.exception.ItemNotAvailableError;
import it.exolab.ecommerce.exception.RequiredFieldError;
import it.exolab.ecommerce.mapper.UserOrderMapper;
import it.exolab.ecommerce.model.Cart;
import it.exolab.ecommerce.model.Item;
import it.exolab.ecommerce.model.User;
import it.exolab.ecommerce.model.UserOrder;
import it.exolab.ecommerce.model.UserOrderRow;

public class UserOrderCRUD extends GenericCRUD<UserOrder,UserOrderMapper> {
	CartCRUD cartCRUD = new CartCRUD();
	ItemCRUD itemCRUD = new ItemCRUD();
	public UserOrderCRUD() {
		super(UserOrderMapper.class);

	}
	private void validateInsertOrUpdate(UserOrder model) throws Exception {
		
		
	}
	@Override
	protected void validateInsert(UserOrder model) throws Exception {
		validateInsertOrUpdate(model);
		
	}
	public void insert(UserOrder model,boolean openConnection) throws Exception {
		validateInsert(model);
		if(openConnection)
			openConnection();
		model.setUosid(1);
		getMapper().insert(model);
		if(openConnection)
			closeConnection(true);
		
	}

	@Override
	public void insert(UserOrder model) throws Exception {
		insert(model,true);
		
	}

	@Override
	protected void validateUpdate(UserOrder model) throws Exception {
		validateInsertOrUpdate(model);
		
	}

	@Override
	public void update(UserOrder model) throws Exception {
		validateUpdate(model);
		openConnection();
		getMapper().update(model);
		closeConnection(true);
		
	}

	@Override
	public UserOrder find(Long id) throws EntityNotFoundError {
		UserOrder ret = null;
		openConnection();
		ret = getMapper().find(id);
		closeConnection();
		
		if(ret==null) {
			throw new EntityNotFoundError();
		}
		return ret;
	}

	@Override
	public List<UserOrder> all() {
		List<UserOrder> ret = new ArrayList<UserOrder>();
		openConnection();
		ret = getMapper().all();
		closeConnection();
		return ret;
	}

	@Override
	public void delete(Long id) {
		openConnection();
		getMapper().delete(id);
		closeConnection(true);
		
	}

	private void validateConfirm(User user) throws Exception {
		if(user==null) {
			throw new Exception("user not found");
		}
		validator.required("session_id", user.getSession_id());
		
		List<Cart> rows = cartCRUD.all(user.getSession_id(), true);
		if(rows.size()==0) {
			throw new Exception("Carrello vuoto");
		}
		
		for(Cart row : rows) {
			Item item = row.getItem();
			if(item.getQuantity()<row.getQuantity()) {
				throw new ItemNotAvailableError();
			}
			
		}
		
		
		
	}
	public void confirm(User user) throws Exception {
		
		validateConfirm(user);
		String session_id = user.getSession_id();
		openConnection();
		
		UserOrderRowCRUD userOrderRowCRUD = new UserOrderRowCRUD();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
		
		
		String order_no = sdf.format(new Date());
		UserOrder userOrder = new UserOrder();
		userOrder.setUid(user.getId());
		//userOrder.setDiscount(user.getDid());
		userOrder.setDiscount(user.getDiscount().getDiscount());
		userOrder.setOrder_no(order_no);
		
		insert(userOrder,false);

		long uoid = userOrder.getId();
		
		List<Cart> cart = cartCRUD.all(session_id,false);
		for(Cart row : cart) {
			UserOrderRow userOrderRow = new UserOrderRow();
			userOrderRow.setUoid(uoid);
			userOrderRow.setIid(row.getIid());
			userOrderRow.setPrice(row.getPrice());
			userOrderRow.setQuantity(row.getQuantity());
			userOrderRowCRUD.insert(userOrderRow,false);
			
			Item item = row.getItem();
			item.setQuantity(row.getQuantity());
			itemCRUD.updateQuantity(item,false);
			
		}
		cartCRUD.deleteBySessionId(session_id, false);
		closeConnection(true);
		
	}
	
	
	public List<UserOrder> findAllByUid(long uid) {
		List<UserOrder> ret = new ArrayList<UserOrder>();
		openConnection();
		ret = getMapper().findAllByUid(uid);
		closeConnection();
		return ret;
	}
	
	public int countByUid(long uid) {
		openConnection();
		int ret = getMapper().countByUid(uid);
		closeConnection();
		return ret;
	}
	
	public void updateSetPrendiInCarico(UserOrder model) throws Exception {
		model.setUosid(2);
		openConnection();
		getMapper().update(model);
		closeConnection(true);
		
	}
	
	public void updateSetSpedito(UserOrder model) throws Exception {
		model.setUosid(3);
		openConnection();
		getMapper().update(model);
		closeConnection(true);
		
	}
	public void updateSetConsegnato(UserOrder model) throws Exception {
		model.setUosid(4);
		openConnection();
		getMapper().update(model);
		closeConnection(true);
		
	}
	
	
}
