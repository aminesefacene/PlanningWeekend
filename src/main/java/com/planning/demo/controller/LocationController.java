package com.planning.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.planning.demo.domain.Location;
import com.planning.demo.repository.LocationRepository;

@Controller
@RequestMapping("/location")
class LocationController {

	@Autowired
	private LocationRepository locationRepository;
	
	
	

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public List<Location> findAll() {
		return locationRepository.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Location findOne(@PathVariable("id") Long id) {
		return locationRepository.findById(id).get();
	}
	


	@RequestMapping(value="/test", method = RequestMethod.GET)
	@ResponseBody
	public String hello() {
		return "salut";
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	@ResponseBody
	public Location create(@RequestBody Location Location) {
		return locationRepository.save(Location);
		
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Location update(@PathVariable("id") Long id, @Valid @RequestBody Location location) {
		location.setIdLocation(id);
		return locationRepository.saveAndFlush(location);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		locationRepository.deleteById(id);
	}

}