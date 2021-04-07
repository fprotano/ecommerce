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
import it.exolab.ecommerce.model.Item;
import it.exolab.ecommerce.util.Utils;

@Controller(url="/admin-item")
public class AdminItemController extends BaseController {
	
	
	public AdminItemController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		baseView = "/backend/item/";
	}
	@RequestMapping(url="/remove",requestMethod=RequestMethod.GET)
	public void remove() throws ServletException, IOException {
		
		long id = Utils.getLongOrNull("id", request);
		itemCRUD.delete(id);
		sendRedirect("/admin-item/list");
		
	}
	
	@RequestMapping(url="/list",requestMethod=RequestMethod.GET)
	public void list() throws ServletException, IOException {
		view = "list.jsp";
		List<Item> model = this.itemCRUD.all();
		request.setAttribute("model",model);
		view();
	}
	
	@RequestMapping(url="/edit",requestMethod=RequestMethod.GET)
	public void edit() throws ServletException, IOException {
		
		long id = Utils.getLongOrNull("id", request);
		
		Item model;
		try {
			model = itemCRUD.find(id);
			request.setAttribute("model", model);
			request.setAttribute("categories", categoryCRUD.all());
			view = "form.jsp";
			view();
			
		} catch (EntityNotFoundError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(url="/add",requestMethod=RequestMethod.GET)
	public void add() throws ServletException, IOException {
		Item model = new Item();
		request.setAttribute("model", model);
		request.setAttribute("categories", categoryCRUD.all());
		view = "form.jsp";
		view();
	}
	
	@RequestMapping(url="/save",requestMethod=RequestMethod.POST)
	public void save() throws ServletException, IOException {
		
		
		
		    
		String err = null;
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String picture = uploadPicture("picture");
		Float price = Utils.getFloatOrNull("price", request);
		Integer quantity = Utils.getIntOrNull("quantity", request);
		Long cid = Utils.getLongOrNull("cid", request);
		Long id = Utils.getLongOrNull("id", request);
		
		boolean isInsert = id==null;
		
			
		
		
		
		try {
		
			Item model = null;
			if(!isInsert) {
				
				model = itemCRUD.find(id);
				
				
			} else {
				model = new Item();
				
				
			}
		
			if(picture!=null)
				model.setPicture(picture);
			
			model.setCid(cid);
			model.setDescription(description);
			model.setPrice(price);
			model.setQuantity(quantity);
			model.setTitle(title);
			model.setQuantity(quantity);
		
			
			if(isInsert) {
				itemCRUD.insert(model);	
			} else {
				model.setId(id);
				itemCRUD.update(model);
			}
			
			
			sendRedirect("/admin-item/list");
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
		
		
		
		sendRedirect( isInsert ? "/admin-item/add" : "/admin-item/edit?id="+id);
		
		
	}
	
	
	
	
	
	
	
		
	
}
