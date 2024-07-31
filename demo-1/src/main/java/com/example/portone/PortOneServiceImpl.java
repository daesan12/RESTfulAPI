package com.example.portone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortOneServiceImpl implements PortOneService {
	@Autowired
	PortOneDao dao;

	@Override
	public String insert(PortOneVO vo) {
		try {
			dao.insert(vo);
			System.out.println("insert들어옴"+ vo);
			return "/PortOne/result";
		} catch (Exception e) {
			return "/PortOne/fail";
		}

	}

	@Override
	public PortOneVO edit(PortOneVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

}
