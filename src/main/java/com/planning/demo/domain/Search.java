package com.planning.demo.domain;

import java.util.List;

public class Search {
	
	private List<Activity> activities;
	private List<Region> regions; 
	
	public Search() {
		super();
	}
	
	public Search(List<Activity> activities, List<Region> regions) {
		super();
		this.activities = activities;
		this.regions = regions;
	}
	
	public Search(User user) {
		super();
		this.activities = user.getActivities();
		this.regions = user.getRegions();
		
	}

	public List<Activity> getActivities() {
		return activities;
	}
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public List<Region> getRegions(){
		return regions;
	}
	
	public void setRegions(List<Region> region) {
		this.regions = region;
	}

	
}
