package com.itwillbs.test;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration
// -> 스프링 테스트 처리

// @WebAppConfiguration
// -> 스프링MVC 테스트 처리

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"}
		)
public class SampleControllerTest {

	// 테스트를 사용해서 컨트롤러를 구현(was없이 컨트롤러 실행)
	private static final Logger logger =
			LoggerFactory.getLogger(SampleControllerTest.class);
	
	// 객체 의존 주입
	@Inject
	private WebApplicationContext wac;
	
	// MVC구조 테스트 객체 : 가상의 응답, 요청을 처리하는 객체
	private MockMvc mockMvc;
	
	// @Before : 테스트 전에 실행될 동작
	
	@Before
	public void sutUp() {
		// 테스트 하기전에 미리 필요한 객체/ 정보들을 준비 단계
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		logger.info(" MockMvc 객체가 생성완료! MVC 테스트 준비 끝");
		
	}
	
	@Test
	public void testDoA() throws Exception {
		
		logger.info("테스트 시작(MVC패턴 구조를 테스트)");
		
		// 해당 객체를 사용해서 GET방식으로 페이지 호출
		mockMvc.perform(MockMvcRequestBuilders.get("/doA"));
		
		logger.info("테스트 완료, 페이지 호출 완료");
	}
}
