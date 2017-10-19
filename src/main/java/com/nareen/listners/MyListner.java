package com.nareen.listners;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Locale;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.nareen.webser.DataBaseConnect;

/**
 * Application Lifecycle Listener implementation class MyListner
 *
 */
public class MyListner implements HttpSessionAttributeListener, HttpSessionListener, ServletContextAttributeListener,
		ServletContextListener, ServletRequestListener, ServletRequestAttributeListener, HttpSessionActivationListener,
		HttpSessionBindingListener {
	private static final Logger log = LogManager.getLogger(MyListner.class);
	private boolean login = false;
	private boolean logout = false;

	/**
	 * Default constructor.
	 */
	public MyListner() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		log.info("Session created " + se.getSession());
	}

	/**
	 * @see ServletRequestAttributeListener#attributeAdded(ServletRequestAttributeEvent)
	 */
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
		log.info("ServletRequestAttributeEvent " + srae.getName());
	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		log.info("Session Destroyed " + se.getSession());

	}

	/**
	 * @see ServletRequestAttributeListener#attributeReplaced(ServletRequestAttributeEvent)
	 */
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
		log.info("ServletRequestAttributeEvent" + srae.getName());
	}

	/**
	 * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
	 */
	public void attributeAdded(HttpSessionBindingEvent se) {
		login = (Boolean) se.getSession().getAttribute("logIn");
		if (login) {
			Timestamp loginTime = getDate();
			se.getSession().setAttribute("loginTime", loginTime);
			log.info("HttpSessionBindingEvent added " + se.getName());
			// TODO Auto-generated method stub
			se.getSession().setAttribute("logIn", false);
		}
		if (!((Boolean) se.getSession().getAttribute("LogOut") == null)
				&& ((Boolean) se.getSession().getAttribute("LogOut") == true)) {
			logout = (Boolean) se.getSession().getAttribute("LogOut");
		}
		if (logout) {
			String currentLocale = Locale.getDefault().getCountry();
			log.info("Current Locale " + currentLocale);
			Timestamp logoutTime = getDate();
			Timestamp loginTime = (Timestamp) se.getSession().getAttribute("loginTime");
			log.info("Login time :" + loginTime);
			log.info("Logout Time :" + logoutTime);
			long sessionTime = getSessionTime(logoutTime, loginTime);
			log.info("Session Time :" + sessionTime);
			DataBaseConnect dbConn = new DataBaseConnect("root", "myLIFE@2153", "car_schema");
			try {
				Connection conn = dbConn.getConnection();
				PreparedStatement stmt = conn.prepareStatement("INSERT IGNORE INTO audit_table VALUES(?,?,?,?,?)");
				stmt.setString(1, ((String) se.getSession().getAttribute("user")).toUpperCase());
				stmt.setTimestamp(2, loginTime);
				stmt.setTimestamp(3, logoutTime);
				stmt.setLong(4, sessionTime);
				stmt.setString(5, currentLocale);
				int i = stmt.executeUpdate();
				log.info("Updated rows " + i);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			se.getSession().setAttribute("LogOut", false);
		}

	}

	/**
	 * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
	 */
	public void attributeReplaced(ServletContextAttributeEvent scab) {
		// TODO Auto-generated method stub
		log.info("ServletContextAttributeEvent replaced :" + scab.getName());
	}

	public Timestamp getDate() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp;
	}

	public long getSessionTime(Timestamp a, Timestamp b) {
		long diff = a.getTime() - b.getTime();
		return diff / 1000;
	}

	public void valueBound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub

	}

	public void valueUnbound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub

	}

	public void sessionWillPassivate(HttpSessionEvent se) {
		// TODO Auto-generated method stub

	}

	public void sessionDidActivate(HttpSessionEvent se) {
		// TODO Auto-generated method stub

	}

	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub

	}

	public void requestDestroyed(ServletRequestEvent sre) {
		// TODO Auto-generated method stub

	}

	public void requestInitialized(ServletRequestEvent sre) {
		// TODO Auto-generated method stub

	}

	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

	public void attributeAdded(ServletContextAttributeEvent scab) {
		// TODO Auto-generated method stub

	}

	public void attributeRemoved(ServletContextAttributeEvent scab) {
		// TODO Auto-generated method stub

	}

	public void attributeRemoved(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub

	}

	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub

	}

}
