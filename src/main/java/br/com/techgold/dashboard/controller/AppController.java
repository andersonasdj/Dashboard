package br.com.techgold.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
	
	@GetMapping("/login")
	public String login() {
		return "templates/login.html";
	}
	
	@GetMapping("/inicio")
	public String home() {
		return "templates/home.html";
	}
	
	@GetMapping("/sobre")
	public String sobre() {
		return "templates/sobre.html";
	}
}
