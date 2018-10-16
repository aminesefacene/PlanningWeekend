package com.planning.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.planning.demo.domain.Activity;
import com.planning.demo.domain.Location;
import com.planning.demo.domain.Region;
import com.planning.demo.domain.User;
import com.planning.demo.domain.Level;
import com.planning.demo.repository.ActivityRepository;
import com.planning.demo.repository.LocationRepository;
import com.planning.demo.repository.RegionRepository;
import com.planning.demo.repository.UserRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
        ActivityRepository ar = ctx.getBean(ActivityRepository.class);
        Activity a1 = new Activity("Football", Level.EASY); 
        ar.save(a1);
        
        RegionRepository rr = ctx.getBean(RegionRepository.class);
        Region r1 = new Region("Bretagne","Ile et Vilaine","Rennes");
        rr.save(r1);
        
        LocationRepository lr = ctx.getBean(LocationRepository.class);
        Location l1 = new Location(37,"rue bernard", "35000", "Rennes",r1);
        l1.addActivity(a1);
        lr.save(l1);
        
        UserRepository ur = ctx.getBean(UserRepository.class);
        User u1 = new User("Amine","Amine","Amine","sef@f.f","53421");
        u1.addActivity(a1);
        u1.addRegion(r1);
        ur.save(u1);
        
        
        
		
	}
}
