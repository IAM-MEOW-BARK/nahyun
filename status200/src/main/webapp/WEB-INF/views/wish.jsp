<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

	<!-- 전체 중앙 정렬 컨테이너 -->
	<div class="center-container">
		<!-- 찜한 상품 -->
		<div class="table-container">
			<h4>찜한 상품</h4>
			<div class="justify-content-center">
				<table class="table justify-content-center mx-auto"
					style="text-align: center;">
					<tr>
						<th class="col-md-1 table-light"><input type='checkbox'
							name='wish' value='selectall' onclick='selectAll(this)' /></th>
						<th class="col-md-6 table-light" colspan="2">제품</th>
						<th class="col-md-3 table-light">가격</th>
						<th class="col-md-2 table-light">장바구니 이동</th>
					</tr>
					<c:forEach var="wish" items="{wishlist }">
						<tr>
							<td><input type='checkbox' name='wish' value='{wish.item}' />
							</td>
							<td class="col-md-1"><a href="detail?bno={wish.제품번호}">
									{wish.제품이미지} </a></td>
							<td>{wish.제품이름}</td>
							<td>{wish.제품금액}</td>
							<td>
								<button class="btn btn-outline-secondary"
									onclick="location.href='찜 삭제, 장바구니 이동'">장바구니로 이동</button>
							</td>
						</tr>
					</c:forEach>

					<!-- 페이징 처리 필요 -->

					<tr>
						<td colspan="5" align="center">
							<ul class="list-group">
								<li class="list-group-item" value="${page.pre}" id="pre"><a
									href="./notice?page=${pager.page-1}" aria-label="Previous">
										<button type="button" class="btn btn-primary"
											aria-hidden="true">👈</button>
								</a></li>
								<c:forEach var="i" begin="1" end="${totalPage}">
									<a href="list?pageNUM=${i}">
										<button type="button" class="btn btn-primary">${i}</button>
									</a>
								</c:forEach>
								<li class="list-group-item" value="{page.next}"
									${page.next?'':'disabled'}" id="next"><a class="page-link"
									href="./notice?page=${page.nowPage+1}" aria-label="Next"> <span
										aria-hidden="true">👉</span>
								</a></li>
							</ul>
						</td>
					</tr>

					<!-- / 페이징 처리 필요. 끝. -->

				</table>
				<button class="btn btn-outline-secondary"
					onclick="location.href='totalWish'">전체 찜 내역</button>
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
	</div>
	<!-- / 최근 찜 내역. 끝. -->
	</div>
</body>
</html>