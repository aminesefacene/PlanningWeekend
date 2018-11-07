package com.planning.demo.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.planning.demo.domain.Activity;
import com.planning.demo.domain.Region;
import com.planning.demo.domain.User;
import com.planning.demo.repository.UserRepository;

@CrossOrigin
@RestController
@RequestMapping("/user")
class UserController {

	@Autowired
	private UserRepository userRepository;
	
	
	

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@ResponseBody
	public List<User> findAll() {
		try {
			return userRepository.findAll();
		} catch (Exception e) {
			System.out.println("Error getting users: " + e.toString());
		}
		return null;
	}

	@RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
	@ResponseBody
	public User findOne(@PathVariable("id") Long id) {
		try {
			return userRepository.findById(id).get();
		} catch (Exception e) {
			System.out.println("Error getting the user : " + e.toString());
		}
		return null;
	}
	
	@RequestMapping(value = "/getUserByLogin/{login}/{password}", method = RequestMethod.GET)
	@ResponseBody
	public Long findOneUsername(@PathVariable("login") String login, @PathVariable("password") String password) {
		try {
			Optional<User> user = userRepository.findByUsername(login);
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
			if(encoder.matches(password, user.get().getPassword())) {
				return user.get().getIdUser();
			}
			return (long) -1;
		} catch (Exception e) {
			System.out.println("Error getting the user : " + e.toString());
		}
		return (long) -1;
	}
	

	
	@RequestMapping(value="/test", method = RequestMethod.GET)
	@ResponseBody
	public String hello() {
		return "salut";
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	@ResponseBody
	public User create(@RequestBody User user) {
		try {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
	        String password = encoder.encode(user.getPassword());
	        user.setPassword(password);
	        
	        userRepository.save(user);
		} catch (Exception e) {
			System.out.println("Error saving the user : " + e.toString());
		}
		return null;
		
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public User update(@PathVariable("id") Long id, @RequestBody User user) {
		try {
			user.setIdUser(id);
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
	        String password = encoder.encode(user.getPassword());
	        user.setPassword(password);

			return userRepository.saveAndFlush(user);
		} catch (Exception e) {
			System.out.println("Error updating the user : " + e.toString());
		}
		return null;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			System.out.println("Error deleting the user : " + e.toString());
		}
	}
	

	



}