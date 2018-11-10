package com.planning.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.planning.demo.domain.Location;
import com.planning.demo.domain.User;

public interface UserRepository extends JpaRepository<User, Long>  {

	Optional<User> findByUsername(String username);
	
	@Query("SELECT u FROM User u")
	public List<User> getAllUsers();



}
