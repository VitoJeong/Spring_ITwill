package com.itwillbs.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// @Controller - 일반 클래스가 어노테이션을 사용해서 컨트롤러 역할로 사용

@Controller
public class SampleController1 {
	
	// 해당파일에 로그를 자겅하는 객체를 준비
	private static final Logger logger =
			LoggerFactory.getLogger(SampleController1.class);
	
	@RequestMapping("/doA")
	public void doA() {
		logger.info("doA() 메서드 호출!!!!!!");
		
	}
	
	// doB() 생성해서 페이지 호출
	@RequestMapping("/doBB")
	public void doB() {
		logger.info("doB() 메서드 호출@@@@@@");
		
	}
	
	// * 컨트롤러 안에서 동작을 메서드로 구현
	// 메서드가 리턴타입이 void일 경우 해당 경로에 해당하는 jsp페이지 호출
	// ex) http://localhost:8080/test/doBB
	//		-> doBB.jsp페이지 호출
	
	// /doB1 주소 호출을 통해서
	// doB1()메서드 구현 -> doB1.jsp 페이지
	@RequestMapping("/doB1")
	public void doB1() {
		logger.info("doB1() 메서드 호출~~~~~~");
		
	}
}
