package com.planning.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planning.demo.domain.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {

}
