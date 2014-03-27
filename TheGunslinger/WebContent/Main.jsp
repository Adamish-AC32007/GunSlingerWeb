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
	<ul>
		<li><a href="<%=request.getContextPath()%>/Main.jsp"> <%
 	session.getAttribute("UserName");
 %>
		</a></li>
		<li><a href="<%=request.getContextPath()%>/Friends.jsp">Friends</a></li>
		<li><a href="<%=request.getContextPath()%>/Global.jsp">Global</a></li>
		<li><a href="<%=request.getContextPath()%>/Tracker.jsp">Tracker</a></li>
	</ul>
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
			<%
				List<GunSlingerStore> lGun = (List<GunSlingerStore>) request
						.getAttribute("Scores");
				if (lGun == null) {
			%>
			<p>No player data found</p>
			<%
				} else {
					Iterator<GunSlingerStore> iterator;

					iterator = lGun.iterator();
					while (iterator.hasNext()) {
						GunSlingerStore gs = (GunSlingerStore) iterator.next();
			%>
			<tr onmouseover="this.style.backgroundColor='#ffff66';"
				onmouseout="this.style.backgroundColor='#d4e3e5';">
				<td>
					<%
						gs.getUsername();
					%>
				</td>
				<td>
					<%
						gs.getHighscore();
					%>
				</td>
				<td>
					<%
						gs.getAccuracy();
					%>
				</td>
				<td>
					<%
						gs.getTotalshots();
					%>
				</td>
				<td>
					<%
						gs.getMeleekills();
					%>
				</td>
				<td>
					<%
						gs.getTotalkills();
					%>
				</td>
			</tr>
			<%
				}
				}
			%>
		</table>
	</div>
</body>
</html>