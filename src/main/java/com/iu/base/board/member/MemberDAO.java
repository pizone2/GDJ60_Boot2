package com.iu.base.board.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {

	public MemberVO getLogin(MemberVO memberVO)throws Exception;

	public int setJoin(MemberVO memberVO)throws Exception;
}
