package com.disneyland.attraction.dto;

import java.util.List;

public class LocationAttractionsDTO {

	private String location_name;
	
	private List<AttractionDTO> attractions;
	
	public LocationAttractionsDTO() {
		
	}

	public String getLocation_name() {
		return location_name;
	}

	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}

	public List<AttractionDTO> getAttractions() {
		return attractions;
	}

	public void setAttractions(List<AttractionDTO> attractions) {
		this.attractions = attractions;
	}
}
