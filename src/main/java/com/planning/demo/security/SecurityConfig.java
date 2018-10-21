package com.planning.demo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	public void globalConfig(AuthenticationManagerBuilder auth) {
			
		
	}

}
