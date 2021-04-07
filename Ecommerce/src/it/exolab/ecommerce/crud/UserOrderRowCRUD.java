package it.exolab.ecommerce.crud;

import java.util.ArrayList;
import java.util.List;

import it.exolab.ecommerce.exception.EntityNotFoundError;
import it.exolab.ecommerce.mapper.UserOrderRowMapper;
import it.exolab.ecommerce.model.UserOrderRow;

public class UserOrderRowCRUD extends GenericCRUD<UserOrderRow,UserOrderRowMapper> {

	UserOrderRowCRUD() {
		super(UserOrderRowMapper.class);
		
	}
	private void validateInsertOrUpdate(UserOrderRow model) throws Exception {
		
		
	}
	@Override
	protected void validateInsert(UserOrderRow model) throws Exception {
		validateInsertOrUpdate(model);
		
	}

	public void insert(UserOrderRow model,boolean openConnection) throws Exception {
		validateInsert(model);
		if(openConnection)
			openConnection();
		getMapper().insert(model);
		if(openConnection)
			closeConnection(true);
		
	}

	
	@Override
	public void insert(UserOrderRow model) throws Exception {
		insert(model,true);
	}

	@Override
	protected void validateUpdate(UserOrderRow model) throws Exception {
		validateInsertOrUpdate(model);
		
	}

	@Override
	public void update(UserOrderRow model) throws Exception {
		validateUpdate(model);
		openConnection();
		getMapper().update(model);
		closeConnection(true);
		
	}

	@Override
	public UserOrderRow find(Long id) throws EntityNotFoundError {
		UserOrderRow ret = null;
		openConnection();
		ret = getMapper().find(id);
		closeConnection();
		
		if(ret==null) {
			throw new EntityNotFoundError();
		}
		return ret;
	}

	@Override
	public List<UserOrderRow> all() {
		List<UserOrderRow> ret = new ArrayList<UserOrderRow>();
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

}
