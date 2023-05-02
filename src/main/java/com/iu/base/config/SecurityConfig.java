package com.iu.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.iu.base.security.UserLogoutSuccessHandler;
import com.iu.base.board.member.MemberService;
import com.iu.base.board.member.MemberSocialService;
import com.iu.base.security.UserLoginFailHandler;
import com.iu.base.security.UserLogoutHandler;
import com.iu.base.security.UserSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	@Autowired
	private UserLogoutSuccessHandler userLogoutSuccessHandler;

	@Autowired
	private MemberSocialService memberSocialService;
	
	@Autowired
	private UserLogoutHandler userLogoutHandler;
	
	

	@Bean
	//public 을 선언하면 default로 바꾸라는 메세지 출력
	WebSecurityCustomizer webSecurityConfig() {
	//Security에서 무시해야하는 URL 패턴 등록
		return web -> web
					.ignoring()
					.antMatchers("/images/**")
					.antMatchers("/js/**")
					.antMatchers("/css/**")
					.antMatchers("/favicon/**");
					
					
}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception{
		httpSecurity
				.cors()				
				.and()
				.csrf()
				.disable()
			
				
				
			.authorizeRequests()
				// URL과 권한 매창
				.antMatchers("/").permitAll()
				.antMatchers("/member/join").permitAll()
				.antMatchers("/notice/add").hasRole("MEMBER")
				.antMatchers("/notice/update").hasRole("ADMIN")
				.antMatchers("/notice/delete").hasRole("ADMIN")
				.antMatchers("/notice/*").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/qna/add").hasAnyRole("ADMIN","MANAGER","MEMBER")
				//.anyRequest().authenticated()
			.anyRequest().permitAll()				
				.and()
			.formLogin()
				.loginPage("/member/login")
				.usernameParameter("username")
				//.defaultSuccessUrl("/")    
				.successHandler(new UserSuccessHandler())
				//.failureUrl("/member/login")
				.failureHandler(new UserLoginFailHandler())
				.permitAll()
				.and()
			.logout()
				.logoutUrl("/member/logout")    
//				.logoutSuccessUrl("/")
			.addLogoutHandler(userLogoutHandler)
				.logoutSuccessHandler(userLogoutSuccessHandler)
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.permitAll()
				.and()
			.oauth2Login()
				.userInfoEndpoint()
				.userService(memberSocialService)
				;
//		 .sessionManagement()
//          .maximumSessions(1) //최대허용가능한 session의수 -1은 무한대접속가능 
//          .maxSessionsPreventsLogin(false)//flase 이전사용자 세션마료,
//				;
				
			
				
		return httpSecurity.build();
	}
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
