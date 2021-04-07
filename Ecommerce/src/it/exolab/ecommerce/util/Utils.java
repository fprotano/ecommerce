package it.exolab.ecommerce.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Utils {
	
	
	private static List<String> allowedURLS= new ArrayList<String>();
	private static List<String> protectedURLS= new ArrayList<String>();
	private static List<String> forAdminProtectedURLS= new ArrayList<String>();
	
	static {
		try {
			init();
		} catch (IOException e) {
			
		}
	}
	
	public static String dateToString(String dateAsString) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Date ret;
		try {
			ret = sdf.parse(dateAsString);
			
			sdf = new SimpleDateFormat("yyyymmdd");
			
			return sdf.format(ret);
			
			
		} catch (ParseException e) {
			
			
		}
		//Date ret = new Date(dateAsString);
		
		return "";
		
	}
	
	 
	 public static Integer getIntegerOrDefault(String param,HttpServletRequest request, Integer defValue) {
			
	    	try {
	    		Integer ret = Integer.valueOf( request.getParameter(param));
	    		return ret;
	    	} catch (Exception ex) {
	    		
	    	}
	    	
	    	return defValue;
			
		}
	 
    public static Long getLongOrNull(String param,HttpServletRequest request) {
		
    	try {
    		Long ret = Long.valueOf( request.getParameter(param));
    		return ret;
    	} catch (Exception ex) {
    		
    	}
    	
    	return null;
		
	}
    
    public static Float getFloatOrNull(String param,HttpServletRequest request) {
		
    	try {
    		Float ret = Float.valueOf( request.getParameter(param));
    		return ret;
    	} catch (Exception ex) {
    		
    	}
    	
    	return null;
		
	}

    public static Integer getIntOrNull(String param,HttpServletRequest request) {
		
    	try {
    		Integer ret = Integer.valueOf( request.getParameter(param));
    		return ret;
    	} catch (Exception ex) {
    		
    	}
    	
    	return null;
		
	}
	public static boolean isNullOrEmpty(String value) {
		
		return value==null || value.equals("");
	}
	public static boolean isNullOrEmpty(Integer value) {
		
		return value==null;
	}
	public static boolean isNullOrEmpty(Long value) {
		
		return value==null;
	}
	public static boolean isNullOrEmpty(Date value) {
		
		return value==null;
	}
	
	
	
	private static void init() throws IOException{
		Utils u = new Utils();
		Properties prop = new Properties();
		String propFileName = "config.properties";
		protectedURLS = new ArrayList<String>();
		InputStream inputStream = u.getClass().getClassLoader().getResourceAsStream(propFileName);
		 
		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}
		String[] urls = prop.getProperty("protected_url").split(",");
		for(String url : urls) {
			protectedURLS.add(url.trim());
		}
		
		urls = prop.getProperty("allowed_url").split(",");
		for(String url : urls) {
			allowedURLS.add(url.trim());
		}
		
		urls = prop.getProperty("for_admin_protected_url").split(",");
		for(String url : urls) {
			forAdminProtectedURLS.add(url.trim());
		}
		
		
	}
	public static List<String> getForAdminProtectedURLS() throws IOException{
		
		return forAdminProtectedURLS;
		
	}
	public static List<String> getProtectedURLS() throws IOException{
		
		return protectedURLS;
		
	}
	public static List<String> getAllowedURLS() throws IOException{
		
		return allowedURLS;
		
	}
	
	
	public static String createOrGetCookie(HttpServletRequest request,HttpServletResponse response) {
		if(request.getSession().getAttribute("session_id")!=null) {
			return (String) request.getSession().getAttribute("session_id");
		}
		Cookie[] cookies = request.getCookies();
		String value = null;
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("session_id") ) {
				value = cookie.getValue();
				break;
			}
		}
		
		if(value==null) {
			value = request.getSession().getId();
			Cookie c = new Cookie("session_id",value);
			c.setMaxAge(3600);
			response.addCookie(c);
			
		}
		
		request.getSession().setAttribute("session_id", value);
		
		
		return value;
		
	}

}
