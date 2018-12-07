package com.moviedb.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.moviedb.movies.web.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailServiceImpl userDetailsService;	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests().antMatchers("/css/**").permitAll() //permits all to access the css
				.and()
			.authorizeRequests().antMatchers("/signup", "/saveuser").permitAll() //permits all to access the sign up and saving of the user information
				.and()
			.authorizeRequests()
				.antMatchers("/users", "/user/**", "/movies", "/movie/**", "/reviews", "/review/**").hasAuthority("ADMIN") //protects accessing REST API from non-admin users
			.anyRequest().authenticated() //Authenticates the user
				.and()
			.formLogin() //Login form
				.loginPage("/login") //Sends to the login page
				.defaultSuccessUrl("/homepage", true) //After a successful login the user is sent here. Added ", true" to fix a bug in which the user had to re-log to move forward.
				.permitAll()
				.and()
			.logout().permitAll(); //Permits all to log out
	}
	
	 @Autowired
	 public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder()); //Password hash encryption encoder
	}

}
