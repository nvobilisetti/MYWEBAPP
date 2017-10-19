<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><title>User Registration Page</title>
<style>
form {
	border: 3px solid #f1f1f1;
}

input[type=text], input[type=password] {
	width: 60%;
	padding: 8px 10px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}

input[type=submit] {
	background-color: #4CAF50;
	color: white;
	padding: 10px 10px;
	margin: 20px 0;
	border: none;
	cursor: pointer;
	width: 50%;
}

</style></head>
<body>
<form action="http://localhost:8080/webapp/RegistrationServlet" method="GET">
<label><b>UserID</b></label><br/>
<input type="text" placeholder="Enter UserID" name="userid">
<br/>
<label><b>Password</b></label><br/>
<input type="password" placeholder="Enter Password" name="password">
<br/>
<label><b>Email</b></label><br/>
<input type="text" placeholder="Enter Email" name="email">
<br/>
<label><b>Mobile</b></label><br/>
<input type="text" placeholder="Enter Mobile Number" name="mobile">
<br/>
<input type="checkbox" checked="checked">
 By creating you agree our terms and Conditions
<br/>
<input type="submit" value="SignUp">
<br/><br/>
</form>
<a href="http://localhost:8080/webapp/login-form.jsp">LOGIN!!!</a>
</body>
</html>