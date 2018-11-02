package com.planning.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.planning.demo.domain.Location;
import com.planning.demo.repository.LocationRepository;

@RestController
@RequestMapping("/location")
class LocationController {

	@Autowired
	private LocationRepository locationRepository;
	
	
	
	@Secured(value={"ROLE_ADMIN","ROLE_ETUDIANT"})
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@ResponseBody
	public List<Location> findAll() {
		try {
			return locationRepository.findAll();
		} catch (Exception e) {
			System.out.println("Error getting locations: " + e.toString());
		}
		return null;
	}

	@Secured(value={"ROLE_ADMIN","ROLE_ETUDIANT"})
	@RequestMapping(value = "/getLocation/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Location findOne(@PathVariable("id") Long id) {
		try {
			return locationRepository.findById(id).get();
		} catch (Exception e) {
			System.out.println("Error getting the location : " + e.toString());
		}
		return null;
	}
	


	@RequestMapping(value="/test", method = RequestMethod.GET)
	@ResponseBody
	public String hello() {
		return "salut";
	}
	
	@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value="/create", method = RequestMethod.POST)
	@ResponseBody
	public Location create(@RequestBody Location location) {
		try {
			locationRepository.save(location);
		} catch (Exception e) {
			System.out.println("Error saving the location : " + e.toString());
		}
		return null;
		
	}

	@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Location update(@PathVariable("id") Long id, @Valid @RequestBody Location location) {
		try {
			location.setIdLocation(id);;
			return locationRepository.saveAndFlush(location);
		} catch (Exception e) {
			System.out.println("Error updating the location : " + e.toString());
		}
		return null;
	}

	@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		try {
			locationRepository.deleteById(id);
		} catch (Exception e) {
			System.out.println("Error deleting the location : " + e.toString());
		}
	}

}