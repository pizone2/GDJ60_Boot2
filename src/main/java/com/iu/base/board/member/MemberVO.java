package com.iu.base.board.member;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO {
	@NotBlank
	private String userName;
	@NotBlank
	private String password;
	
	private String passwordCheck;
	@NotBlank
	private String name;
	@Email
	private String email;
	@Past
	private Date birth;
//	@NotBlank
//	private String passwordCheck;
	private Boolean enabled;
	private List<RoleVO> roleVOs;
	
}
