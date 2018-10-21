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
import com.planning.demo.domain.Region;
import com.planning.demo.repository.RegionRepository;

@Controller
@RequestMapping("/region")
class RegionController {

	@Autowired
	private RegionRepository regionRepository;
	
	
	

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@ResponseBody
	public List<Region> findAll() {
		try {
			return regionRepository.findAll();
		} catch (Exception e) {
			System.out.println("Error getting regions: " + e.toString());
		}
		return null;
	}

	@RequestMapping(value = "/getRegion/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Region findOne(@PathVariable("id") Long id) {
		try {
			return regionRepository.findById(id).get();
		} catch (Exception e) {
			System.out.println("Error getting region: " + e.toString());
		}
		return null;
	}
	


	@RequestMapping(value="/test", method = RequestMethod.GET)
	@ResponseBody
	public String hello() {
		return "salut";
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	@ResponseBody
	public Region create(@RequestBody Region region) {
		try {
			return regionRepository.save(region);
		} catch (Exception e) {
			System.out.println("Error saving the region : " + e.toString());
		}
		return null;
		
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Region update(@PathVariable("id") Long id, @Valid @RequestBody Region region) {
		try {
			region.setIdRegion(id);
			return regionRepository.saveAndFlush(region);
		} catch (Exception e) {
			System.out.println("Error updating the region : " + e.toString());
		}
		return null;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		try {
			regionRepository.deleteById(id);
		} catch (Exception e) {
			System.out.println("Error deleting the region: " + e.toString());
		}
	}

}