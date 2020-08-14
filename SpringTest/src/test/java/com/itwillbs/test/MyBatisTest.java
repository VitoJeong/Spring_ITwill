package com.itwillbs.test;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
public class MyBatisTest {

	// Mybatis 연결 테스트
	// -> SqlSessionFactory 객체 생성(bean 의존 주입)
	@Inject
	private SqlSessionFactory sqlFactory;
	
	// 테스트 메서드 생성
	@Test
	public void testFactory() {
		
		System.out.println("bean 객체 의존 주입!(객체 생성)");   
		System.out.println("@@@@@@@@@ sqlFactory : "+sqlFactory);
	}
	
	// sqlFactory 객체를 사용해서 연결
	@Test
	public void testSessionFactory() {
		try(SqlSession session = sqlFactory.openSession()){
			
			System.out.println(" sqlFactory사용 DB연결");
			System.out.println("@@@@@@@@@ 연결된 객체 정보 : " + session);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
