package com.disneyland.attraction.dto;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.disneyland.attraction.model.Attraction;

public class LocationAttractionDTO {

	private Long attraction_id;
	
	private String name;
	
	private String description;
	
	private String start_hour;
	
	private String end_hour;
	
	private Boolean availability;
	
	private String image_url;
	
	public LocationAttractionDTO(Attraction attraction) {
		setAttraction_id(attraction.getAttraction_id());
		setName(attraction.getName());
		setDescription(attraction.getDescription());
		setStart_hour(attraction.getStart_hour());
		setEnd_hour(attraction.getEnd_hour());
		setAvailability(attraction.getAvailability().equals("Y"));
		setImage_url(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/attraction/" + getAttraction_id() + "/image" );
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

	public Boolean getAvailability() {
		return availability;
	}

	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
}
