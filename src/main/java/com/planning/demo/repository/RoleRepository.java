package com.planning.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planning.demo.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}

