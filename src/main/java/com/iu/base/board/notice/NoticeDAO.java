package com.iu.base.board.notice;

import org.apache.ibatis.annotations.Mapper;

import com.iu.base.board.BoardDAO;

@Mapper
public interface NoticeDAO extends BoardDAO {

	public int setBirth()throws Exception;
}
