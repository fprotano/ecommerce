package it.exolab.ecommerce.crud;

import java.util.ArrayList;
import java.util.List;

import it.exolab.ecommerce.exception.EntityNotFoundError;
import it.exolab.ecommerce.exception.UniqueFieldError;
import it.exolab.ecommerce.mapper.UserMapper;
import it.exolab.ecommerce.model.Category;
import it.exolab.ecommerce.model.User;

public class UserCRUD  extends GenericCRUD<User,UserMapper> {

	DiscountCRUD discountCRUD = new DiscountCRUD(); 
	public UserCRUD() {
		super(UserMapper.class);

	}
	
	private void validateInsertOrUpdate(User model) throws Exception {
		validator.required("name", model.getName());
		validator.maxLength("name", model.getName(), 70);
		
		validator.required("surname", model.getSurname());
		validator.maxLength("surname", model.getSurname(), 70);
		
		validator.required("email", model.getEmail());
		validator.maxLength("email", model.getEmail(), 70);
		
		validator.required("password", model.getPassword());
		validator.maxLength("password", model.getPassword(), 70);
		
		validator.maxLength("phone", model.getPhone(), 15);
		
		validator.required("coupon", model.getDiscount().getCoupon());
		
		
		String coupon = model.getDiscount().getCoupon();
		
		
		discountCRUD.findByCoupon(coupon);
		
		
		
	}

	@Override
	protected void validateInsert(User model) throws Exception {
		validateInsertOrUpdate(model);
		
		try {
			findByEmailAndPassword(model);
			throw new UniqueFieldError("email");
		} catch (EntityNotFoundError ex) {
			//ok
		}
		
		
	}

	@Override
	public void insert(User model) throws Exception {
		validateInsert(model);
		long did =   discountCRUD.findByCoupon(model.getDiscount().getCoupon()).getId();
		model.setDid(did);
		openConnection();
		getMapper().insert(model);
		closeConnection(true);
		
	}

	@Override
	protected void validateUpdate(User model) throws Exception {
		validateInsertOrUpdate(model);
		
	}

	@Override
	public void update(User model) throws Exception {
		validateUpdate(model);
		openConnection();
		getMapper().update(model);
		closeConnection(true);
		
	}

	@Override
	public User find(Long id) throws EntityNotFoundError {
		User ret = null;
		openConnection();
		ret = getMapper().find(id);
		closeConnection();
		
		if(ret==null) {
			throw new EntityNotFoundError();
		}
		return ret;
	}

	@Override
	public List<User> all() {
		List<User> ret = new ArrayList<User>();
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
	
	
	public User findByEmailAndPassword(User model) throws EntityNotFoundError {
		User ret = null;
		openConnection();
		ret = getMapper().findByEmailAndPassword(model);
		closeConnection();
		
		if(ret==null) {
			throw new EntityNotFoundError();
		}
		return ret;
	}

}
