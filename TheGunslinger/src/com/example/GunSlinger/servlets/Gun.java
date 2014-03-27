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
@WebServlet({"/Gun","/Gun/*"})
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
		gsm.setCluster(cluster);		//connect to Cassandra cluster
		gsm.createDB();					//create DB keyspace and tables

		RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");	//Send the user to the login page
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		GunSlingerModel gm = new GunSlingerModel();
		gm.setCluster(cluster);

		//if the login page is displayed
		//test the users entered details 
		if (request.getParameter("login") != null) {
			boolean loggedin = false;
			String username = request.getParameter("username");
			Name = username;
			String password = request.getParameter("password");

			loggedin = gm.Login(Name,password);
			if (loggedin == true) {
				request.setAttribute("UserName", Name);
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
		if(request.getParameter("main") != null)
		{
			LinkedList<GunSlingerStore> scoreList = gm.getScores(Name);			
			request.setAttribute("Scores", scoreList);
		}
	}

}