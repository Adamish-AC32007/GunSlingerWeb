package com.example.GunSlinger.models;

/*
 * Expects a cassandra columnfamily defined as
 * use keyspace2;
  CREATE TABLE Tweets (
  user varchar,
  interaction_time timeuuid,
   tweet varchar,
   PRIMARY KEY (user,interaction_time)
  ) WITH CLUSTERING ORDER BY (interaction_time DESC);
 * To manually generate a UUID use:
 * http://www.famkruithof.net/uuid/uuidgen
 */


import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.UUIDs;
import com.example.GunSlinger.lib.*;
import com.example.GunSlinger.stores.*;

public class GunSlingerModel {
	Cluster cluster;
	public GunSlingerModel(){

	}

	public void setCluster(Cluster cluster){
		this.cluster=cluster;
	}

	//creates database if it doesn't exist
	public void createDB(){

		Session session = cluster.connect();
		session.execute("create keyspace if not exists playerData WITH replication = {'class':'SimpleStrategy','replication_factor':1};");

		Session session1 = cluster.connect("playerData"); 	
		session1.execute("CREATE TABLE if not exists data (username varchar,accuracy float,totalshots int, meleekills int, totalkills int, highscore int, playtime UUID, PRIMARY KEY (username,playtime)) WITH CLUSTERING ORDER BY (playtime DESC);");
		session1.execute("CREATE TABLE if not exists friends (username varchar,friends set<varchar>, PRIMARY KEY (username))");
		session1.execute("CREATE TABLE if not exists login (username varchar,password varchar, PRIMARY KEY (username))");
	}

	public LinkedList<GunSlingerStore> getScores(String name){
		LinkedList<GunSlingerStore> gsList= new LinkedList<GunSlingerStore>();
		Session session = cluster.connect("playerData");

		PreparedStatement statement = session.prepare("SELECT * from data where username=?");	//create the cql statement to be executed
		BoundStatement boundStatement = new BoundStatement(statement); 			
		ResultSet rs = session.execute(boundStatement.bind(name));
		if (rs.isExhausted()) { 												//if the result set is empty
			System.out.println("No scores returned");
		} else { 																//else (meaning there is more data)
			for (Row row : rs) { 				//for each row
				GunSlingerStore gs = new GunSlingerStore(); 					
				gs.setUsername(row.getString("username")); 	
				gs.setAccuracy(row.getFloat("accuracy"));
				gs.setTotalshots(row.getInt("totalshots"));		
				gs.setMeleekills(row.getInt("meleekills"));
				gs.setTotalkills(row.getInt("totalkills"));
				gs.setHighscore(row.getInt("highscore"));
				gs.setPlaytime(row.getUUID("playtime"));
				gsList.add(gs); 					
				Collections.sort(gsList,Collections.reverseOrder());
			}
		}
		session.close();
		return gsList;
	}


	public LinkedList<GunSlingerStore> getTrackerScores(String name){
		LinkedList<GunSlingerStore> gsList= new LinkedList<GunSlingerStore>();
		Session session = cluster.connect("playerData");

		PreparedStatement statement = session.prepare("SELECT * from data where username=?");	//create the cql statement to be executed
		BoundStatement boundStatement = new BoundStatement(statement); 			
		ResultSet rs = session.execute(boundStatement.bind(name));
		if (rs.isExhausted()) { 												//if the result set is empty
			System.out.println("No scores returned");
		} else { 																//else (meaning there is more data)
			for (Row row : rs) { 				//for each row
				GunSlingerStore gs = new GunSlingerStore(); 					
				gs.setUsername(row.getString("username")); 	
				gs.setAccuracy(row.getFloat("accuracy"));
				gs.setTotalshots(row.getInt("totalshots"));		
				gs.setMeleekills(row.getInt("meleekills"));
				gs.setTotalkills(row.getInt("totalkills"));
				gs.setHighscore(row.getInt("highscore"));
				gs.setPlaytime(row.getUUID("playtime"));
				gsList.add(gs); 					
			}
		}
		session.close();
		return gsList;
	}


	public LinkedList<GunSlingerStore> getGlobalScores(){
		LinkedList<GunSlingerStore> gsList= new LinkedList<GunSlingerStore>();
		Session session = cluster.connect("playerData");

		PreparedStatement statement = session.prepare("SELECT * from data;");	//create the cql statement to be executed
		BoundStatement boundStatement = new BoundStatement(statement); 			
		ResultSet rs = session.execute(boundStatement);
		if (rs.isExhausted()) { 												//if the result set is empty
			System.out.println("No scores returned");
		} else { 																//else (meaning there is more data)
			for (Row row : rs) { 				//for each row
				GunSlingerStore gs = new GunSlingerStore(); 					
				gs.setUsername(row.getString("username")); 	
				gs.setAccuracy(row.getFloat("accuracy"));
				gs.setTotalshots(row.getInt("totalshots"));		
				gs.setMeleekills(row.getInt("meleekills"));
				gs.setTotalkills(row.getInt("totalkills"));
				gs.setHighscore(row.getInt("highscore"));
				gs.setPlaytime(row.getUUID("playtime"));
				gsList.add(gs); 	
				Collections.sort(gsList,Collections.reverseOrder());
			}
		}
		session.close();
		return gsList;
	}

