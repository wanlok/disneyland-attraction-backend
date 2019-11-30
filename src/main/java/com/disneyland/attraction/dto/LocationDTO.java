package com.disneyland.attraction.dto;

public class LocationDTO {
	
	private Long location_id;
	
	private String name;
	
	public LocationDTO() {
		
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
}
