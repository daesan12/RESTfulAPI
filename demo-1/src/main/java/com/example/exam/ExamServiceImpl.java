package com.example.exam;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamServiceImpl implements ExamService{
	
	@Autowired
	ExamDao dao;

	@Override
	public List<ExamVO> ExamList(ExamVO vo) {
		// TODO Auto-generated method stub
		return dao.ExamList(vo);
	}





}
