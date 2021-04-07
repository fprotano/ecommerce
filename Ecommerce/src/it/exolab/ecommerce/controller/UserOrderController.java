package it.exolab.ecommerce.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.exolab.ecommerce.exception.EntityNotFoundError;
import it.exolab.ecommerce.exception.FieldError;
import it.exolab.ecommerce.framework.Controller;
import it.exolab.ecommerce.framework.RequestMapping;
import it.exolab.ecommerce.framework.RequestMethod;
import it.exolab.ecommerce.model.User;
import it.exolab.ecommerce.model.UserOrder;
import it.exolab.ecommerce.util.Utils;

@Controller(url="/order")
public class UserOrderController extends BaseController {
	public UserOrderController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		baseView = "/frontend/order/";
	}
	
	
	@RequestMapping(url="/confirm-cart",requestMethod=RequestMethod.POST)
	public void confirm() throws ServletException, IOException {
		
		String err = null;
		
		if(user==null) {
			sendRedirect("/user/login");
			return;
		}
		
		try {
			
			userOrderCRUD.confirm(user);
		} catch (Exception e) {
			e.printStackTrace();
			err="Errore sconosciuto";
			if(e instanceof FieldError) {
				err = ((FieldError)e).getDescription(e);
			}
		}
		
		if(err!=null)
			flash("err",err);
		
		
		request.getSession().removeAttribute("items_in_cart");
		sendRedirect("/order/my-orders");
		
		
	}
	
	@RequestMapping(url="/my-orders",requestMethod=RequestMethod.GET)
	public void myOrders() throws ServletException, IOException {
		
		view = "my_orders.jsp";
		
		request.setAttribute("model",userOrderCRUD.findAllByUid(user.getId()));
		view();
		
		
	}
	
	
	@RequestMapping(url="/my-orders/view",requestMethod=RequestMethod.GET)
	public void myOrderView() throws ServletException, IOException {
		
		view = "my_order_view.jsp";
		long id = Utils.getLongOrNull("id", request);
		UserOrder model;
		try {
			model = userOrderCRUD.find(id);
			request.setAttribute("model",model);
			view();
		} catch (EntityNotFoundError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
}
