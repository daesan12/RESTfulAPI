package com.example.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService{

	BoardServiceImpl() {
		System.out.println("===>BoardServiceImpl");
	}

	@Autowired
	BoardDao dao;

	@Override
	public List<BoardVO> getBoardList(String ch1, String ch2) {
		
		
		System.out.println( "확인"+ch1+ch2);
		return dao.getBoardList(ch1,"%"+ch2+"%");
	}

	@Override
	public int insert(BoardVO vo) {
		
		dao.insert(vo);
		return dao.totalCount(vo);
		
	}

	@Override
	public BoardVO getBoard(BoardVO vo) {
		
		return dao.getBoard(vo);
	}

	@Override
	public void BoardUpdate(BoardVO vo) {
	dao.BoardUpdate(vo);
		
	}

	@Override
	public void BoardDelete(BoardVO vo) {
	dao.BoardDelete(vo);
		
	}


}
