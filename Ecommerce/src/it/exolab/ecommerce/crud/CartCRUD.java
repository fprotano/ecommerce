package it.exolab.ecommerce.crud;

import java.util.ArrayList;
import java.util.List;

import it.exolab.ecommerce.exception.EntityNotFoundError;
import it.exolab.ecommerce.exception.ItemNotAvailableError;
import it.exolab.ecommerce.exception.MinLengthError;
import it.exolab.ecommerce.exception.MinValueError;
import it.exolab.ecommerce.exception.UniqueFieldError;
import it.exolab.ecommerce.mapper.CartMapper;
import it.exolab.ecommerce.model.Cart;
import it.exolab.ecommerce.model.Item;
import it.exolab.ecommerce.model.User;

public class CartCRUD extends GenericCRUD<Cart,CartMapper> {

	ItemCRUD itemCRUD = new ItemCRUD();
	public CartCRUD() {
		super(CartMapper.class);
		
	}

	@Override
	public void insert(Cart model) throws Exception {
		validateInsert(model);
		openConnection();
		Cart alreadyPresent = null;
		try {
			alreadyPresent = findByIid(model);
				
		} catch (EntityNotFoundError ex) {
			
		}
		
		if(alreadyPresent!=null) {
			alreadyPresent.setQuantity(alreadyPresent.getQuantity()+model.getQuantity());
			alreadyPresent.setPrice(model.getPrice());
			update(alreadyPresent,false);
		} else {
			getMapper().insert(model);
		}
		
		
		closeConnection(true);
		
	}

	public void update(Cart model,Boolean openConnection) throws Exception {
		validateUpdate(model);
		if(openConnection) {
			openConnection();	
		}
		
		getMapper().update(model);
		if(openConnection) {
			closeConnection(true);	
		}
		
		
		
	}
	
	@Override
	public void update(Cart model) throws Exception {
		
		update(model,true);
		
	}

	@Override
	public Cart find(Long id) throws EntityNotFoundError {
		Cart ret = null;
		openConnection();
		ret = getMapper().find(id);
		closeConnection();
		
		if(ret==null) {
			throw new EntityNotFoundError();
		}
		return ret;
	}
	

	private Cart findByIid(Cart model,boolean openConnection) throws EntityNotFoundError {
		Cart ret = null;
		if(openConnection)
			openConnection();
		ret = getMapper().findByIid(model);
		
		if(openConnection)
			closeConnection();
		
		if(ret==null) {
			throw new EntityNotFoundError();
		}
		
		return ret;
	}
	

	private Cart findByIid(Cart model) throws EntityNotFoundError {
		
		return findByIid(model,false);
	}
	

	@Override
	public List<Cart> all() {
		List<Cart> ret = new ArrayList<Cart>();
		openConnection();
		ret = getMapper().all();
		closeConnection();
		return ret;
	}
	public List<Cart> all(String session_id,boolean openConnection) {
		List<Cart> ret = new ArrayList<Cart>();
		if(openConnection)
			openConnection();
		ret = getMapper().all(session_id);
		if(openConnection)
			closeConnection();
		return ret;
	}
	public List<Cart> all(String session_id) {
		return all(session_id,true);
	}
	@Override
	public void delete(Long id) {
		openConnection();
		getMapper().delete(id);
		closeConnection(true);
		
	}
	public void deleteBySessionId(String session_id) {
		deleteBySessionId(session_id,true);
	}
	public void deleteBySessionId(String session_id,boolean openConnection) {
		if(openConnection)
		openConnection();
		getMapper().deleteBySessionId(session_id);
		if(openConnection)
		closeConnection(true);
		
	}
	

	@Override
	protected void validateInsert(Cart model) throws Exception {
		 
		 validator.required("session_id", model.getSession_id());
		 
		 
		 
		
		 Item item = itemCRUD.find(model.getIid());
			//ok
			
		 
		   if(model.getQuantity()<=0) {
			   throw new MinValueError("quantity",1);	 
		   }
			 
		 
			if(item.getQuantity()<model.getQuantity()) {
				throw new ItemNotAvailableError();
			}
			
			try {
				findByIid(model,true);
				throw new UniqueFieldError("iid");
			} catch (EntityNotFoundError ex) {
				//ok
			}
			
			
		
		
	}

	@Override
	protected void validateUpdate(Cart model) throws Exception {
		 	Item item = itemCRUD.find(model.getIid());
			//ok
			
			if(item.getQuantity()<model.getQuantity()) {
				throw new ItemNotAvailableError();
			}
			
			
		
	}
	
	
	public int count(String session_id) {
		return count(session_id,true);
	}
	public int count(String session_id,boolean openConnection) {
		int ret = 0;
		if(openConnection)
			openConnection();
		ret = getMapper().count(session_id);
		if(openConnection)
			closeConnection();
		return ret;
	}
}
