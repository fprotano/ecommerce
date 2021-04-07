package it.exolab.ecommerce.crud;

import java.util.ArrayList;
import java.util.List;

import it.exolab.ecommerce.exception.EntityNotFoundError;
import it.exolab.ecommerce.exception.UniqueFieldError;
import it.exolab.ecommerce.mapper.DiscountMapper;
import it.exolab.ecommerce.model.Category;
import it.exolab.ecommerce.model.Discount;

public class DiscountCRUD   extends GenericCRUD<Discount,DiscountMapper> {

	public DiscountCRUD() {
		super(DiscountMapper.class);

	}

	@Override
	protected void validateInsert(Discount model) throws Exception {
		validateInsertOrUpdate(model);
		
		try {
			findByCoupon(model.getCoupon());
			throw new UniqueFieldError("coupon");
		} catch (EntityNotFoundError ex) {
			//ok
		}
		
	}
	
	private void validateInsertOrUpdate(Discount model) throws Exception {
		validator.required("coupon", model.getCoupon());
		validator.maxLength("coupon", model.getCoupon(), 6);
		
		
		
	}

	@Override
	public void insert(Discount model) throws Exception {
		validateInsert(model);
		openConnection();
		getMapper().insert(model);
		closeConnection(true);
		
	}

	@Override
	protected void validateUpdate(Discount model) throws Exception {
		validateInsertOrUpdate(model);
		
	}

	@Override
	public void update(Discount model) throws Exception {
		validateUpdate(model);
		openConnection();
		getMapper().update(model);
		closeConnection(true);
		
	}

	@Override
	public Discount find(Long id) throws EntityNotFoundError {
		Discount ret = null;
		openConnection();
		ret = getMapper().find(id);
		closeConnection();
		
		if(ret==null) {
			throw new EntityNotFoundError();
		}
		return ret;
	}

	@Override
	public List<Discount> all() {
		List<Discount> ret = new ArrayList<Discount>();
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
	

	public Discount findByCoupon(String coupon) throws EntityNotFoundError {
		Discount ret = null;
		openConnection();
		ret = getMapper().findByCoupon(coupon);
		closeConnection();
		
		if(ret==null) {
			throw new EntityNotFoundError();
		}
		return ret;
	}

}
