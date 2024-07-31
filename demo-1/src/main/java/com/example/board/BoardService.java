package com.example.board;

import java.util.List;


public interface BoardService {
List<BoardVO>getBoardList(String ch1, String ch2);
int insert(BoardVO vo);
BoardVO getBoard(BoardVO vo);
void BoardUpdate(BoardVO vo);
void BoardDelete(BoardVO vo);
}
