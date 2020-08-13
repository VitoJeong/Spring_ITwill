<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WEB-INF/views/itwillMember.jsp</h1>
	
	<h2>전달 객체값 확인</h2>
	
	<h1>addAttribute 이름없이 저장</h1>
	<!-- 클래스 타입이 대문자로 이루어진 이름일 때 첫글자 소문자변경 X -->
	<h2>이름 : ${ITWILLBean.name }</h2>
	<h2>전화번호 : ${ITWILLBean.tel }</h2>
	<h2>나이 : ${ITWILLBean.age }</h2>
	
	<hr>
	<h1>addAttribute 이름값(itwill) 포함해서 저장</h1>
	<h2>이름 : ${itwill.name }</h2>
	<h2>전화번호 : ${itwill.tel }</h2>
	<h2>나이 : ${itwill.age }</h2>
</body>
</html>