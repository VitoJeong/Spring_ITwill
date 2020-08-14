package com.itwillbs.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

// @Service : 스프링이 해당파일을 MemberService객체로 인식하도록 처리

@Service
public class MeberServiceImpl implements MemberService{
	
	// DB에 접근이 가능
	// MemberDAO 객체 생성 -> 객체 의존주입
	@Inject
	private MemberDAO mdao;
	
	@Override
	public void insertMember(MemberVO vo) {

		System.out.println("@@@@ Service : DAO연결을 위해서 객체 주입");
		System.out.println("@@@@ Service : 회원가입을 하기위해서 DAO 처리 메서드 호출");
		
		mdao.insertMember(vo);
		
		System.out.println("@@@@ Service : DAO처리 완료 서비스처리 끝");
		System.out.println("@@@@ Service : 컨트롤러 페이지로 이동");
	
		
	}

	@Override
	public MemberVO loginCheck(MemberVO vo) {

		System.out.println("@@@@ Service: controller에서 로그인 체크 호출");
		System.out.println("@@@@ Service: 호출시 로그인 체크 정보를 가져옴");
		System.out.println("@@@@ Service: "+vo);
		
		// 로그인 체크 기능이 있는 DAO 객체로 이동
		MemberVO DBvo = 
				mdao.getMemberWithIdPw(vo.getUserid(), vo.getUserpw());
		
		System.out.println("@@@@ Service: DAO 처리 완료 후 정보 저장 완료");
		System.out.println("@@@@ Service: "+ DBvo);
		System.out.println("@@@@ Service: controller 페이지로 이동");
		
		return DBvo;
	}
	
	
	
	
	
}