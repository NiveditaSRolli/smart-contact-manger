////package com.example.demo.controller;
////
////import com.example.demo.dao.UserRepository;
////import com.example.demo.entities.Contact;
////import com.example.demo.entities.User;
////import com.example.demo.helper.Message;
////
////import jakarta.servlet.http.HttpSession;
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.core.io.ClassPathResource;
////import org.springframework.stereotype.Controller;
////import org.springframework.ui.Model;
////import org.springframework.web.bind.annotation.*;
////import org.springframework.web.multipart.MultipartFile;
////
////import java.io.File;
////import java.nio.file.Files;
////import java.nio.file.Path;
////import java.nio.file.Paths;
////import java.nio.file.StandardCopyOption;
////import java.security.Principal;
////
////@Controller
////@RequestMapping("/user")
////public class UserController {
////
////    private final UserRepository userRepository;
////
////    @Autowired
////    public UserController(UserRepository userRepository) {
////        this.userRepository = userRepository;
////    }
////
////    @ModelAttribute
////    public void addCommonData(Model model, Principal principal,jakarta.servlet.http.HttpSession session) {
////        String userName = principal.getName();
////        System.out.println("USERNAME: " + userName);
////
////        User user = userRepository.getUserByUserName(userName);
////        System.out.println("USER: " + user);
////        model.addAttribute("user", user);
////    }
////
////    @GetMapping("/index")
////    public String dashboard(Model model) {
////        return "normal/user_dashboard";
////    }
////
////    @GetMapping("/add-contact")
////    public String openAddContactForm(Model model) {
////        model.addAttribute("title", "Add Contact");
////        model.addAttribute("contact", new Contact());
////        return "normal/add_contact_form";
////    }
////
////    @PostMapping("/process-contact")
////    public String processContact(@ModelAttribute Contact contact,
////                                 @RequestParam("profileImage") MultipartFile file,
////                                 Principal principal,
////                                 HttpSession session) {
////        try {
////            String name = principal.getName();
////            User user = userRepository.getUserByUserName(name);
////
////            if (!file.isEmpty()) {
////                contact.setImage(file.getOriginalFilename());
////
////                File saveFile = new ClassPathResource("static/img").getFile();
////                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
////
////                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
////                System.out.println("Image is uploaded.");
////            } else {
////                System.out.println("File is empty.");
////            }
////
////            user.getContacts().add(contact);
////            contact.setUser(user);
////            userRepository.save(user);
////
////            System.out.println("Data: " + contact);
////            System.out.println("Added to database.");
////
////            session.setAttribute("message", new Message("Your contact is added!! Add more..", "success"));
////        } catch (Exception e) {
////            System.out.println("Error: " + e.getMessage());
////            e.printStackTrace();
////            session.setAttribute("message", new Message("Something went wrong!! Try again..", "danger"));
////        }
////        return "redirect:/user/add-contact";
////    }
////}
//
//package com.example.demo.controller;
//
//import java.io.File;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//import java.security.Principal;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.example.demo.dao.ContactRepository;
//import com.example.demo.dao.UserRepository;
//import com.example.demo.entities.Contact;
//import com.example.demo.entities.User;
//import com.example.demo.helper.Message;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import jakarta.servlet.http.HttpSession;
//
//@Controller
//@RequestMapping("/user")
//public class UserController {
//
//	private final UserRepository userRepository;
//
//	private  final ContactRepository contactRepository;
//
//	  @Autowired
//	    public UserController(UserRepository userRepository, ContactRepository contactRepository) {
//	        this.userRepository = userRepository;
//	        this.contactRepository = contactRepository;
//	    }
//	
//	
//	
//
//	@ModelAttribute
//	public void addCommonData(Model model, Principal principal) {
//		String userName = principal.getName();
//		System.out.println("USERNAME: " + userName);
//
//		User user = userRepository.getUserByUserName(userName);
//		System.out.println("USER: " + user);
//		model.addAttribute("user", user);
//	}
//
//	@GetMapping("/index")
//	public String dashboard(Model model) {
//		return "normal/user_dashboard";
//	}
//
//	@GetMapping("/add-contact")
//	public String openAddContactForm(Model model) {
//		model.addAttribute("title", "Add Contact");
//		model.addAttribute("contact", new Contact());
//		return "normal/add_contact_form";
//	}
//
//	@PostMapping("/process-contact")
//	public String processContact(@ModelAttribute Contact contact, @RequestParam("profileImage") MultipartFile file,
//			Principal principal, HttpSession session) {
//
//		try {
//			String name = principal.getName();
//			User user = userRepository.getUserByUserName(name);
//
////      if(3>2) {
////          throw new  Exception();
////      }
//
//			// processing and uploading files
//			if (file.isEmpty()) {
//				System.out.println("File is empty.");
//				contact.setImage("contact.png");
//			} else {
//				contact.setImage(file.getOriginalFilename());
//
//				File saveFile = new ClassPathResource("static/img").getFile();
//				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
//
//				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//				System.out.println("Image is uploaded.");
//			}
//
//			user.getContacts().add(contact);
//			contact.setUser(user);
//			userRepository.save(user);
//
//			System.out.println("Data: " + contact);
//			System.out.println("Added to database.");
//
//			// session.setAttribute("message", "Your contact is added. Add more.");
//			session.setAttribute("message", new Message("Your contact is added !! Add more..", "success"));
//		}
//
//		catch (Exception e) {
//			System.out.println("Error: " + e.getMessage());
//			e.printStackTrace();
//			// session.setAttribute("message", "Something went wrong. Try again.");
//			session.setAttribute("message", new Message("something went wrong !! try again..", "danger"));
//
//		}
//		return "redirect:/user/add-contact"; // Redirect to add contact form
//	}
//
//	// show contact handler
//	// per page =5[n] ,for all the contacts page
//	// current page=0[page]
////
////	 @GetMapping("/show-contacts/{page}")
////	    public String showContacts(@PathVariable("page") Integer page,Model m, Principal principal) {
////	        m.addAttribute("title", "show user contacts");
////	     
////	       // sending contact lists
////
//////	    	String userName=principal.getName();
//////	    	User user=this.userRepository.getUserByUserName(userName);
//////	       List<Contact> contacts	=user.getContacts();
////
////	        String userName = principal.getName();
////	        User user = this.userRepository.getUserByUserName(userName);
////	        
////	       Pageable pageable=PageRequest.of(page, 5);
////	        
////	        Page<Contact> contacts = this.contactRepository.findContactsByUser(user.getId(),pageable);
////	        m.addAttribute("contacts", contacts);
////	        m.addAttribute("currentPage",page);
////	        m.addAttribute("totalpages",contacts.getTotalPages());
////	        return "normal/show_contacts";
////	    }
////	 
//
//
//	// Other imports...
//	@GetMapping("/show-contacts/{page}")
//	public String showContacts(@PathVariable("page") Integer page, Model m, Principal principal) {
//	    m.addAttribute("title", "show user contacts");
//
//	    String userName = principal.getName();
//	    User user = this.userRepository.getUserByUserName(userName);
//	    
//	    // Define pagination parameters
//	    int pageSize = 5; // Number of contacts per page
//	    PageRequest pageRequest = PageRequest.of(page + 1, pageSize);
//
//	    // Fetch contacts using pagination
//	    Page<Contact> contactsPage = this.contactRepository.findContactsByUser(user.getId(), pageRequest);
//	    
//	    // Extract contacts and pagination info
//	    List<Contact> contacts = contactsPage.getContent();
//	    int totalPages = contactsPage.getTotalPages();
//
//	    // Add attributes to the model
//	    m.addAttribute("contacts", contacts);
//	    m.addAttribute("currentPage", page);
//	    m.addAttribute("totalPages", totalPages);
//
//	    return "normal/show_contacts";
//	}
//
//
//	// showing particular contact details
//	@RequestMapping("/{cid}/contact")
//	public String showContactDetails(@PathVariable("cid") Integer cid))
//{
//		System.out.println("CId"+cid);
//		return "normal/contact_detail";
//	}
//	
//
//	 
//	 
//
//	 
//	 
//}

