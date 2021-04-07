package it.exolab.ecommerce.crud;

import java.util.List;

import it.exolab.ecommerce.exception.EntityNotFoundError;
import it.exolab.ecommerce.mybatis.SqlMapFactory;
import it.exolab.ecommerce.util.Validator;

public abstract class GenericCRUD<T,K>  {

	private Class mapper;
	protected Validator validator = new Validator();
	GenericCRUD(Class mapper){
		this.mapper = mapper;
	}
	protected K getMapper() {
		return (K)SqlMapFactory.instance().getMapper(mapper);
	}
	
	protected void openConnection() {
		SqlMapFactory.instance().openSession();
	}
	
	protected void rollback() {
		SqlMapFactory.instance().rollbackSession();
		closeConnection();
	}
	
	
	protected void closeConnection() {
		closeConnection(false);
	}
	protected void closeConnection(boolean commit) {
		if(commit)
			SqlMapFactory.instance().commitSession();
		SqlMapFactory.instance().closeSession();
	}
	
	protected abstract void validateInsert(T model) throws Exception;
	public abstract void insert(T model) throws Exception;
	protected abstract void validateUpdate(T model) throws Exception;
	public abstract void update(T model) throws Exception;
	public abstract T find(Long id) throws EntityNotFoundError;
	public abstract List<T> all();
	public abstract void delete(Long id);
	
	
}
