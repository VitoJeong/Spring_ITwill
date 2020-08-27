package com.itwillbs.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.itwillbs.service.BoardService;

@Controller
public class BoardController {
	// 서비스 호출 -> DAO
	
	// 서비스 객체 주입(DI)
	@Inject
	private BoardService service;
	
	// 로그 객체
	private static Logger logger = 
			LoggerFactory.getLogger(BoardController.class);
}
