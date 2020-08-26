package com.itwillbs.controller;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
public class DataSourceTest {
	// root-context.xml 파일과 연결해서 DB연결 테스트
	// + (의존 주입 확인)
	
	// DB 연결객체를 의존주입(DI)
	@Inject
	private DataSource ds;
	
	// 테스트 작업 -> 메서드
	@Test
	public void testCon() throws Exception {
			// 주입받은 ds객체를 con 객체에 주입
		try(Connection con = ds.getConnection()) {
			
			System.out.println("DB 연결 테스트 성공!");
			System.out.println("con : " + con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
