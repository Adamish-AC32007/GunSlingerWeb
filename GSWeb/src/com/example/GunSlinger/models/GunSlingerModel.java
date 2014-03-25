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


import java.util.LinkedList;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import com.example.GunSlinger.lib.*;
import com.example.GunSlinger.stores.*;
public class GunSlingerModel {
	Cluster cluster;
	public GunSlingerModel(){

	}

	public void setCluster(Cluster cluster){
		this.cluster=cluster;
	}

	public LinkedList<GunSlingerStore> getTweets() {
		LinkedList<GunSlingerStore> tweetList = new LinkedList<GunSlingerStore>();
		Session session = cluster.connect("keyspace2");

		PreparedStatement statement = session.prepare("SELECT * from tweets");
		BoundStatement boundStatement = new BoundStatement(statement);
		ResultSet rs = session.execute(boundStatement);
		if (rs.isExhausted()) {
			System.out.println("No Tweets returned");
		} else {
			for (Row row : rs) {
				GunSlingerStore ts = new GunSlingerStore();
				//ts.setTweet(row.getString("tweet"));
				//ts.setUser(row.getString("user"));
				tweetList.add(ts);
			}
		}
		session.close();
		return tweetList;
	}
}