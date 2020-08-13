package com.itwillbs.test;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

// 스프링 테스트로 사용할 수 있도록 설정
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
public class MemberDAOTest {
	
	// 컨트롤러/뷰에서 호출 역할
	
	// DB 처리 객체 - 의존 주입(DI)
	@Inject
	private MemberDAO mdao;
	
	// 테스트 동작
	// DB 시간정보 호출 메서드
	
	// @Test : 있어야지만 반드시 Junit을 실행가능
	@Test
	public void testTime() throws Exception{
		System.out.println("Di를 사용해서 MemberDAO 생성완료!");
		
		System.out.println("DAO동작 호출");
		String value = mdao.getTime();
		
		System.out.println("결과 : " + value);
		
		System.out.println("DAO 처리 완료!");
	}
	
	// 회원 가입 처리하는 동작(테스트)
	@Test
	public void testInsertMember() throws Exception{
		// MemberDAO 회원가입 메서드 호출
		
		// 회원 객체 생성
		MemberVO kim = new MemberVO();
		kim.setUserid("vito21");
		kim.setUserpw("1234");
		kim.setUsername("정창하");
		kim.setEmail("ckdghk@itwill.co.kr");
		
		System.out.println("회원 객체 생성 완료");
		
		System.out.println("DAO 객체 사용해서 회원가입 메서드 호출");
		
		// mdao.insertMember(kim);
		
		System.out.println("회원가입 테스트 완료!");
	}
	
	@Test
	public void getMember() throws Exception {
		
		System.out.println("@@@@ TEST : 회원 정보 호출 실행 @@@@");
		
		MemberVO vo = mdao.getMember("admin");
		
		System.out.println("@@@@ TEST : DAO 호출 완료! 결과 확인 @@@@");
		
		System.out.println(vo);
		
	}
	
	@Test
	public void getMemberWithIDPW() throws Exception {
		
		System.out.println("@@@@ TEST : ID,PW 사용 본인정보 호출 @@@@");
		System.out.println("@@@@ TEST : DAO 객체 사용접근 (의존주입) @@@@");
		System.out.println("@@@@ TEST : DAO안의 처리 메서드 호출 @@@@");
		
		MemberVO vo 
				= mdao.getMemberWithIdPw("admin", "12");
		
		System.out.println("@@@@ TEST : DAO에서 처리 완료! @@@@");
		System.out.println("@@@@ TEST : 결과 확인");
		System.out.println(vo);
	}
}
