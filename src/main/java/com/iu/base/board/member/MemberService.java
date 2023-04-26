package com.iu.base.board.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService {

	@Autowired
	private MemberDAO memberDAO;
	
	//패스워드가 일치하는지 검사
	public boolean memberCheck(MemberVO memberVO,BindingResult bindingResult) throws Exception{
		boolean result = false;
		//false : error가 없음, 검증성공
		//ture : error가 실패, 검증실패
		
		//1.annotation 검증 결과
		result = bindingResult.hasErrors();
		
		//2.password가 일치 하는지 검즘
//		if(!memberVO.getPassword().equals(memberVO.getPasswordCheck())){
//			result = true;
//			bindingResult.rejectValue("passwordCheck", "member.password.notEqual");
//		}
		
		//3.ID중복 검사
		
		
		if(memberDAO.idDuplicateCheck(memberVO) != null) {
			result = true;
			bindingResult.rejectValue("userName","member.duplicationID");
		}
		return result;
	}
	
	public MemberVO getLogin(MemberVO memberVO) throws Exception {
		return memberDAO.getLogin(memberVO);
	}
	
	public MemberVO idDuplicateCheck(MemberVO memberVO)throws Exception{
		return memberDAO.idDuplicateCheck(memberVO);
	}	
	
	public int setJoin(MemberVO memberVO)throws Exception{
		memberVO.setEnabled(true);
		int result = memberDAO.setJoin(memberVO);
		Map<String, Object> map = new HashMap<>();
		map.put("userName", memberVO.getUserName());
		map.put("num", 3);
		result = memberDAO.setMemberRole(map);
		
		
		return result;
	}
	
	public List<MemberVO> getMemberList ()throws Exception{
		return memberDAO.getMemberList();
	}
	
	public int setLogOut (MemberVO memberVO)throws Exception{
		log.error("로그아웃");
		return memberDAO.setLogOut(memberVO);
	}
}
