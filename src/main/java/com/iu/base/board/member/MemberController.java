package com.iu.base.board.member;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.catalina.filters.ExpiresFilter.XHttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {

   @Autowired
   private MemberService memberService;
   
   @Autowired
   private PasswordEncoder passeEncoder;
   
   @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
   private String adminkey;
   
   @GetMapping("delete")
   public String delete()throws Exception {
	   MemberVO memberVO =  (MemberVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	   //회원가입 방법 구분
	   this.kakaoDelete(memberVO);
	   
	   return "redirect:./logout/";
	   
   }
   
   private void kakaoDelete(MemberVO memberVO) {
	  RestTemplate restTemplate = new RestTemplate();
	  
	  HttpHeaders headers = new HttpHeaders();
	  headers.add("Authorization", "KakaoAK "+adminkey);
	  headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	  
	  MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	  params.add("target_id_type", "user_id");
	  params.add("target_id", memberVO.getAttributes().get("id").toString());
	  
	  HttpEntity< MultiValueMap<String, String>> req =new HttpEntity<>(params);
	  
	  String id = restTemplate.postForObject("https://kapi.kakao.com/v1/user/unlink", req, String.class);
	  log.error("Delete {} ::", id);
   }
   
   
   @GetMapping("findPassword")
	public ModelAndView getMemberFindPw(MemberVO memberVO) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("member/findPassword");
		return modelAndView;
	}
	
	// memberId, email 입력받음
	@PostMapping("findPassword")
	public ModelAndView getMemberFindPw(MemberVO memberVO,HttpSession session) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		String message = "";
		String url = "/";
		int result = memberService.getMemberFindPw(memberVO);
		
		if(result > 0) {
			message = "이메일에 비밀번호를 성공적으로 보냄!";
		}else {
			message = "존재하지 않는 아이디 또는 이메일 입니다";
			url = "./findPw";
		}
		
		modelAndView.addObject("message", message);
		modelAndView.addObject("url", url);
		modelAndView.setViewName("redirect:/");
		
		return modelAndView;
	}
	
   
   @GetMapping("info")
   public void info(HttpSession session) {
	   String pw = "useruser2";
	   
	   MemberVO memberVO = (MemberVO) memberService.loadUserByUsername("useruser2");
	   
	   log.error("{} :::: ",memberVO.getPassword());
	   log.error("{} :::: ",passeEncoder.encode(pw));
	   log.error("{} :::: ",memberVO.getPassword().equals( passeEncoder.encode(pw)));
	   
	   boolean check = passeEncoder.matches(pw, memberVO.getPassword());
	   log.error("{} :::: ",check);
	   
	   
	   log.error("======= Login Info ======");
	   //SPRING_SECURITY_CONTEXT
//	   Enumeration<String> names = session.getAttributeNames();
//	   while(names.hasMoreElements()) {
//		   log.error("=== {} ===", names.nextElement());
//	   }
	   Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
	   SecurityContextImpl contextImpl = (SecurityContextImpl) obj;
	   Authentication authentication = contextImpl.getAuthentication();
	   
	   log.error("==== {} ======",obj);
	   log.error("==== NAME :  {} ======",authentication.getName());
	   log.error("==== DETAIL  :  {} ======",authentication.getDetails());
	   log.error("==== Principal  :  {} ======",authentication.getPrincipal());
	   
   }
   
 
   
   @GetMapping("mypage")
   public void getMyPage() throws Exception{
	   
   }
   
   @GetMapping("admin")
   public void getAdminPage() throws Exception{
	   
   }
   
   
   
   
   @GetMapping("login")
   public ModelAndView getLogin(HttpSession session) throws Exception{
      ModelAndView mv = new ModelAndView();
      
   
      mv.setViewName("./member/login");
      return mv;
   }
   
//   @GetMapping("socialLogout")
//   public ModelAndView getLogOut(HttpSession session,MemberVO memberVO) throws Exception{
//      ModelAndView mv = new ModelAndView();
//      
//      
//      memberVO = (MemberVO) session.getAttribute("member");
//      memberVO.setUsername(memberVO.getUsername());
//      memberService.setLogOut(memberVO);
//      
//      session.invalidate();
//      mv.setViewName("redirect:/");
//      return mv;
//   }
   
  
   
   
// @@@@@@@@@@@@@@@@@@@@  Security Config 사용하면 없어도 실행됌 @@@@@@@@@@@@@@@@@@@@
   
//   @PostMapping("login")
//   public ModelAndView getLogin(MemberVO memberVO, HttpSession session,String remember,HttpServletResponse response) throws Exception{
//      ModelAndView mv = new ModelAndView();
//      memberVO = memberService.getLogin(memberVO);
//      
//      session.setAttribute("member", memberVO);
//
//      mv.setViewName("redirect:./login");
//      if(memberVO !=null) {
//
//         mv.setViewName("redirect:/");
//      }
//      
//      
//      return mv;
//   }
   
   
//   @GetMapping("idDuplicateCheck")
//   @ResponseBody
//	public Boolean idDuplicateCheck(MemberVO memberVO)throws Exception{
//	   log.debug("============= ID 중복체크 =============");
//	   boolean check =false;
//	   
//	   memberVO = memberService.idDuplicateCheck(memberVO);
//	   
//	   if(memberVO == null ) {
//		   check = true;
//	   }
//	   
//	   return check;
//   }
   
   @GetMapping("join")
	public ModelAndView setJoin(@ModelAttribute MemberVO memberVO)throws Exception{
		ModelAndView mv = new ModelAndView();		
		mv.setViewName("/member/join");
		return mv;
	}
	
	@PostMapping("join")
	public ModelAndView setJoin(@Valid MemberVO memberVO,BindingResult bindingResult) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		boolean check = memberService.memberCheck(memberVO, bindingResult);
		
		if(check) {
			log.warn("=== 검증실패 ===");			
			mv.setViewName("member/join");
			return mv;
		}
		
		int result = memberService.setJoin(memberVO);
		
		
		mv.setViewName("redirect:../");
		return mv;
	}
   
   
   
   
}
