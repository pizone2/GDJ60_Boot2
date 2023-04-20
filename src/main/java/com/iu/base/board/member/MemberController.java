package com.iu.base.board.member;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.filters.ExpiresFilter.XHttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/member/*")
public class MemberController {

   @Autowired
   private MemberService memberService;
   
   
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
   
   @GetMapping("join")
	public ModelAndView setMemberAdd() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/member/join");
		return mv;
	}
	
	@PostMapping("join")
	public ModelAndView setMemberAdd(MemberVO memberVO) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		int result = memberService.setJoin(memberVO);
		
		modelAndView.addObject("url", "/");
		modelAndView.setViewName("redirect:/");
		return modelAndView;
	}
   
   
   
   
}
