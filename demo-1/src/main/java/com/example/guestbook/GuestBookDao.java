package com.example.guestbook;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface GuestBookDao {
void insert(GuestBookVO vo);
List<GuestBookVO> list(GuestBookVO vo);
int totalCount(GuestBookVO vo);
}
