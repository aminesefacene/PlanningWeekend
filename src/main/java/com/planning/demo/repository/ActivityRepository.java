package com.planning.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planning.demo.domain.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

}
