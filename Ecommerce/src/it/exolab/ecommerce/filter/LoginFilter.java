package it.exolab.ecommerce.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.exolab.ecommerce.util.Utils;

public class LoginFilter  implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
//		List<String> protectedURLS = Utils.getProtectedURLS();
//		List<String> forAdminProtectedURLS = Utils.getForAdminProtectedURLS();
//		
//		
//		
//		String relative_path = uri.substring(req.getContextPath().length());
		
		
		String cp = req.getContextPath();
		String srv_path = req.getServletPath();
		
		String front_url = cp + srv_path + "/front/home";
		
		
		String uri = req.getRequestURI();
		Boolean isPrivate =( uri.contains("/user") && !uri.contains("login")
				&& !uri.contains("do-login")
				&& !uri.contains("register")
				&& !uri.contains("do-register")
				&& !uri.contains("logout")
				)
				|| (uri.contains("my-orders")|| uri.contains("my-orders-view")
						
				)
				
				
				;
		
		isPrivate =isPrivate || (uri.contains("admin-home") 
				|| uri.contains("admin-category") 
				|| uri.contains("admin-item")
				|| uri.contains("admin-discount")
				);
		
		if(isPrivate) {
			HttpSession session = req.getSession();
			
			if(session.getAttribute("user")==null
					&& session.getAttribute("admin")==null
					) {
					res.sendRedirect(front_url);
					return;
			}	
			
			
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	

}
