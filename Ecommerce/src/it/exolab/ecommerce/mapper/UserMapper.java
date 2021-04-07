package it.exolab.ecommerce.mapper;

import it.exolab.ecommerce.model.User;

public interface UserMapper   extends GenericMapper<User> {

	
	public User findByEmailAndPassword(User model);
}
