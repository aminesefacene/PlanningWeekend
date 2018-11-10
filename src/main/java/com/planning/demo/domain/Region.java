package com.planning.demo.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Region implements Serializable {

	@Id
	@GeneratedValue
	private long idRegion;
	protected String region;
	protected String department;
	protected String city;
	
	@ManyToMany(mappedBy = "regions")
	private List<User> user;
	
	
	public Region() {
		super();
	}



	public Region(String name, String departemnt, String city) {
		super();
		this.region = name;
		this.department = departemnt;
		this.city = city;
	}



	public long getIdRegion() {
		return idRegion;
	}



	public void setIdRegion(long idRegion) {
		this.idRegion = idRegion;
	}



	public String getRegion() {
		return region;
	}



	public void setRegion(String region) {
		this.region = region;
	}



	public List<User> getUser() {
		return user;
	}



	public void setUser(List<User> user) {
		this.user = user;
	}



	public String getDepartment() {
		return department;
	}



	public void setDepartment(String department) {
		this.department = department;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}
	
	
	
	
	
}
