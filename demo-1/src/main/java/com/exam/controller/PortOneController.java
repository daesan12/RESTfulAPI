package com.exam.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.portone.PortOneService;
import com.example.portone.PortOneVO;

@RequestMapping("/PortOne")
@Controller
public class PortOneController {
	PortOneController(){
	System.out.println("포트원 컨트롤러실행");
}
	@Autowired
	PortOneService service;
	@GetMapping("/start")
	public String start(PortOneVO vo,Model model) {

		vo.setGname("포웨이짐");
		vo.setGymnum(90001);
		
		vo.setMmail("diqkraud@naver.com");
		vo.setMname("장대산");
		vo.setMphone("01024793876");
		vo.setMembernum("10001");
		
		Date d =new  Date();
		SimpleDateFormat f=new SimpleDateFormat("yyyy년M월dd일");
		System.out.println(f.format(d));
		
		vo.setDataName("3개월권");
		vo.setDataPrice(100);
		vo.setDataGoodsnum("70001");		
		vo.setToday(f.format(d));
		
		model.addAttribute("m", vo);
		System.out.println("===> 결제시작하기 ");
		return "PortOne/start.html";		
	}
	
	@ResponseBody
		@PostMapping("/insertMPay")   // @RequestBody 비동기 통신의 값을 요청하기 위해서는 필수  (중요) 
		public String insertMPay(@RequestBody PortOneVO vo, Model model) {
			
			
			String OK = service.insert(vo);
			System.out.println("===> insert 확인 " + OK);
			return OK ;	
		}
	
	@GetMapping("/result")
	public String result(Model model) {
		
		model.addAttribute("hello", "hello");
		System.out.println("===> result 확인 ");
		return "PortOne/result.html" ;		
	}
	
	@GetMapping("/fail")
	public String fail(Model model) {
		
		model.addAttribute("fail", "fail");
		System.out.println("===> fail 확인 ");
		return "PortOne/fail.html" ;		
	}
	
}
