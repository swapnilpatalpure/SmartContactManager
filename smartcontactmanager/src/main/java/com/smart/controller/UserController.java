package com.smart.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@RequestMapping(value = "/index")
	public String dashboard() {
		System.out.println("11111");
		return "ok";
	}
}

 