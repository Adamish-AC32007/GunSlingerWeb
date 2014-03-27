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
		<!-- Table goes in the document BODY -->
		<table class="hovertable">
			<tr>
				<th>Player Name</th>
				<th>Highscore</th>
				<th>Accuracy (%)</th>
				<th>Shots Fired</th>
				<th>Melee Kills</th>
				<th>Total Kills</th>
			</tr>
			<tr onmouseover="this.style.backgroundColor='#ffff66';"
				onmouseout="this.style.backgroundColor='#d4e3e5';">
				<td>
					<%
						request.getAttribute("Name");
					%>
				</td>
				<td>
					<%
						request.getAttribute("score");
					%>
				</td>
				<td>
					<%
						request.getAttribute("accuracy");
					%>
				</td>
				<td>
					<%
						request.getAttribute("shots");
					%>
				</td>
				<td>
					<%
						request.getAttribute("melee");
					%>
				</td>
				<td>
					<%
						request.getAttribute("kills");
					%>
				</td>
			</tr>
			<tr onmouseover="this.style.backgroundColor='#ffff66';"
				onmouseout="this.style.backgroundColor='#d4e3e5';">
				<td>Eve</td>
				<td>Jackson</td>
				<td>94</td>
			</tr>
			<tr onmouseover="this.style.backgroundColor='#ffff66';"
				onmouseout="this.style.backgroundColor='#d4e3e5';">
				<td>Item 3A</td>
				<td>Item 3B</td>
				<td>Item 3C</td>
			</tr>
			<tr onmouseover="this.style.backgroundColor='#ffff66';"
				onmouseout="this.style.backgroundColor='#d4e3e5';">
				<td>Item 4A</td>
				<td>Item 4B</td>
				<td>Item 4C</td>
			</tr>
			<tr onmouseover="this.style.backgroundColor='#ffff66';"
				onmouseout="this.style.backgroundColor='#d4e3e5';">
				<td>John</td>
				<td>Doe</td>
				<td>80</td>
			</tr>
		</table>

</div>

</body>
</html>