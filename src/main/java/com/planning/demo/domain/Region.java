package com.planning.demo.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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
		// TODO Auto-generated constructor stub
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
