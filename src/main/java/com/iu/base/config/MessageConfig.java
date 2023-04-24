package com.iu.base.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.iu.base.board.member.MemberVO;

@Configuration
public class MessageConfig implements WebMvcConfigurer{

	//객체 생성 : annotation, xml -> <bean>
	@Bean
	public LocaleResolver localeResolver() {
		//1. session
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(Locale.KOREA);
		
		//2.cookie
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setDefaultLocale(Locale.KOREAN);
		cookieLocaleResolver.setCookieName("lang");
		
		
		return sessionLocaleResolver;
	}
	
	@Bean
	public LocaleChangeInterceptor getchangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang_opt");
		//parameter를 받아서 언어 구분
		//url?lang_opt=en
		return localeChangeInterceptor;
	}
	
	
	
	
	
	
	}