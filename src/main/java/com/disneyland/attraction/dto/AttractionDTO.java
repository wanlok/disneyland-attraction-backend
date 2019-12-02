package com.disneyland.attraction.dto;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.disneyland.attraction.model.Attraction;

public class AttractionDTO {

	private Long attraction_id;
	
	private String name;
	
	private String description;
	
	private String height;
	
	private String start_hour;
	
	private String end_hour;
	
	private String availability;

	private Long location_id;
	
	private String image_url;
	
	public AttractionDTO(Attraction attraction) {
		setAttraction_id(attraction.getAttraction_id());
		setName(attraction.getName());
		setDescription(attraction.getDescription());
		setHeight(attraction.getHeight());
		setStart_hour(attraction.getStart_hour());
		setEnd_hour(attraction.getEnd_hour());
		setAvailability(attraction.getAvailability());
		setLocation_id(attraction.getLocation_id());
		String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
		setImage_url(baseUrl + "/attraction/" + getAttraction_id() + "/image" );
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

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
}
