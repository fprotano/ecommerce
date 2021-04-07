package it.exolab.ecommerce.test;

import com.sun.scenario.effect.Blend.Mode;

import it.exolab.ecommerce.crud.DiscountCRUD;
import it.exolab.ecommerce.model.Discount;

public class DiscountTest {

	public static void main(String[] args) {
		DiscountCRUD crud = new DiscountCRUD();
		Discount model = new Discount("abc",10);
		
		try {
			crud.insert(model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
