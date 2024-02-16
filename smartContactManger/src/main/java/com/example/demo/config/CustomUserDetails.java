
//login using spring security
//1. provide the implementation of UserDetails  <-- customeruserdetail
//2.provide the implementaion of userDetailService <-- UserDetailServiceImpl
//3. write configuration class with all configuration 
// a simple class the extends websecurityconfigrations




package com.example.demo.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entities.User;

public class CustomUserDetails implements UserDetails {

	private User user;

//	public CustomUserDetails(org.apache.catalina.User user2) {
//		super();
//		this.user = (User) user2;
//	}
	
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	SimpleGrantedAuthority  simpleGrantedAuthority=	new SimpleGrantedAuthority(user.getRole());
		return List.of(simpleGrantedAuthority);
	}

	

	public CustomUserDetails(User user) {
	super();
	this.user = user;
}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {

		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

}


