package com.iu.base.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.FixedRateTask;
import org.springframework.stereotype.Component;

import com.iu.base.board.member.MemberDAO;
import com.iu.base.board.member.MemberVO;
import com.iu.base.board.notice.NoticeDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TestSchedule {
	
	@Autowired
	 private MemberDAO memberDAO;
	@Autowired
	 private NoticeDAO noticeDAO;
	@Autowired
	 private MailManager mailManager;
	
//	@Scheduled(cron = "*/10 * * * * *")	
	public void test() throws Exception {
		
//		List<MemberVO> ar = memberDAO.get
//		
		
		
		List<MemberVO> ar = memberDAO.getMemberList();
//		memberDAO.setEnabled();
//		noticeDAO.setBirth();
		
		for(MemberVO memberVO :ar ) {
			log.error("========== ID : {}  ========",memberVO.getUserName());
		}
		
		
		
	}
}
