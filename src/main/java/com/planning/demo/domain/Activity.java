package com.planning.demo.domain;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Activity implements Serializable{

	@Id
	@GeneratedValue
	private long idActivity;
	private String name;
	private Level level;
	
	
	public Activity() {
		super();
	}

	public Activity(String name, Level level) {
		super();
		this.name = name;
		this.level = level;
	}

	public long getIdActivity() {
		return idActivity;
	}

	public void setIdActivity(long idActivity) {
		this.idActivity = idActivity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
	
	@Override
	public String toString() {
		return "Activities [name = " + name + ", level = " + level + "]";
	}

}
