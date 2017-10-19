package com.nareen.webser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class UserDAO {
	private static final Logger log = LogManager.getLogger(UserDAO.class);
	public String insertUser(Connection conn, User userInfo) throws SQLException {

		PreparedStatement stmt = conn.prepareStatement("INSERT IGNORE INTO user_info VALUES(?,?,?,?)");
		stmt.setString(1, userInfo.getUserName());
		stmt.setString(2, userInfo.getUserPassword());
		stmt.setString(3, userInfo.getUserMail());
		stmt.setLong(4, userInfo.getUserMobile());

		int i = stmt.executeUpdate();
		log.info(i+" Rows updated in user table");
		return i + " Row updated";

	}

	public ResultSet getUser(Connection conn,String userName,String userPassword) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user_info WHERE user_id=? AND user_password=?");
		stmt.setString(1, userName);
		stmt.setString(2, userPassword);
		ResultSet rs = stmt.executeQuery();
		return rs;	
	}
	public String generateHash(String password) {
		 try{
		        MessageDigest digest = MessageDigest.getInstance("SHA-256");
		        byte[] hash = digest.digest(password.getBytes("UTF-8"));
		        StringBuffer hexString = new StringBuffer();

		        for (int i = 0; i < hash.length; i++) {
		            String hex = Integer.toHexString(0xff & hash[i]);
		            if(hex.length() == 1) hexString.append('0');
		            hexString.append(hex);
		        }
		        return hexString.toString();
		    } catch(Exception ex){
		       throw new RuntimeException(ex);
		    }

}
}
