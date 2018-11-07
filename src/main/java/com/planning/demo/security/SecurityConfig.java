package com.planning.demo.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.planning.demo.repository.UserRepository;
import com.planning.demo.service.CustumerUserDetailsService;



@Configuration
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

	@Autowired
	CustumerUserDetailsService custumerUserDetailsService;

	
	@Autowired
	public void Configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(custumerUserDetailsService)
			.passwordEncoder(getPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/user/delete/*").authenticated()
			.antMatchers("/user/update/*").authenticated()
			.antMatchers("/activity/create").authenticated()
			.antMatchers("/activity/update/*").authenticated()
			.antMatchers("/activity/delete/*").authenticated()
			.antMatchers("/region/create").authenticated()
			.antMatchers("/location/**").authenticated()
			.antMatchers("/region/update/*").authenticated()
			.antMatchers("/region/delete/*").authenticated()
			.antMatchers("/location/create").authenticated()
			.antMatchers("/location/update/*").authenticated()
			.antMatchers("/location/delete/*").authenticated()
			.anyRequest().permitAll()
			.and().formLogin().permitAll();
		
	}
	
	private PasswordEncoder getPasswordEncoder() {
		return new PasswordEncoder() {
			
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public String encode(CharSequence rawPassword) {
				return rawPassword.toString();
			}
		};
	}
}