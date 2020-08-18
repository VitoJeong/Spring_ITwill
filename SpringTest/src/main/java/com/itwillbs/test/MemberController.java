package com.itwillbs.test;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	// http://localhost:8080/member/insertForm
	@RequestMapping(value = "/insert", method=RequestMethod.GET)
	public String insertGET() throws Exception{
		
		logger.info(" 회원가입 정보 입력창(view) 호출");
		logger.info("/member/insert (get) -> views/member/insertForm.jsp 이동");
		
		return "/member/insertForm";
	}
	
	
	// 회원 가입 처리동작(POST)
	// http://localhost:8080/test/member/login(x)
	// http://localhost:8080/member/login
	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public String insertPOST(MemberVO vo) throws Exception{
		// 메서드 전달인자를 사용해서 페이지 이동시 정보를 가져올 수 있음
		// 입력받는페이지(view-jsp)에서 전달되는 데이터의 이름을
		// vo 객체의 변수면(컬럼명)으로 통일시켜서 이동시킨다.
		
		
		logger.info("컨트롤러에서 /insert주소에 POST방식");
		logger.info(vo+"");
		// 회원가입 처리 메서드 호출(서비스 계층)
		service.insertMember(vo);
		
		
		logger.info("회원가입완료!");
		
		// 페이지 이동(로그인 페이지-> 컨트롤러 -> 뷰)
		// WEB-INF/views/member/login.jsp 이동
		return "redirect:/member/login";
	}
	
	
	// 로그인처리 (GET) 메서드
	// http://localhost:8080/test/member/login(x)
	// http://localhost:8080/member/login
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String loginGET() throws Exception{
		
		logger.info("로그인 메서드 loginGET() 실행");
		logger.info("view 페이지로 연결(/member/login.jsp)");
		
		
		logger.info("/member/login get -> /member/login.jsp 이동");
		
		return "/member/login"; 
		
	}
	
	// login처리 (POST)
	// http://localhost:8080/test/member/login(x)
	// http://localhost:8080/member/login
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginPOST(MemberVO vo,HttpSession session) throws Exception{
		logger.info("/member/login.jsp -> loginPOST() 호출 (아이디,비밀번호)");
		// 1) 아이디, 비밀번호 저장
		logger.info("전달 정보 : "+vo);
		// 2) 로그인여부 판별
		MemberVO DBvo = service.loginCheck(vo);
		logger.info("확인결과 : "+ DBvo);
		// 3) 로그인성공 - 아이디값 (세션) 0> main.jsp 이동
		//		로그인실패 - /test/member/login 이동
		
		// DBvo객체가 null인지 아닌지 판별
		if(DBvo == null) {
			return "redirect:/member/login";
		}
		
		// 로그인 성공
		// 세션객체 사용(login.jsp(get) 페이지에서 post방식으로 올때 내장객체를 가지고온다.)
		session.setAttribute("userid", DBvo.getUserid());
		
		// 페이지 이동
		// /member/main
		
		return "redirect:/member/main";
	}
	
	// 메인페이지 (/member/main (get))
	// http://localhost:8080/test/member/main(x)
	// http://localhost:8080/member/main
	@RequestMapping(value = "/main", method=RequestMethod.GET)
	public void mainGET(HttpSession session) throws Exception{
		
		logger.info("/member/main (get) -> /member/main.jsp");
		
		// 파라미터값 저장
		
		
		
	}
	
	// 로그아웃(/member/logout)
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logoutGET(HttpSession session) throws Exception{
		
		logger.info("/member/logout (get) -> /member/main");
		
		// 세션값을 초기화
		session.removeAttribute("userid");
		
		// main 페이지로 이동
		
		return "redirect:/member/main";
	}
	
	
	// 회원정보보기(/member/info)
	@RequestMapping(value="/info", method=RequestMethod.GET)
	public String infoGET(HttpSession session, Model model) throws Exception {
		
		logger.info("/member/info (get) -> /member/info.jsp");
		
		// 세션값(id) 가져오기
		String id = (String) session.getAttribute("userid");
		
		// 회원 정보 가져오기 (service -> DAO -> Mysql)
		MemberVO vo = service.getMember(id);
		logger.info("찾아온 회원 정보 : "+vo);
		
		// 가져온 회원정보를 저장해서 -> view(.jsp) 이동
		// Model 객체 사용(컨트롤러 -> 뷰 이동시 정보저장 공간)
		model.addAttribute("membervo", vo);
		
		
		//해당 jsp페이지로 이동
		return "/member/info";
	}
	
	// 회원정보 수정(/member/update)
	// http://localhost:8080/member/update
	@RequestMapping(value="/update", method = RequestMethod.GET)
	public void updateGET(HttpSession session, Model model) throws Exception{
		
		logger.info("updateGET() 호출");
		logger.info("/member/update get -> /member/update.jsp");
		
		// 세션값(id) 가져오기
		String id = (String) session.getAttribute("userid");
		
		// 서비스 -> DAO -> DB
		// 회원정보 모두를 가져오는 동작
		MemberVO vo = service.getMember(id);
		logger.info("찾아온 회원 정보 : "+vo);
		
		// 회원정보를 Model 객체에 담아서 view 페이지로 전달
		model.addAttribute("membervo", vo);
		
		
		// 페이지이동(void)
		// /member/update.jsp페이지로 이동
	}
	
	// 회원 정보 수정 처리 /member/update
	@RequestMapping(value="/member/update", method = RequestMethod.POST)
	public String updatePOST(MemberVO vo) {
		
		// /member/update.jsp 에서 입력받은 수정할 정보를 가져와서
		// DB로 이동(service -> DAO -> mapper -> Mysql)
		logger.info("/member/update.jsp submit -> /member/update POST");
		logger.info("vo(수정할정보) : " + vo);
		
		// 서비스 객체에 있는 정보 수정메서드 호출
		service.updateMember(vo);
		
		logger.info("service 처리 완료 (회원정보 수정완료)");
		
		// 수정완료 후 페이지 이동(main)
		
		return "redirect:/member/main";
	}
	
	
	// /member/delete
	// http://localhost:8080/member/delete
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteGET() throws Exception{
		
		
		// /WEB-INF/views/member/deleteForm.jsp 페이지 이동
		return "/member/deleteForm";
		
	}
	
	// /member/delete (회원탈퇴 처리)
	// http://localhost:8080/member/delete
	@RequestMapping(value= "/delete", method = RequestMethod.POST)
	public String deletePOST(MemberVO vo, HttpSession session,Model model) throws Exception{
		
		logger.info("/member/delete.jsp submit -> /member/delete POST");
		
		// jsp 페이지에서 전달한(submit) 정보를 저장(userid,userpw)
		// + session 내장객체정보 저장
		
		// DAO 이동해서 삭제 (service -> DAO ->mapper -> Mysql)
		// 의존주입 받은 서비스 객체 사용
		service.deleteMember(vo);
		// 세션값을 초기화
		session.invalidate();
		
		// 삭제완료시 메세지
		// view페이지로 데이터 전달
		model.addAttribute("result","delOK");
		
		// 페이지 이동(main)
		return "redirect:/member/main";
	}
	
	// /member/list
	// 회원 목록 출력 처리
	// http://localhost:8080/member/list
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listGET(HttpSession session) throws Exception{
		
		logger.info("/member/list get -> member/memberList.jsp 이동" );
		
		// 세션값 (id) 사용해서 관리자인지 판별
		String id = (String) session.getAttribute("userid");
		if(id == null || !id.equals("admin")) {
			return "redirect:/member/login";
		}
		
		// 관리자 일때 서비스를 통해서 회원 목록을 전부 가져오기
		List<MemberVO> memberList = service.getMemberList();
		logger.info(memberList +"");
		
		// 회원정보를 모두 저장 후 /member/memberList.jsp 페이지에서 출력
		
		
		return "";
	}
	
	
} // MemberController 끝





