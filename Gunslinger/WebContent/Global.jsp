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
						.getAttribute("GlobalScores");
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
						
					%> <br>
				<p class="MsoNormal" value=><%=gs.getUsername()%></p>
					<%
						
					%>
				</td>
				<td>
					<%
						
					%> <br>
				<p class="MsoNormal" value=><%=gs.getHighscore()%></p>
					<%
						
					%>
				</td>
				<td>
					<%
						
					%> <br>
				<p class="MsoNormal" value=><%=gs.getAccuracy()%></p>
					<%
						
					%>
				</td>
				<td>
					<%
						
					%> <br>
				<p class="MsoNormal" value=><%=gs.getTotalshots()%></p>
					<%
						
					%>
				</td>
				<td>
					<%
						
					%> <br>
				<p class="MsoNormal" value=><%=gs.getMeleekills()%></p>
					<%
						
					%>
				</td>
				<td>
					<%
						
					%> <br>
				<p class="MsoNormal" value=><%=gs.getTotalkills()%></p>
					<%
						
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