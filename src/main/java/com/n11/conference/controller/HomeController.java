package com.n11.conference.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/insert")
	public String insertHtml() {
		return "insert.html";
	}
	
	@GetMapping("/list")
	public String listHtml() {
		return "list.html";
	}

}
