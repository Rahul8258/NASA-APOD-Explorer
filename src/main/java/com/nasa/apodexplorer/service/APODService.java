package com.nasa.apodexplorer.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nasa.apodexplorer.model.APODResponse;

@Service
public class APODService {

	private RestTemplate restTemplate = new RestTemplate();
	
	@Cacheable(value = "apodExplorerCache", key = "'today'")
	public APODResponse getAPOD() {
		
		String url = "https://api.nasa.gov/planetary/apod?"
				+ "api_key=tBIewopCneEgjiBuuqIyg5bhHxYZV0QE3gNEjvNk";
		
		return restTemplate.getForObject(url, APODResponse.class);
	}
	
	@Cacheable(value = "apodExplorerCache", key = "#date")
	public APODResponse getAPOD(String date) {
		
		String url = "https://api.nasa.gov/planetary/apod?"
				+ "api_key=tBIewopCneEgjiBuuqIyg5bhHxYZV0QE3gNEjvNk&date="+date;
		
		return restTemplate.getForObject(url, APODResponse.class);
	}

	@Cacheable(value = "apodExplorerCache", key = "'recent'")
	public List<APODResponse> getPastAPODs(LocalDate today, LocalDate pastDays) {
		
		String url = "https://api.nasa.gov/planetary/apod?"
				+ "api_key=tBIewopCneEgjiBuuqIyg5bhHxYZV0QE3gNEjvNk"
				+ "&start_date="+pastDays
				+"&end_date="+today;
		
		APODResponse[] responses = restTemplate.getForObject(url, APODResponse[].class);
		List<APODResponse> list = Arrays.asList(responses);
		Collections.reverse(list);
		return list;
	}
}
