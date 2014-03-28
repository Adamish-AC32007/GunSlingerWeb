package com.example.GunSlinger.stores;

import java.util.UUID;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GunSlingerStore implements Comparable<GunSlingerStore>{
	
	String username;
	float accuracy;
	int totalshots;
	int meleekills;
	int totalkills;
	int highscore;
	UUID playtime;
	
	String type;
	
	public String getUsername(){
		return username;
	}
	public float getAccuracy(){
		return accuracy;
	}
	public int getTotalshots(){
		return totalshots;
	}
	public int getMeleekills(){
		return meleekills;
	}
	public int getTotalkills(){
		return totalkills;
	}
	public int getHighscore(){
		return highscore;
	}
	public UUID getPlaytime(){
		return playtime;
	}
	
	public void setUsername(String username){
		this.username=username;
	}
	public void setAccuracy(float accuracy){
		this.accuracy=accuracy;
	}
	public void setTotalshots(int totalshots){
		this.totalshots=totalshots;
	}
	public void setMeleekills(int meleekills){
		this.meleekills=meleekills;
	}
	public void setTotalkills(int totalkills){
		this.totalkills=totalkills;
	}
	public void setHighscore(int highscore){
		this.highscore=highscore;
	}
	public void setPlaytime(UUID playtime){
		this.playtime=playtime;
	}
	
	
	@Override
	public int compareTo(GunSlingerStore o) {
		int comparedScore = o.highscore;
		if (this.highscore > comparedScore) {
			return 1;
		} else if (this.highscore == comparedScore) {
			return 0;
		} else {
			return -1;
		}
	}
 
	public String toString() {
		return type;
	}

}
