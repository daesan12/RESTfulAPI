package com.example.guestbook;

import java.util.List;

import org.springframework.stereotype.Service;

public interface GuestBookService {
	void insert(GuestBookVO vo);
	List<GuestBookVO> list(GuestBookVO vo);
	int totalCount(GuestBookVO vo);
}
