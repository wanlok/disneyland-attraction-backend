package com.disneyland.attraction.service;

import java.util.List;

import com.disneyland.attraction.model.Location;

public interface LocationService {
	Location get(String id);
	List<Location> get();
}
