package com.nareen.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.nareen.webser.LoginServlet;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
public class AuthenticationFilter implements Filter {
	private static final Logger log = LogManager.getLogger(AuthenticationFilter.class);
	/**
	 * Default constructor.
	 */
	public AuthenticationFilter() {
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
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		log.info("Requested Resource::"+uri);
		HttpSession session = req.getSession(false);
		log.info(session);
		log.info(session.getAttribute("user"));
		if(session == null || validUrl(uri)||(session.getAttribute("user")==null)){
			if((validUrl(uri)&&!(session.getAttribute("user")==null))) {
				log.info("Session already exists");
				chain.doFilter(request, response);		
			}else{
			log.info("Unauthorized access request");
			res.sendRedirect("index.jsp");
			}
		}else{
			log.info("Forwaded to do filer method"+uri);
			// pass the request along the filter chain
			chain.doFilter(request, response);
	}
	}


	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	public boolean validUrl(String uri) {
		String[] defaultUrls = { "login-form.jsp", "index.jsp", "LoginServlet", "registration-form.jsp" };
		for (String validateUrl : defaultUrls) {
			if (uri.endsWith(validateUrl)) {
				return false;
			}
		}
		return true;
	}
}
