package com.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.board.BoardService;
import com.example.board.BoardVO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/board")
@Controller
public class BoardController {
	BoardController(){
	 System.out.println("===>BoardController");
 }
	@Autowired
	BoardService service;

	@GetMapping("/form")
	private String form() {
		System.out.println("폼");
		return "board/form.html";
	}
	
	@GetMapping("/insert")
	private String insert(Model model, BoardVO vo, HttpSession session) {
		int totalCount = service.insert(vo);
		model.addAttribute("totalCount", totalCount);
		return "redirect:/board/getBoardList";
	}

	@GetMapping("/getBoardList")
	private String getBoardList(@RequestParam(value="ch1", defaultValue="name", required=false) String ch1
            , @RequestParam(value="ch2", defaultValue="", required=false)  String ch2 
            ,Model model)  {
		System.out.println("===>getBoardList");
		
		model.addAttribute("li",service.getBoardList(ch1,ch2));
		return "board/getBoardList.html";
	}
	
	@GetMapping("/getBoard")
	private String getBoard(Model model, BoardVO vo) {
	 model.addAttribute("m" , service.getBoard(vo));
	 System.out.println("===>getBoard 이름:"+vo.getName()+"   번호:"+vo.getIdx());
		return "board/getBoard";
	}
	
	@GetMapping("/BoardUpdate")
	private String BoardUpdate(Model model, BoardVO vo) {
	
	 service.BoardUpdate(vo);
	 System.out.println("===>BoardUpdate" +vo );
		return "redirect:/board/getBoardList";
	}
	
	@GetMapping("/BoardDelete")
	private String BoardDelete(Model model, BoardVO vo) {
	
	 service.BoardDelete(vo);
	 System.out.println("===>BoardDelete" +vo );
		return "redirect:/board/getBoardList";
	}

}
