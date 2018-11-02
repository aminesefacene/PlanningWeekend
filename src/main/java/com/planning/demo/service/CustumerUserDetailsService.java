package com.planning.demo.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.planning.demo.domain.CustumerUserDetails;
import com.planning.demo.domain.User;
import com.planning.demo.repository.UserRepository;

@Service
public class CustumerUserDetailsService implements UserDetailsService
{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> optionalUser = userRepository.findByUsername(username);
		
		optionalUser
			.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		
		return optionalUser
				.map(CustumerUserDetails::new).get();
		
	}

}
