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
   private String kind;
   private String contents;
   private String writer;
   private String search;
   private Long totalPage;
   //한블럭당 출력할 번호의 갯수
   private Long perBlock = 10L;
   //table에서 조회할 끝번호 
   private Long lastRow;
   private Long startNum;
   private Long lastNum;
   //다음 블럭 유무
   private boolean after; 
   //이전 블럭 유무
   private boolean before;
   
   
   //시작 index 번호를 계산하는 메서드
   public void makeStartRow() {
      //page = 1, startRow = 0
      //page = 2, startRow = 10
      //page = 3, startRow = 20
      this.startRow = (this.getPage()-1)*this.getPerPage();
   }
   
   //startRow, lastRow 계산 하는 메서드
      public void makeRow() {
      this.startRow = (this.getPage()-1)*this.getPerPage()+1;
      this.lastRow = this.getPage()*this.getPerPage();
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
   

   
   public  void makeNum(Long totalCount) {
   //1. 전체 글의 갯수를 받아옴
      
   //2. 전체 글의 갯수로 전체 페이지 갯수 구하기
      this.totalPage = totalCount/this.getPerPage();
      if (totalCount%this.getPerPage() !=0 ) {
         totalPage+=1;
      }
      if(this.getPage()>totalPage) {
         this.setPage(totalPage);
      }
      
   //3. 전체 페이지로 전체 블럭의 갯수 구하기
      Long totalBlock = totalPage / this.getPerBlock();
      if(this.totalPage % this.getPerBlock() !=0 ) {
         totalBlock++;
      }
   //4. page 번호로 현재 블럭 번호 구하기
      Long curBlock = this.getPage() /this.getPerBlock();
      if(this.page % this.getPerBlock() != 0) {
         curBlock++;
      }
   //5. 현재 블럭 번호로 시작 번호와 끝번호 구하기
      this.startNum = (curBlock-1)*this.getPerBlock()+1;
      this.lastNum = (curBlock*this.getPerBlock());
   //6. 현재 블럭 번호가 마지막 블럭 이라면 끝번호는 전체 페이지 번호와 같음
      this.after=true;
      if(curBlock==totalBlock) {
         lastNum = totalPage;
         this.after=false;
      }
   //7. 이전 블럭, 다음 블럭 가능한지 유무 
      if(curBlock==1) {
         this.before=true;
      }
   }
   
   
}