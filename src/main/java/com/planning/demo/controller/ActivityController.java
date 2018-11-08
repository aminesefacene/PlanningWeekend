package com.planning.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.planning.demo.domain.Activity;
import com.planning.demo.domain.Level;
import com.planning.demo.domain.Location;
import com.planning.demo.domain.Region;
import com.planning.demo.domain.Search;
import com.planning.demo.domain.User;
import com.planning.demo.repository.ActivityRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/activity")
class ActivityController {

	@Autowired
	private ActivityRepository activityRepository;

	@ApiOperation(value = "Return all activities" )
	@Secured(value = { "ROLE_ADMIN", "ROLE_ETUDIANT" })
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
	@Secured(value = { "ROLE_ADMIN", "ROLE_ETUDIANT" })
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

	
	@ApiOperation(value = "Return a list of locations" )
	@RequestMapping(value = "/resultSearch", method = RequestMethod.POST)
	public List<Location> selectActivities(@RequestBody Search search) {

		Region region = search.getRegion();
		
		List<Location> location = activityRepository.getAllRes(region.getRegion(), region.getDepartment(), region.getCity());
		List<Activity> activities = search.getActivities();
		
		return location.stream().filter(loc-> isActivityEquals(loc.getActivities(), activities)).collect(Collectors.toList());
		
	}
	
	
	
	@RequestMapping(value = "/getSearch", method = RequestMethod.GET)
	public Search selectSearch() {

		
		Activity a1 = new Activity("Football", Level.EASY);
		Activity a2 = new Activity("Volleyball", Level.EASY);
		Activity a3 = new Activity("Natation", Level.EASY);
		//List<Activity> activities = user.getActivities();
		List<Activity> activities = new ArrayList<>();
		activities.add(a1);
		activities.add(a2);
		activities.add(a3);

		Region region = new Region("blabal","balala","balak");
		Search search = new Search(activities,region);
		
		return search;
	}
	
	
	private boolean isActivityEquals(List<Activity> activity, List<Activity> activities) {
		
		for(Activity act:activity) {
			for(Activity acts:activities) {
				if(act.getIdActivity() == act.getIdActivity()) {
					return true;
				}
			}
		}
		return false;
	}

}