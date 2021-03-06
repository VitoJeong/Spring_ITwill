package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;

public interface BoardDAO {
	// 게시판 처리동작 선언 -> BoardDAOImpl 동작구현
	
	// 글 쓰기(Create)
	public void create(BoardVO vo) throws Exception;
	
	// 글 읽기(Read)
	public BoardVO read(BoardVO vo) throws Exception;
	
	// 글 수정(Update)
	public void update(BoardVO voNew) throws Exception;
	
	// 글 삭제(Delete)
	public void delete(BoardVO vo) throws Exception;
	
	// 글 목록 가져오기(List)
	public List<BoardVO> listAll() throws Exception;
	
	// 글 목록 10개씩 가져오기
	public List<BoardVO> listPage(int page) throws Exception;
	
	// 글 목록 10개씩 가져오기 (객체 사용)
	public List<BoardVO> listPage(Criteria cri) throws Exception;
	
}
