package com.smart.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smart.dao.UserRepository;
import com.smart.entities.User;
import com.smart.helper.Message;

@Controller
public class HomeController {

	
	
	@Autowired
	private UserRepository userRepository;

//	@RequestMapping("/test")
//	public String test() {
//		
//		User user=new User();
//		user.setName("Priyanka");
//		user.setEmail("p@gmail.com");
//		userRepository.save(user);
//		return "working";
//	}
	
	@RequestMapping(value = "/")
	public String home(Model model) {
		model.addAttribute("title", "Home-Smart Contact Manager");
		return "home";
	}
	
	@RequestMapping(value = "/about")
	public String about(Model model) {
		model.addAttribute("title", "About-Smart Contact Manager");
		return "about";
	}
	
	@RequestMapping(value = "/signup")
	public String signup(Model model) {
		model.addAttribute("title", "Register-Smart Contact Manager");
		model.addAttribute("user", new User());
		return "signup";
	}

	//Handler for registering user
	@PostMapping(value = "/do_register")
	public String registerUser(@Valid @ModelAttribute User user, BindingResult result1, 
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, 
			Model model,  HttpSession session) {
		
		try {
			if(!agreement) {
				System.out.println("You have not agreed the terms and conditions");
				throw new Exception("You have not agreed the terms and conditions");
			}
			
			if(result1.hasErrors()) {
				System.out.println("ERROR "+result1.toString());
				model.addAttribute("user", user);
				return "signup";
			}
			
			user.setRole("Role_USER");
			user.setEnabled(true);
			user.setImageUrl("default.png");
			
			User result=userRepository.save(user);
			
			System.out.println("Agreement " +agreement);
			System.out.println("User "+result);
			
			model.addAttribute("user", new User());
			session.setAttribute("message", new Message("Successfully Registered ", "alert-success"));
			return "signup";
			
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user", user);
			session.setAttribute("message", new Message(e.getMessage(), "alert-danger"));
			return "signup";
		}
		
		
	}
}
