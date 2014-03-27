<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.example.GunSlinger.stores.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="CSS/Design.css">
<title>The Gunslinger</title>
</head>
<body>
	<form action="Gun" method="post">
		<ul>
			<li><input value="Home" name="main" type="submit" /></li>
			<li><input value="Friends" name="friends" type="submit" /></li>
			<li><input value="Global" name="global" type="submit" /></li>
			<li><input value="Tracker" name="tracker" type="submit" /></li>
		</ul>
	</form>

	<div>
	<image src="<%request.getContextPath();%>/chart.jpg"></image>
	</div>
</body>
</html>