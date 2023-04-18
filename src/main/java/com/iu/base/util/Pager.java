package com.iu.base.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Pager {
	
	//page 번호 담을 변수
	private Long page;
	
	//한페이지에 보여줄 글의 갯수
	private Long perPage;
	
	//시작 index 번호
	private Long startRow;
	private Long lastRow;
	
	//검색 종류(사용할 컬럼)
	private String kind;
	
	// 검색어
	private String search;
	
	//한 블럭당 출력할 번호의 갯수
	private Long perBlock;
	
	// jsp 출력용
	//시작 page 번호
	private Long startNum;
	//끝 page 번호
	private Long lastNum;
	
	private Long totalPage;
	private boolean before;
	private boolean after;
	
	//시작 index 번호를 계산하는 메서드
	public void makeStartRow() {
		//page = 1, startRow = 0
		//page = 2, startRow = 10
		//page = 3, startRow = 20
		this.startRow = (this.getPage()-1)*this.getPerPage();
	}
	
	public Long getPage() {
		if(this.page== null || this.page == 0) {
			this.page=1L;
		}
		
		return this.page;
	}
	
	public Long getPerPage() {
		if(this.perPage== null || this.perPage == 0) {
			this.perPage=10L;
		}
		
		return this.perPage;
	}
	
//	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	public void makeNum(Long totalCount) {
		// 전체 row의 갯수
		
		// 총 페이지의 갯수
		totalPage = totalCount/this.getPerPage();
		if(totalCount % this.getPerPage() != 0) {
			totalPage+=1;
		}
		
		if(this.getPage() > totalPage) {
			this.setPage(totalPage);
		}
		
		// 한번에 보여줄 페이지 번호갯수
		
		
		// 한번에 보여줄 페이지 블럭 갯수
		Long totalBlock = (totalPage / this.getPerBlock());
		if(totalPage % this.getPerBlock() != 0) {
			totalBlock++;
		}
		
		// 현재 페이지 블럭 번호
		Long curBlock = this.getPage() / this.getPerBlock();
		if(this.page % perBlock != 0) {
			curBlock++;
		}
		
		// 현재 블럭 번호의 시작 번호와 끝 번호 계산
		this.startNum = ((curBlock-1) * this.getPerBlock()) + 1;
		this.lastNum = curBlock * this.getPerBlock();
		
		this.after=true;
		//현재블럭 번호가 마지막 블럭 이라면 끝번호는 전체 페이지 번호와 같음
		if(curBlock == totalBlock) {
			lastNum = totalPage;
			this.after=false;
		}
		
		if(curBlock == 1) {
			this.before = true;
		}
	}
//	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	public String getKind() {
		if(kind == null) {
			kind = "";
		}
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getSearch() {
		if(search == null) {
			search="";
		}
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public Long getPerBlock() {
		if(this.perBlock == null || this.perBlock < 1) {
			this.perBlock = (long) 5;
		}
		
		return perBlock;
	}
	public void setPerBlock(Long perBlock) {
		this.perBlock = perBlock;
	}
	// startRow lastRow 계산 하는 메서드
		public void makeRow() {
			this.startRow = (this.getPage()-1)*this.getPerPage() + 1;
			this.lastRow = this.getPage() * this.getPerPage();
		}
		public void setBefore(boolean before) {
			this.before = before;
		}
		public void setAfter(boolean after) {
			this.after = after;
		}
		
		public void setStartNum(Long startNum) {
			this.startNum = startNum;
		}
	
		public void setPerPage(Long perPage) {
			this.perPage = perPage;
		}
	
		public void setPage(Long page) {
			this.page = page;
		}
	
	
		public void setStartRow(Long startRow) {
			this.startRow = startRow;
		}
		public void setLastRow(Long lastRow) {
			this.lastRow = lastRow;
		}
		
	
}
