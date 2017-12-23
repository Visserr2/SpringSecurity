package nl.thuis.tutorial.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	private static final String LOGIN_PAGE = "fancy-login";
	
	@GetMapping("/showLogin")
	public String showLogin() {
		
		return LOGIN_PAGE;
	}
}
