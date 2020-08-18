package com.itwillbs.persistence;


import java.util.List;

import com.itwillbs.domain.MemberVO;

public interface MemberDAO {
	
	// DB를 사용해서 현재 시간정보 출력
	public String getTime();
	
	
	// g회원 가입 처리 메서드
	public void insertMember(MemberVO vo);
	
	// ID에 해당하는 회원정보 모두를 가져오는 메서드
	public MemberVO getMember(String id);
	
	
	// ID/PW에 해당하는 회원정보를 가져오는 메서드
	public MemberVO getMemberWithIdPw(String id, String pw);
	
	// 회원정보 수정 처리 메서드
	public void updateMember(MemberVO vo);

	// 회원정보 삭제 처리 메서드
	public int deleteMember(MemberVO vo);
	
	// 회원목록 출력 처리
	public List<MemberVO> getMemberList();
	
}
