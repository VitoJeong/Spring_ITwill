<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WEB-INF/views/main.jsp</h1>
	
	<%
		// 세션값 체크
		
		String id = (String) session.getAttribute("userid");
		
		// 세션값이 없을경우
		if (id == null){
			response.sendRedirect(request.getContextPath()+"/member/login");
		}
		
	%>
	
	<!-- jsp 표현식 (자바코드의 변수에 접근해서 화면에 출력) -->
	<h3><%=id %> 님이 로그인 하셨습니다.</h3>
	
	<!-- el 표현식(영역의 데이터를 접근해서 화면에 출력) -->
	<!-- 
		el 표현식 사용가능한 내장객체 (p517~)
		
		pageScope
		requestScope
		sessionScope
		applicationScope
		
		param $ (param.파라미터 이름)
		paramValues
		
	 -->
	<!-- 내장객체 영역.속성명 -->
	<h3>${ sessionScope.userid } 님이 로그인 하셨습니다.</h3>
	
	<!-- 내장객체 영역.속성명(내장 객체의 영역이름을 생략가능) -->
	<!-- 내장객체 영역
		(pageScope>requestScope>sessionScope>applicationScope)
		모두 순차적으로 검색
		만약에 먼저 사용되는 속성이 있을경우 다음 영역의 검색은 생략 -->
	<h3>${ userid } 님이 로그인 하셨습니다.</h3>
	
	
	<hr>
	
	<input type="button" value="로그아웃" 
		onclick="location.href='/member/logout'"
	>
	
	<hr>
	<h3><a href="/member/info"> 회원 정보 보기(select)</a></h3>
	
	<hr>
	<h3><a href="/member/update"> 회원 정보 수정(update)</a></h3>
	
</body>



</html>




