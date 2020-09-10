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
import com.itwillbs.domain.Criteria;
import com.itwillbs.domain.PageMaker;
import com.itwillbs.service.BoardService;

// @RequestMapping("/board/*")
// @RequestMapping(value="/board/*")
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
	public void readGET(@RequestParam("bno") int bno,Model model) throws Exception {
		
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
		
	}
	
	// http://localhost:8082/board/remove
	// 글 삭제 동작
	@RequestMapping(value="/remove", method = RequestMethod.POST)
	public String removePOST(int bno, RedirectAttributes rttr) throws Exception{
		
		logger.info("/remove 호출(삭제처리)");
		
		// 삭제 -> 서비스 -> DAO -> DB 삭제
		// 삭제할 글 번호
		logger.info("삭제할 글번호 : "+ bno);
		
		// 서비스에 삭제 처리하는 메서드 호출
		service.remove(bno);
		
		// 페이지 이동
		rttr.addFlashAttribute("result", "delOk");
		
		return "redirect:/board/listAll";
	}
	
	
	// 글 수정하기(modify)
	// http://localhost:8082/board/modify?bno=9
	@RequestMapping(value="/modify", method = RequestMethod.GET)
	public void modifyGET(@RequestParam("bno") int bno, Model model) throws Exception{
		
		logger.info("/board/modify -> /board/modify.jsp 이동");
		// 전달받은 파라미터 값 저장
		logger.info(" 수정할 글 번호 : " + bno + " 번 글");
		
		// DB에서 수정할 정보를 가지고 와야함(Model 객체 사용)
		// 정보 저장 -> View 페이지로 이동
		
		// DB에서 글 정보를 가져오기(글번호) -> 서비스 기능 호출
		BoardVO vo = service.read(bno);
		
		// DB -> 컨트롤러 정보전달 완료
		// 저장된 정보 가지고 View페이지 이동
		model.addAttribute("boardVO", vo);
		
	}
	
	// http://localhost:8082/board/modify?bno=9
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modifyPOST(BoardVO vo,RedirectAttributes rttr) throws Exception{
		
		logger.info("/modify (get) -> /modify (post) 호출");
		logger.info("vo : " + vo);
		
		// 수정할 정보를 받아서 (저장) -> 서비스 -> DAO -> Mapper
		service.modify(vo);
		
		rttr.addFlashAttribute("result", "modifyOK");
		
		return "redirect:/board/listAll";
	}
	
	// http://localhost:8082/board/listCri
	// http://localhost:8082/board/listCri?page=2
	// http://localhost:8082/board/listCri?pageSize=5
	// http://localhost:8082/board/listCri?page=2&pageSize=5
	
	// 전달되는 파라미터 값들이 메서드 전달인자에 저장 처리(set메서드)
	
	@RequestMapping(value="/listCri", method=RequestMethod.GET)
	public void listCri(Model model,Criteria cri) throws Exception{
		// 전달인자 Criteria 객체를 사용해서 기본값으로 페이징 처리
		logger.info("C : /listCri 호출");
		
		// 서비스 호출 -> DAO -> Mapper -> DB 처리 후
		List<BoardVO> boardList = service.listCri(cri);
		
		// -> 정보를 저장해서 view 페이지 이동 Model
		model.addAttribute("boardList", boardList);
		
	}
	
	// http://localhost:8082/board/listPage
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void listPage(Criteria cri, Model model) throws Exception{
		
		logger.info(" C: /listPage 호출 ");
		logger.info("cri : " + cri);
		
		// List<BoardVO> boardList = service.listCri(cri);
		// model.addAttribute("boardList", boardList);
		
		// 1) 페이징처리 (본문)
		// Controller -> Service -> DAO -> Mapper -> .... -> Controller -> View
		model.addAttribute("boardList", service.listCri(cri));
		
		// 2) 페이징처리 (하단)
		PageMaker pm = new PageMaker();
		
		pm.setCri(cri); // 외부에서 전달되는 정보(파라미터값 page, pageSize)
		// SELECT count(*) FROM tbl_board; 사용 계산
		// -> DB쿼리 사용변경 에정
		pm.setTotalCount(12);
		
		// 페이징 처리 정보를 model 객체에 저장 -> view 페이지로 이동
		model.addAttribute("pm", pm);
		
	}
	
}



