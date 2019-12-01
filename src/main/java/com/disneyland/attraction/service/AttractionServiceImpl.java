package com.disneyland.attraction.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.disneyland.attraction.dao.AttractionDAO;
import com.disneyland.attraction.dao.LocationDAO;
import com.disneyland.attraction.dto.AttractionDTO;
import com.disneyland.attraction.dto.AttractionImageDTO;
import com.disneyland.attraction.dto.AttractionRemoveDTO;
import com.disneyland.attraction.dto.LocationAttractionDTO;
import com.disneyland.attraction.dto.LocationAttractionsDTO;
import com.disneyland.attraction.model.Attraction;
import com.disneyland.attraction.model.Location;

@Service
@Transactional
public class AttractionServiceImpl implements AttractionService {

	public static final String UPLOAD_DIRECTORY ="C:/Files/images";
	
	@Autowired
	private AttractionDAO attractionDAO;
	
	@Autowired
	private LocationDAO locationDAO;
	
	@Override
	public void create(Attraction attraction) {
		attraction.setAttraction_id(attractionDAO.nextPrimaryKey());
		attractionDAO.create(attraction);
	}
	
	@Override
	public void modify(Attraction attraction) {
		attractionDAO.modify(attraction);
	}

	@Override
	public AttractionRemoveDTO remove(String id) {
		AttractionRemoveDTO attractionRemoveDTO = new AttractionRemoveDTO();
		long attractionId;
		try {
			attractionId = Long.parseLong(id);
		} catch (NumberFormatException e) {
			attractionId = 0L;
		}
		attractionDAO.remove(attractionId);
		attractionRemoveDTO.setStatus(true);
		return attractionRemoveDTO;
	}
	
	@Override
	public AttractionImageDTO saveImage(String id, CommonsMultipartFile file, HttpSession session) {
	    AttractionImageDTO attractionImageDTO = new AttractionImageDTO();
//	    ServletContext context = session.getServletContext();
	    String path = UPLOAD_DIRECTORY;
//	    String fileName = file.getOriginalFilename();
	    String fileName = id;
	    byte[] bytes = file.getBytes();
	    try {
		    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path + File.separator + fileName)));
		    System.out.println(path + File.separator + fileName);
		    stream.write(bytes);
		    stream.flush();
		    stream.close();
		    attractionImageDTO.setStatus(true);
	    } catch (Exception e) {
	    	attractionImageDTO.setStatus(false);
	    }
	    return attractionImageDTO;
	}
	
	
	
	@Override
	public AttractionDTO get(String id) {
		long attractionId;
		try {
			attractionId = Long.parseLong(id);
		} catch (NumberFormatException e) {
			attractionId = 0L;
		}
		return new AttractionDTO(attractionDAO.get(attractionId));
	}

	@Override
	public List<Attraction> get() {
		return attractionDAO.get();
	}
	
	@Override
	public List<LocationAttractionsDTO> getByLocation() {
		List<LocationAttractionsDTO> locationAttractionsDTOs = new ArrayList<LocationAttractionsDTO>();
		List<Attraction> attractions = attractionDAO.get();
		List<Location> locations = locationDAO.get();
		for (Location location: locations) {
			List<LocationAttractionDTO> locationAttractionDTOs = new ArrayList<LocationAttractionDTO>();
			for (Attraction attraction: attractions) {
				if (location.getLocation_id() == attraction.getLocation_id()) {
					LocationAttractionDTO locationAttractionDTO = new LocationAttractionDTO(attraction);
					locationAttractionDTOs.add(locationAttractionDTO);
				}
			}
			LocationAttractionsDTO locationAttractionsDTO = new LocationAttractionsDTO();
			locationAttractionsDTO.setLocation_name(location.getName());
			locationAttractionsDTO.setAttractions(locationAttractionDTOs);
			locationAttractionsDTOs.add(locationAttractionsDTO);
		}
		return locationAttractionsDTOs;
	}
}
