package com.disneyland.attraction.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.disneyland.attraction.dao.AttractionDAO;
import com.disneyland.attraction.dao.LocationDAO;
import com.disneyland.attraction.dto.AttractionDTO;
import com.disneyland.attraction.dto.AttractionImageDTO;
import com.disneyland.attraction.dto.AttractionRemoveDTO;
import com.disneyland.attraction.dto.LocationAttractionsDTO;
import com.disneyland.attraction.model.Attraction;
import com.disneyland.attraction.model.Location;

@PropertySource("classpath:application.properties")
@Service
@Transactional
public class AttractionServiceImpl implements AttractionService {

	@Autowired
	private Environment environment;
	
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
		String path = environment.getProperty("image.path") + File.separator + id;
		File file = new File(path);
		if (file.exists()) {
			file.delete();
			attractionRemoveDTO.setStatus(true);
		} else {
			attractionRemoveDTO.setStatus(false);
		}
		return attractionRemoveDTO;
	}
	
	@Override
	public AttractionImageDTO saveImage(String id, CommonsMultipartFile file, HttpSession session) {
	    AttractionImageDTO attractionImageDTO = new AttractionImageDTO();
//	    ServletContext context = session.getServletContext();
	    String path = environment.getProperty("image.path");
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
	public byte[] getImage(String id) {
		String path = environment.getProperty("image.path") + File.separator;
		File file = new File(path + id);
//		if (!file.exists()) {
//			file = new File(path + 0);
//		}
		byte[] bytes = null;
		try {
			InputStream inputStream = new FileInputStream(file);
			bytes = IOUtils.toByteArray(inputStream);
			inputStream.close();
		} catch (Exception e) {
			
		}
	    return bytes;
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
	public List<AttractionDTO> get() {
		List<Attraction> attractions = attractionDAO.get();
		List<AttractionDTO> attractionDTOs = new ArrayList<AttractionDTO>();
		for (Attraction attraction: attractions) {
			AttractionDTO attractionDTO = new AttractionDTO(attraction);
			attractionDTOs.add(attractionDTO);
		}
		return attractionDTOs;
	}
	
	@Override
	public List<LocationAttractionsDTO> getByLocation() {
		List<LocationAttractionsDTO> locationAttractionsDTOs = new ArrayList<LocationAttractionsDTO>();
		List<Attraction> attractions = attractionDAO.get();
		List<Location> locations = locationDAO.get();
		for (Location location: locations) {
			List<AttractionDTO> attractionDTOs = new ArrayList<AttractionDTO>();
			for (Attraction attraction: attractions) {
				if (location.getLocation_id() == attraction.getLocation_id()) {
					AttractionDTO attractionDTO = new AttractionDTO(attraction);
					attractionDTOs.add(attractionDTO);
				}
			}
			LocationAttractionsDTO locationAttractionsDTO = new LocationAttractionsDTO();
			locationAttractionsDTO.setLocation_name(location.getName());
			locationAttractionsDTO.setAttractions(attractionDTOs);
			locationAttractionsDTOs.add(locationAttractionsDTO);
		}
		return locationAttractionsDTOs;
	}
}
