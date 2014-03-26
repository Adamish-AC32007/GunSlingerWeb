<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="CSS/Design.css">
<title>The Gunslinger</title>
</head>
<body>
<ul>
<li><a href="<%=request.getContextPath()%>/Main.jsp">You</a></li>
<li><a href="<%=request.getContextPath()%>/Friends.jsp">Friends</a></li>
<li><a href="<%=request.getContextPath()%>/Global.jsp">Global</a></li>
<li><a href="<%=request.getContextPath()%>/Tracker.jsp">Tracker</a></li></ul>
</ul>

<div>
<table style="width:300px;">
  <tr>
    <th>Player Name</th>
    <th>Highscore</th>
    <th>Accuracy (%)</th>
    <th>Shots Fired</th>
    <th>Melee Kills</th>
    <th>Total Kills</th>
  </tr>
<tr>
  <td>Jill</td>
  <td>50</td>		
  <td>50</td>
  <td>30</td>
  <td>4</td>		
  <td>5</td>
  </tr>
<tr>
  <td>Eve</td>
  <td>Jackson</td>		
  <td>94</td>
</tr>
<tr>
  <td>John</td>
  <td>Doe</td>		
  <td>80</td>
</tr>
</table>
</div>

</body>
</html>