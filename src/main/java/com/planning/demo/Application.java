package com.planning.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
@EnableScheduling
public class Application {


	public static void main(String[] args) {
       		
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
        ActivityRepository ar = ctx.getBean(ActivityRepository.class);

        //Mise en place de la base		
        Activity a1_1 = new Activity("Football", Level.EASY); 
        ar.save(a1_1);
        Activity a1_2 = new Activity("Football", Level.MEDIUM); 
        ar.save(a1_2);
        Activity a1_3 = new Activity("Football", Level.HARD); 
        ar.save(a1_3);
        
        Activity a2_1 = new Activity("Tennis", Level.EASY); 
        ar.save(a2_1);
        Activity a2_2 = new Activity("Tennis", Level.MEDIUM); 
        ar.save(a2_2);
        Activity a2_3 = new Activity("Tennis", Level.HARD); 
        ar.save(a2_3);
        
        Activity a3_1 = new Activity("Equitation", Level.EASY); 
        ar.save(a3_1);
        Activity a3_2 = new Activity("Equitation", Level.MEDIUM); 
        ar.save(a3_2);
        Activity a3_3 = new Activity("Equitation", Level.HARD); 
        ar.save(a3_3);
        
        Activity a4_1 = new Activity("Judo", Level.EASY); 
        ar.save(a4_1);
        Activity a4_2 = new Activity("Judo", Level.MEDIUM); 
        ar.save(a4_2);
        Activity a4_3 = new Activity("Judo", Level.HARD); 
        ar.save(a4_3);
        
        Activity a5_1 = new Activity("Basket-ball", Level.EASY); 
        ar.save(a5_1);
        Activity a5_2 = new Activity("Basket-ball", Level.MEDIUM); 
        ar.save(a5_2);
        Activity a5_3 = new Activity("Basket-ball", Level.HARD); 
        ar.save(a5_3);
        
        Activity a6_1 = new Activity("Handball", Level.EASY); 
        ar.save(a6_1);
        Activity a6_2 = new Activity("Handball", Level.MEDIUM); 
        ar.save(a6_2);
        Activity a6_3 = new Activity("Handball", Level.HARD); 
        ar.save(a6_3);
        
        Activity a7_1 = new Activity("Rugby", Level.EASY); 
        ar.save(a7_1);
        Activity a7_2 = new Activity("Rugby", Level.MEDIUM); 
        ar.save(a7_2);
        Activity a7_3 = new Activity("Rugby", Level.HARD); 
        ar.save(a7_3);
        
        Activity a8_1 = new Activity("Golf", Level.EASY); 
        ar.save(a8_1);
        Activity a8_2 = new Activity("Golf", Level.MEDIUM); 
        ar.save(a8_2);
        Activity a8_3 = new Activity("Golf", Level.HARD); 
        ar.save(a8_3);
        
        Activity a9_1 = new Activity("Canoë-Kayak", Level.EASY); 
        ar.save(a9_1);
        Activity a9_2 = new Activity("Canoë-Kayak", Level.MEDIUM); 
        ar.save(a9_2);
        Activity a9_3 = new Activity("Canoë-Kayak", Level.HARD); 
        ar.save(a9_3);
        
        Activity a10_1 = new Activity("Natation", Level.EASY); 
        ar.save(a10_1);
        Activity a10_2 = new Activity("Natation", Level.MEDIUM); 
        ar.save(a10_2);
        Activity a10_3 = new Activity("Natation", Level.HARD); 
        ar.save(a10_3);
        
        //fin de mise en place des activitées
        
        
        
        RegionRepository rr = ctx.getBean(RegionRepository.class);
        
        Region r1 = new Region("Île-de-France","Seine","Paris");
        rr.save(r1);
        Region r2 = new Region("Provence-Alpes-Côte d'Azur","Bouches-du-Rhône","Marseille");
        rr.save(r2);
        Region r3 = new Region("Rhône-Alpes","Rhône","Lyon");
        rr.save(r3);
        Region r4 = new Region("Midi-Pyrénées","Haute-Garonne","Toulouse");
        rr.save(r4);
        Region r5 = new Region("Provence-Alpes-Côte d'Azur","Alpes-Maritimes","Nice");
        rr.save(r5);
        Region r6 = new Region("Pays de la Loire","Loire-Atlantique","Nantes");
        rr.save(r6);
        Region r7 = new Region("Languedoc-Roussillon","Hérault","Montpellier");
        rr.save(r7);
        Region r8 = new Region("Alsace","Bas-Rhin","Strasbourg");
        rr.save(r8);
        Region r9 = new Region("Aquitaine","Gironde","Bordeaux");
        rr.save(r9);
        Region r10 = new Region("Nord-Pas-de-Calais","Nord","Lille");
        rr.save(r10);
        Region r11 = new Region("Bretagne","Ille-et-Vilaine","Rennes");
        rr.save(r11);
        Region r12 = new Region("Bretagne","Finistère","Brest");
        rr.save(r12);
        
        //fin de mise en place des régions
        
        LocationRepository lr = ctx.getBean(LocationRepository.class);

        
        Location l1 = new Location(17,"Avenue Edison", "75013", r1);
        l1.addActivity(a4_1);
        l1.addActivity(a4_2);
        l1.addActivity(a4_3);
        l1.addActivity(a5_1);
        l1.addActivity(a5_2);
        l1.addActivity(a5_3);
        l1.addActivity(a6_1);
        l1.addActivity(a6_2);
        l1.addActivity(a6_3);
        lr.save(l1);
        
        Location l2 = new Location(65,"Avenue Clot Bey", "13008", r2);
        l2.addActivity(a1_1);
        l2.addActivity(a1_2);
        l2.addActivity(a1_3);
        lr.save(l2);
        
        Location l3 = new Location(153,"Rue Vendôme", "69003", r3);
        l3.addActivity(a3_1);
        l3.addActivity(a3_2);
        l3.addActivity(a3_3);
        l3.addActivity(a8_1);
        l3.addActivity(a8_2);
        l3.addActivity(a8_3);
        lr.save(l3);
        
        Location l4 = new Location(20,"Chemin de Garric", "31200", r4);
        l4.addActivity(a3_1);
        l4.addActivity(a3_2);
        l4.addActivity(a3_3);
        l4.addActivity(a7_1);
        l4.addActivity(a7_2);
        l4.addActivity(a7_3);
        l4.addActivity(a8_1);
        l4.addActivity(a8_2);
        l4.addActivity(a8_3);
        lr.save(l4);

        Location l5 = new Location(2,"Rue Jean Allègre", "06364", r5);
        l5.addActivity(a9_1);
        l5.addActivity(a9_2);
        l5.addActivity(a9_3);
        l5.addActivity(a10_1);
        l5.addActivity(a10_2);
        l5.addActivity(a10_3);
        lr.save(l5);
        
        Location l6 = new Location(49,"Rue Chanoine Larose", "44100", r6);
        l6.addActivity(a1_1);
        l6.addActivity(a1_2);
        l6.addActivity(a2_1);
        l6.addActivity(a2_2);
        l6.addActivity(a5_1);
        l6.addActivity(a6_2);
        lr.save(l6);
        
        Location l7 = new Location(345,"Avenue de Heidelberg", "34080", r7);
        l7.addActivity(a1_3);
        l7.addActivity(a2_3);
        l7.addActivity(a6_3);
        lr.save(l7);
        
        Location l8 = new Location(1,"allée Konrad Roentgen", "67000", r8);
        l8.addActivity(a2_1);
        l8.addActivity(a2_2);
        l8.addActivity(a2_3);
        l8.addActivity(a8_1);
        l8.addActivity(a8_2);
        l8.addActivity(a8_3);
        lr.save(l8);
        
        Location l9 = new Location(7,"Rue Parlement Sainte-Catherine", "33000", r9);
        l9.addActivity(a1_2);
        l9.addActivity(a2_2);
        l9.addActivity(a3_2);
        l9.addActivity(a4_2);
        l9.addActivity(a5_2);
        l9.addActivity(a6_2);
        l9.addActivity(a7_2);
        l9.addActivity(a8_2);
        l9.addActivity(a9_2);
        l9.addActivity(a10_2);
        lr.save(l9);
        
        Location l10 = new Location(8,"Rue De La Quennette", "59800", r10);
        l10.addActivity(a3_1);
        l10.addActivity(a3_2);
        l10.addActivity(a3_3);
        l10.addActivity(a7_1);
        l10.addActivity(a7_2);
        l10.addActivity(a7_3);
        l10.addActivity(a8_1);
        l10.addActivity(a8_2);
        l10.addActivity(a8_3);
        lr.save(l10);
        
        Location l11 = new Location(30,"Rue Charles Géniaux", "35000", r11);
        l11.addActivity(a1_1);
        l11.addActivity(a1_2);
        l11.addActivity(a7_1);
        l11.addActivity(a7_2);
        lr.save(l11);
        
        Location l12 = new Location(25,"Rue Paul Bert", "29200", r12);
        l12.addActivity(a9_1);
        l12.addActivity(a9_2);
        l12.addActivity(a9_3);
        l12.addActivity(a10_1);
        l12.addActivity(a10_2);
        l12.addActivity(a10_3);
        lr.save(l12);
        //fin de mise en place des locations
        
        RoleRepository rolerepo = ctx.getBean(RoleRepository.class);
        Role role = new Role("ADMIN");
        rolerepo.save(role);

        Role role1 = new Role("UTILISATEUR");
        rolerepo.save(role1);
        
        
        UserRepository ur = ctx.getBean(UserRepository.class);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
        String password = encoder.encode("admin");
        
        User u1 = new User("admin",password,"sef@f.f");
        u1.addActivity(a1_1);
        u1.addActivity(a6_2);
        u1.addActivity(a9_1);
        u1.addActivity(a5_2);
        u1.addActivity(a10_1);
        u1.addActivity(a8_3);
        u1.addRegion(r12);
        u1.addRegion(r2);
        u1.addRegion(r3);
        u1.addRegion(r6);
        u1.addRegion(r9);
        u1.addRegion(r11);
        
        u1.setRoles(role);
        ur.save(u1);
        
	}
}
