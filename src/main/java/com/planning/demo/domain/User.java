package com.planning.demo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User implements Serializable{
	
	
	@Id
	@GeneratedValue
	private long idUser;
	private String name;
	private String firstName;
	private String userName;
	private String mail;
	private String phone;
	
	@OneToMany
	private List<Activity> activities;
	
	@OneToMany
	private List<Region> regions;
	
	
	public User() {
		super();
	}

	public User(String name, String firstName, String userName, String mail, String phone) {
		super();
		this.name = name;
		this.firstName = firstName;
		this.userName = userName;
		this.mail = mail;
		this.phone = phone;
		this.activities = new ArrayList<Activity>();
		this.regions = new ArrayList<Region>();
	}

	public void addActivity(Activity activity) {
		this.activities.add(activity);
	}
	
	public void addRegion(Region region) {
		this.regions.add(region);
	}
	
	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	/*
	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	@Override
	public String toString() {
		return "User [idUser = " + idUser + ",name = " + name + ",firsname = " + firstName + 
						",activities = " + activities + "]";
	}*/

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

}
