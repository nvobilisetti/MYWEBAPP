package com.nareen.webser;

public class User{
	
	private String userName=null;
	private String userPassword;
	private String userMail;
	private long userMobile;
	public User() {
	}
	public String getUserName() {
		return userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public String getUserMail() {
		return userMail;
	}
	public long getUserMobile() {
		return userMobile;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	public void setUserMobile(long userMobile) {
		this.userMobile = userMobile;
	}
	
	
}
	


