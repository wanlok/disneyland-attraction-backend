package com.disneyland.attraction.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.disneyland.attraction.dto.LocationDTO;
import com.disneyland.attraction.model.Location;
import com.disneyland.attraction.service.LocationService;

@RestController
public class LocationController {

	@Autowired
	private LocationService locationService;
	
	private String complete(Location location, List<Location> locations) {
		String s = "";
		if (location.getParent_location_id() != null) {
			for (Location l: locations) {
				if (l.getLocation_id() == location.getParent_location_id()) {
					s += complete(l, locations) + " > " + location.getName();
					break;
				}
			}
		} else {
			s = location.getName();
		}
		return s;
	}
	
	@RequestMapping(value="/location", method = { RequestMethod.GET })
	public ResponseEntity<List<LocationDTO>> get() {
		List<LocationDTO> locationDTOs = new ArrayList<LocationDTO>();
		List<Location> locations = locationService.get();
		for (Location location: locations) {
			LocationDTO locationDTO = new LocationDTO();
			locationDTO.setLocation_id(location.getLocation_id());
			locationDTO.setName(complete(location, locations));
			locationDTOs.add(locationDTO);
		}
		return ResponseEntity.ok().body(locationDTOs);
	}
}
