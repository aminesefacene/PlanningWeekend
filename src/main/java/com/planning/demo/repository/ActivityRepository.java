package com.planning.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.planning.demo.domain.Activity;
import com.planning.demo.domain.Location;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

	@Query("SELECT loc FROM Region reg, Location loc where reg.region = :region AND reg.department = :department AND reg.city = :city AND reg.idRegion = loc.region.idRegion")
	public List<Location> getAllRes(@Param("region") String region,@Param("department") String department,@Param("city") String city);

}
