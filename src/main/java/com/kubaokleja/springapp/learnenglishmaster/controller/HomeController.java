package com.kubaokleja.springapp.learnenglishmaster.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/index")
	public String indexPage() {
		return "index";
	}
	@GetMapping("/")
	public String homePage() {
		return "index";
	}
	@GetMapping("/about-me")
	public String aboutPage() {
		return "about-me";
	}
}
