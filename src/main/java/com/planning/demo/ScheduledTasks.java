package com.planning.demo;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.planning.demo.email.NotificationEmail;



@Component
public class ScheduledTasks {

	@Autowired
    NotificationEmail notificationEmail;
	

    @Scheduled(fixedDelay = 5000)
    public void reportCurrentTime() {
 
    }
    
    
	
	

	
}
