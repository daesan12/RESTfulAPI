package com.example.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardDao {
	List<BoardVO> getBoardList(String ch1, String ch2);
	void insert(BoardVO vo);
	void BoardDelete(BoardVO vo);
	void BoardUpdate(BoardVO vo);
	int totalCount(BoardVO vo);
	BoardVO getBoard(BoardVO vo);
}
