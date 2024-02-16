package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>  {

	
	// for security purpose we are setting the email as username and password

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User getUserByUserName(@Param("email") String email);

    
    @Query("SELECT u FROM User u WHERE u.email = :email")
	List<User> findUsersByEmail(String email);
   }
