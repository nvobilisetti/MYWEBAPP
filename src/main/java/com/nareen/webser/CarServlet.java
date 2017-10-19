package com.nareen.webser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
 * Servlet implementation class CarServlet
 */
public class CarServlet extends HttpServlet {
	private static final Logger log = LogManager.getLogger(CarServlet.class);
	private static final long serialVersionUID = 1L;
	Inventory I = Inventory.getInstance();
	DataBaseConnect dbConn = new DataBaseConnect("root", "myLIFE@2153", "car_schema");
	CarDAO carDao = new CarDAO();
	Connection conn;
	ResultSet rs;
	ArrayList<Car> cars = null;
	Car carObject;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			try {
				conn = dbConn.getConnection();
				log.info("Connected to Database");
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs = carDao.retriveCar(conn);
			cars = new ArrayList<Car>();
			while (rs.next()) {
				carObject = new Car();
				carObject.carName = rs.getString(1);
				carObject.carModel = rs.getString(2);
				carObject.carYear = rs.getInt(3);
				carObject.carPrice = rs.getInt(4);
				cars.add(carObject);
			}
			request.getSession().setAttribute("cars", cars);
			RequestDispatcher rd = request.getRequestDispatcher("display-cars.jsp");
			rd.forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
		PrintWriter out = response.getWriter();
		carObject.carName = request.getParameter("carName").toUpperCase();
		carObject.carModel = request.getParameter("carModel").toUpperCase();
		carObject.carPrice = Integer.parseInt(request.getParameter("carPrice"));
		carObject.carYear = Integer.parseInt(request.getParameter("carYear"));
		try {
			try {
				conn = dbConn.getConnection();
				log.info("Connected to Database");
				if (request.getSession(false) != null) {
					carDao.insertCar(conn, carObject);
					log.info("Car Added to the Database");
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			doGet(request, response);

		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
