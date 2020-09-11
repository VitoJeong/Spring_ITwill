package com.itwillbs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.SampleVO;

// @RestController : 해당 클래스(컨트롤러)가 Rest 데이터 처리 전용 컨트롤러
// -> 클래스 안에있는 모든 메서드에는  @ResponseBody 추가되어 있음(생략가능)

@RestController
@RequestMapping("/sample")
public class RestControllerSample {
	
	// * 일반 Controller와 달리 페이지이동 X,
	// RestController는 데이터를 전송(전달)하는 목적
	
	// http://localhost:8082/controller/sample/hello(x)
	// http://localhost:8082/sample/hello
	// 문자데이터 처리
	@RequestMapping(value="/hello")
	public String testHello() {
		
		return "Itwill Hello";
	}
	
	// http://localhost:8082/sample/sendVO
	// JSON 타입 데이터 처리
	@RequestMapping("/sendVO")
	public SampleVO testSendVO() {
		
		SampleVO vo = new SampleVO();
		vo.setName("ITWILL");
		vo.setTel("010-4412-8989");
		vo.setAge(20);
		
		// jackson-databind를 통해 JSON형태로 출력
		return vo;
	}
	
	// list
	@RequestMapping(value="/sendList")
	public List<SampleVO> sendList(){
		
		
		List<SampleVO> list = new ArrayList<SampleVO>();
		
		for(int i=0;i<10;i++) {
			SampleVO vo = new SampleVO();
			vo.setAge(i*5);
			vo.setName("사용자"+i);
			vo.setTel("010-1599-158"+i);
			
			list.add(vo);
		}
		
		return list;
	}
	
	// map
	@RequestMapping(value ="/sendMap")
	public Map<Integer, SampleVO> sendMap(){
		
		Map<Integer, SampleVO> map
		 = new HashMap<Integer, SampleVO>();
		
		for(int i=0;i<10;i++) {
			SampleVO vo = new SampleVO();
			vo.setAge(i*5);
			vo.setName("사용자"+i);
			vo.setTel("010-1599-158"+i);
			
			map.put(i, vo);
		}
		
		return map;
	}
	
	// RestController 사용해서 데이터만 전달
	// -> 문제발생을 관리 X 
	// -> ResponseEntity 객체 : 결과 데이터 + Http 상태 코드 제어 클래스
	
	// http 상태정보를 전달
	@RequestMapping("sendError")
	public ResponseEntity<Void> sendError(){
		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	// 데이터 + Http 상태 정보 전달
	// ResponseEntity<전달할 데이터 타입>
	@RequestMapping("sendErrorList")
	public ResponseEntity<List<SampleVO>> sendErrorList(){

		List<SampleVO> list = new ArrayList<SampleVO>();
		
		for(int i=0;i<10;i++) {
			SampleVO vo = new SampleVO();
			vo.setAge(i*5);
			vo.setName("사용자"+i);
			vo.setTel("010-1599-158"+i);
			
			list.add(vo);
		}
		
		ResponseEntity<List<SampleVO>> dataError = null;
		
		/*
		 if(dataError == null) {
		 List<SampleVO> errorList = new ArrayList<SampleVO>();
		 new ResponseEntity<List<SampleVO>> (errorList,HttpStatus.NOT_FOUND); 
		 } else {
		 new ResponseEntity<List<SampleVO>> (list,HttpStatus.NOT_FOUND); 
		 }
		 
		 return dataError;
		 */
	
		return new ResponseEntity<List<SampleVO>> (list,HttpStatus.OK);
	}
	
	// 데이터 전달 방법 (method)
	// get/post 방식 사용 데이터 처리
	// GET/POST/DELETE/PUT/PATCH 방식 사용 데이터 처리 (REST)
	
	// * REST방식 주소 처리 원칙
	// 1) URI는 사용자가 원하는 리소스를 의미.
	// 2) 명사를 사용해서 주소 작성  (복수형)
	// 3) URI에 확인할 데이터값을 같이 가지고 이동
	
	// URI : /boards/100 -> 100번 글 조회 (HttpMethod - GET/DELETE)
	// URI : /boards/100/replies/10 -> 100번 글의 댓글 10번 조회
	// URI : /boards/100/10 -> 100번글 댓글 10번을 조회
	// URI : /boards/ -> 신규 입력페이지 (HttpMethod - POST/PUT(신규자료 수정 또는 등록,PATCH))
	// (HttpMethod -POST/PUT(신규자료 수정 또는 등록,PATCH))
	
	//@RequestMapping(value = "/boards/100",method = RequestMethod.GET )
	@RequestMapping(value = "/boards/100",method = RequestMethod.DELETE)
	public void Test1() {
		
	}
	
	
}
