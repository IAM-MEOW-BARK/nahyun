<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 주문 내역</title>
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
				<!-- 주문 정보 -->
				<div class="table-container">
					<table class="table" style="text-align: center;">
						<tr>
							<th class="col-md-1 table-light">주문 번호</th>
							<td class="col-md-2">${orderDetail.orderCode}</td>
							<th class="col-md-1 table-light">주문자(아이디)</th>
							<td class="col-md-2">${orderDetail.userId}</td>
						</tr>
					</table>
				</div>
				<!-- / 주문 정보. 끝. -->

				<!-- 내 주문상품 정보 -->
				<div class="table-container">
					<h5>내 주문상품 정보</h5>
					<table class="table" style="text-align: center;">
						<tr>
							<th class="table-light" colspan="2">제품명</th>
							<th class="table-light">수량</th>
							<th class="table-light">가격</th>
							<th class="table-light">상태</th>
							<th class="table-light">구매후기</th>
						</tr>

						<c:forEach var="item" items="${orderItemDetail}">

							<tr>
								<td class="col-md-1" style="vertical-align: middle;">
									<img src="${pageContext.request.contextPath}/resources/img/${item.thumbnailImg}" alt="상품 이미지" style="height: 100px; width: 100px;">
								</td>
								<td class="col-md-2" style="vertical-align: middle;">${item.productName}</td>
								<td class="col-md-1" style="vertical-align: middle;">${item.orderQuantity}개</td>
								<td class="col-md-1" style="vertical-align: middle;">${item.totalPrice}원</td>
								<td class="col-md-1" style="vertical-align: middle;">${item.orderStatus}</td>
								<td class="col-md-1" style="vertical-align: middle;">
									<!-- 구매후기 버튼 -->
									<c:choose>
										<c:when test="${product.review != null}">
											<button class="btn btn-outline-secondary" onclick="location.href='reviewDetail?reviewNo=${product.review.reviewNo}'">후기보기</button>
										</c:when>
										<c:otherwise>
											<button class="btn btn-outline-secondary" onclick="location.href='writeReview?productCode=${product.product_code}'">후기작성</button>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<!-- / 내 주문상품 정보. 끝. -->

				<!-- 결제 정보 -->
				<div class="table-container">
					<h5>결제 정보</h5>
					<table class="table" style="text-align: center;">
						<tr>
							<th class="table-light col-md-1">결제 방법</th>
							<td>신용카드</td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<th class="table-light col-md-1">결제금액</th>
							<td class="col-md-2">${totalCost}원</td>
							<th class="table-light col-md-1">결제 처리일</th>
							<td class="col-md-2">${orderDetail.orderedAt}</td>
						</tr>
					</table>
				</div>
				<!-- / 결제 정보. 끝. -->

				<!-- 내 배송지 정보 -->
				<div class="table-container">
					<h5>내 배송지 정보</h5>
					<table class="table">
						<tr>
							<th class="table-light col-md-1" style="text-align: center;">받으실 분</th>
							<td class="col-md-5" style="text-align: left;">박송송</td>
						</tr>
						<tr>
							<th class="table-light align-middle" style="text-align: center;">주소</th>
							<td style="text-align: left;">
								${orderDetail.zipcode}<br> ${orderDetail.address}<br> ${orderDetail.detailAddress}
							</td>
						</tr>
						<tr>
							<th class="table-light" style="text-align: center;">휴대폰번호</th>
							<td style="text-align: left;">010-1234-5678</td>
						</tr>
						<tr>
							<th class="table-light" style="text-align: center;">배송 요청사항</th>
							<td style="text-align: left;"></td>
						</tr>
					</table>
				</div>
				<!-- / 내 배송지 정보. 끝. -->

				<!-- 목록으로 버튼 -->
				<div class="center-container" style="margin-bottom: 50px;">
					<button class="btn btn-secondary" onclick="location.href='totalOrder'">목록으로</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
