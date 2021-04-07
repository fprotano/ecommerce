package it.exolab.ecommerce.crud;

import java.util.ArrayList;
import java.util.List;


import it.exolab.ecommerce.exception.EntityNotFoundError;
import it.exolab.ecommerce.mapper.CategoryMapper;
import it.exolab.ecommerce.model.Category;

public class CategoryCRUD extends GenericCRUD<Category,CategoryMapper> {

	
	public CategoryCRUD() {
		super(CategoryMapper.class);

	}

	@Override
	protected void validateInsert(Category model) throws Exception {
		validateInsertOrUpdate(model);
	}

	@Override
	public void insert(Category model) throws Exception {
		validateInsert(model);
		openConnection();
		getMapper().insert(model);
		closeConnection(true);
		
	}

	@Override
	protected void validateUpdate(Category model) throws Exception {
		validateInsertOrUpdate(model);
		
	}

	private void validateInsertOrUpdate(Category model) throws Exception {
		validator.required("title", model.getTitle());
		validator.maxLength("title", model.getTitle(), 70);
		
	}
	
	@Override
	public void update(Category model) throws Exception {
		validateUpdate(model);
		openConnection();
		getMapper().update(model);
		closeConnection(true);
		
	}

	@Override
	public Category find(Long id) throws EntityNotFoundError {
		Category ret = null;
		openConnection();
		ret = getMapper().find(id);
		closeConnection();
		
		if(ret==null) {
			throw new EntityNotFoundError();
		}
		return ret;
	}

	@Override
	public List<Category> all() {
		List<Category> ret = new ArrayList<Category>();
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
