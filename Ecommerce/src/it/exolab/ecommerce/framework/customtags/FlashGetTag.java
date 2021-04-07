package it.exolab.ecommerce.framework.customtags;
import javax.servlet.jsp.tagext.*;

import it.exolab.ecommerce.dto.FlashDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class FlashGetTag extends SimpleTagSupport {
	
private String attribute;
	


	public String getAttribute() {
		return attribute;
	}



	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public void doTag() throws JspException, IOException {
		
		JspWriter jspWriter = getJspContext().getOut();
	  
	    
	    PageContext pageContext = (PageContext) getJspContext();  
	    HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
	    
	    FlashDTO flash = new FlashDTO(request);

       
        // Output to the JSP writer
        jspWriter.write( (String)flash.getAttribute(attribute));
        
    }
}
