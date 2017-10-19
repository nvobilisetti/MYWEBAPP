package com.nareen.webser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final Logger log = LogManager.getLogger(LoginServlet.class);
	private static final long serialVersionUID = 1L;
	/* Establishing a Database connection */
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
		String userId = request.getParameter("userid");
		String password = request.getParameter("password");
		password = userDao.generateHash(password);
		try {
			try {
				conn = dbConn.getConnection();
				log.info("Connected to Database");
			} catch (Exception e) {
				log.error("Failed to Connect to Database");
				e.printStackTrace();
			}
			res = userDao.getUser(conn, userId, password);
			if (res.next()) {
				HttpSession session = request.getSession();
				log.info("LoginServlet: "+session);
				session.setAttribute("logIn",true);
				//session.setAttribute("user", userId);
				log.info(session.getAttribute("user"));
				log.info("Redirected to CarServlet");
				RequestDispatcher rd = request.getRequestDispatcher("/CarServlet");
				rd.forward(request, response);
			} else {
				log.info("Redirected to login-failed");
				RequestDispatcher rd = request.getRequestDispatcher("login-failed.jsp");
				rd.forward(request, response);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				log.error("failed to close the connection");
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
