package it.exolab.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.exolab.ecommerce.exception.EntityNotFoundError;
import it.exolab.ecommerce.exception.FieldError;
import it.exolab.ecommerce.framework.Controller;
import it.exolab.ecommerce.framework.RequestMapping;
import it.exolab.ecommerce.framework.RequestMethod;
import it.exolab.ecommerce.model.Admin;


@Controller(url="/admin")
public class AdminController extends BaseController {
	
	
	public AdminController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		baseView = "/backend/";
	}
	
	
	@RequestMapping(url="/login",requestMethod=RequestMethod.GET)
	public void login() throws ServletException, IOException {
		view = "login.jsp";
		
		String err = (String)flash("err");
		Admin user = (Admin)flash("model");
		if(user==null)
			user = new Admin();
		
		
		
		request.setAttribute("model",user);
		request.setAttribute("err",err);
		view();
	}
	
	
	@RequestMapping(url="/logout",requestMethod=RequestMethod.GET)
	public void logout() throws ServletException, IOException {
		
		request.getSession().removeAttribute("admin");
		request.getSession().invalidate();
		sendRedirect("/admin/login");
		
		
		
	}
	
	@RequestMapping(url="/do-login",requestMethod=RequestMethod.POST)
	public void doLogin() throws ServletException, IOException {
		Admin model = new Admin();
		String err = "";
		model.setEmail(request.getParameter("email"));
		model.setPassword(request.getParameter("password"));
		
			
			
			Admin admin = (Admin) request.getServletContext().getAttribute("admin_test");
			
			if(model.equals(admin)) {
				request.getSession().setAttribute("admin",model);
				
				
				sendRedirect("/admin/home");
				return;	
			}
			
			
		
		err = "Credenziali errate";
		
		flash("err",err);
		flash("model",model);
		sendRedirect("/admin/login");
		
		
	}
	
	@RequestMapping(url="/home",requestMethod=RequestMethod.GET)
	public void home() throws ServletException, IOException {
		view = "home.jsp";
		view();
	}
	
		
	
}