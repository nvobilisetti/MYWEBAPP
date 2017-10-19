<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.nareen.webser.Car"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Car available</title>
<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 60%;
	font-size: 12px;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}
</style>
</head>
<jsp:useBean id="userBean" class="com.nareen.webser.User"
	scope="session">
	<jsp:setProperty name="userBean" property="userName" param="userid" />
</jsp:useBean>
<body>
	<h2>
		Hello  <jsp:getProperty property="userName" name="userBean" /> !!
	</h2>

	<br />
	<br />
	<table>
		<tr>
			<th>CarName</th>
			<th>Model</th>
			<th>Price</th>
			<th>Year</th>
		</tr>
		<%
		List<Car> cars = (ArrayList<Car>)request.getSession().getAttribute("cars");
		for (Car carobj:cars) {
		%>
		<tr>
			<td><%=carobj.getCarName()%></td>
			<td><%=carobj.getCarModel()%></td>
			<td><%=carobj.getCarPrice()%></td>
			<td><%=carobj.getCarYear()%></td>
		</tr>
	
	<%
	}
	%>
	</table>
<br/><br/>
	<form action="http://localhost:8080/webapp/add-car.jsp">
		<input type="submit" value="Add CAR">
	</form>
	<form action="http://localhost:8080/webapp/LogoutServlet" method="GET">
<input type="submit" value="Logout"/>
</form>
</body>
</html>