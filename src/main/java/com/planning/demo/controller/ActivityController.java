package com.planning.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.planning.demo.domain.Activity;
import com.planning.demo.domain.Location;
import com.planning.demo.domain.Region;
import com.planning.demo.domain.ResultSearch;
import com.planning.demo.domain.User;
import com.planning.demo.repository.ActivityRepository;
import com.planning.demo.repository.UserRepository;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping("/activity")
class ActivityController {
	
	


	@Autowired
	private ActivityRepository activityRepository;


	@Autowired
	private UserRepository userRepository;

	
	@ApiOperation(value = "Return all activities" )
	@Secured(value = { "ROLE_ADMIN", "ROLE_UTILISATEUR" })
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@ResponseBody
	public List<Activity> findAll() {
		try {
			return activityRepository.findAll();
		} catch (Exception e) {
			System.out.println("Error getting activities: " + e.toString());
		}
		return null;
	}

	@ApiOperation(value = "Return an activity by id" )
	@Secured(value = { "ROLE_ADMIN", "ROLE_UTILISATEUR" })
	@RequestMapping(value = "/getActivity/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Activity findOne(@PathVariable("id") Long id) {

		try {
			return activityRepository.findById(id).get();
		} catch (Exception e) {
			System.out.println("Error getting the activity : " + e.toString());
		}
		return null;
	}

	@ApiOperation(value = "return hello" )
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public String hello() {
		return "hello";
	}

	@ApiOperation(value = "Create an activity" )
	@Secured(value = { "ROLE_ADMIN" })
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Activity create(@RequestBody Activity activity) {
		try {
			activityRepository.save(activity);
		} catch (Exception e) {
			System.out.println("Error saving the activity : " + e.toString());
		}
		return null;

	}

	@ApiOperation(value = "Update an activity" )
	@Secured(value = { "ROLE_ADMIN" })
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Activity update(@PathVariable("id") Long id, @Valid @RequestBody Activity activity) {
		try {
			activity.setIdActivity(id);
			;
			return activityRepository.saveAndFlush(activity);
		} catch (Exception e) {
			System.out.println("Error updating the activity : " + e.toString());
		}
		return null;
	}

	@ApiOperation(value = "Delete an activity" )
	@Secured(value = { "ROLE_ADMIN" })
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		try {
			activityRepository.deleteById(id);
		} catch (Exception e) {
			System.out.println("Error deleting the activity : " + e.toString());
		}
	}

	
	@ApiOperation(value = "Return propositions of activities for week end for the user" )
	@RequestMapping(value = "/resultSearch", method = RequestMethod.GET)
	public List<ResultSearch> select() {

		List<ResultSearch> res = new ArrayList<>();
		
		List<User> users = userRepository.getAllUsers();
		
		List<Region> regions = new ArrayList<>();
		List<Activity> activities = new ArrayList<>();

		List<Location> location = new ArrayList<>();
		
		ResultSearch rs;
		
		String locationProvisoire = "";
		String activityProvisoire = "";
		
		for (int s = 0; s < users.size(); s++) {
			regions = users.get(s).getRegions();
			activities = users.get(s).getActivities();
			
			for (int i = 0; i < regions.size(); i++) {
				location = activityRepository.getAllRes(regions.get(i).getRegion(), regions.get(i).getDepartment(), regions.get(i).getCity());
				for (int j = 0; j < location.size(); j++) {
					
					locationProvisoire = "";
					activityProvisoire = "";
					for (int j2 = 0; j2 < location.get(j).getActivities().size(); j2++) {
						for (int k = 0; k < activities.size(); k++) {
							if(activities.get(k) == location.get(j).getActivities().get(j2)) {
								locationProvisoire = locationString(location.get(j), regions.get(i).getCity());
								activityProvisoire += activityString(activities.get(k)) + ", ";
								
							}
						}
					}
					
					rs = new ResultSearch();
					rs.setEmail(users.get(s).getMail());
					rs.setActivities(activityProvisoire);
					rs.setLocation(locationProvisoire);

					res.add(rs);
					for (int a = 0; a < res.size(); a++) {
					}
				}
				

			}
		}
		
		return res; 
	}
	
	public String locationString(Location location, String city) {
		return location.getNumStreet() + ", " + location.getNameStreet() + ", " + location.getZipCode() + ", " + city;
		
	}
	
	public String activityString(Activity activity) {
		return activity.getName() + " " + activity.getLevel(); 
		
	}
	 	

}