package it.exolab.ecommerce.test;

import it.exolab.ecommerce.crud.CartCRUD;
import it.exolab.ecommerce.crud.ItemCRUD;
import it.exolab.ecommerce.exception.EntityNotFoundError;
import it.exolab.ecommerce.model.Cart;
import it.exolab.ecommerce.model.Item;

public class CartInsertTest {

	public static void main(String[] args) {
		CartCRUD crud = new CartCRUD();
		ItemCRUD itemCRUD = new ItemCRUD();
		Cart cart = new Cart();
		try {
			cart.setSession_id("abc");
			Item item = itemCRUD.find(1L);
			cart.setIid(item.getId());
			cart.setPrice(item.getPrice());
			cart.setQuantity(99);
			crud.insert(cart);
			
			System.out.println("Riga nella carrello generata con id "+ cart.getId());
			
		} catch (EntityNotFoundError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
