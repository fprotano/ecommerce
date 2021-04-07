package it.exolab.ecommerce.util;

import it.exolab.ecommerce.exception.MaxLengthError;
import it.exolab.ecommerce.exception.RequiredFieldError;

public class Validator {

	public void required(String field , String value) throws RequiredFieldError {
		if(Utils.isNullOrEmpty(value))
			throw new RequiredFieldError(field);
		
	}
	public void maxLength(String field , String value, int maxLength) throws MaxLengthError {
		if(value!=null && value.length()>maxLength)
			throw new MaxLengthError(field,maxLength);
		
	}
	
	
}
