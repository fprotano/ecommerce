package it.exolab.ecommerce.service;

import java.util.List;

import it.exolab.ecommerce.crud.CartCRUD;
import it.exolab.ecommerce.dto.CartDTO;
import it.exolab.ecommerce.model.Cart;
import it.exolab.ecommerce.model.User;

public class UserService {
	
	public CartDTO getCart(String session_id,User user) {
		int discount = user!=null && user.getDiscount()!=null
				? user.getDiscount().getDiscount():0;
		
		CartCRUD cartCRUD = new CartCRUD();
		List<Cart> rows =  cartCRUD.all(session_id);
		float total=0;
		float cartTotal=0;
		float discountTotal=0;
		for(Cart row : rows) {
			float sub_total = row.getPrice()*row.getQuantity();
			cartTotal+=sub_total;
			discountTotal += sub_total * ((float)discount/100);
			sub_total -= sub_total * ((float)discount/100);
			total+=sub_total;
		}
		
		CartDTO ret = new CartDTO();
		ret.setRows(rows);
		ret.setTotal(total);
		ret.setCartTotal(cartTotal);
		ret.setDiscountTotal(discountTotal);
		
		return ret;
	}

}
