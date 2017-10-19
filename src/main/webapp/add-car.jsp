<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add car</title>
</head>
<body>
<body>
<form action="http://localhost:8080/webapp/CarServlet" method="post">
Car Name:<br/><input type="text" name="carName" >
<br/>
Car Model:<br/><input type="text" name="carModel">
<br/>
Car price:<br/><input type="text" name="carPrice">
<br/>
Car year:<br/><input type="text" name="carYear">
<br/>
<br/>
<input type="submit" value="Add Car">
</form>
</body>
</body>
</html>