package com.itwillbs.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MemberVO;

// @Repository : 해당클래스를 DAO로 사용하겠다. 스프링에 등록 (root-context.xml연결)

@Repository
public class MemberDAOimpl implements MemberDAO{

	// DAO 처리
	
	// DB연결
	@Inject
	private SqlSession sqlSession;
	
	// mapper 주소 (mappers/memberMapper.xml)
	private static final String namespace
					= "com.itwillbs.mapper.MemberMapper";
			
	
	@Override
	public String getTime() {
		// sqlSession 객체를 사용해서 select 구문을 호출
		// memberMapper.xml 파일에 구문을 호출(namespace+".id값"
		
		System.out.println("MemberDAOImpl_getTime() 호출!");
		
		System.out.println("sqlSession 객체를 주입받는다!");
		
		System.out.println("SQL 구문실행하기 위해서 mapper로 이동");
		String tmp =sqlSession.selectOne(namespace+".getTime");
		
		System.out.println("mapper에서 해당 sql 구문 실행 완료");
		System.out.println("호출했던 페이지 MemberDAOTest로 이동");
		
		return tmp;
		//	return sqlSession.selectOne(namespace+".getTime");
	}


	@Override
	public void insertMember(MemberVO vo) {
		// 회원가입처리 - MemberMapper.xml 파일과 연결처리
		
		System.out.println("DAOImpl : 회원가입 메서드 호출(회원정보 가져옴)"+vo);
		System.out.println("MemberMapper.xml 호출");
		
		// memberMapper.xml 로 전달
		sqlSession.insert(namespace+".insertMember",vo);
		
		System.out.println("DAOImpl : 회원 저장완료! Test파일로 이동");
		
	}


	@Override
	public MemberVO getMember(String id) {
		
		System.out.println("@@@@ DAO : TEST 파일에서 메서드 호출 @@@@");
		
		System.out.println("@@@@ DAO : MyBatis 사용 memberMapper로 이동 @@@@");
		
		// com.itwillbs.mapper.MemberMapper.getMember
		MemberVO vo 
			= sqlSession.selectOne(namespace+".getMember",id);
				
		System.out.println("@@@@ DAO : Mapper에서 SQL구문 실행완료!! @@@@");
		System.out.println("@@@@ DAO : 결과를 저장해서 TEST페이지로 이동 @@@@");
		
		return vo;
	}


	@Override
	public MemberVO getMemberWithIdPw(String id, String pw) {
		// 회원정보(ID/PW)에 해당하는 회원정보 가져오는 메서드
		System.out.println("@@@@ DAO : TEST에서 메서드 호출!");
		System.out.println("@@@@ DAO : DB 연결 준비(의존주입 - sqlSession)");
		System.out.println("@@@@ DAO : MyBatis 사용 Mapper에서 sql구문 호출 ");
		
		Map<String,Object> paraMap 
			= new HashMap<String, Object>();
		
		// 저장할 때 키값을 테이블의 컬럼명으로 저장
		//	-> mapper에서 바로 전달할 수 있도록 처리
		
		paraMap.put("userid", id);
		paraMap.put("userpw", pw);
		
		MemberVO vo 
			= sqlSession.selectOne(namespace+".getMemberWithIdPw",paraMap);
		
		System.out.println("@@@@ DAO : SQL구문 실행 완료");
		System.out.println("@@@@ DAO : 결과 저장해서 다시 테스트 페이지이동");
		
		return vo;
		
	}
	
	
	
	
	
}
