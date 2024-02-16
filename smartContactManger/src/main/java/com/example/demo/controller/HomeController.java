package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.UserRepository;
import com.example.demo.entities.User;
import com.example.demo.helper.Message;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@Autowired
	private UserRepository userRepository;

//	@GetMapping("/test")
//	@ResponseBody
//	public String test() {
//		User user = new User();
//		user.setName("sonu");
//		user.setEmail("sonu@gmail.com");
//
//		userRepository.save(user);
//		
//		return "working";
//	
//	}

	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Home-smart contact manger");
		return "home";
	}

	@RequestMapping("/about")
	public String about(Model model) {
		model.addAttribute("title", "Home-smart contact manger");
		return "about";
	}


	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title", "Home-smart contact manger");
      model.addAttribute("user",new User());
		return "signup";
	}

	
	// handle for registrering user
	
	@RequestMapping(value = "/do_register", method = RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user") User user,
			BindingResult result1, // for validation purpose
			@RequestParam(value = "agreement", defaultValue = "false") 
			boolean agreement, Model model,
			 HttpSession session) {

		try {

			if (!agreement) {
				System.out.println("you have not agreed the termes and condition");
				throw new Exception("you have not agreed the termes and condition");
			}
			if (result1.hasErrors()) {
				model.addAttribute("user", user); // if any error has occurs in the field , the filled data should be return back in the data
				System.out.println("ERROR"+result1.toString());
				return "signup";
			}
			
			
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setImageUrl("default.png");
			user.setPassword(passwordEncoder.encode(user.getPassword()));

			System.out.println("Agreement" + agreement); // non declared form fileds
			System.out.println("USER" + user);
			
			User result = this.userRepository.save(user); // sending the data in database
			model.addAttribute("user",result);
			
			model.addAttribute("user", new User(0, null, null, null, null, null, null, agreement, null, null, null));
		session.setAttribute("message", new Message("Successfully Registered !!", "alert-success"));

		session.removeAttribute("message");
		
			return "signup";
		} 
		
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			model.addAttribute("user", user);

			session.setAttribute("message", new Message("Something Went Wrong!! " + e.getMessage(), "alert-danger"));
 return "signup";
		}

	}
	

	@RequestMapping("/login")
	public String customlogin(Model model) {
		model.addAttribute("title", "Home-smart contact manger");
		return "login";
	}
}

