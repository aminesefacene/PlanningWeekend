package com.planning.demo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class User implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long idUser;
	private String username;
	private String password;
	private String mail;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Role> roles;
	
	@OneToMany
	private List<Activity> activities;
	
	@OneToMany
	private List<Region> regions;
	
	
	public User() {
		super();
	}

	public User(String userName, String password, String mail) {
		super();
		this.username = userName;
		this.password = password;
		this.mail = mail;
		this.activities = new ArrayList<Activity>();
		this.regions = new ArrayList<Region>();
		this.roles = new ArrayList<Role>();
	}

	public User(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.mail = user.getMail();
		this.activities = user.getActivities();
		this.regions = user.getRegions();
		this.roles = user.getRoles();
		
	}

	public void addActivity(Activity activity) {
		this.activities.add(activity);
	}
	
	public void addRegion(Region region) {
		this.regions.add(region);
	}
	
	public void addRole(Role role) {
		this.roles.add(role);
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}
	
}
