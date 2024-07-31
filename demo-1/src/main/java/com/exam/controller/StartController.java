package com.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class StartController {
	@GetMapping("/index")
	private String index() {
		System.out.println("index확인");
		return "index.html";
	}
}
