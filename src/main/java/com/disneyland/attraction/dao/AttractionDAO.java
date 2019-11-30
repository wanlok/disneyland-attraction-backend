package com.disneyland.attraction.dao;

import java.util.List;

import com.disneyland.attraction.model.Attraction;

public interface AttractionDAO {
	long nextPrimaryKey();
	void create(Attraction attraction);
	void modify(Attraction attraction);
	void remove(Long attractionId);
	Attraction get(Long attractionId);
	List<Attraction> get();
}
