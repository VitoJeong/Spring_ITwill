package com.itwillbs.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.persistence.BoardDAO;
import com.itwillbs.persistence.BoardDAOImpl;

// @Service 추가
// BoardService 역할을 처리

@Service
public class BoardServiceImpl implements BoardService{
	
	// BoardDAO 객체 의존 주입(DI)
	@Inject
	private BoardDAO bdao;
	
	// 정보를 로그형태로 출력
	private static Logger logger =
			LoggerFactory.getLogger(BoardServiceImpl.class);
		
	@Override
	public void regist(BoardVO board) throws Exception {
		// 컨트롤러 <-> 서비스 <-> DAO <-> MyBatis(Mysql)
		logger.info("컨트롤러에서 서비스 요청(regist)");
		logger.info("DAO 정보 전달해서 DB저장");
		
		bdao.create(board);
		
		logger.info("글쓰기 완료! 컨트롤러로 페이지 이동");
		
	}

	@Override
	public List<BoardVO> listAll() throws Exception {

		logger.info("컨트롤러에서 호출 서비스(listAll()) 실행");
		logger.info("DAO 객체(DI)로 이동해서 해당 동작 메서드 호출");
		
		List<BoardVO> bList = bdao.listAll();
		
		logger.info("service - DB값 저장해서 Controller로 이동");
		
		return bList;
		
		// return bdao.listAll();
	}

	@Override
	public BoardVO read(Integer bno) throws Exception {
		
		logger.info("컨트롤러에서 서비스 호출(read)");
		
		BoardVO vo = new BoardVO();
		vo.setBno(bno);
		
		logger.info("SQL구문 실행완료 -> 컨트롤러 이동");
		
		return bdao.read(vo);
	}

	@Override
	public void remove(Integer bno) throws Exception {
		
		logger.info("컨트롤러에서 서비스 객체 호출");
		logger.info("서비스에서 DAO 객체로 이동");
		
		BoardVO vo = new BoardVO();
		vo.setBno(bno);
		
		bdao.delete(vo);
		
		logger.info("삭제완료 후 컨트롤러로 이동");
		
	}

	@Override
	public void modify(BoardVO board) throws Exception {
		
		logger.info("컨트롤러에서 서비스 요청");
		logger.info("DAO 객체를 주입 받아서 처리");
		
		bdao.update(board);
		
		logger.info("정보 수정완료");
		logger.info("컨트롤러로 페이지 이동");
		
	}

	
	
	
}
