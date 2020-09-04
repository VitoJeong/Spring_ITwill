package com.itwillbs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

// @ControllerAdvice : 해당클래스 컨트롤러에서 발생하는 모든 예외를 처리

@ControllerAdvice
public class CommonExceptionAdvice {
	
	// AOP - advice : 구현하고자하는 동작의 실체(클래스)
	
	private static final Logger logger = 
			LoggerFactory.getLogger(CommonExceptionAdvice.class);
	
	// 예외 처리하는 동작구현
	// @ExceptionHandler(Exception.class)
	public String common(Exception e) {
		logger.info(e.toString());
		
		return "error_common";
	}
	
	// 예외처리시 정보 가지고 페이지 이동
	@ExceptionHandler(Exception.class)
	public ModelAndView errorModelAndView(Exception e) {
		
		// ModelandView : @ControllerAdvice 사용하는 클래스는
		// 전달인자로 Exception 계열의 객체들만 사용가능 (view 페이지에 정보전달 x)
		// 그래서 하나의 객체 안에 Model데이터와 view페이지 설정을
		// 한 번에 처리하는 객체
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/error_common"); // view 페이지 정보 저장
		mav.addObject("e",e);
		
		return mav;
	}
	
}
