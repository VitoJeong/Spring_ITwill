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
				
				
				
				<!-- form 태그 method 속성이 없을경우 기본 전달방식은 get -->
				<!-- form 태그 action 속성이 없을경우 다시 자신페이지 호출 -->
					<div class="box-body">
						<div class="form-group">
							<label for="exampleInputEmail1">글 번호</label> 
							<input type="text" name='bno' class="form-control" value="${boardVO.bno }" readonly>
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">제목</label> 
							<input type="text" name='title' class="form-control">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">내용</label>
							<textarea class="form-control" name="content" rows="3"></textarea>
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">글쓴이</label> 
							<input type="text" name="writer" class="form-control"  value="${boardVO.writer }" readonly>
						</div>
					</div>
					<!-- /.box-body -->

					<div class="box-footer">
						<button type="button" class="btn btn-warning">수정하기</button>
						<button type="button" class="btn btn-danger">취소하기</button>
					</div>
					
					</form>


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

		var formObj = $("form[role='form']");
		
		// 수정하기 클릭
		$(".btn-warning").on("click",function(){
			// 폼태그의 정보를 저장해서 전달(처리페이지) => submit

			formObj.submit();
			
			});
		
		
		// 취소하기 클릭
		$(".btn-danger").on("click",function(){

			location.href="/board/listAll";
			});
		
		
		}); // jQuery 끝

</script>
<%@ include file="../include/footer.jsp" %>

