package com.disneyland.attraction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disneyland.attraction.dao.LocationDAO;
import com.disneyland.attraction.model.Location;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationDAO locationDAO;
	
	@Override
	public Location get(String id) {
		long locationId;
		try {
			locationId = Long.parseLong(id);
		} catch (NumberFormatException e) {
			locationId = 0L;
		}
		return locationDAO.get(locationId);
	}

	@Override
	public List<Location> get() {
		return locationDAO.get();
	}
}
