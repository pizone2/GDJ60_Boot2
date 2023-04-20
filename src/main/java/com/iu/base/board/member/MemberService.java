package com.iu.base.board.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	private MemberDAO memberDAO;
	
	public MemberVO getLogin(MemberVO memberVO) throws Exception {
		return memberDAO.getLogin(memberVO);
	}
	
	public int setJoin(MemberVO memberVO)throws Exception{
		return memberDAO.setJoin(memberVO);
}
}
