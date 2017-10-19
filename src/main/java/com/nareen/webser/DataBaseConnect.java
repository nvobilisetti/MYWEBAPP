package com.nareen.webser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DataBaseConnect {
	private static final Logger log = LogManager.getLogger(DataBaseConnect.class);
	public static final String connectionUrl = "jdbc:mysql://localhost:3306/";
	String userName;
	String password;
	String databaseName;

	public DataBaseConnect(String userName, String password, String databaseName) {
		super();
		this.userName = userName;
		this.password = password;
		this.databaseName = databaseName;
	}

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectionUrl + this.databaseName, this.userName, this.password);
		log.info("Created a Database Connection Object");
		return conn;

	}

}
