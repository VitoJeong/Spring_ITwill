<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WEB-INF/views/member/update.jsp</h1>
	
	<h1>회원 정보 수정</h1>
	
	<%
		// 세션 데이터 처리
		String id = (String) session.getAttribute("userid");
	
		if(id == null){
			response.sendRedirect(request.getContextPath()+"/member/login");
		}
	%>
	<fieldset>
		<legend>회원 수정</legend>
		<form action="/member/" method="post" >
			아이디 : <input type="text" name="userid" value="${membervo.userid }"><br>
			비밀번호 : <input type="text" name="userpw"><br>
			이름 : <input type="text" name="username" value="${membervo.username }"><br>
			이메일 : <input type="text" name="email" value="${membervo.email }"><br>
			<input type="submit" value="회원수정">
		</form>
	</fieldset>
</body>
</html>