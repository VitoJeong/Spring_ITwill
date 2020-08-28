package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.service.BoardService;

// @RequestMapping("/board/*")
// board로 시작되는 모든 주소에 대한 처리
// -> 컨트롤러 클래스에  사용되는 RequestMapping의미는 컨트롤러 구분목적


@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	// 서비스 호출 -> DAO
	// 서비스 객체 주입(DI)
	@Inject
	private BoardService service;
	
	// 로그 객체
	private static final Logger logger = 
			LoggerFactory.getLogger(BoardController.class);
	
	// 글쓰기 동작 - 입력 -> view 페이지 이동
	// method = {RequestMethod.GET, RequestMethod.POST}
	// - > 두 가지 방식 모두 처리 가능
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET() throws Exception{
		logger.info(" /register 호출 -> register.jsp로 이동");
		logger.info("submit() 사용해서 정보를 전달 ");
		
	}
	
	
	// 글쓰기 동작 - 처리
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(BoardVO vo, Model model, RedirectAttributes rttr) throws Exception{
		// 메서드 전달인자는 상황에 따라서 get()/set() 역할을 처리
		// jsp -> 컨트롤러(정보전달) 스프링이 해당객체를 생성후 정보를 자동 전달
		
		// Model 객체는 스프링에서 제공하는 데이터 전달 객체
		// (키, 값) 쌍으로 데이터 저장 -> 전달받는 jsp페이지에서 el표현식으로 사용가능
		
		logger.info("/register post 요청");
		logger.info("전달 데이터 : "+vo);
		
		// DB 글쓰기 동작(서비스를 통해서 DAO이동)
		// DI 주입된 서비스 객체를 사용해서 글쓰기 동작
		service.regist(vo);
		
		logger.info("서비스 처리 완료(글 등록완료)");
		
		// model 객체에 정보를 저장해서 페이지 이동
		//model.addAttribute("result", "SUCCESS!");
		// get 방식(redirect) : 해당 attribute 정보를 주소(URL)에 표시
		// post 방식 : 정보를 Body에 저장
		
		// RedirectAttribute 객체 사용 페이지이동
		rttr.addFlashAttribute("result", "SUCCESS@");
		
		/*
		 * 
		 * 데이터를 저장해서 View 이동객체
		 * 
		 * Model 객체 - addAtribute()
		 * - (key, value)쌍으로 데이터 저장
		 * - get : 주소에 보임, post : 주소에 안보임
		 * - F5(새로고침) : 정보 유지
		 * 
		 * RedirectAttributes 객체 - addFlashAttribute()
		 * - (key, value)쌍으로 데이터 저장
		 * - get : 주소에 안보임, post : 주소에 안보임
		 * - F5(새로고침) : 정보 1회만 사용후 사라짐
		 * 
		*/
		
		// return "/board/success";
		// 페이지 이동 (글 목록페이지)
		//return "/board/listAll";
		// 페이지이동 (화면전환-> 다른동작)
		return "redirect:/board/listAll";
	}
	
	// 글목록 동작(/board/listAll)
	// http://localhost:8082/board/listAll?result=SUCCESS%21
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAllGET(@ModelAttribute("result") String result,Model model) throws Exception{
		
		// @ModelAttribute("result") String result
		// -> model 객체에 있는 속성값을 가져오는 어노테이션
		// 변수에 저장 후 (변수값은 컨트롤러에서 사용)
		// 변수에 저장 후 -> 연결돼있는 view(jsp)에 전달(el 표현식 -> model 저장한 키값 호출)
		
		if(result.equals("SUCCESS!")) {
			logger.info("/register POST -> /listAll GET (리다이렉트)");
		} else {
			logger.info(" / listAll GET 호출");
		}
		
		// DB 글 목록을 가져와서 -> view(jsp) 전달 출력
		// model 객체 사용해서 전달
		// /board/listAll.jsp (표에 데이터 채우기)
		
		List<BoardVO> bList =  service.listAll();
		
		// 서비스에서 전달된 글목록 확인(출력)
		// logger.info("목록 : " + bList);
		
		// model 객체 사용 데이터 저장
		model.addAttribute("boardList", bList);
		
		
	}
	
	// 글 본문 보기 동작(/board/read)
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public String readGET(@RequestParam("bno") int bno,Model model) throws Exception {
		
		// @RequestParam("bno")
		// => request.getParameter() (문자열 타입)처럼 작동하는 어노테이션
		// -> 문자열, 숫자, 날짜 형 변환 가능
		
		
		logger.info(" /listAll -> /read GET 페이지 호출");
		logger.info("전달된 글번호: "+bno);
		
		// 글 번호에 해당하는 글정보 모두를 가져오기 
		// DB에 접근하기 위해 service 객체를 통한 접근시도
		BoardVO vo = service.read(bno);
		// 글정보를 전달받아 view 페이지(/board/read.jsp)로 이동
		logger.info(bno + "번 글정보 : " + vo);
		model.addAttribute("boardVO", vo);
		
		return "/board/read";
	}
	
	
}



