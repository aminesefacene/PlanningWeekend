package com.planning.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.planning.demo.domain.Activity;
import com.planning.demo.domain.User;

public interface UserRepository extends JpaRepository<User, Long>  {
	
}
