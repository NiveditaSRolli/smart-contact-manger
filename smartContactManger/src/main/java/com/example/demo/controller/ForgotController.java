package com.example.demo.controller;

import java.util.List;
import java.util.Random;

import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.UserRepository;
import com.example.demo.entities.User;
import com.example.demo.service.EmailService;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class ForgotController {

	
	@Autowired
	private EmailService emailService;
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	// email id from open handler
	
	@RequestMapping("/forgot")
	public String openEmailForm()
	{
		return "forgot_email_form";
	}
	@PostMapping("/send-otp")
	public String sendotp(@RequestParam("email") String email, HttpSession session) {
	    System.out.println("Email " + email);
	    
	    // generating OTP of 4 digits
	    Random random = new Random();
	    int otp = random.nextInt(9999);
	    System.out.println("otp " + otp);
	    
	    // write code for sending OTP to email
	    String subject = "OTP from SCM";
	    String message = "<div style='border:1px solid #e2e2e2; padding:20px'>" + 
	                     "<h1>otp is <b>" + otp + "</b></h1>" + 
	                     "</div>";
	    String to = email;
	    boolean flag = this.emailService.sendEmail(subject, message, to);
	    
	    if (flag) {
	        session.setAttribute("myotp", otp);
	        session.setAttribute("email", email);
	        return "varify_otp";
	    } else {
	        session.setAttribute("message", "Check your mail id!");
	        return "forgot_email_form";
	    }
	}

	
//	// verify otp
//	@PostMapping("/verify-otp")
//	public String verifyotp(@RequestParam("otp") Integer otp,HttpSession session) {
//		
//		Integer myotp=(Integer) session.getAttribute("myotp");
//		String email=(String) session.getAttribute("email");
//		if(myotp==otp)
//		{
//			// password change form
//		
//			 User user =this.userRepository.getUserByUserName(email);
//			if(user==null) {
//				session.setAttribute("message", "no user with this email");
//				return "forgot_email_form";
//			}
//			else {
//				
//			}
//			
//			return "password_change_form";
//		}
//		else {
//			session.setAttribute("message", "you have enterd the wrong otp");
//			return "varify_otp";
//		}
//	}
//	
	
	
	@PostMapping("/verify-otp")
	public String verifyotp(@RequestParam("otp") Integer otp, HttpSession session) {
	    Integer myotp = (Integer) session.getAttribute("myotp");
	    String email = (String) session.getAttribute("email");
	    
	    if (myotp != null && myotp.equals(otp)) {
	        List<User> users = this.userRepository.findUsersByEmail(email);
	        
	        if (users.isEmpty()) {
	            session.setAttribute("message", "No user with this email");
	            return "forgot_email_form";
	        } else if (users.size() == 1) {
	            // Handle password change form logic here
	            return "password_change_form";
	        } else {
	            // Multiple users with the same email found
	            // Handle this situation (e.g., prompt user to choose or handle first user)
	            User user = users.get(0); // Selecting the first user for simplicity
	            // Handle password change form logic here
	            return "password_change_form";
	        }
	    } else {
	        session.setAttribute("message", "You have entered the wrong OTP");
	        return "varify_otp";
	    }
	}

	
//	// change password
//	@PostMapping("/change-password")
//	 public String changepassword(@RequestParam("newpassword") String newpassword,HttpSession session) {
//		 String email=(String) session.getAttribute("email");
//		  User user=this.userRepository.getUserByUserName(email);
//	user.setPassword(this.bCrypt.encode(newpassword));
//	this.userRepository.save(user);
//	 session.setAttribute("message", "You have entered the wrong OTP");
//     return "redirect:/signin?change=password changed successfully";
//
//	
//	}
// for this above code i ahd recived this error Query did not return a unique result: 3 results were returned
	//org.springframework.dao.IncorrectResultSizeDataAccessException: Query did not return a unique result: 3 results were returned 
			// so to overcome that i had written this code 


//	@PostMapping("/change-password")
//	public String changePassword(@RequestParam("newpassword") String newPassword, HttpSession session) {
//	    String email = (String) session.getAttribute("email");
//	    
//	    // Find user by email
//	    List<User> users = this.userRepository.findUsersByEmail(email);
//	    
//	    // Check if user exists
//	    if (!users.isEmpty()) {
//	        // Get the first user from the list (assuming email is unique)
//	        User user = users.get(0);
//	        
//	        // Update user's password
//	        user.setPassword(this.bCrypt.encode(newPassword));
//	        this.userRepository.save(user);
//	        
//	        session.setAttribute("message", "Password changed successfully");
//	        return "redirect:/login?change=password changed successfully"; 
//	    } 
//	    
//	    else {
//	        // User not found
//	        session.setAttribute("message", "User not found");
//	    return "redirect:/signup?change=password changed successfully"; 
//	    }
//	}

	
	@PostMapping("/change-password")
	public String changePassword(@RequestParam("newpassword") String newPassword, HttpSession session) {
	    String email = (String) session.getAttribute("email");

	    // Find user by email
	    List<User> users = this.userRepository.findUsersByEmail(email);

	    // Check if user exists
	    if (users.size()==1) {
	        // Get the first user from the list (assuming email is unique)
	        User user = users.get(0);

	        // Update user's password
	        user.setPassword(this.bCrypt.encode(newPassword));
	        this.userRepository.save(user);

	        // Set success message
	        session.setAttribute("message", "Password changed successfully");
	    } else {
	        // User not found, set error message
	        session.setAttribute("message", "User not found");
	    }

	    // Redirect to the sign-in page
	    return "redirect:/login?change=password changed successfully"; 
	}

}

	
		
	
