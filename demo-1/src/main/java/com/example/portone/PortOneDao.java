package com.example.portone;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PortOneDao {

	 void insert(PortOneVO vo);
}