package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dao.ContactRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.entities.Contact;
import com.example.demo.entities.User;
import com.example.demo.helper.Message;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private final UserRepository userRepository;
	private final ContactRepository contactRepository;

	@Autowired
	public UserController(UserRepository userRepository, ContactRepository contactRepository) {
		this.userRepository = userRepository;
		this.contactRepository = contactRepository;
	}

	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("USERNAME: " + userName);

		User user = userRepository.getUserByUserName(userName);
		System.out.println("USER: " + user);
		model.addAttribute("user", user);
	}

	@GetMapping("/index")
	public String dashboard(Model model) {
		return "normal/user_dashboard";
	}

	@GetMapping("/add-contact")
	public String openAddContactForm(Model model) {
		model.addAttribute("title", "Add Contact");
		model.addAttribute("contact", new Contact());
		return "normal/add_contact_form";
	}

	@PostMapping("/process-contact")
	public String processContact(@ModelAttribute Contact contact, @RequestParam("profileImage") MultipartFile file,
			Principal principal, HttpSession session) {

		try {
			String name = principal.getName();
			User user = userRepository.getUserByUserName(name);

			if (file.isEmpty()) {
				System.out.println("File is empty.");
				contact.setImage("contact.png");
			}

			else {
				contact.setImage(file.getOriginalFilename());

				File saveFile = new ClassPathResource("static/img").getFile();
				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());

				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				System.out.println("Image is uploaded.");
			}

			user.getContacts().add(contact);
			contact.setUser(user);
			userRepository.save(user);

			System.out.println("Data: " + contact);
			System.out.println("Added to database.");

			session.setAttribute("message", new Message("Your contact is added !! Add more..", "success"));
		}

		catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
			session.setAttribute("message", new Message("something went wrong !! try again..", "danger"));

		}
		return "redirect:/user/add-contact"; // Redirect to add contact form
	}

	@GetMapping("/show-contacts/{page}")
	public String showContacts(@PathVariable("page") Integer page, Model model, Principal principal) {
		model.addAttribute("title", "show user contacts");

		String userName = principal.getName();
		User user = this.userRepository.getUserByUserName(userName);

		// Define pagination parameters
		int pageSize = 5; // Number of contacts per page
		PageRequest pageRequest = PageRequest.of(page, pageSize);

		// Fetch contacts using pagination
		Page<Contact> contactsPage = this.contactRepository.findContactsByUser(user.getId(), pageRequest);

		// Extract contacts and pagination info
		List<Contact> contacts = contactsPage.getContent();
		int totalPages = contactsPage.getTotalPages();

		// Add attributes to the model
		model.addAttribute("contacts", contacts);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);

		return "normal/show_contacts";
	}

