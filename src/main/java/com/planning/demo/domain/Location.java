package com.planning.demo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Location implements Serializable{
	
	@Id
	@GeneratedValue
	private long idLocation;
	private long numStreet;
	private String nameStreet;
	private String zipCode;
	private String city;
	
	@OneToMany
	private List<Activity> activities;
	
	@OneToOne
	@JoinColumn(name="idRegion")
	private Region region;
	
	public Location() {
		super();
	}

	
	public Location(long numStreet, String nameStreet, String zipCode, String city, Region region) {
		super();
		this.numStreet = numStreet;
		this.nameStreet = nameStreet;
		this.zipCode = zipCode;
		this.city = city;
		this.activities = new ArrayList<Activity>();
		this.region = region;
	}

	public void addActivity(Activity activity) {
		this.activities.add(activity);
	}
		
	public long getIdLocation() {
		return idLocation;
	}
	public void setIdAddress(long idAddress) {
		this.idLocation = idAddress;
	}
	public long getNumStreet() {
		return numStreet;
	}
	public void setNumStreet(long numStreet) {
		this.numStreet = numStreet;
	}
	public String getNameStreet() {
		return nameStreet;
	}
	public void setNameStreet(String nameStreet) {
		this.nameStreet = nameStreet;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
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


	public void setIdLocation(long idLocation) {
		this.idLocation = idLocation;
	}

	

}