	public LinkedList<GunSlingerStore> getFriendsScore(String name){
		LinkedList<GunSlingerStore> gsList= new LinkedList<GunSlingerStore>();
		Session session = cluster.connect("playerData");

		Set<String> set = new HashSet<String> ();
		PreparedStatement statement = session.prepare("SELECT friends from friends where username = ?");	
		BoundStatement boundStatement = new BoundStatement(statement); 			
		ResultSet rs = session.execute(boundStatement.bind(name));							  
		if (rs.isExhausted()) { 						

		} else { 																
			for (Row row : rs) { 												
				set = row.getSet("friends", String.class);
				Iterator iterator = set.iterator();
				while(iterator.hasNext()){
					String friend = (String) iterator.next();
					PreparedStatement statement1 = session.prepare("SELECT * from data where username = ?");	
					BoundStatement boundStatement1 = new BoundStatement(statement1); 			
					ResultSet rs1 = session.execute(boundStatement1.bind(friend));
					if (rs1.isExhausted()) { 
						System.out.println("User has no friends listed");
					} else { 																
						for (Row row1 : rs1) { 												
							GunSlingerStore nGS = new GunSlingerStore(); 				
							nGS.setUsername(row1.getString("username")); 	
							nGS.setAccuracy(row1.getFloat("accuracy"));
							nGS.setTotalshots(row1.getInt("totalshots"));		
							nGS.setMeleekills(row1.getInt("meleekills"));
							nGS.setTotalkills(row1.getInt("totalkills"));
							nGS.setHighscore(row1.getInt("highscore"));
							nGS.setPlaytime(row1.getUUID("playtime"));						
							gsList.add(nGS); 						
							Collections.sort(gsList,Collections.reverseOrder());
						}
					}
				}
			}
		}

		session.close();
		return gsList;
	}

	public boolean Login(String name, String password) {
		Session session = cluster.connect("playerData");
		PreparedStatement statement = session.prepare("SELECT * from login where username = ?;");
		ResultSet rs = session.execute(statement.bind(name));
		boolean loggedin = false;
		if (rs.isExhausted()) {
			System.out.println("No users returned");
		} else {
			for (Row row : rs) {
				String comparepass = row.getString("password");
				if(comparepass.equals(password)){
					loggedin = true;
				}
			}
			System.out.println("User found");
		}
		session.close();
		return loggedin;
	}


	public boolean Register(String name, String password) {
		boolean found=false;
		Session session = cluster.connect("playerData");

		PreparedStatement searchStatement = session.prepare("SELECT * from login where username = ?");
		ResultSet SearchRS = session.execute(searchStatement.bind(name));

		if(SearchRS.isExhausted())
		{
			// username doesnt already exist
			// so allow user to be added
			PreparedStatement statement = session.prepare("INSERT INTO login(username, password) values (?,?);");
			session.execute(statement.bind(name,password));	
			found=true;
		}
		else
		{
			// username already exists in the DB
			found=false;
		}
		session.close();
		return found;
	}

	//fully working
	//finalised
	public LinkedList<GunSlingerStore> searchUser(String user){		
		LinkedList<GunSlingerStore> gsList = new LinkedList<GunSlingerStore>();
		Session session = cluster.connect("playerData"); 

		PreparedStatement statement = session.prepare("select * from data where username = ?");
		BoundStatement boundStatement = new BoundStatement(statement);
		ResultSet rs = session.execute(boundStatement.bind(user));
		if (rs.isExhausted()) { 												
			System.out.println("No users returned");
		} else { 																
			for (Row row : rs) { 												
				GunSlingerStore nGS = new GunSlingerStore();
				nGS.setUsername(row.getString("username")); 	
				nGS.setAccuracy(row.getFloat("accuracy"));
				nGS.setTotalshots(row.getInt("totalshots"));		
				nGS.setMeleekills(row.getInt("meleekills"));
				nGS.setTotalkills(row.getInt("totalkills"));
				nGS.setHighscore(row.getInt("highscore"));
				nGS.setPlaytime(row.getUUID("playtime"));						
				gsList.add(nGS); 
			}
		}
		session.close();
		return gsList;
	}

	public boolean addFriend(String name, String friend) {
		Session session = cluster.connect("playerData");

		Set<String> sub = new HashSet<String>();
		sub.add(friend);
		PreparedStatement statement = session.prepare("update friends set friends = friends + ? where username = ?;");
		BoundStatement boundStatement = new BoundStatement(statement);
		session.execute(boundStatement.bind(sub,name));

		session.close();
		return true;
	}
}