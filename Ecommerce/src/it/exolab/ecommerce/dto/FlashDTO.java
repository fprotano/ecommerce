package it.exolab.ecommerce.dto;

import javax.servlet.http.HttpServletRequest;

public class FlashDTO {
	
	HttpServletRequest request;
	
	
	public FlashDTO(HttpServletRequest request) {
		super();
		this.request = request;
	}
	public boolean hasAttribute(String attribute) {
		
		Object ret = request.getSession().getAttribute(attribute);
		
		if(ret!=null) {
			return true;
		}
		return false;
	}
	

	public Object getAttribute(String attribute) {
		
		Object ret = request.getSession().getAttribute(attribute);
		
		if(ret!=null) {
			request.getSession().removeAttribute(attribute);
		}
		return ret;
	}
	
}
