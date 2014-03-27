<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.example.GunSlinger.stores.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>The Gunslinger</title>
<form action="Gun" method="post">
	<ul>
		<li><input value="Home" name="main" type="submit"
			style="background-image: url('CSS/signbackground.jpg'); background-position: center center; background-size: auto; background-repeat: no-repeat; width: 6em; text-decoration: none; text-align: center; font-size: large; color: white; background-color: purple; padding: 0.6em 0.8em; font-family: georgia, serif;"></li>
		<li><input value="Friends" name="friends" type="submit"
			style="background-image: url('CSS/signbackground.jpg'); background-position: center center; background-size: auto; background-repeat: no-repeat; width: 6em; text-decoration: none; text-align: center; font-size: large; color: white; background-color: purple; padding: 0.6em 0.8em; font-family: georgia, serif;"></li>
		<li><input value="Global" name="global" type="submit"
			style="background-image: url('CSS/signbackground.jpg'); background-position: center center; background-size: auto; background-repeat: no-repeat; width: 6em; text-decoration: none; text-align: center; font-size: large; color: white; background-color: purple; padding: 0.6em 0.8em; font-family: georgia, serif;"></li>
		<li><input value="Tracker" name="tracker" type="submit"
			style="background-image: url('CSS/signbackground.jpg'); background-position: center center; background-size: auto; background-repeat: no-repeat; width: 6em; text-decoration: none; text-align: center; font-size: large; color: white; background-color: purple; padding: 0.6em 0.8em; font-family: georgia, serif;"></li>
		<li><input value="Logout" name="logout" type="submit"
			style="background-image: url('CSS/signbackground.jpg'); background-position: center center; background-size: auto; background-repeat: no-repeat; width: 6em; text-decoration: none; text-align: center; font-size: large; color: white; background-color: purple; padding: 0.6em 0.8em; font-family: georgia, serif;"></li>
	</ul>
	<input class="form-control" id="search_bar" name="searchText"
				placeholder="Search User Name" type="text" maxlength="35"
				size="45px" autocomplete="off" style="width:10%; left:50px;">
		<button type="submit" name="enterSearch" class="btn btn-info">Search</button>
	
</form>
<%
				int [] highscores = new int [10]; 
				List<GunSlingerStore> lGun = (List<GunSlingerStore>) request.getAttribute("PlayerScores");
				if (lGun == null) {
			%>
<p>No player data found</p>
<%
				} else {
			%>
<%
				Iterator <GunSlingerStore> iterator; 
				iterator = lGun.iterator(); 
				int i = 0;
				while (iterator.hasNext()) 
				{ 
					GunSlingerStore gs =(GunSlingerStore) iterator.next(); 
					int highscore = gs.getHighscore();
					if(i > 10)
					{
						%>Got to break<%
					 	break;
					}
					else
					{
						highscores[i] = highscore;
						i++;
					}
				}
			}
			%>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/CSS/bootstrap.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/CSS/bootstrap-theme.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/CSS/bootstrap-theme.min.css"
	rel="stylesheet">
<link href="CSS/signin.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="CSS/graph.css">
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      var p = <%=highscores[0]%>;
      var q = <%=highscores[1]%>;
      var r = <%=highscores[2]%>;
      var s = <%=highscores[3]%>;
      var t = <%=highscores[4]%>;
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Previous games', 'Highscores'],
          ['1',  p],
          ['2',  q],
          ['3',  r],
          ['4',  s],
          ['5', t]
        ]);

        var options = {
        	    title: 'Player Performance',
        	    curveType: 'function',
        	    legend: { position: 'bottom' }
        	  };

        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>

</head>
<body>
	<div id="chart_div" style="width: 600px; height: 500px; top:100px;left:50px"></div>
</body>
</html>