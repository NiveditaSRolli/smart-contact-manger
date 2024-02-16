package com.example.demo.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
////import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//
//@Configuration
//@EnableWebSecurity
//public class MyConfig  extends WebSecurityConfiguration{
//
//	
//	@Bean
//	public UserDetailsService getDetailsService() {
//		return (UserDetailsService) new UserDetailsServiceImpl();
//		}
//	
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	@Bean
//	public DaoAuthenticationProvider  authenticationProvider() {
//		DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
//		daoAuthenticationProvider.setUserDetailsService(this.getDetailsService());
//		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());;
//		return daoAuthenticationProvider;
//	}
//	
//	
//	// configure methode...
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		auth.authenticationProvider(authenticationProvider());
//		
//	}
//	
//
//	protected void configure(HttpSecurity http) throws Exception{
//		http.authorizeRequests().requestMatchers("/admin/**").hasRole("ADMIN")
//		.requestMatchers("/user/**").hasRole("USER")
//		.requestMatchers("/**").permitAll().and().formLogin().and().csrf().disable();
//	}
//	
//}

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MyConfig{
	
	@Bean
	public UserDetailsService getUserDetailsService() {
return new UserDetailsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	public DaoAuthenticationProvider daoAuthenticationProvider() {
//		DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
//		daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
//	daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//	 return daoAuthenticationProvider;
//	
//	
	
	
	@Bean
	public DaoAuthenticationProvider getDaoAuthProvider1() {
	    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
	    daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
	    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
	    return daoAuthenticationProvider;
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.authenticationProvider(getDaoAuthProvider());
	}


//	
	
	// configure methode
//	
////	@Override
//	protected  void  configure(AuthenticationManagerBuilder  auth) throws Exception{
//		auth.authenticationProvider(daoAuthenticationProvider());
//	}
	
	public AuthenticationProvider getDaoAuthProvider() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		 http
	        .authorizeRequests()
	            .requestMatchers("/admin/**").hasRole("ADMIN")
	            .requestMatchers("/user/**").authenticated() // Assuming you want users with any role to access /user/**
	            .requestMatchers("/**").permitAll()
	            .and()
	        .formLogin().
	        loginPage("/login").
	        loginProcessingUrl("/dologin").
	        defaultSuccessUrl("/user/index").
	        //failureUrl("/login_fail")
	        
	            and()
	        .csrf().disable();
	    
	    http.authenticationProvider(getDaoAuthProvider1());
	    return http.build();
		
	}
	
//	authorizeRequests(): This starts the authorization configuration. It allows configuring access rules.
//
//	antMatchers(): It specifies the URL patterns to which the following configurations will apply.
//
//	hasRole("ADMIN"): Specifies that only users with the role "ADMIN" can access URLs matching "/admin/**".
//
//	authenticated(): Specifies that any authenticated user (regardless of role) can access URLs matching "/user/**".
//
//	permitAll(): Allows unrestricted access to URLs matching "/**". This is often used for public pages.
//
//	and(): Combines the individual configurations.
//
//	formLogin(): Configures form-based login.
//
//	csrf().disable(): Disables CSRF protection. Note that disabling CSRF should be done carefully and only in specific scenarios.

}
