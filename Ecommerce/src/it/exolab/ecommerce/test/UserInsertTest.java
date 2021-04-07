package it.exolab.ecommerce.test;

import it.exolab.ecommerce.crud.UserCRUD;
import it.exolab.ecommerce.exception.FieldError;
import it.exolab.ecommerce.model.User;

public class UserInsertTest {

	public static void main(String[] args) {
		User user = new User();
		user.setName("mario1");
		user.setSurname("rossi1");
		user.setEmail("mario.rossi1@gmail.com");
		user.setPassword("demo");
		user.setPhone("+396713349");
		user.setAddress("via delle vie");
		user.getDiscount().setCoupon("abc");
		UserCRUD userCRUD = new UserCRUD();
		try {
			userCRUD.insert(user);
			System.out.println("Utente inserito con id "+ user.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			if(e instanceof FieldError) {
				System.out.println( ((FieldError)e).getDescription(e));
			}
		}
				

	}

}
