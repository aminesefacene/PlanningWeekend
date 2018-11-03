package com.planning.demo.domain;

import java.util.List;

public class Search {
	
	private long idSearch;
	private List<Activity> activities;
	private Region region;
	
	
	public Search() {
		super();
	}

	public Search(List<Activity> activities, Region region) {
		super();
		this.activities = activities;
		this.region = region;
	}
	
	public List<Activity> getActivities() {
		return activities;
	}
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	

	
}
