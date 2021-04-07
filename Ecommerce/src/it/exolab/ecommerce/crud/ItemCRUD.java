package it.exolab.ecommerce.crud;

import java.util.ArrayList;
import java.util.List;

import com.sun.scenario.effect.Blend.Mode;

import it.exolab.ecommerce.exception.EntityNotFoundError;
import it.exolab.ecommerce.exception.FormatError;
import it.exolab.ecommerce.exception.ItemNotAvailableError;
import it.exolab.ecommerce.mapper.ItemMapper;
import it.exolab.ecommerce.model.Item;

public class ItemCRUD extends GenericCRUD<Item,ItemMapper> {

	public ItemCRUD() {
		super(ItemMapper.class);

	}
	private void validateInsertOrUpdate(Item model) throws Exception {
		validator.required("title", model.getTitle());
		validator.maxLength("title", model.getTitle(), 70);
		
		validator.required("picture", model.getPicture());
		validator.maxLength("picture", model.getPicture(), 100);
		
	}
	private void validateUpdateQuantity(Item model) throws Exception {
		validateUpdateQuantity(model,true);
		
	}
	private void validateUpdateQuantity(Item model,boolean openConnection) throws Exception {
		if(model.getQuantity()<=0) {
			throw new FormatError("quantity");
		}
		if(model.getId()==0) {
			throw new EntityNotFoundError();
		}
		
		Item exists = find(model.getId(),openConnection);
		if(exists.getQuantity()<model.getQuantity()) {
			throw new ItemNotAvailableError();
		}
		
	}
	
	@Override
	protected void validateInsert(Item model) throws Exception {
		validateInsertOrUpdate(model);
		
	}

	@Override
	public void insert(Item model) throws Exception {
		validateInsert(model);
		openConnection();
		getMapper().insert(model);
		closeConnection(true);
		
	}

	@Override
	protected void validateUpdate(Item model) throws Exception {
		validateInsertOrUpdate(model);
		
	}
	public void update(Item model,boolean openConnection) throws Exception {
		validateUpdate(model);
		if(openConnection)
			openConnection();
		getMapper().update(model);
		if(openConnection)
			closeConnection(true);
		
	}
	
	@Override
	public void update(Item model) throws Exception {
		update(model,true);
		
	}
	public Item find(Long id,boolean openConnection) throws EntityNotFoundError {
		Item ret = null;
		if(openConnection)
			openConnection();
		ret = getMapper().find(id);
		if(openConnection)
			closeConnection();
		
		if(ret==null) {
			throw new EntityNotFoundError();
		}
		return ret;
	}
	
	@Override
	public Item find(Long id) throws EntityNotFoundError {
		return find(id,true);
	}

	@Override
	public List<Item> all() {
		List<Item> ret = new ArrayList<Item>();
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

	public void updateQuantity(Item model) throws Exception {
		updateQuantity(model,true);
		
	}
	public void updateQuantity(Item model,boolean openConnection) throws Exception {
		validateUpdateQuantity(model,openConnection);
		if(openConnection)
			openConnection();
		getMapper().updateQuantity(model);
		if(openConnection)
			closeConnection(true);
		
	}
	
	
	
}
