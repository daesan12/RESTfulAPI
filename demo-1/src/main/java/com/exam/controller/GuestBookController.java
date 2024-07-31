package com.exam.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.guestbook.GuestBookService;
import com.example.guestbook.GuestBookVO;

@RequestMapping("/guestBook")
@Controller
public class GuestBookController {
	
	GuestBookController() {
		System.out.println("====>GuestBookController");
		

	}
	
	@Autowired
	private GuestBookService service;
	
	@GetMapping("/insert")
	public String insert(GuestBookVO vo) {
		
		
		String [] fistName= {"최","박","석","김","나","이","장","정","한","임","신","남"};
		String [] Lastname= {"하니","두리","지효","지솔","태익","길동","루피","조로","상디"};
		int[] age= {11,12,13,14,15,16,17,18,19,20};
		String [] meme= {"감사합니다","잘부탁드려요","너무 좋아요","응원합니다","안녕하세요","왔다갑니다","파이팅","마음에 들어요"};
	
		Random random = new Random();
		 for(int i=0; i<5; i++) {
		  String randomFirstName = fistName[random.nextInt(fistName.length)];
	        String randomLastName = Lastname[random.nextInt(Lastname.length)];
	        int randomAge = age[random.nextInt(age.length)];
	        String randomMemo = meme[random.nextInt(meme.length)];
	     
	        vo.setName(randomFirstName+randomLastName);
	        vo.setAge(randomAge);
	        vo.setMemo(randomMemo);
	       
	        service.insert(vo);
	        };
	        
		return "redirect:/guestBook/list";
	}
	@GetMapping("/list")
    public String getGuestBookList( GuestBookVO vo , Model model ) {
		System.out.println("===> list ");
    int start = 0;
    int pageSize = 10;
    int pageListSize = 10;
    int totalCount = service.totalCount(vo);
    if(vo.getStart()==0) {
    	start = 1;
    }else {
    	start = vo.getStart();
    }
    int end =start + pageSize -1;
    int totalPage =(int) (Math.ceil((double)totalCount /pageSize));
    int currentPage = (start/ pageSize)+1;
    int lastpage = (totalPage -1 ) * pageSize +1;
    int listStartPage = (currentPage -1 )/pageListSize * pageListSize+1;
    int listEndPage = listStartPage + pageListSize -1;

    //1.페이지 사이즈
    model.addAttribute("pageSize",pageSize);
    //2.페이지 리스트 사이즈
    model.addAttribute("pageListSize",pageListSize);
    //3.전체레코드 수
    model.addAttribute("totalCount",totalCount);
    //4.총 페이지 수
    model.addAttribute("totalPage",totalPage);
    //5.현재레코드
    model.addAttribute("start",start);
    //6.현재 페이지
    model.addAttribute("currentPage",currentPage);
    //7.하단 가로 시작
    model.addAttribute("listStartPage",listStartPage);
    //8.하단 가로 끝
    model.addAttribute("listEndPage",listEndPage);
    model.addAttribute("lastPage",lastpage);
    vo.setStart(start);
    vo.setEnd(end);
    model.addAttribute("ch1",vo.getCh1());
    model.addAttribute("ch2",vo.getCh2());
    model.addAttribute("li",service.list(vo));
		
		return "guestBook/list";    	
    }
	
}
