package com.itwillbs.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwillbs.domain.ITWILLBean;

@Controller
public class SampleController3 {
	
	// 해당 컨트롤러의 정보를 로그로 출력하기 위한 준비
	private static final Logger logger = 
			LoggerFactory.getLogger(SampleController3.class);
	
	// /doD 주소를 사용해서 데이터 전달하는 처리
	// name=홍길동 값을
	@RequestMapping("/doD")
	public String doD(@ModelAttribute("name") String name) {
		
		logger.info("/doD 주소를 통해서 itwillTest.jsp이동");
		//logger.warn("/doD 주소를 통해서 itwillTest.jsp이동");
		
		return "itwillTest";
	}
	
	/*
	컨트롤러 -> view 이동시 객체를 가지고 이동하기 위해서는
			Model 객체(뷰페이지 이동시 정보를 가지고 갈 객체)가 필요함
			
			Model 객체에 값 저장 1) addAttribute(객체);
							2) addAttribute("이름",객체);
							
			1) 로 저장해서 전달할 경우, 뷰페이지에서 ${"객체의 클래스타입(첫글자 소문자)".속성} 호출
			2) 로 저장해서 전달할 경우, 뷰페이지에서 ${"이름".속성} 호출
	 */
	
	// view 페이지로 객체를 전달
	// /doMember 주소 요청 -> itwillMember.jsp 생성
	// 객체에 들어있는 회원정보 출력
	@RequestMapping("/doMember")
	public String doMember(Model model) {
		// Model클래스 -> 스프링에서 제공되는 뷰에 데이터를 전달하는 컨테이너(상자)
		logger.info("/doMember 주소 요청");
		
		// 회원 객체 생성
		ITWILLBean user = new ITWILLBean("관리자", "010-1234-4455", 22);
		logger.info("ITWILLBean 객체를 생성 : "+ user);
		
		// 해당정보를 저장 -> view 페이지로 이동
		// 1) addAttribute(객체)
		model.addAttribute(user);
		// -> key값 없이 전달할 경우에는 뷰페이지에서
		//		전달받는 객체의 클래스 이름으로 호출(클래스 이름의 첫 글자를 소문자로 변경)
		
		// 2) addAttribute("이름",객체)
		// model.addAttribute("itwill", user);
		logger.info("model 객체에 정보를 저장하고 페이지 이동");
		
		return "itwillMember";
		
	}
	
	
	
}






