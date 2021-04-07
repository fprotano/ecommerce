package it.exolab.ecommerce.mapper;

import java.util.List;

import it.exolab.ecommerce.model.UserOrder;

public interface UserOrderMapper  extends GenericMapper<UserOrder> {
	List<UserOrder> findAllByUid(long uid);
	int countByUid(long uid);
}
