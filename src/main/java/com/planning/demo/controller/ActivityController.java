package com.planning.demo.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.SocketUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.planning.demo.domain.Activity;
import com.planning.demo.domain.Level;
import com.planning.demo.domain.Location;
import com.planning.demo.domain.Region;
import com.planning.demo.domain.Search;
import com.planning.demo.domain.User;
import com.planning.demo.repository.ActivityRepository;


import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping("/activity")
class ActivityController {

	@Autowired
	private ActivityRepository activityRepository;

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

	
	@ApiOperation(value = "Return a list of locations" )
	@RequestMapping(value = "/resultSearch", method = RequestMethod.POST)
	public List<Location> selectActivities(@RequestBody Search search) {

		List<Region> regions = search.getRegions();
		List<Location> location = new ArrayList<>();
		List<Activity> activities = search.getActivities();
		
		List<Location> locationFinal = new ArrayList<>();
		
		for (int i = 0; i < regions.size(); i++) {
			location = activityRepository.getAllRes(regions.get(i).getRegion(), regions.get(i).getDepartment(), regions.get(i).getCity());
			location.stream().filter(loc-> isActivityEquals(loc.getActivities(), activities)).collect(Collectors.toList()); 
			locationFinal.addAll(location);
		}
		
		return locationFinal;
		
	}
	
	@RequestMapping(value = "/result", method = RequestMethod.POST)
	public List<Location> select(@RequestBody Search search) {

		List<Region> regions = search.getRegions();
		List<Location> location = new ArrayList<>();
		List<Activity> activities = search.getActivities();
		
		List<Location> locationFinal = new ArrayList<>();
		
		for (int i = 0; i < regions.size(); i++) {
			location = activityRepository.getAllRes(regions.get(i).getRegion(), regions.get(i).getDepartment(), regions.get(i).getCity());
			location.stream().filter(loc-> isActivityEquals(loc.getActivities(), activities)).collect(Collectors.toList()); 
			locationFinal.addAll(location);
		}
		
		return locationFinal; 
	}
	
	@RequestMapping(value = "/weather", method = RequestMethod.GET)
	public HttpResponse<JsonNode> show() throws UnirestException {
	//Method body
	//In the method body you should make a request to the openweather server with an api key which you can get by registering in the website. You can achieve this with Unirest library (it's the easiest way)
	HttpResponse<JsonNode> response = Unirest.get("http://api.openweathermap.org/data/2.5/weather")
	                            .queryString("APPID","a70aaf666a6b23f772a86455a7c9776d")
	                            .queryString("city","London")
	                            .header("content-type","application/json")
	                            .asJson();

	return response;

	}
	

    	
	
	
	@RequestMapping(value = "/getSearch", method = RequestMethod.GET)
	public Search selectSearch() {

		
		Activity a1 = new Activity("Football", Level.EASY);
		Activity a2 = new Activity("Volleyball", Level.EASY);
		Activity a3 = new Activity("Basketball", Level.EASY);
		List<Activity> activities = new ArrayList<>();
		activities.add(a1);
		activities.add(a2);
		activities.add(a3);

		List<Region> regions = new ArrayList<>();
		Region region = new Region("Bretagne","Ile et Vilaine","Rennes");
		Region region2 = new Region("Aquitaine", "Gironde", "Bordeaux");
		regions.add(region);
		regions.add(region2);
	
		Search search = new Search(activities, regions);
				
		return search;
	}
	
	
	private boolean isActivityEquals(List<Activity> activity, List<Activity> activities) {
		
		for(Activity act:activity) {
			for(Activity acts:activities) {
				if(act.getIdActivity() == acts.getIdActivity()) {
					System.out.println(act.getIdActivity() + " "+ act.getName() +  " " + acts.getIdActivity() + " " + acts.getName());
					return true;
				}
			}
		}
		return false;
	}

}