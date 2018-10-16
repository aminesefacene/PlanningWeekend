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
import com.planning.demo.domain.User;
import com.planning.demo.repository.UserRepository;

@Controller
@RequestMapping("/user")
class UserController {

	@Autowired
	private UserRepository userRepository;
	
	
	

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public User findOne(@PathVariable("id") Long id) {
		return userRepository.findById(id).get();
	}
	


	@RequestMapping(value="/test", method = RequestMethod.GET)
	@ResponseBody
	public String hello() {
		return "salut";
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	@ResponseBody
	public User create(@RequestBody User user) {
		return userRepository.save(user);
		
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public User update(@PathVariable("id") Long id, @Valid @RequestBody User user) {
		user.setIdUser(id);
		return userRepository.saveAndFlush(user);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		userRepository.deleteById(id);
	}

}