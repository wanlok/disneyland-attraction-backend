package com.disneyland.attraction.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Location")
public class Location {
	
	@Id
	@Column(name="location_id")
	private Long location_id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="parent_location_id")
	private Long parent_location_id;
	
	public Location() {
		
	}

	public Long getLocation_id() {
		return location_id;
	}

	public void setLocation_id(Long location_id) {
		this.location_id = location_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParent_location_id() {
		return parent_location_id;
	}

	public void setParent_location_id(Long parent_location_id) {
		this.parent_location_id = parent_location_id;
	}
}
