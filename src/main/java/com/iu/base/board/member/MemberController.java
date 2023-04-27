package com.iu.base.board.member;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.catalina.filters.ExpiresFilter.XHttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {

   @Autowired
   private MemberService memberService;
   
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
   public ModelAndView getLogin() throws Exception{
      ModelAndView mv = new ModelAndView();
      mv.setViewName("./member/login");
      return mv;
   }
   
   @GetMapping("logout")
   public ModelAndView getLogOut(HttpSession session,MemberVO memberVO) throws Exception{
      ModelAndView mv = new ModelAndView();
      
      
      memberVO = (MemberVO) session.getAttribute("member");
      memberVO.setUsername(memberVO.getUsername());
      memberService.setLogOut(memberVO);
      
      session.invalidate();
      mv.setViewName("redirect:/");
      return mv;
   }
   
  
   
   
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
