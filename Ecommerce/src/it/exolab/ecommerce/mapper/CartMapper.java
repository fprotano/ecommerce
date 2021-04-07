package it.exolab.ecommerce.mapper;

import java.util.List;

import it.exolab.ecommerce.model.Cart;

public interface CartMapper extends GenericMapper<Cart> {
	List<Cart> all(String session_id);
	Cart findByIid(Cart model);
	int count(String session_id);
	
}
