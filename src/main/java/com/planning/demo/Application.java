package com.planning.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.planning.demo.domain.Activity;
import com.planning.demo.domain.Location;
import com.planning.demo.domain.Region;
import com.planning.demo.domain.Role;
import com.planning.demo.domain.User;
import com.planning.demo.domain.Level;
import com.planning.demo.repository.ActivityRepository;
import com.planning.demo.repository.LocationRepository;
import com.planning.demo.repository.RegionRepository;
import com.planning.demo.repository.RoleRepository;
import com.planning.demo.repository.UserRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
        ActivityRepository ar = ctx.getBean(ActivityRepository.class);
        Activity a1 = new Activity("Volleyball", Level.EASY); 
        ar.save(a1);
        Activity a2 = new Activity("Basketball", Level.EASY); 
        ar.save(a2);
        
        RegionRepository rr = ctx.getBean(RegionRepository.class);
        Region r1 = new Region("Bretagne","Ile et Vilaine","Rennes");
        rr.save(r1);
        
        LocationRepository lr = ctx.getBean(LocationRepository.class);
        Location l1 = new Location(37,"rue bernard", "35000", r1);
        l1.addActivity(a1);
        lr.save(l1);
        
        RoleRepository rolerepo = ctx.getBean(RoleRepository.class);
        Role role = new Role("ADMIN");
        rolerepo.save(role);

        Role role1 = new Role("ETUDIANT");
        rolerepo.save(role1);
        
        
        UserRepository ur = ctx.getBean(UserRepository.class);
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        //String password = encoder.encode("admin");
        User u1 = new User("Sefacene","Amine","admin","admin","sef@f.f","53421");
        u1.addActivity(a1);
        u1.addRegion(r1);
        u1.addRole(role);
        ur.save(u1);
        User u2 = new User("testUser2","testMan","test","test","test@test.test","00000");
        u2.addActivity(a2);
        //u2.addRegion(r1);
        u2.addRole(role1);
        ur.save(u2);
        
	}
}
