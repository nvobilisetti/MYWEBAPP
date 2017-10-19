package com.nareen.webser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class RegistrationServlet
 */
public class RegistrationServlet extends HttpServlet {
	private static final Logger log = LogManager.getLogger(RegistrationServlet.class);
	private static final long serialVersionUID = 1L;

	DataBaseConnect dbConn = new DataBaseConnect("root", "myLIFE@2153", "car_schema");
	Connection conn;
	UserDAO userDao = new UserDAO();
	ResultSet res;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		User userReg = new User();
		userReg.setUserName(request.getParameter("userid"));
		userReg.setUserPassword(userDao.generateHash(request.getParameter("password")));
		userReg.setUserMail(request.getParameter("email"));
		userReg.setUserMobile(Long.parseLong(request.getParameter("mobile")));
		try {
			try {
				conn = dbConn.getConnection();
				log.info("Connected to Database");
			} catch (Exception e) {
				e.printStackTrace();
			}
			res=userDao.getUser(conn, userReg.getUserName(), userReg.getUserPassword());
			if(!res.next()) {
				if(request.getSession(false)!=null) {
				userDao.insertUser(conn,userReg);
				log.info("User Registed");
				}
				log.info("Redirected to CarServlet");
				RequestDispatcher rd = request.getRequestDispatcher("login-form.jsp");
				rd.forward(request, response);
			}else {
				log.info("Redirected to login-failed");
				RequestDispatcher rd = request.getRequestDispatcher("registration-failed.jsp");
				rd.forward(request, response);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
