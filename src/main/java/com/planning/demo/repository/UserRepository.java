package com.planning.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planning.demo.domain.User;

public interface UserRepository extends JpaRepository<User, Long>  {

}
