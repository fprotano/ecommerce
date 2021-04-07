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
import it.exolab.ecommerce.model.Category;
import it.exolab.ecommerce.model.Item;
import it.exolab.ecommerce.util.Utils;

@Controller(url="/admin-category")
public class AdminCategoryController extends BaseController {
	
	
	public AdminCategoryController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		baseView = "/backend/category/";
	}
	@RequestMapping(url="/remove",requestMethod=RequestMethod.GET)
	public void remove() throws ServletException, IOException {
		
		long id = Utils.getLongOrNull("id", request);
		categoryCRUD.delete(id);
		sendRedirect("/admin-category/list");
		
	}
	
	@RequestMapping(url="/list",requestMethod=RequestMethod.GET)
	public void list() throws ServletException, IOException {
		view = "list.jsp";
		List<Category> model = categoryCRUD.all();
		request.setAttribute("model",model);
		view();
	}
	
	@RequestMapping(url="/edit",requestMethod=RequestMethod.GET)
	public void edit() throws ServletException, IOException {
		
		long id = Utils.getLongOrNull("id", request);
		
		Category model;
		try {
			model = categoryCRUD.find(id);
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
		Category model = new Category();
		request.setAttribute("model", model);
		
		view = "form.jsp";
		view();
	}
	
	@RequestMapping(url="/save",requestMethod=RequestMethod.POST)
	public void save() throws ServletException, IOException {
		
		
		
		    
		String err = null;
		String title = request.getParameter("title");
		Long id = Utils.getLongOrNull("id", request);
		
		boolean isInsert = id==null;
		
			
		
		
		
		try {
		
			Category model = null;
			if(!isInsert) {
				
				model = categoryCRUD.find(id);
				
				
			} else {
				model = new Category();
				
				
			}
			model.setTitle(title);
		
			
			if(isInsert) {
				categoryCRUD.insert(model);	
			} else {
				model.setId(id);
				categoryCRUD.update(model);
			}
			
			
			sendRedirect("/admin-category/list");
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
		
		
		
		sendRedirect( isInsert ? "/admin-category/add" : "/admin-category/edit?id="+id);
		
		
	}
	
	
	
	
	
	
	
		
	
}
