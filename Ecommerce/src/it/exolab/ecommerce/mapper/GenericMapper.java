package it.exolab.ecommerce.mapper;

import java.util.List;

public interface GenericMapper<T> {

	void insert(T model);
	void update(T model);
	T find(Long id);
	List<T> all();
	void delete(Long id); 
	void deleteBySessionId(String session_id);
}
