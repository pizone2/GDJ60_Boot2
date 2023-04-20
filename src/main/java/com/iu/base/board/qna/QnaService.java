package com.iu.base.board.qna;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.iu.base.board.BoardFileVO;
import com.iu.base.board.BoardService;
import com.iu.base.board.BoardVO;
import com.iu.base.util.FileManager;
import com.iu.base.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class QnaService implements BoardService {
	@Autowired
	private QnaDAO qnaDAO;
	
	@Value("${app.upload.qna}")
	private String path;
	
	@Autowired
	private FileManager fileManager;

	
	public List<BoardVO> getList(Pager pager) throws Exception {		
		pager.makeStartRow();
		pager.makeNum(qnaDAO.getTotalCount(pager));
		return qnaDAO.getList(pager);
	}

	
	public BoardVO getDetail(QnaVO qnaVO) throws Exception {
		// 
		return qnaDAO.getDetail(qnaVO);
	}

	@Override
	public BoardFileVO getFileDown(BoardFileVO boardFileVO) throws Exception {
		// TODO Auto-generated method stub
		return qnaDAO.getFileDown(boardFileVO);
	}
	

	
	
	public int setInsert(QnaVO qnaVO,MultipartFile[] multipartFiles) throws Exception {
		int result =qnaDAO.setInsert(qnaVO);
			result = qnaDAO.setInsertRef(qnaVO);
		log.error("Num ==========> {}",qnaVO.getNum());
		
//		Random random = new Random();
//		int num = random.nextInt(1);
//		
//		if(num == 0) {
//			throw new Exception();
//		}
//		
		if(multipartFiles != null) {
			for(MultipartFile multipartFile:multipartFiles) {
//				multipartFile.isEmpty() 비어있다면
				if(!multipartFile.isEmpty()) {
					String fileName = fileManager.saveFile(path, multipartFile);
					BoardFileVO boardFileVO = new BoardFileVO();
					boardFileVO.setFileName(fileName);
					boardFileVO.setOriName(multipartFile.getOriginalFilename());
					boardFileVO.setNum(qnaVO.getNum()); //durlddd
					result = qnaDAO.setFileInsert(boardFileVO);
					
				}
			}
		}
		
		return result; //qnaDAO.setInsert(boardVO);
	}

	@Override
	public int setUpdate(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setDelete(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setInsert(BoardVO boardVO, MultipartFile[] multipartFiles) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public BoardVO getDetail(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
