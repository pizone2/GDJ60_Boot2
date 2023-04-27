package com.iu.base.board.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor =  Exception.class)
public class MemberService implements UserDetailsService {

	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private JavaMailSender jmsender;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public int getMemberFindPw(MemberVO memberVO) throws Exception {
		MemberVO memberDTO2 = memberDAO.getMemberDetail(memberVO);
		if(memberDTO2 != null &&memberVO.getEmail().equals(memberDTO2.getEmail())) {
			memberVO.setPassword(memberDTO2.getPassword());
			String newPw = UUID.randomUUID().toString().substring(0, 7);
			memberVO.setChangeMemberPw(newPw);

			int result = memberDAO.setMemberUpdatePw(memberVO);
			if(result > 0) {
				
				MimeMessage message = jmsender.createMimeMessage();
				
				message.setSubject("[가지마켓 안내] 비밀번호 알려드립니다.");
				String htmlStr = "<div style=\"background-image: url('https://png.pngtree.com/thumb_back/fw800/background/20220309/pngtree-blue-sky-and-white-clouds-meadow-cherry-blossom-spring-flowers-image_1067065.jpg');height: 300px;width: 300px;border-radius: 70px;border: 1px black solid;\">\r\n"
						+ "        <h2 style=\"text-align: center;\">Branch Market</h2>\r\n"
						+ "        <h4>안녕하세요 가지마켓 입니다 임시 비밀번호는 " + newPw +" 입니다.</h4>\r\n"
						+ "     </div>";
				message.setText(htmlStr,"UTF-8","html");
				message.addRecipient(RecipientType.TO, new InternetAddress(memberVO.getEmail()));
				jmsender.send(message);
				
				return 1;
			}else {
				return 0;
			}
		}else {
			return 0;
		}
		
	}
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		log.error("=========== Spring Security Login =============");
		log.error("=========== {} =============",username);
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(username);		
		try {
			memberVO = memberDAO.getLogin(memberVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memberVO;
	}

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
			bindingResult.rejectValue("username","member.duplicationID");
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
		// memberVO.setEnabled(true);
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
		int result = memberDAO.setJoin(memberVO);
		Map<String, Object> map = new HashMap<>();
		map.put("username", memberVO.getUsername());
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
