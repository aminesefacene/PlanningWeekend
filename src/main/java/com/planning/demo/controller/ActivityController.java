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
import com.planning.demo.domain.Activity;
import com.planning.demo.repository.ActivityRepository;

@Controller
@RequestMapping("/activity")
class ActivityController {

	@Autowired
	private ActivityRepository activityRepository;
	
	
	

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public List<Activity> findAll() {
		return activityRepository.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Activity findOne(@PathVariable("id") Long id) {
		return activityRepository.findById(id).get();
	}
	


	@RequestMapping(value="/test", method = RequestMethod.GET)
	@ResponseBody
	public String hello() {
		return "salut";
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	@ResponseBody
	public Activity create(@RequestBody Activity Activity) {
		return activityRepository.save(Activity);
		
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Activity update(@PathVariable("id") Long id, @Valid @RequestBody Activity activity) {
		activity.setIdActivity(id);
		return activityRepository.saveAndFlush(activity);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		activityRepository.deleteById(id);
	}

}