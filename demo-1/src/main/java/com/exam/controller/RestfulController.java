package com.exam.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.guestbook.GuestBookService;
import com.example.guestbook.GuestBookVO;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/Restful")
public class RestfulController {
	@Autowired
	private GuestBookService service;

	@GetMapping(value = "/list", produces = "application/json; charset=UTF-8")
	public String getGuestBookList(GuestBookVO vo, Model model) {
		System.out.println("===> list ");
		int start = 0;
		int pageSize = 10;
		int pageListSize = 10;
		int totalCount = service.totalCount(vo);
		if (vo.getStart() == 0) {
			start = 1;
		} else {
			start = vo.getStart();
		}
		int end = start + pageSize - 1;
		int totalPage = (int) (Math.ceil((double) totalCount / pageSize));
		int currentPage = (start / pageSize) + 1;
		int lastpage = (totalPage - 1) * pageSize + 1;
		int listStartPage = (currentPage - 1) / pageListSize * pageListSize + 1;
		int listEndPage = listStartPage + pageListSize - 1;
		vo.setStart(start);
		vo.setEnd(totalCount);
		int rownum=vo.getRownum();
		int rnum=vo.getRnum();
		/*
		 * model.addAttribute("pageSize", pageSize);
		 * 
		 * model.addAttribute("pageListSize", pageListSize);
		 * 
		 * model.addAttribute("totalCount", totalCount);
		 * 
		 * model.addAttribute("totalPage", totalPage);
		 * 
		 * model.addAttribute("start", start);
		 * 
		 * model.addAttribute("currentPage", currentPage);
		 * 
		 * model.addAttribute("listStartPage", listStartPage);
		 * 
		 * model.addAttribute("listEndPage", listEndPage);
		 * model.addAttribute("lastPage", lastpage);
		 * 
		 * model.addAttribute("li", service.list(vo));
		 */

		Map<String, Object> resultMap = new HashMap<>();

		resultMap.put("totalCount", totalCount);
		resultMap.put("start", start);

		resultMap.put("pageSize", pageSize);
		resultMap.put("pageListSize", pageListSize);
		resultMap.put("pageSize", pageSize);
		resultMap.put("totalPage", totalPage);
		resultMap.put("currentPage", currentPage);
		resultMap.put("listStartPage", listStartPage);
		resultMap.put("listEndPage", listEndPage);
		resultMap.put("lastPage", lastpage);
		resultMap.put("rnum", rnum);
		resultMap.put("rownum", rownum);
	
		resultMap.put("li", service.list(vo));
		System.out.println("resultMap"+resultMap);
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}


}
