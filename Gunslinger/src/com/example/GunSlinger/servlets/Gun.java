package com.example.GunSlinger.servlets;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.utils.UUIDs;
import com.example.GunSlinger.lib.*;
import com.example.GunSlinger.models.*;
import com.example.GunSlinger.stores.*;
/**
 * Servlet implementation class Gun
 */
@WebServlet({"/Gun","/Main","/Friends","/Global","/Tracker"})
public class Gun extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Cluster cluster;
	String Name = null;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Gun() {
		super();
		System.out.println("Entered Gun.java");
	}

	public void init(ServletConfig config) throws ServletException {
		cluster = CassandraHosts.getCluster();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GunSlingerModel gsm = new GunSlingerModel();
		String[] url = request.getRequestURI().split("/");
		gsm.setCluster(cluster);		//connect to Cassandra cluster
		gsm.createDB();					//create DB keyspace and tables

		if ((url[(url.length) - 1]).equals("Gun")) {
			RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		GunSlingerModel gm = new GunSlingerModel();
		gm.setCluster(cluster);

		HttpSession session = request.getSession();

		//if the login page is displayed
		//test the users entered details 
		if (request.getParameter("Login") != null) {
			boolean loggedin = false;
			String username = request.getParameter("username");
			Name = username;
			String password = request.getParameter("password");

			loggedin = gm.Login(Name,password);
			if (loggedin == true) {
				session.setAttribute("UserName", Name);
				LinkedList<GunSlingerStore> scoreList = gm.getScores(Name);			
				request.setAttribute("PlayerScores", scoreList);
				RequestDispatcher rd = request.getRequestDispatcher("/Main.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("invalidlogin", Name);
				RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
				rd.forward(request, response);
			}
		}

		//if the main page is displayed
		//show the logged in users game results
		else if(request.getParameter("main")!=null)
		{
			LinkedList<GunSlingerStore> scoreList = gm.getScores(Name);			
			request.setAttribute("PlayerScores", scoreList);
			if(scoreList==null)
			{
				System.out.println("No data recieved from model");
			}
			else{
				System.out.println("Player data found");
				RequestDispatcher rd = request.getRequestDispatcher("/Main.jsp");
				rd.forward(request, response);
			}			
		}
		//view friends leaderboard
		else if(request.getParameter("friends")!=null)
		{
			LinkedList<GunSlingerStore> friendList = gm.getFriendsScore(Name);
			request.setAttribute("FriendsScores", friendList);
			RequestDispatcher rd = request.getRequestDispatcher("/Friends.jsp");
			rd.forward(request, response);
		}
		//view global leaderboard
		else if(request.getParameter("global")!=null)
		{
			LinkedList<GunSlingerStore> globalList = gm.getGlobalScores();			
			request.setAttribute("GlobalScores", globalList);
			if(globalList==null)
			{
				System.out.println("No data recieved from model");
			}
			else{
				System.out.println("Player data found");
				RequestDispatcher rd = request.getRequestDispatcher("/Global.jsp");
				rd.forward(request, response);
			}
		}
		//tracker page clicked
		else if(request.getParameter("tracker")!=null)
		{
			String url = null;

			LinkedList<GunSlingerStore> friendList = gm.getFriendsScore(Name);
			request.setAttribute("FriendsScores", friendList);

			LinkedList<GunSlingerStore> scoreList = gm.getScores(Name);			
			request.setAttribute("PlayerScores", scoreList);
			
			RequestDispatcher rd = request.getRequestDispatcher("/Tracker.jsp");
			rd.forward(request, response);
		}
		//add friend button clicked
		else if(request.getParameter("addFriend")!=null)
		{
			String friend = request.getParameter("enterFriend");
			
			gm.addFriend(Name,friend);
			
			RequestDispatcher rd = request.getRequestDispatcher("/Friends.jsp");
			rd.forward(request, response);
		}
		//search button clicked
		else if(request.getParameter("enterSearch")!=null)
		{
			String search = request.getParameter("searchText");
			
			LinkedList<GunSlingerStore> scoreList = gm.searchUser(search);
			
			request.setAttribute("PlayerScores", scoreList);
			if(scoreList==null)
			{
				System.out.println("No data recieved from model");
			}
			else{
				System.out.println("Player data found");
				RequestDispatcher rd = request.getRequestDispatcher("/Main.jsp");
				rd.forward(request, response);
			}
		}
		//logout button clicked
		else if(request.getParameter("logout")!=null)
		{
			Name=null;
			
			RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
			rd.forward(request, response);
		}
	}
}