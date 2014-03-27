<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ page
	import="com.example.GunSlinger.stores.*,com.example.GunSlinger.models.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">

<link href="<%=request.getContextPath()%>/CSS/bootstrap.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/CSS/bootstrap-theme.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/CSS/bootstrap-theme.min.css"
	rel="stylesheet">
<link href="CSS/signin.css" rel="stylesheet">

<title>The Gunslinger</title>
</head>
<body>
	<div class="container">
		<form action="Gun" method="post" class="form-signin">
			<h3 class="form-signin-heading">Login</h3>
			<br> <input type="text" id="username" name="username"
				class="form-control" placeholder="User Name" required autofocus><br>
			<input type="password" id="password" name="password"
				class="form-control" placeholder="Password" required><br>
			<button class="btn btn-lg btn-primary btn-block" id="Login"
				name="Login" type="submit">Sign in</button>
		</form>

		<div class="container">
			<form action="Gun" method="post" class="form-signin">
				<h3 class="form-signin-heading">Register</h3>
				<br> <input type="text" id="Rusername" name="Rusername"
					class="form-control" placeholder="User Name" required><br>
				<input type="password" id="Rpassword" name="Rpassword"
					class="form-control" placeholder="Password" required><br>
				<button class="btn btn-lg btn-primary btn-block" id="Register"
					name="Register" type="submit">Register</button>
			</form>
		</div>
</body>
</html>