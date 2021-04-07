package it.exolab.ecommerce.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import it.exolab.ecommerce.exception.EntityNotFoundError;
import it.exolab.ecommerce.exception.FieldError;
import it.exolab.ecommerce.framework.Controller;
import it.exolab.ecommerce.framework.RequestMapping;
import it.exolab.ecommerce.framework.RequestMethod;
import it.exolab.ecommerce.model.Discount;
import it.exolab.ecommerce.model.Item;
import it.exolab.ecommerce.util.Utils;

@Controller(url="/admin-discount")
public class AdminDiscountController extends BaseController {
	
	
	public AdminDiscountController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		baseView = "/backend/discount/";
	}
	@RequestMapping(url="/remove",requestMethod=RequestMethod.GET)
	public void remove() throws ServletException, IOException {
		
		long id = Utils.getLongOrNull("id", request);
		discountCRUD.delete(id);
		sendRedirect("/admin-discount/list");
		
	}
	
	@RequestMapping(url="/list",requestMethod=RequestMethod.GET)
	public void list() throws ServletException, IOException {
		view = "list.jsp";
		List<Discount> model = discountCRUD.all();
		request.setAttribute("model",model);
		view();
	}
	
	@RequestMapping(url="/edit",requestMethod=RequestMethod.GET)
	public void edit() throws ServletException, IOException {
		
		long id = Utils.getLongOrNull("id", request);
		
		Discount model;
		try {
			model = discountCRUD.find(id);
			request.setAttribute("model", model);
			
			view = "form.jsp";
			view();
			
		} catch (EntityNotFoundError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(url="/add",requestMethod=RequestMethod.GET)
	public void add() throws ServletException, IOException {
		Discount model = new Discount();
		request.setAttribute("model", model);
		
		view = "form.jsp";
		view();
	}
	
	@RequestMapping(url="/save",requestMethod=RequestMethod.POST)
	public void save() throws ServletException, IOException {
		
		
		
		    
		String err = null;
		String coupon = request.getParameter("coupon");
		Integer discount = Utils.getIntOrNull("discount", request);
		Long id = Utils.getLongOrNull("id", request);
		
		boolean isInsert = id==null;
		
			
		
		
		
		try {
		
			Discount model = null;
			if(!isInsert) {
				
				model = discountCRUD.find(id);
				
				
			} else {
				model = new Discount();
				
				
			}
		
			model.setCoupon(coupon);
			model.setDiscount(discount);
			
			
			if(isInsert) {
				discountCRUD.insert(model);	
			} else {
				model.setId(id);
				discountCRUD.update(model);
			}
			
			
			sendRedirect("/admin-discount/list");
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
		
		
		
		sendRedirect( isInsert ? "/admin-discount/add" : "/admin-discount/edit?id="+id);
		
		
	}
	
	
	
	
	
	
	
		
	
}
