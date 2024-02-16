package com.example.demo.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="user")
public class User implements UserDetails {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank(message="Name field is required!!")
	@Size(min=2,max=20,message="min 2 and max 20 characters are allowed!!")
	
	private String name;

	private String email;
	private String password;
	private String role;
	private boolean enabled;
	private String imageUrl;
	private String about;
	
	
	@OneToMany( mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true,  fetch = FetchType.LAZY)
	private List<Contact> contacts=new ArrayList<>();


	public User(int id,
			@NotBlank(message = "Name field is required!!") @Size(min = 2, max = 20, message = "min 2 and max 20 characters are allowed!!") String name,
			String lname, String email, String password, String cpassword, String role, boolean enabled,
			String imageUrl, String about, List<Contact> contacts) {
		super();
		this.id = id;
		this.name = name;
		
		this.email = email;
		this.password = password;
		
		this.role = role;
		this.enabled = enabled;
		this.imageUrl = imageUrl;
		this.about = about;
		this.contacts = contacts;
	} 


	public User() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public String getAbout() {
		return about;
	}


	public void setAbout(String about) {
		this.about = about;
	}


	public List<Contact> getContacts() {
		return contacts;
	}


	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}


	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // You need to implement this method based on your role logic
        // For example, you can return a list of roles as SimpleGrantedAuthority objects
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getUsername() {
        return email; // Assuming email is the username in your case
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Assuming accounts never expire
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Assuming accounts are never locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Assuming credentials never expire
    }

    public boolean isEnabled1() {
        return enabled;
    }
	
	
	
	
	
	
}
