<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp" %>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">게시글 읽기</h3>
				</div>
				<!-- /.box-header -->
				<form role="form" action="" method="post">
				
					<input type="hidden" name="bno" value="${boardVO.bno }">
				
				</form>
				
				<!-- form 태그 method 속성이 없을경우 기본 전달방식은 get -->
				<!-- form 태그 action 속성이 없을경우 다시 자신페이지 호출 -->
					<div class="box-body">
						<div class="form-group">
							<label for="exampleInputEmail1">글 번호</label> 
							<input type="text" name='title' class="form-control" value="${boardVO.bno }" readonly>
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">제목</label> 
							<input type="text" name='title' class="form-control" value="${boardVO.title }"readonly>
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">내용</label>
							<textarea class="form-control" name="content" rows="3"
								 readonly>${boardVO.content }</textarea>
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">글쓴이</label> 
							<input type="text" name="writer" class="form-control"  value="${boardVO.writer }" readonly>
						</div>
					</div>
					<!-- /.box-body -->

					<div class="box-footer">
						<button type="button" class="btn btn-warning">수정하기</button>
						<button type="button" class="btn btn-danger">삭제하기</button>
						<button type="button" class="btn btn-primary">목록으로</button>
					</div>


			</div>
			<!-- /.box -->
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->
<script src="${pageContext.request.contextPath }/resources/plugins/jQuery/jQuery-2.1.4.min.js">

</script>
<script type="text/javascript">
	$(document).ready(function(){
		// alert("확인!");

		// 목록으로 버튼 클릭
		$(".btn-primary").on("click",function(){
			// 목록페이지로 이동
			location.href="/board/listAll";
		
			});

		// 본문에 글번호 정보를 가지고 있는 form 태그 정보를 가져오기  
		
		var formObj = $("form[role='form']");
		console.log("form : " + formObj);
		
		// 삭제하기 클릭
		$(".btn-danger").on("click",function(){
			// 삭제 페이지로 이동
			// 이동시 삭제할 글 번호를 가지고 이동
			formObj.attr("action", "/board/remove");
			formObj.submit();
		
			});
		
	
		// 수정하기 클릭
		$(".btn-warning").on("click",function(){
			// alert("수정하기");

			// 수정하는 페이지로 이동(+수정할 글번호 가지고 이동)
			// /board/modify 주소로 bno 저장해서 post방식 submit()
			formObj.attr("action","/board/modify");
			formObj.attr("method","get");
			formObj.submit();
			
			});
		
		});

</script>
<%@ include file="../include/footer.jsp" %>

