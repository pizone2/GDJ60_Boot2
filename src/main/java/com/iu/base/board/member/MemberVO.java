package com.iu.base.board.member;

import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO {

	private String userName;
	private String password;
	private String name;
	private String email;
	private Date birth;
	private Boolean enabled;
	private List<RoleVO> roleVOs;
	
}
