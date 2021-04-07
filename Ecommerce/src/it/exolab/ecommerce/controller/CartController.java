package it.exolab.ecommerce.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.scenario.effect.Blend.Mode;

import it.exolab.ecommerce.exception.EntityNotFoundError;
import it.exolab.ecommerce.exception.FieldError;
import it.exolab.ecommerce.exception.ItemNotAvailableError;
import it.exolab.ecommerce.framework.Controller;
import it.exolab.ecommerce.framework.RequestMapping;
import it.exolab.ecommerce.framework.RequestMethod;
import it.exolab.ecommerce.model.Cart;
import it.exolab.ecommerce.model.Item;
import it.exolab.ecommerce.model.User;
import it.exolab.ecommerce.util.Utils;

@Controller(url="/cart")
public class CartController extends BaseController {

	
	public CartController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		baseView = "/frontend/cart/";
	}
	@RequestMapping(url="/list",requestMethod=RequestMethod.GET)
	public void list() throws ServletException, IOException {
		includeCart=true;
		view = "list.jsp";
		view();
	}
	
	
	@RequestMapping(url="/insert",requestMethod=RequestMethod.POST)
	public void insert() throws ServletException, IOException {
		
		String err = null;
		Integer quantity = Utils.getIntOrNull("quantity", request);
		Long iid = Utils.getLongOrNull("iid", request);
		
		Item item;
		try {
			item = itemCRUD.find(iid);
			Cart model = new Cart();
			model.setIid(iid);
			model.setQuantity(quantity);
			model.setSession_id(session_id);
			model.setPrice(item.getPrice());
			cartCRUD.insert(model);
			
		} catch (EntityNotFoundError e) {
			err="Errore sconosciuto";
		} catch (Exception e) {
			e.printStackTrace();
			err="Errore sconosciuto";
			if(e instanceof FieldError) {
				err = ((FieldError)e).getDescription(e);
			}
			
			if(e instanceof ItemNotAvailableError) {
				err = "prodotto non disponibile";
			}
		}
		
		if(err!=null)
			flash("err",err);
		
		
		int count = this.cartCRUD.count(session_id);
		request.getSession().setAttribute("items_in_cart", count);
		
		sendRedirect("/front/home");
		
		
	}
	
	
	@RequestMapping(url="/remove",requestMethod=RequestMethod.GET)
	public void remove() throws ServletException, IOException {
		
		String err = null;
		Long id = Utils.getLongOrNull("id", request);
		try {
			cartCRUD.delete(id);
			
		} catch (Exception e) {
			e.printStackTrace();
			err="Errore sconosciuto";
			if(e instanceof FieldError) {
				err = ((FieldError)e).getDescription(e);
			}
		}
		
		if(err!=null)
			flash("err",err);
		
		int count = this.cartCRUD.count(session_id);
		request.getSession().setAttribute("items_in_cart", count);
		
		
		sendRedirect("/front/home");
		
		
	}
	

	
}
