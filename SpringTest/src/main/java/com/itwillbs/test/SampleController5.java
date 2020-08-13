package com.itwillbs.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.domain.ITWILLBean;

@Controller
public class SampleController5 {
	
	// 로그로 출력하기 위한 준비
	private static final Logger logger =
			LoggerFactory.getLogger(SampleController4.class);
	
	// JSON데이터 처리가 쉬움
	@RequestMapping("/doJson")
	public @ResponseBody ITWILLBean doJson() {
		// @ResponseBody : 일반 객체를 Json 타입으로 변경해서 리턴
		// * 상용브라우저들은 Json파서를 포함하고있음
		logger.info("/doJson 페이지 요청으로 doJson() 호출");
		
		// Json 객체를 만들 데이터 생성
		ITWILLBean member =
				new ITWILLBean("홍길동", "010-2321-2332",30);
		
		return member;
		
	}
}
