package com.nareen.filters;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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

/**
 * Servlet Filter implementation class PerformanceLogFilter
 */
public class PerformanceLogFilter implements Filter {
	private static final Logger log = LogManager.getLogger(PerformanceLogFilter.class);
	/**
	 * Default constructor.
	 */
	public PerformanceLogFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		log.info(uri);
		HttpSession session = req.getSession(false);
		log.info(session);
		if(uri.endsWith("/LoginServlet")) {
			//getDate();
			log.info(getDate());
			//session.setAttribute("loginTime", );
			chain.doFilter(request, response);
			
		 }else if(uri.endsWith("/LogoutServlet")) {
			 chain.doFilter(request, response);
			 
		 }
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	} 
	public String getDate(){
		  DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		  Calendar calobj = Calendar.getInstance();
	      df.format(calobj.getTime());
	      return null;
	      }

}
