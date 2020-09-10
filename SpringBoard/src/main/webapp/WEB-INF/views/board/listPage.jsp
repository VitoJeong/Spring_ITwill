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
				<div class="box-footer">
						<button type="button" class="btn btn-primary"
							onclick="location.href='/board/register'">
							글쓰기
						</button>
				</div>
				<!-- /.box-footer-->
				
				<div class = "text-center">
				
					<ul class="pagination">
					
					<!-- 이전 -->
					<c:if test="${pm.prev }">
						<li>
							<a href="listPage?page=${pm.startPage-1 }">이전</a>
						</li>
					</c:if>
					
					<!-- 페이지 번호 -->
					<c:forEach begin="${pm.startPage }" end="${pm.endPage }"
								var ="idx"
					>
						<li
							<c:out value="${pm.cri.page == idx? 'class=active':'' }"/>
						>
							<a href="listPage?page=${idx}">${idx}</a>
						</li>
					</c:forEach>
					
					<!-- 다음 -->
					<c:if test="${pm.next && pm.endPage>0 }">
						<li>
							<a href="listPage?page=${pm.endPage+1 }">다음</a>
						</li>
					</c:if>
					
					</ul>
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
		alert("글쓰기 완료!\n" +result);
	}else if(result == 'delOk'){
		// 삭제 완료처리
		alert("글 삭제 완료! " + result);
	}else if(result == 'modifyOK'){
		// 수정 완료 처리
		alert("글 수정 완료! " +result)
	}
	
	
</script>


<%@ include file="../include/footer.jsp" %>





