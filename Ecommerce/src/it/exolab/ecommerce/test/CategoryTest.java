package it.exolab.ecommerce.test;

import it.exolab.ecommerce.crud.CategoryCRUD;
import it.exolab.ecommerce.model.Category;

public class CategoryTest {

	public static void main(String[] args) {
		
		CategoryCRUD crud = new CategoryCRUD();
		try {
			crud.insert(new Category("Test"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
