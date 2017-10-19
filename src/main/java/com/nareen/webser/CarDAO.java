package com.nareen.webser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class CarDAO {
	
	private static final Logger log = LogManager.getLogger(CarDAO.class);
	public String insertCar(Connection conn,Car carInfo) throws SQLException {
		
		PreparedStatement stmt=conn.prepareStatement("INSERT IGNORE INTO car_inventory VALUES(?,?,?,?)");
		stmt.setString(1, carInfo.carName);
		stmt.setString(2, carInfo.carModel);
		stmt.setInt(3, carInfo.carYear);
		stmt.setInt(4, carInfo.carPrice);
		int i=stmt.executeUpdate();  
		log.info("Updated Rows "+i);
		return i+" Row updated";
		
		
		
	}
	public String deleteCar(Connection conn,Car carInfo) throws SQLException {
		PreparedStatement stmt=conn.prepareStatement("DELETE from car_inventory WHERE car_name=? AND car_year=?");
		stmt.setString(1, carInfo.carName);
		stmt.setInt(2, carInfo.carYear);
		int i=stmt.executeUpdate();  
		return i+" Row deleted";
	}
	public String updateCar(Connection conn,Car carInfo) throws SQLException{
		PreparedStatement stmt=conn.prepareStatement("UPDATE car_inventory SET car_name=? WHERE car_year=?");
		stmt.setString(1, carInfo.carName);
		stmt.setInt(2, carInfo.carYear);
		int i=stmt.executeUpdate();  
		return i+" Row updated";
		
	}
	public ResultSet retriveCar(Connection conn) throws SQLException{
		PreparedStatement stmt=conn.prepareStatement("SELECT * FROM car_inventory");
		ResultSet rs=stmt.executeQuery(); 
		return rs;
	}
}

 