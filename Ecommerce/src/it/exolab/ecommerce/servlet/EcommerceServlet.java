package it.exolab.ecommerce.servlet;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Stream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.exolab.ecommerce.framework.Config;
import it.exolab.ecommerce.framework.Controller;
import it.exolab.ecommerce.framework.RequestMapping;
import it.exolab.ecommerce.framework.RequestMethod;
import it.exolab.ecommerce.framework.UrlAndClassMapping;
import it.exolab.ecommerce.framework.UrlAndMethod;
import it.exolab.ecommerce.model.Admin;

public class EcommerceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private boolean isValidMapping = true;
	private Collection<String> mappings;
	
	private Config config = new Config();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EcommerceServlet() {
        super();
        
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		String controllers_package = config.getInitParameter("controllers_package");
		String repository_dir = config.getInitParameter("repository_dir");
		String admin_email = config.getInitParameter("admin_email");
		String admin_pass = config.getInitParameter("admin_pass");
		
		
		
		Admin admin  = new Admin();
		admin.setEmail(admin_email);
		admin.setPassword(admin_pass);
		
		ServletContext servletContext = config.getServletContext();
		
		servletContext.setAttribute("repository_dir", repository_dir);
		servletContext.setAttribute("admin_test", admin);
		
		try {
			ServletRegistration servletRegistration = servletContext.getServletRegistration(config.getServletName());
			mappings = servletRegistration.getMappings();
			isValidMapping = true;
			for(String mapping : mappings) {
				if(mapping.equals("*")
						|| mapping.equals("/*")
						) {
					isValidMapping =false;
					break;
				}
			}
			
			if(!isValidMapping ) {
				throw new Exception("URL pattern * oppure /* non consentito: usare ad esempio /pages/* oppure *.do");
			}
			
			
			Class[] allClasses;
			try {
				allClasses = scanPackage(controllers_package);
				
				for(Class theClass : allClasses) {
				 Annotation[] annotations = theClass.getAnnotations();
				  for(Annotation annotation : annotations) {
					if(annotation instanceof Controller) {
						Controller annotationController = (Controller) annotation;
						//String url, Class type, Annotation annotatio
						UrlAndClassMapping urlAndClassMapping = new UrlAndClassMapping(
								annotationController.url()
								,theClass
								,annotationController
								);
						
						Method[] methods = theClass.getDeclaredMethods();
						for(Method method : methods) {
							Annotation[] annotationsOfMethod = method.getAnnotations();
							for(Annotation annotationOfMethod : annotationsOfMethod) {
								if(annotationOfMethod instanceof RequestMapping) {
									RequestMapping annotationRequestMapping = (RequestMapping) annotationOfMethod;
									//String url, Method method, Annotation annotation
									UrlAndMethod urlAndMethod = new UrlAndMethod(
											annotationRequestMapping.url()
											,method
											,annotationRequestMapping
											);
									urlAndClassMapping.getMethods()
									.put(annotationRequestMapping.url(), urlAndMethod);
								}
								
							}
							
						}
						
						this.config.getControllers()
						.put(annotationController.url(), urlAndClassMapping);	
					}
					
				}
			}
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doHTTPRequest(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doHTTPRequest(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	private void doHTTPRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(!isValidMapping ) {
			throw new Exception("URL pattern * oppure /* non consentito: usare ad esempio /pages/* oppure *.do");
		}
		
		boolean isPost = request.getMethod().equals("POST");
		String url = request.getRequestURI();
		String cp = request.getContextPath();
		String cp_and_mapping = null;
		for(String mapping : mappings) {
			
			mapping = mapping.replaceAll("\\*", "");
			cp_and_mapping = cp+mapping;
			
			if(url.startsWith(cp_and_mapping)) {
				url = url.substring(cp_and_mapping.length());
				break;
			}
			
			
		}
		
		String relative_url   = url;
		
		String controller_url = "";
		String method_url = "";
		
		String[] peaces = relative_url.split("/");
		if(peaces.length>0) {
			int start_index=1;
			if(peaces[0].equals("")) {
				start_index=2;
			}
			
			controller_url ="/"+peaces[start_index-1];
			for(int i=start_index;i<peaces.length;i++) {
				method_url+="/"+peaces[i]; 
			}
		}
		
		if(config.getControllers().containsKey(controller_url)) {
			UrlAndClassMapping controllerUrlAndClassMapping = config.getControllers().get(controller_url);
			Class controllerClass = controllerUrlAndClassMapping.getType();
			HashMap<String, UrlAndMethod> methods = controllerUrlAndClassMapping.getMethods();
			for(Entry<String, UrlAndMethod> entry : methods.entrySet()) {
				if(entry.getKey().equals(method_url)) {
					UrlAndMethod urlAndMethod = entry.getValue();
					Method method = urlAndMethod.getMethod();
					RequestMapping requestMapping = (RequestMapping) urlAndMethod.getAnnotation();
					if(requestMapping.requestMethod().equals(RequestMethod.POST) && !isPost) {
						throw new Exception("cant invoke POST");
					}
					if(requestMapping.requestMethod().equals(RequestMethod.GET) && isPost) {
						throw new Exception("cant invoke GET");
					}
					try {
						Object theController = getNewInstance(controllerClass,request,response);
					
						method.invoke(theController);
						
						
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private Object getNewInstance(Class c,HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
		
		
		
		Constructor<?> cons = c.getConstructor(HttpServletRequest.class,HttpServletResponse.class);
		
		return  cons.newInstance(request,response);
		
	}

	public static Class[] scanPackage(String pckgname) {
		 List classes=new ArrayList();
		 Class[] classesA=null;
		 try{
		      
		     // Get a File object for the package 
		     File directory=null; 
		     try { 
		       directory=new File( new URI("file:///"+ Thread.currentThread().getContextClassLoader().getResource(pckgname.replace('.', '/')).getFile())); 
		     } catch(NullPointerException x) { 
		       System.out.println("Nullpointer");
		       throw new ClassNotFoundException(pckgname+" does not appear to be a valid package"); 
		     } 
		     if(directory.exists()) { 
		       // Get the list of the files contained in the package 
		       String[] files=directory.list(); 
		       for(int i=0; i<files.length; i++) { 
				 if(files[i].endsWith(".class")) { 
				   // removes the .class extension 
				   classes.add(Class.forName(pckgname+'.'+files[i].substring(0, files[i].length()-6))); 
				 } 
		       } 
		     } else { 
		    	 
		       throw new ClassNotFoundException(pckgname+" does not appear to be a valid package"); 
		     } 
		     classesA=new Class[classes.size()]; 
		     classes.toArray(classesA); 
		      
		     return classesA;
		    
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 
		 return classesA;
	  }
	
	
}
