package it.exolab.ecommerce.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.exolab.ecommerce.crud.CartCRUD;
import it.exolab.ecommerce.crud.UserCRUD;
import it.exolab.ecommerce.exception.EntityNotFoundError;
import it.exolab.ecommerce.exception.FieldError;
import it.exolab.ecommerce.framework.Controller;
import it.exolab.ecommerce.framework.RequestMapping;
import it.exolab.ecommerce.framework.RequestMethod;
import it.exolab.ecommerce.model.User;
import it.exolab.ecommerce.util.Utils;

@Controller(url="/user")
public class UserController extends BaseController {
	
	UserCRUD userCRUD = new UserCRUD();
	public UserController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		baseView = "/frontend/user/";
	}
	
	@RequestMapping(url="/login",requestMethod=RequestMethod.GET)
	public void login() throws ServletException, IOException {
		view = "login.jsp";
		
		String err = (String)flash("err");
		User user = (User)flash("model");
		if(user==null)
			user = new User();
		
		
		
		request.setAttribute("model",user);
		request.setAttribute("err",err);
		view();
	}
	
	
	@RequestMapping(url="/logout",requestMethod=RequestMethod.GET)
	public void logout() throws ServletException, IOException {
		
		request.getSession().removeAttribute("user");
		request.getSession().invalidate();
		sendRedirect("/front/home");
		
		
		
	}
	
	@RequestMapping(url="/do-login",requestMethod=RequestMethod.POST)
	public void doLogin() throws ServletException, IOException {
		User user = new User();
		String err = "";
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		try {
			user = userCRUD.findByEmailAndPassword(user);
			
			user.setSession_id(Utils.createOrGetCookie(request, response));
			request.getSession().setAttribute("user",user);
			
			
			sendRedirect("/user/check-cart");
			return;
		} catch (EntityNotFoundError e) {
		
		}
		
		err = "Credenziali errate";
		
		flash("err",err);
		flash("model",user);
		response.sendRedirect("login");
		
		
	}
	
	@RequestMapping(url="/home",requestMethod=RequestMethod.GET)
	public void home() throws ServletException, IOException {
		view = "home.jsp";
		int orders = this.userOrderCRUD.countByUid(user.getId());
		request.setAttribute("orders", orders);
		view();
	}
	
	
	@RequestMapping(url="/register",requestMethod=RequestMethod.GET)
	public void register() throws ServletException, IOException {
		view = "register.jsp";
		
		//String err = (String)flash("err");
		User user = (User)flash("model");
		if(user==null)
			user = new User();
		
		
		
		request.setAttribute("model",user);
		//request.setAttribute("err",err);
		view();
	}
	
	@RequestMapping(url="/do-register",requestMethod=RequestMethod.POST)
	public void doRegister() throws ServletException, IOException {
		User user = new User();
		String err = "";
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setName(request.getParameter("name"));
		user.setSurname(request.getParameter("surname"));
		user.setPhone(request.getParameter("phone"));
		user.setAddress(request.getParameter("address"));
		user.getDiscount().setCoupon(request.getParameter("coupon"));
		
		
		
		try {
			userCRUD.insert(user);
			sendRedirect("/user/login");
			return;
	
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			err = "Errore sconosciuto";
			if(e instanceof FieldError) {
				err =  ((FieldError)e).getDescription(e);
			}
			
			if(e instanceof EntityNotFoundError) {
				err =  "Coupon non trovato";
			}
			
		}
		
		
		
		flash("err",err);
		flash("model",user);
		sendRedirect("/user/register");
		
		
	}
	
	@RequestMapping(url="/check-cart",requestMethod=RequestMethod.GET)
	public void checkCart() throws ServletException, IOException {
		
		
		if(user==null) {
			sendRedirect("/front/home");
			return;
		}
		
		CartCRUD crud = new CartCRUD();
		int cart_records = crud.count(user.getSession_id());
		if(cart_records>0) {
			sendRedirect("/cart/list");
			return;
		}
		
		sendRedirect("/front/home");
		
		
		
	}
	
	
	
}