// showing parricular contact details
	@GetMapping("/{cid}/contact")
	public String showContactDetails(@PathVariable("cid") Integer cid, Model model, Principal principal) {

		System.out.println("CID: " + cid);
		// Fetch the contact by its ID and add it to the model

		Optional<Contact> contactoptional = this.contactRepository.findById(cid);
		Contact contact = contactoptional.get();

		// getting current login user
		String userName = principal.getName();
		User user = this.userRepository.getUserByUserName(userName);

		if (user.getId() == contact.getUser().getId()) { // this is for the purpose of ,if the user wanat to login to
															// the another account he doesn't get the permisiion to
															// lohin to the anither account
			model.addAttribute("contact", contact);
			model.addAttribute("title", contact.getName());
		}

		return "normal/contact_detail";
	}
	
	

////	// delte contact handler
//	@GetMapping("/delete/{cid}")
//	public String deleteConatct(@PathVariable("cid") Integer cid, Model model, HttpSession session,Principal principal) {
//		System.out.println("CID " + cid);
//		Contact contact = this.contactRepository.findById(cid).get();
//
//		System.out.println("Contact " + contact.getCid());
//		
//		// delte old photo
//		User user=this.userRepository.getUserByUserName(principal.getName());
//		user.getContacts().remove(contact);
//		this.userRepository.save(user);
//
//		// check.
//		System.out.println("deleted");
//		session.setAttribute("message", new Message("Contact deleted successfully.", "success"));
//
//		return " redirect:/normal/show_contacts/0";
//	}
//	
//	@GetMapping("/delete/{cid}")
//	public String deleteConatct(@PathVariable("cid") Integer cid) {
//		System.out.println("CID " + cid);
//		userRepository.deleteById(cid);
//		//return " redirect:/normal/show_contacts";
//		return "redirect:/user/show_contacts"; // Assuming '/user' is your base path
//
//	}
	
	
	@GetMapping("/delete/{cid}")
	public String deleteContact(@PathVariable("cid") Integer cid, Model model, HttpSession session, Principal principal) {
	    System.out.println("CID " + cid);
	    
	    // Fetch the contact from the repository
	    Contact contact = this.contactRepository.findById(cid).orElse(null);

	    // Check if contact exists
	    if (contact != null) {
	        // Delete the contact from the user's contact list
	        User user = this.userRepository.getUserByUserName(principal.getName());
	        user.getContacts().remove(contact);
	        
	        // Save the changes to the user
	        this.userRepository.save(user);

	        // Print confirmation message
	        System.out.println("Contact deleted");

	        // Set session message
	        session.setAttribute("message", new Message("Contact deleted successfully.", "success"));
	    } else {
	        // Print error message if contact does not exist
	        System.out.println("Contact not found");
	        
	        // Set session message for error
	        session.setAttribute("message", new Message("Contact not found.", "danger"));
	    }

	    // Redirect to the show_contacts page
	   // return "redirect:/normal/show_contacts";
	    //return "normal/show_contacts";
	    return " redirect:/normal/show_contacts";
	}

	

	// open update form handler

