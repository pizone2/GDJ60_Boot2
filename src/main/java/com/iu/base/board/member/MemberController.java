package com.iu.base.board.member;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.filters.ExpiresFilter.XHttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
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
   public ModelAndView getLogOut(HttpSession session) throws Exception{
      ModelAndView mv = new ModelAndView();
      session.invalidate();
      mv.setViewName("redirect:/");
      return mv;
   }
   
   
   @PostMapping("login")
   public ModelAndView getLogin(MemberVO memberVO, HttpSession session,String remember,HttpServletResponse response) throws Exception{
      ModelAndView mv = new ModelAndView();
      memberVO = memberService.getLogin(memberVO);
      
      session.setAttribute("member", memberVO);

      mv.setViewName("redirect:./login");
      if(memberVO !=null) {

         mv.setViewName("redirect:/");
      }
      
      
      return mv;
   }
   
   
   @GetMapping("idDuplicateCheck")
   @ResponseBody
	public Boolean idDuplicateCheck(MemberVO memberVO)throws Exception{
	   log.debug("============= ID 중복체크 =============");
	   boolean check =false;
	   
	   memberVO = memberService.idDuplicateCheck(memberVO);
	   
	   if(memberVO == null ) {
		   check = true;
	   }
	   
	   return check;
   }
   
   @GetMapping("join")
	public ModelAndView setJoin()throws Exception{
		ModelAndView mv = new ModelAndView();		
		mv.setViewName("/member/join");
		return mv;
	}
	
	@PostMapping("join")
	public ModelAndView setJoin(MemberVO memberVO) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		int result = memberService.setJoin(memberVO);
		
		
		modelAndView.setViewName("redirect:../");
		return modelAndView;
	}
   
   
   
   
}
