<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../include/header.jsp" %>


<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->

			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">글 목록:[${result }]</h3>
				</div>
				<div class="box-body">
				
				<table class="table table-bordered">
					<tr>
						<th style="width: 60px">글번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>날짜</th>
						<th style="width: 60px">조회수</th>
					</tr>
					<c:forEach var="boardVO" items="${boardList }">
						<tr>
							<td>${boardVO.bno }</td>
							<td>
								<a href="/board/read?bno=${boardVO.bno }">
									${boardVO.title }
								</a>
							</td>
							<td>${boardVO.writer }</td>
							<td>
								<fmt:formatDate value="${boardVO.regdate }"
									pattern="yy-MM-dd HH:mm"/>
							</td>
							<td>
								<span class="badge bg-red">
								${boardVO.viewcnt }
								</span>
							</td>
						</tr>
					</c:forEach>


				</table>

				</div>
				<!-- /.box-body -->
				<div class="box-footer">Footer</div>
				<!-- /.box-footer-->
				<div class="box-footer">
						<button type="button" class="btn btn-primary"
							onclick="location.href='/board/register'">
							글쓰기
						</button>
				</div>
			</div>
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<!-- JavaScript 사용 -->
<!-- 글쓴후 페이지 이동/페이지 이동 구문 -->
<script type="text/javascript">

	/* 전달된 속성의 값을 저장해서 비교한 후 메세지 출력 */
	// 속성(attribute)에 저장된 정보를 JS에 전달
	var result = '${result}';

	if(result == 'SUCCESS@'){
		// 글쓰고 리스트 페이지 왔을때만 출력
		alert("글쓰기 완료!<br>" +result);
		
	}
	
	
</script>


<%@ include file="../include/footer.jsp" %>





