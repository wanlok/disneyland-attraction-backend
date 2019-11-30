package com.disneyland.attraction.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.disneyland.attraction.dto.AttractionDTO;
import com.disneyland.attraction.dto.AttractionImageDTO;
import com.disneyland.attraction.dto.AttractionRemoveDTO;
import com.disneyland.attraction.dto.AttractionV2DTO;
import com.disneyland.attraction.model.Attraction;
import com.disneyland.attraction.service.AttractionService;
import com.disneyland.attraction.service.AttractionServiceImpl;

@RestController
public class AttractionController {

	@Autowired
	private AttractionService attractionService;
	
	@RequestMapping(value="/attraction", method = { RequestMethod.GET })
	public ResponseEntity<List<Attraction>> get() {
		List<Attraction> attractions = attractionService.get();
		return ResponseEntity.ok().body(attractions);
	}
	
	@RequestMapping(value="/attractionV2", method= { RequestMethod.GET })
	public ResponseEntity<List<AttractionV2DTO>> getV2() {
		List<AttractionV2DTO> attractions = attractionService.getV2();
		return ResponseEntity.ok().body(attractions);
	}
	
	@RequestMapping(value="/attraction/{id}", method = { RequestMethod.GET })
	public ResponseEntity<AttractionDTO> get(@PathVariable("id") String id) {
		AttractionDTO attractionDTO = attractionService.get(id);
		return ResponseEntity.ok().body(attractionDTO);
	}
		
	@RequestMapping(value="/attraction", method = { RequestMethod.POST })
	public ResponseEntity<?> post(@RequestBody Attraction attraction) {
		attractionService.create(attraction);
		return ResponseEntity.ok().body(attraction);
	}
	
	@ResponseBody
	@RequestMapping(value = "/attraction/{id}/image", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getImage(@PathVariable("id") String id) throws IOException {
		String path = AttractionServiceImpl.UPLOAD_DIRECTORY + File.separator + id;
		File file = new File(path);
		if (!file.exists()) {
			file = new File(AttractionServiceImpl.UPLOAD_DIRECTORY + File.separator + 0);
		}
		InputStream inputStream = new FileInputStream(file);
	    return IOUtils.toByteArray(inputStream);
	}
	
	@RequestMapping(value="/attraction/{id}/image", method = { RequestMethod.POST })
	public ResponseEntity<AttractionImageDTO> post(@PathVariable("id") String id, @RequestParam("file") CommonsMultipartFile file, HttpSession session) {
		AttractionImageDTO attractionImageDTO = attractionService.saveImage(id, file, session);
		return ResponseEntity.ok().body(attractionImageDTO);
	}
	
	@RequestMapping(value="/attraction", method = { RequestMethod.PUT })
	public ResponseEntity<?> put(@RequestBody Attraction attraction) {
		attractionService.modify(attraction);
		return ResponseEntity.ok().body(attraction);
	}
	
	@RequestMapping(value="/attraction/{id}", method = { RequestMethod.DELETE })
	public ResponseEntity<?> put(@PathVariable("id") String id) {
		AttractionRemoveDTO attractionRemoveDTO = attractionService.remove(id);
		return ResponseEntity.ok().body(attractionRemoveDTO);
	}
}