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
@WebServlet({"/Gun","/Login","/Main","/Friends","/Global","/Tracker"})
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
		System.out.println("In the get method");
		GunSlingerModel gsm = new GunSlingerModel();
		gsm.setCluster(cluster);
		gsm.createDB();
		
		String[] url = request.getRequestURI().split("/");
		
		if ((url[(url.length) - 1]).equals("Login")) {
			RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
			rd.forward(request, response);
		}
		else if ((url[(url.length) - 1]).equals("Main")) {
			RequestDispatcher rd = request.getRequestDispatcher("Main.jsp");
			rd.forward(request, response);
		}
		else if ((url[(url.length) - 1]).equals("Friends")) {
			RequestDispatcher rd = request.getRequestDispatcher("Friends.jsp");
			rd.forward(request, response);
		}
		else if ((url[(url.length) - 1]).equals("Global")) {
			RequestDispatcher rd = request.getRequestDispatcher("global.jsp");
			rd.forward(request, response);
		}
		else if ((url[(url.length) - 1]).equals("Tracker")) {
			RequestDispatcher rd = request.getRequestDispatcher("Tracker.jsp");
			rd.forward(request, response);
		}
		/*
		LinkedList<GunSlingerStore> gssList = gsm.getScores(); 					//creates a linked list of tweet strores and holds the linked list returned
		request.setAttribute("gsScore", gssList); 							//Set a bean with the list in it
		RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");	//Send the user to the login page
		rd.forward(request, response);
		*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		System.out.println("Entered doPost");
		GunSlingerModel gm = new GunSlingerModel();
		gm.setCluster(cluster);
		String[] url = request.getRequestURI().split("/");

		if (request.getParameter("Login") != null || (url[(url.length) - 1]).equals("Gun")) {
			boolean loggedin = false;
			String username = request.getParameter("username");
			Name = username;
			String password = request.getParameter("password");
			
			loggedin = gm.Login(Name,password);
			if (loggedin == true) {
				session.setAttribute("UserName", Name);
				RequestDispatcher rd = request.getRequestDispatcher("/Main.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("invalidlogin", Name);
				RequestDispatcher rd = request
						.getRequestDispatcher("/Login.jsp");
				rd.forward(request, response);
			}
		}
	}

}