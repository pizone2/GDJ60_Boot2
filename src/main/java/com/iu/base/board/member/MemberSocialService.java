package com.iu.base.board.member;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class MemberSocialService extends DefaultOAuth2UserService{

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// TODO Auto-generated method stub
		
		//kakao 로그인 처리 후 실행
		
		log.error("{} ::: social",userRequest.getAccessToken());
		
		ClientRegistration registration =  userRequest.getClientRegistration();
		
		log.error("{} ::: ID",registration.getRegistrationId());
		log.error("{} :::Scopes",registration.getScopes());
		log.error("{} ::: ID",registration.getClientName());
		log.error("{} ::: ID",registration.getClientId());
		OAuth2User user = super.loadUser(userRequest);
		log.error("{} ::: ",user.getName());
		
		return this.socialJoinCheck(userRequest);
	}
	
	private OAuth2User socialJoinCheck(OAuth2UserRequest auth2UserRequest) {
		// DB에서 조회 후 회원추가 또는 회원정보(Role) 조회
		//kakao에서 받은 정보를 MemberVO로 변경
		//
		OAuth2User user = super.loadUser(auth2UserRequest);
		
			Map<String, Object> map = user.getAttributes();
			
			Iterator<String> it = map.keySet().iterator();
			
			while( it.hasNext()) {
				String key = it.next();
				log.error("Key :: {} ",key);
				log.error("Value :: {} ",map.get(key));
			}
			
			HashMap<String, Object> m = (HashMap<String, Object>) map.get("properties");
			log.error("NickName {} :: ",m.get("nickname"));
			
			
			
			MemberVO memberVO = new MemberVO();
			memberVO.setUsername(m.get("nickname").toString());
			memberVO.setAttributes(m);
			
			List<RoleVO> roleVOs = new ArrayList<>();
			RoleVO roleVO = new RoleVO();
			roleVO.setRoleName("ROLE_MEMBER");
			roleVOs.add(roleVO);
			
			memberVO.setRoleVOs(roleVOs);
			
			memberVO.setEnabled(true);
			
			

			
			return memberVO;
	}
		
}
