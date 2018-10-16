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
	
	
	

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public List<Region> findAll() {
		return regionRepository.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Region findOne(@PathVariable("id") Long id) {
		return regionRepository.findById(id).get();
	}
	


	@RequestMapping(value="/test", method = RequestMethod.GET)
	@ResponseBody
	public String hello() {
		return "salut";
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	@ResponseBody
	public Region create(@RequestBody Region Region) {
		return regionRepository.save(Region);
		
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Region update(@PathVariable("id") Long id, @Valid @RequestBody Region region) {
		region.setIdRegion(id);
		return regionRepository.saveAndFlush(region);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		regionRepository.deleteById(id);
	}

}