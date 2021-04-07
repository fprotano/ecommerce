package it.exolab.ecommerce.mapper;

import it.exolab.ecommerce.exception.EntityNotFoundError;
import it.exolab.ecommerce.model.Discount;

public interface DiscountMapper  extends GenericMapper<Discount> {

	public Discount findByCoupon(String coupon) throws EntityNotFoundError;
}
