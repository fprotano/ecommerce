package it.exolab.ecommerce.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import it.exolab.ecommerce.crud.CartCRUD;
import it.exolab.ecommerce.crud.CategoryCRUD;
import it.exolab.ecommerce.crud.DiscountCRUD;
import it.exolab.ecommerce.crud.ItemCRUD;
import it.exolab.ecommerce.crud.UserCRUD;
import it.exolab.ecommerce.crud.UserOrderCRUD;
import it.exolab.ecommerce.model.User;
import it.exolab.ecommerce.service.UserService;
import it.exolab.ecommerce.util.Utils;
public class BaseController {

	
	protected String view;
	protected String baseView;
	protected boolean includeCart=false;
	protected DiscountCRUD discountCRUD = new DiscountCRUD();
	protected CartCRUD cartCRUD = new CartCRUD();
	protected UserCRUD userCRUD = new UserCRUD();
	protected ItemCRUD itemCRUD = new ItemCRUD();
	protected UserOrderCRUD userOrderCRUD = new UserOrderCRUD();
	protected CategoryCRUD categoryCRUD = new CategoryCRUD();
	protected UserService userService = new UserService();
	protected User user=null;
	protected String session_id=null;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected BaseController() {
		
		
	}
	
	public BaseController(HttpServletRequest request, HttpServletResponse response) {
		this.request=request;
		this.response=response;
		session_id = Utils.createOrGetCookie(request, response);
		user = null;
		if(request.getSession().getAttribute("user")!=null) {
			user = (User)request.getSession().getAttribute("user");
		}
			
			
		
		
	}
	protected void view() throws ServletException, IOException {
		
		
		if(includeCart && session_id!=null) {
			request.setAttribute("cart", userService.getCart(session_id, user) );
		}
		request.getRequestDispatcher(baseView+ view).include(request, response);
		
		
	}
	protected Object flash(String attribute) {
		
		Object ret = request.getSession().getAttribute(attribute);
		
		if(ret!=null) {
			request.getSession().removeAttribute(attribute);
		}
		return ret;
	}
	protected Object flash(String attribute,Object value) {
		
		request.getSession().setAttribute(attribute,value);
		return null;
	}
	protected void sendRedirect(String url) throws IOException {
		
		String base_path = (String)request.getAttribute("base_path");
		
		response.sendRedirect(base_path+url);
	}
	
	
	
	protected String uploadPicture(String field) throws IOException, ServletException {
		
		
		String ret=null;
		
		String imageSavePath = (String) request.getServletContext().getAttribute("repository_dir");
		Part filePart = request.getPart(field);
        String picture = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        FileOutputStream outputStream = null;
        InputStream fileContent = null;
        
        try {
        	

            outputStream = new FileOutputStream(new File(imageSavePath + File.separator + picture));
            //creating a new file with file path and the file name
            fileContent = filePart.getInputStream();
            //getting the input stream
            int readBytes = 0;
            byte[] readArray = new byte[1024];
            //initializing a byte array with size 1024

            while ((readBytes = fileContent.read(readArray)) != -1) {
                outputStream.write(readArray, 0, readBytes);
            }//this loop will write the contents of the byte array unitl the end to the output stream
            ret = picture;
            
        } catch (Exception ex) {
            System.out.println("Error Writing File: " + ex);
        } finally {
            if (outputStream != null) {
                outputStream.close();
                //closing the output stream
            }
            if (fileContent != null) {
                fileContent.close();
                //clocsing the input stream
            }
        }
    	return ret;
    }
	
	
	
}
