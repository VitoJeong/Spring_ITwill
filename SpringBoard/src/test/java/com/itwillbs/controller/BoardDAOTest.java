package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.persistence.BoardDAO;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}	
)
public class BoardDAOTest {
	// Junit사용
	// BoardDAO 연결상태 테스트
	
	// 정보를 로그형태로 출력
	private static Logger logger = 
			LoggerFactory.getLogger(BoardDAOTest.class);
	
	// BoardDAO 객체 생성(x) -> 의존 주입(DI)
	@Inject
	private BoardDAO bdao;
	
	@Test
	public void TestDAO() throws Exception{
		logger.info("BoardDAO 객체 : "+bdao );
	}
	
	// 글쓰기 테스트
	@Test
	public void CreateTest() throws Exception{
		logger.info("Test_CreateTest() 실행");
		
		logger.info("글 정보 생성");
		// 글정보 저장하는 객체 생성
		BoardVO vo = new BoardVO();
		
		// 데이터 추가
		vo.setTitle("2번 테스트 글");
		vo.setContent("안녕하세요 테스트입니다!");
		vo.setWriter("사용자");
		
		// DAO 객체 - 메서드 호출
		logger.info("DI처리된 객체를 사용해서 메서드 호출");
		//bdao.create(vo);
		logger.info("글쓰기 완료");
		
	}
	
	
	// 글 정보를 확인동작 처리 (글 번호)
	// -> 1번 글의 정보를 가져와서 확인
	//@Test
	public void ReadTest() throws Exception{
		
		logger.info("DI 객체 (bdao) 사용 글 읽어오는 메서드 실행");
		
		// 글정보 읽어오는 객체 생성
		BoardVO vo = new BoardVO();
		
		// 데이터 추가
		//vo.setBno(1);
		
		// DAO 객체 - 메서드 호출
		logger.info("DI처리된 객체를 사용해서 메서드 호출");
		bdao.read(vo);
		logger.info("글 읽기 완료");
		
	}
	
	// 글정보 수정하기
	//@Test
	public void UpdateTest() throws Exception{
		
		logger.info("Test-UpdateTest() 호출");
		logger.info("bdao 객체를 사용해서 update() 호출");
		
		BoardVO newVO = new BoardVO();
		newVO.setBno(2);
		newVO.setTitle("앙 수정띄!");
		newVO.setContent("오져따리 ㅋㅋ");
		newVO.setWriter("사용자2");
		
		//bdao.update(newVO);
		
		logger.info("글 정보 수정완료~");
		
		
	}
	
	// 글 삭제하기
	//@Test
	public void DeleteTest() throws Exception {
		
		logger.info("Test-DeleteTest() 호출");
		logger.info("bdao 객체를 사용해서 delete() 호출");
		
		BoardVO vo = new BoardVO();
		
		vo.setBno(3);
		
		bdao.delete(vo);
		
		logger.info("글 삭제완료");
		
	}
	
	// 글 목록보기
	// @Test
	public void ListAllTest() throws Exception {
		
		logger.info("Test-ListAllTest() 호출");
		
		List<BoardVO> boardList = bdao.listAll();
		
		logger.info(boardList+"");
		
	}
	
	// 글목록 10개씩 처리 동작
	// @Test
	public void TestListPage() throws Exception{
		// Test파일 -> DAO -> Mapper -> DB
		
		// DI를 사용해서 DAO 객체 호출
		List<BoardVO> boardList = bdao.listPage(2);
		
		for(BoardVO vo : boardList) {
			System.out.println(vo.getBno()+"---"+vo.getTitle());
		}
	}
	
	// 객체를 사용해서 페이징 처리
	@Test
	public void TestListCri() throws Exception{
		
		// 페이징처리 객체 생성 초기화
		Criteria cri = new Criteria();
		cri.setPage(2);
		cri.setPageSize(5);
		
		// DAO이동 후 정보를 처리
		List<BoardVO> boardList = bdao.listPage(cri);
		
		// 정보 출력
		for(BoardVO vo: boardList) {
			logger.info(vo.getBno()+"---"+vo.getTitle());
		}
	}
	
}





