<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="include/head.jsp"%>
<style>
.center-container {
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column; /* 세로 방향으로 정렬 */
}

.table-container {
	width: 1000px;
	margin: 20px; /* 표 간 간격 */
}
</style>
</head>
<body>
	<%@ include file="include/header.jsp"%>
	<%@ include file="include/mypageheader.jsp"%>
	<!-- 마이페이지 -->
	<div class="container-lg my-5">
		<div class="row">
			<!-- 왼쪽 내비게이션 -->
			<%@ include file="include/mypagenav.jsp"%>

			<!-- 오른쪽 콘텐츠 -->
			<div class="col-md-9">
				<!-- 찜한 상품 -->
				<div class="table-container">
					<h4>찜한 상품</h4>
					<div class="justify-content-center">
						<table class="table justify-content-center mx-auto" style="text-align: center;">
							<tr>
								<th class="col-md-1 table-light"><input type='checkbox' name='wish' value='selectall' onclick='selectAll(this)' /></th>
								<th class="col-md-6 table-light" colspan="2">제품</th>
								<th class="col-md-3 table-light">가격</th>
								<th class="col-md-2 table-light">장바구니 이동</th>
							</tr>
							<c:forEach var="wish" items="{wishlist }">
								<tr>
									<td>
										<input type='checkbox' name='wish' value='{wish.item}' />
									</td>
									<td class="col-md-1">
										<a href="detail?bno={wish.제품번호}"> {wish.제품이미지} </a>
									</td>
									<td>{wish.제품이름}</td>
									<td>{wish.제품금액}</td>
									<td>
										<button class="btn btn-outline-secondary" onclick="location.href='cartPop'">장바구니로 이동</button>
									</td>
								</tr>
							</c:forEach>

							<!-- 페이징 처리 필요 -->

							<tr>
								<td colspan="5" align="center">
									<!-- 페이징 -->
									<nav aria-label="Page navigation">
										<ul class="pagination justify-content-center">
											<!-- 이전 페이지 -->
											<li class="page-item"><a class="page-link" href="?page=1" aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
											</a></li>

											<!-- 페이지 번호 -->
											<%
											for (int i = 1; i <= 5; i++) {
											%>
											<li class="page-item <%=(i == 1) ? "active" : ""%>"><a class="page-link" href="?page=<%=i%>"><%=i%></a></li>
											<%
											}
											%>

											<!-- 다음 페이지 -->
											<li class="page-item"><a class="page-link" href="?page=2" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
											</a></li>
										</ul>
									</nav>
								</td>
							</tr>

							<!-- / 페이징 처리 필요. 끝. -->

						</table>
					</div>
				</div>

			</div>
			<!-- / 최근 찜 내역. 끝. -->
		</div>
	</div>
	<!-- 전체 선택 체크박스 클릭 시, 전체 클릭 됨 -->
	<script type="text/javascript">

function selectAll(selectAll)  {
	  const checkboxes 
	     = document.querySelectorAll('input[type="checkbox"]');
	  
	  checkboxes.forEach((checkbox) => {
	    checkbox.checked = selectAll.checked
	  })
	}
</script>
</body>
</html>