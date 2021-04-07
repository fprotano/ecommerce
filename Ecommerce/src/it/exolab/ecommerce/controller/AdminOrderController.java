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

import it.exolab.ecommerce.model.UserOrder;
import it.exolab.ecommerce.util.Utils;

@Controller(url="/admin-order")
public class AdminOrderController extends BaseController {
	
	
	public AdminOrderController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		baseView = "/backend/order/";
	}
	
	@RequestMapping(url="/list",requestMethod=RequestMethod.GET)
	public void list() throws ServletException, IOException {
		view = "list.jsp";
		List<UserOrder> model =userOrderCRUD.all();
		request.setAttribute("model",model);
		view();
	}
	
	@RequestMapping(url="/view",requestMethod=RequestMethod.GET)
	public void details() throws ServletException, IOException {
		
		long id = Utils.getLongOrNull("id", request);
		
		UserOrder model;
		try {
			
			model = userOrderCRUD.find(id);
			request.setAttribute("model", model);
			
			view = "view.jsp";
			view();
			
		} catch (EntityNotFoundError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	@RequestMapping(url="/update",requestMethod=RequestMethod.POST)
	public void update() throws ServletException, IOException {
		
		
		
		    
		String err = null;
		
		Long id = Utils.getLongOrNull("id", request);
		
		
			
		boolean isSetStatusPresoInCarico = request.getParameter("setStatusPresoInCarico")!=null; 
		boolean isSetStatusSpedito = request.getParameter("setStatusSpedito")!=null;
		boolean isSetStatusConsegnato = request.getParameter("setStatusConsegnato")!=null;
		
		
		
		try {
		
				
			
			UserOrder model = userOrderCRUD.find(id);
			if(isSetStatusPresoInCarico) {
				userOrderCRUD.updateSetPrendiInCarico(model);
			}
			if(isSetStatusSpedito) {
				userOrderCRUD.updateSetSpedito(model);
			}
			if(isSetStatusConsegnato) {
				userOrderCRUD.updateSetConsegnato(model);
			}
			
			
			

			
			
			
			sendRedirect("/admin-order/list");
			return;
		} catch (EntityNotFoundError e) {
			err="Errore sconosciuto";
		} catch (Exception e) {
			e.printStackTrace();
			err="Errore sconosciuto";
			if(e instanceof FieldError) {
				err = ((FieldError)e).getDescription(e);
			}
			
			
		}
		
		if(err!=null)
			flash("err",err);
		
		
		
		sendRedirect( "/admin-order/view?id="+id);
		
		
	}
	
	
	
	
	
	
	
		
	
}
