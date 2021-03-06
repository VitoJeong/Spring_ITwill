package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.MemberVO;

public interface MemberService {

	// 회원가입 처리
	public void insertMember(MemberVO vo);
	
	// 로그인 처리
	public MemberVO loginCheck(MemberVO vo);
	
	// 회원정보 가져오는 처리(본인)
	public MemberVO getMember(String id);
	
	// 회원정보 수정 처리
	public void updateMember(MemberVO vo);
	
	// 회원정보 삭제 처리
	public void deleteMember(MemberVO vo);
	
	// 회원목록 출력 처리
	public List<MemberVO> getMemberList();
	
}




