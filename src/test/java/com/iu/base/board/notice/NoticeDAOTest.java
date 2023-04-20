package com.iu.base.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.iu.base.board.BoardVO;

@SpringBootTest
@Rollback(true)
class NoticeDAOTest {
	
	@Autowired
	private NoticeDAO noticeDAO;
	
//	@Test
	void setInsertTest()throws Exception{
		
		
	for(int i=0;i<120;i++) {
		BoardVO boardVO = new BoardVO();
		
		boardVO.setId("kim"+i);
		boardVO.setTitle("insert Test"+i);
		boardVO.setContents("insert Test"+i);
		
		int result = noticeDAO.setInsert(boardVO);
		
		assertEquals(1, result);
	
		
		if(i%10==0) {
			Thread.sleep(500);
		}
		
	}
	}
//	
//	@Test
//	void setUpdateTest()throws Exception{
//		BoardVO boardVO = new BoardVO();
//		
//		
//		boardVO.setTitle("Update Test");
//		boardVO.setContents("Update Test");
//		boardVO.setNum((long) 1);
//		
//		int result = noticeDAO.setUpdate(boardVO);
//		
//		assertEquals(1,result);
//	}
	
//	@Test
//	void setDeleteTest()throws Exception{
//		BoardVO boardVO = new BoardVO();
//		
//		boardVO.setNum((long) 1);
//		
//		int result = noticeDAO.setDelete(boardVO);
//		
//		assertEquals(1,result);
//	}
	
	

}
