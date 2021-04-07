package it.exolab.ecommerce.framework.customtags;
import javax.servlet.jsp.tagext.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class FormTag extends SimpleTagSupport {
	
	private String action;
	private String method;
	private String onsubmit;
	private String enctype;
	
	   
	public String getEnctype() {
		return enctype;
	}



	public void setEnctype(String enctype) {
		this.enctype = enctype;
	}



	public String getOnsubmit() {
		return onsubmit;
	}



	public void setOnsubmit(String onsubmit) {
		this.onsubmit = onsubmit;
	}



	public String getAction() {
		return action;
	}



	public void setAction(String action) {
		this.action = action;
	}



	public String getMethod() {
		return method;
	}



	public void setMethod(String method) {
		this.method = method;
	}



	public void doTag() throws JspException, IOException {
		
		JspWriter jspWriter = getJspContext().getOut();
	    StringWriter stringWriter = new StringWriter();
	    StringBuffer bodyContent = new StringBuffer();
	        
	    
	    PageContext pageContext = (PageContext) getJspContext();  
	    HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		String base_path = (String)request.getAttribute("base_path");
		 // Execute the tag's body into an internal writer
        getJspBody().invoke(stringWriter);


        String form = "<form";
        form+= " action=\""+ base_path + action+"\"";
        form+= " method=\""+ method+"\"";
        if(onsubmit!=null && !onsubmit.equals("")) {
        	form+= " onsubmit=\""+ onsubmit+"\"";
        }
        if(enctype!=null && !enctype.equals("")) {
        	form+= " enctype=\""+ enctype+"\"";
        }
        form+= ">";
        bodyContent.append(form);
        bodyContent.append(stringWriter.getBuffer());
        bodyContent.append("</form>");

        // Output to the JSP writer
        jspWriter.write(bodyContent.toString());
        
    }
}
