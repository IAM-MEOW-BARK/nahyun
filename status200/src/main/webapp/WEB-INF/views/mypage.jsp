<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>마이 냥멍 페이지</title>

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
				<!-- 최근 주문 내역 -->
				<div class="table-container mb-5">
					<h4>최근 주문 내역</h4>
					<table class="table table-bordered text-center">
						<thead class="table-light">
							<tr>
								<th>주문일자</th>
								<th>주문번호</th>
								<th>대표제품</th>
								<th>결제금액</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="order" items="{list}">
								<tr>
									<td>{order.주문일자}</td>
									<td>
										<a href="detailOrder">{order.주문번호}</a>
									</td>
									<td>{order.대표제품}</td>
									<td>{order.total금액}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<button class="btn btn-outline-secondary" onclick="location.href='totalOrder'">전체 주문 내역</button>
				</div>

				<!-- 최근 찜 내역 -->
				<div class="table-container">
					<h4>최근 찜 내역</h4>
					<table class="table table-bordered text-center">
						<thead class="table-light">
							<tr>
								<th><input type="checkbox" name="wish" value="selectall" onclick="selectAll(this)" /></th>
								<th colspan="2">제품</th>
								<th>가격</th>
								<th>장바구니 이동</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="wish" items="{wishlist}">
								<tr>
									<td>
										<input type="checkbox" name="wish" value="{wish.item}" />
									</td>
									<td>
										<a href="detail?bno={wish.제품번호}">{wish.제품이미지}</a>
									</td>
									<td>{wish.제품이름}</td>
									<td>{wish.제품금액}</td>
									<td>
										<button class="btn btn-outline-secondary" onclick="location.href='cart'">장바구니로 이동</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<button class="btn btn-outline-secondary" onclick="location.href='totalWish'">전체 찜 내역</button>
				</div>
			</div>
		</div>
	</div>

	<script>
function selectAll(selectAll) {
    const checkboxes = document.querySelectorAll('input[type="checkbox"]');
    checkboxes.forEach((checkbox) => {
        checkbox.checked = selectAll.checked;
    });
}
</script>

</body>
</html>