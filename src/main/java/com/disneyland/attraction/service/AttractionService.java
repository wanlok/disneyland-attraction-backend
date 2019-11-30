package com.disneyland.attraction.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.disneyland.attraction.dto.AttractionDTO;
import com.disneyland.attraction.dto.AttractionImageDTO;
import com.disneyland.attraction.dto.AttractionRemoveDTO;
import com.disneyland.attraction.dto.AttractionV2DTO;
import com.disneyland.attraction.model.Attraction;

public interface AttractionService {
	void create(Attraction attraction);
	void modify(Attraction attraction);
	AttractionRemoveDTO remove(String id);
	AttractionImageDTO saveImage(String id, CommonsMultipartFile file, HttpSession session);
	AttractionDTO get(String id);
	List<Attraction> get();
	List<AttractionV2DTO> getV2();
}