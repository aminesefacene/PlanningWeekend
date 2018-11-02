package com.planning.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.planning.demo.domain.User;
import com.planning.demo.repository.UserRepository;

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
	

	@PreAuthorize("hasAnyRole('ADMIN')")
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
	public User update(@PathVariable("id") Long id, @Valid @RequestBody User user) {
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