//	@GetMapping("/update-contact/{cid}")
//	public String showUpdateForm(@PathVariable("cid") Integer cid, Model model) throws NotFoundException {
//		// Logic to retrieve contact information by cid
//		// Add contact information to the model
//		Contact contact = this.contactRepository.findById(cid).orElseThrow(() -> new NotFoundException()); // Handle if
//																											// contact
//																											// is not																								// found
//		model.addAttribute("title", "Update Contact");
//		model.addAttribute("contact", contact);
//		return "normal/update_form"; // Return the name of the Thymeleaf template
//	}
	//@RequestMapping(value = "/update-contact/{cid}", method = {RequestMethod.GET, RequestMethod.POST})
	@PostMapping("/update-contact/{cid}")
	public String showUpdateForm(@PathVariable("cid") Integer cid, Model model) throws NotFoundException {
		    Optional<Contact> optional = contactRepository.findById(cid);
		    if (optional.isPresent()) {
		        Contact contact = optional.get();
		        model.addAttribute("title", "Update Contact");
		        model.addAttribute("contact", contact);
		        return "normal/update_form";
		    } else {
		        throw new NotFoundException();
		    }
		}
//
//// update  contact handler

	@RequestMapping( "/process-update")
	//@PostMapping("/process-update")
	public String updateHandler(@ModelAttribute Contact contact, @RequestParam("profileImage") MultipartFile file,
			Model m, HttpSession session, Principal principal) {
		try {
			// old contact details
			Contact oldcontactDetail = this.contactRepository.findById(contact.getCid()).get();

			if (file.isEmpty()) {
				// file work
//			// rewrite
//			// delte old photo
				File deletFile = new ClassPathResource("static/img").getFile();
				File file1 = new File(deletFile, oldcontactDetail.getImage());
				file1.delete();

				// update new photo
				File saveFile = new ClassPathResource("static/img").getFile();
				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				contact.setImage(file.getOriginalFilename());
			}

			else {

				contact.setImage(oldcontactDetail.getImage());
			}

			User user = this.contactRepository.getUserByUserName(principal.getName());

			contact.setUser(user);
			this.contactRepository.save(contact);

			session.setAttribute("message", new Message("Your  contact is updated..", "success"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Contact Name" + contact.getName());
		System.out.println("Contact Id" + contact.getCid());
		return "redirect:/user/" + contact.getCid() + "/contact";
	}

	
//	@GetMapping("/update-contact/{cid}")
//	public String showUpdateForm(@PathVariable("cid") Integer cid, Model model) throws NotFoundException {
//		Optional<Contact> optional=contactRepository.findById(cid);
//		Contact contact=optional.get();
//		System.out.println("data is updated");	
//	return "normal/update_form"; // Return the name of the Thymeleaf template
//	}
	
	

	
	
	// your profile handler

	@GetMapping("/profile")
	public String yourProfile(Model model) {
		model.addAttribute("title", "profile page");
		return "normal/profile";
	}
	
	// open setting handler
	
	@GetMapping("/settings")
	public String openSettings() {
		return "normal/settings";
	}
	
	// change password handler
	
//	@PostMapping("/change-password")
//	public String changepassword(@RequestParam("oldpassword") String oldpassword,
//			@RequestParam("newpassword") String newpassword,
//			@RequestParam("confirmpassword") String confirmpassword,
//			Principal principal,HttpSession session)
//	{
//	System.out.println("old password "+ oldpassword);
//	System.out.println("new password "+ newpassword);
//	System.out.println("confirm password "+ confirmpassword);
//	
//	String userName=principal.getName();
//	User currentUser =this.userRepository.getUserByUserName(userName);
//	System.out.println(currentUser.getPassword());
//	
//	if(this.bCryptPasswordEncoder.matches(oldpassword, currentUser.getPassword()))
//	{
//		
//		// chnage passowrd
//		currentUser.setPassword(this.bCryptPasswordEncoder.encode(newpassword));
//		this.userRepository.save(currentUser);
//		session.setAttribute("message", new Message("Contact password is successfully chnanged...", "alert-success"));
//		
//	}
//	else {
//		// error
//		session.setAttribute("message", new Message("please enter the old password!!", " alert-danger"));
//		return "redirect:/user/settings";
//		
//	}
//	
//		return "redirect:/user/index";
//	}
	
	// chnage password handler
	@PostMapping("/change-password")
    public String changePassword(@RequestParam("oldpassword") String oldPassword,
                                 @RequestParam("newpassword") String newPassword,
                                 @RequestParam("confirmpassword") String confirmPassword,
                                 Principal principal,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {

        // Retrieve the current user
        String userName = principal.getName();
        User currentUser = this.userRepository.getUserByUserName(userName);
//        System.out.println("old password "+ oldPassword);
//   	System.out.println("new password "+ newPassword);
//   	System.out.println("confirm password "+ confirmPassword);

        // Check if the old password matches
        if (this.bCryptPasswordEncoder.matches(oldPassword, currentUser.getPassword())) {
            // Check if the new password matches the confirmed password
            if (newPassword.equals(confirmPassword)) {
                // Change password
                currentUser.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
                this.userRepository.save(currentUser);
                
                // Set success message
                session.setAttribute("message", new Message("Password successfully changed!", "alert-success"));
                
                // Redirect to a success page
                return "redirect:/user/index";
            } 
            else {
                // Set error message for password mismatch
                session.setAttribute("message", new Message("New password and confirm password do not match!", "alert-danger"));
            }
        } 
        else {
            // Set error message for incorrect old password
            session.setAttribute("message", new Message("Please enter the correct old password!", "alert-danger"));
        }

        // Redirect back to the settings page
        return "redirect:/user/settings";
    }
}
