package com.disneyland.attraction.dao;

import java.util.List;

import com.disneyland.attraction.model.Location;

public interface LocationDAO {
	Location get(Long locationId);
	List<Location> get();
}
