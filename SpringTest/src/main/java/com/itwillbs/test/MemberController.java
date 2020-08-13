package com.itwillbs.test;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.service.MemberService;


@Controller
@RequestMapping(value = "/member/*")
public class MemberController {
	
	private static final Logger logger 
		= LoggerFactory.getLogger(MemberController.class);
	
	// 서비스 계층 필요 -> 의존주입
	@Inject
	private MemberService service;
	
	// 동작 구현
	// http://localhost:8080/test/member/testCon
	@RequestMapping("/testCon")
	public void TestController() {
		logger.info("컨트롤러 테스트중");
		logger.info("@@@@ Controller : /testCon주소일때 처리(테스트용 회원가입)");
		
		// 회원 정보 생성(나중에는 View페이지에서 전달받음)
		MemberVO vo = new MemberVO();
		vo.setUserid("iuzoa");
		vo.setUserpw("1111");
		vo.setUsername("아이유조아");
		vo.setEmail("iuzoa@google.com");
		
		
		// 서비스 계층에 있는 동작중에서 회원가입 처리 호출
		// 결과 확인
		service.insertMember(vo);
		
		logger.info("@@@@ Controller : 회원정보 저장 완료!");
		
	}
	
	// 회원가입 정보를 입력 처리(GET) -> 화면
	// value -> 이동할 주소
	@RequestMapping(value = "/insert", method=RequestMethod.GET)
	public String insertGET() throws Exception{
		
		logger.info(" 회원강비 정보 입력창(view) 호출");
		logger.info("/member/insert (get) -> views/member/insertForm.jsp 이동");
		
		return "/member/insertForm";
	}
	
	
	// 회원 가입 처리동작(POST)
	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public void insertPOST(MemberVO vo) throws Exception{
		// 메서드 전달인자를 사용해서 페이지 이동시 정보를 가져올 수 있음
		// 입력받는페이지(view-jsp)에서 전달되는 데이터의 이름을
		// vo 객체의 변수면(컬럼명)으로 통일시켜서 이동시킨다.
		
		
		logger.info("컨트롤러에서 /insert주소에 POST방식");
		logger.info(vo+"");
		// 회원가입 처리 메서드 호출(서비스 계층)
		service.insertMember(vo);
		
		
		logger.info("회원가입완료.");
	}
	
}
