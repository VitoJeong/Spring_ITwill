package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;

public interface BoardService {
	// 사용자 요구사항을 처리하는 비지니스 계층
	// -> 컨트롤러 - DAO 연결
	// -> 컨트롤러의 영속계층(DAO/persistence) 종속적인 상황을 방지
	
	// 글 쓰기 (regist)
	public void regist(BoardVO board) throws Exception;
	
	// 글 목록 가져오기
	public List<BoardVO> listAll() throws Exception;
	
	// 글 읽기 (read)
	public BoardVO read(Integer bno) throws Exception;
	
	// 글 삭제(delete)
	public void remove(Integer bno) throws Exception;
	
	// 글 수정(update)
	public void modify(BoardVO board) throws Exception;
	
	//글 페이징 처리 (Criteria 객체 사용)
	public List<BoardVO> listCri(Criteria cri) throws Exception;
}
