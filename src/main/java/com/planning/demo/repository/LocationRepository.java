package com.planning.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planning.demo.domain.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
