package com.iu.base.board;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class BoardVO {

	private Long num;
	private String id;
	private String title;
	private String contents;
	private Date datecreated;
	private Long hit;
}
