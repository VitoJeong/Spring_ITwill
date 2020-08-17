<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WEB-INF/views/member</h1>
	<h2> 회원 정보 출력</h2>
	
	<%
		// 세션값 처리(ID가 없을 경우 로그인 페이지로 이동)
		String id = (String) session.getAttribute("userid");
		
		if(id == null){
			response.sendRedirect(request.getContextPath() + "/member/login");
		}
	%>
	<h3>아이디 : ${membervo.userid }</h3>
	<h3>비밀번호 : ${membervo.userpw }</h3>
	<h3>이름 : ${membervo.username }</h3>
	<h3>이메일 : ${membervo.email }</h3>
	<h3>회원가입일 : ${membervo.regdate }</h3>
	
	<h2><a href="/member/main">Main 페이지 이동</a></h2>

</body>
</html>