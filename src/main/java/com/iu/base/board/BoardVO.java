package com.iu.base.board;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class BoardVO {

	private Long num;
	@NotBlank
	private String id;
	@NotBlank
	@Size(min = 5, max = 20)
	private String title;
	private String contents;
	private Date datecreated;
	private Long hit;
	private SubVO subVO;
//	private String [] names;
	private List<String> names;
	
	private List<BoardFileVO> boardFileVOs;
}
