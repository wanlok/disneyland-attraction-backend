package com.disneyland.attraction.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Attraction")
public class Attraction {

	@Id
	@Column(name="attraction_id")
	private Long attraction_id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="height")
	private String height;
	
	@Column(name="start_hour")
	private String start_hour;
	
	@Column(name="end_hour")
	private String end_hour;
	
	@Column(name="availability")
	private String availability;
	
	@Column(name="location_id")
	private Long location_id;
	
	public Attraction() {
		
	}

	public Long getAttraction_id() {
		return attraction_id;
	}

	public void setAttraction_id(Long attraction_id) {
		this.attraction_id = attraction_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getStart_hour() {
		return start_hour;
	}

	public void setStart_hour(String start_hour) {
		this.start_hour = start_hour;
	}

	public String getEnd_hour() {
		return end_hour;
	}

	public void setEnd_hour(String end_hour) {
		this.end_hour = end_hour;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public Long getLocation_id() {
		return location_id;
	}

	public void setLocation_id(Long location_id) {
		this.location_id = location_id;
	}
}
