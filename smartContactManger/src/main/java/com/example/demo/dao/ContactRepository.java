//package com.example.demo.dao;
//
//import java.util.List;
//
//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import com.example.demo.entities.Contact;
//import com.example.demo.entities.User;
//
//public interface ContactRepository  extends JpaRepository<Contact, Integer>{
//	
//	// pegination..
//	@Query("from Contact as c where c.user.id =:userId")
//	//public List<Contact> findContactsByUser(@Param("userId")int userId);
//	
//	// currentpage-page
//	// contact per page=5
//	public Page<Contact> findContactsByUser(@Param("userId")int userId,PageRequest pageable);
//
//	public User getUserByUserName(String name);
//
//	//public Page<Contact> findContactsByUser(int id, PageRequest pageable);
//
//	//public Page<Contact> findContactsByUser(int id, PageRequest pageable);
//
//}

package com.example.demo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Contact;
import com.example.demo.entities.User;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    
    // Pagination
    @Query("SELECT c FROM Contact c WHERE c.user.id = :userId")
    public Page<Contact> findContactsByUser(@Param("userId") int userId, Pageable pageable);

    // Get user by username
    public User getUserByUserName(String name);
    
//    
//    // search  
  public List<Contact> findByNameContainingAndUser(String name,User user);
}









