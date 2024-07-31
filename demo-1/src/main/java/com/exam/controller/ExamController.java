package com.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.exam.ExamService;
import com.example.exam.ExamVO;

@RequestMapping("/exam")
@Controller
public class ExamController {
	ExamController(){
		 System.out.println("===>ExamController");
	 }
	
	@Autowired
	ExamService service;
	
	@GetMapping("/List")
	private String List(Model model,ExamVO vo) {
		System.out.println("성적리스트");
		model.addAttribute("li", service.ExamList(vo));
		return "exam/list.html";
	}
	
	
}
