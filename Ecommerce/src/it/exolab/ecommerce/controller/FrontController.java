package it.exolab.ecommerce.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.exolab.ecommerce.framework.Controller;
import it.exolab.ecommerce.framework.RequestMapping;
import it.exolab.ecommerce.framework.RequestMethod;


@Controller(url="/front")
public class FrontController  extends BaseController {
	public FrontController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		baseView = "/frontend/";
	}
	
	@RequestMapping(url="/home",requestMethod=RequestMethod.GET)
	public void home() throws ServletException, IOException {
		view = "index.jsp";
		includeCart=true;
		request.setAttribute("model",itemCRUD.all());
		view();
	}
	
	
	@RequestMapping(url="/get-image",requestMethod=RequestMethod.GET)
	public void getImage() throws ServletException, IOException {
		
		String image = request.getParameter("picture");
	    String imageSavePath =  request.getServletContext().getAttribute("repository_dir")+ File.separator+ image;
	    File file = new File(imageSavePath);
	    byte[] fileContent = Files.readAllBytes(file.toPath());

	    response.setContentType("image/jpg");
	    response.setHeader("Content-Disposition", "filename=\""+image+"\"");
	    response.setContentLength(fileContent.length);
	    OutputStream os = response.getOutputStream();

	    try {
	       os.write(fileContent , 0, fileContent.length);
	    } catch (Exception excp) {
	       //handle error
	    } finally {
	        os.close();
	    }
	    
	    
		
	}
	

	@RequestMapping(url="/error",requestMethod=RequestMethod.GET)
	public void error() throws ServletException, IOException {
		view = "error.jsp";
		String status = request.getParameter("status");
		if(status==null)
			status="500";
		request.setAttribute("status",status);
		view();
	}
	
}
