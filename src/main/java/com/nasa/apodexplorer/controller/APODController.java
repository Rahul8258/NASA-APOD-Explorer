package com.nasa.apodexplorer.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nasa.apodexplorer.model.APODResponse;
import com.nasa.apodexplorer.service.APODService;

@CrossOrigin(origins = "*")
@RestController
public class APODController {
	
	
	@Autowired
	private APODService service;
		
	@GetMapping("api/apod")
	public APODResponse getAPOD() {
		return service.getAPOD();
	}
	
	@GetMapping("api/apod/{date}")
	public APODResponse getAPOD(@PathVariable String date) {
		return service.getAPOD(date);
	}
	
	@GetMapping("api/apod/past")
	public List<APODResponse> getpastAPODs(){
		LocalDate today = LocalDate.now();
		LocalDate yesterday = today.minusDays(1);
		LocalDate pastDays = today.minusDays(30);
		
		return service.getPastAPODs(yesrterday,pastDays);
	}
}


