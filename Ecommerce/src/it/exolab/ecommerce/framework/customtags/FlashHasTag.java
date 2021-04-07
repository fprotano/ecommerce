package it.exolab.ecommerce.framework.customtags;
import javax.servlet.jsp.tagext.*;

import it.exolab.ecommerce.dto.FlashDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.*;
import java.io.*;


public class FlashHasTag extends SimpleTagSupport {
	
	private String attribute;
	


	public String getAttribute() {
		return attribute;
	}



	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}



	public void doTag() throws JspException, IOException {
		
		StringWriter sw = new StringWriter();
		 
	    
	    PageContext pageContext = (PageContext) getJspContext();  
	    HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
	    
	    FlashDTO flash = new FlashDTO(request);
	    
	    if(flash.hasAttribute(attribute)) {
	    	getJspBody().invoke(sw);
	    	getJspContext().getOut().println(sw.toString());
	    }
	    	
	            
    }
}
