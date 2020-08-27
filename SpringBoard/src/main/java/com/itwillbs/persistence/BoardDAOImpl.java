package com.itwillbs.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardVO;

// BoardDAO 역할을 할 수 있도록 지정
@Repository
public class BoardDAOImpl implements BoardDAO {

	// BoardDAO 인터페이스와 약한결합을 사용
	// -> DB와 연결 실행 동작을 처리
	// -> DAO -> MyBatis(XML) -> Mysql
	
	// 정보를 로그형태로 출력
	private static Logger logger =
			LoggerFactory.getLogger(BoardDAOImpl.class);
	
	// DB 연결 객체 (SqlSession)
	@Inject
	private SqlSession session;
	
	// 매퍼를 구분하는 값
	private static final String NAMESPACE="com.itwillbs.mapper.BoardMapper";
		
	
	@Override
	public void create(BoardVO vo) throws Exception {
		// DAO구현 -> Mapper에 접근해서 사용되는 SQL구문 실행
		logger.info("DAO-create() 실행");
		logger.info("SqlSession 객체 사용 접근");
		logger.info("boardMapper.xml 이동해서 해당 SQL구문 실행");
		
		session.insert(NAMESPACE+".create", vo);
		
		logger.info("SQL 구문 실행 완료!");
		// 서비스 객체로 이동
		logger.info("테스트 객체로 이동 (서비스 계층 역할)");
		
	} // create() 끝


	@Override
	public BoardVO read(BoardVO vo) throws Exception {
		logger.info("DAO-read() 실행");
		logger.info("SqlSession 객체 사용접근");
		logger.info("boardMapper.xml 이동해서 해당 SQL구문 실행");
		
		BoardVO board = session.selectOne(NAMESPACE+".read", vo);
		
		logger.info("SQL 구문 실행 완료!");
		logger.info("결과"+board);
		logger.info("테스트 객체로 이동(서비스 계층 역할)");
		
		return board;
	} // read() 끝


	@Override
	public void update(BoardVO voNew) throws Exception {
		logger.info("DAO-update() 호출");
		logger.info("SqlSession 객체 사용 Mapper 접근");
		
		session.update(NAMESPACE+".update", voNew);
		
		logger.info("Mapper - 정보 수정 완료");
		logger.info("서비스 계층 이동(Test)");
		
		
	}


	@Override
	public void delete(BoardVO vo) throws Exception {
		logger.info("DAO - delete() 호출");
		logger.info("SqlSession 객체 사용 Mapper 접근");
		
		session.delete(NAMESPACE+".delete", vo);
		
		logger.info("Mapper - 글 작제 완료");
		logger.info("서비스 계층 이동(Test)");
		
	}


	@Override
	public List<BoardVO> listAll() throws Exception {
		
		logger.info("DAO - listAll() 호출");
		
		// SqlSession 객체 사용 - selectList()
		// session.selectList(statement, parameter);
		List<BoardVO> bList 
			= session.selectList(NAMESPACE+".listAll");
		
		logger.info("DAO - SQL 실행완료");
		
		
		return bList;
	}
	
	
	
	
	
	
} // BoardDAOImpl 클래스 끝



