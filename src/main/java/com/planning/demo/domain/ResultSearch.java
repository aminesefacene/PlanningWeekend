package com.planning.demo.domain;

import java.util.List;

public class ResultSearch {
	
	private String email;
	private String location;
	private String activities;
	
	
	public ResultSearch(String email, String location, String activities)  {
		super();
		this.email = email;
		this.location = location;
		this.activities = activities;
	}
	
	public ResultSearch() {
		super();
		this.email = null;
		this.location = null;
		this.activities = null;
	}
	
	

	public String getActivities() {
		return activities;
	}
	public void setActivities(String activities) {
		this.activities = activities;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email= email;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	

}
