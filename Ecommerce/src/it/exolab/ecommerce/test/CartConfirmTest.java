package it.exolab.ecommerce.test;

import it.exolab.ecommerce.crud.CartCRUD;
import it.exolab.ecommerce.crud.ItemCRUD;
import it.exolab.ecommerce.crud.UserOrderCRUD;
import it.exolab.ecommerce.exception.EntityNotFoundError;
import it.exolab.ecommerce.exception.FieldError;
import it.exolab.ecommerce.model.Cart;
import it.exolab.ecommerce.model.Item;
import it.exolab.ecommerce.model.User;

public class CartConfirmTest {

	public static void main(String[] args) {
		UserOrderCRUD userOrderCRUD = new UserOrderCRUD();
		User user = new User();
		user.setSession_id("abc");
		user.setId(1);
		user.setDid(1);
		try {
			userOrderCRUD.confirm(user);
		} catch (Exception e) {
			
			e.printStackTrace();
			if(e instanceof FieldError) {
				System.out.println( ((FieldError)e).getDescription(e));
			}
			
		}
		
	}

}
