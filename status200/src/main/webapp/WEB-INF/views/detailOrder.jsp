<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

	<!-- 전체 중앙 정렬 컨테이너 -->
	<div class="center-container">
		<!-- 주문 정보 -->
		<div class="table-container">
			<h4>주문 정보</h4>
			<table class="table" style="text-align: center;">
				<tr>
					<th class="col-md-1 table-light">주문 번호</th>
					<td class="col-md-2">20241117</td>
					<th class="col-md-1 table-light">주문자(아이디)</th>
					<td class="col-md-2">박송송(song)</td>
				</tr>
			</table>
		</div>
		<!-- / 주문 정보. 끝. -->

		<!-- 내 주문상품 정보 -->
		<div class="table-container">
			<h4>내 주문상품 정보</h4>
			<table class="table" style="text-align: center;">
				<tr>
					<th class="table-light" colspan="2">제품명</th>
					<th class="table-light">수량</th>
					<th class="table-light">가격</th>
					<th class="table-light">상태</th>
					<th class="table-light">구매후기</th>
				</tr>
				<tr>
					<td class="col-md-1">제품이미지</td>
					<td class="col-md-2">맛있는 제품명</td>
					<td class="col-md-1">1개</td>
					<td class="col-md-1">25,000원</td>
					<td class="col-md-1">구매완료</td>
					<td class="col-md-1">
						<button class="btn btn-outline-secondary"
							onclick="location.href='찜 삭제, 장바구니 이동'">구매후기</button>
					</td>
				</tr>
			</table>
		</div>
		<!-- / 내 주문상품 정보. 끝. -->

		<!-- 결제 정보 -->
		<div class="table-container">
			<h4>결제 정보</h4>
			<table class="table" style="text-align: center;">
				<tr>
					<th class="table-light col-md-1">결제 방법</th>
					<td>신용카드</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<th class="table-light col-md-1">결제금액</th>
					<td class="col-md-2">25,000원</td>
					<th class="table-light col-md-1">결제 처리일</th>
					<td class="col-md-2">2024.11.12. 12:06:36</td>
				</tr>
			</table>
		</div>
		<!-- / 결제 정보. 끝. -->

		<!-- 내 배송지 정보 -->
		<div class="table-container">
			<h4>내 배송지 정보</h4>
			<table class="table">
				<tr>
					<th class="table-light col-md-1" style="text-align: center;">받으실
						분</th>
					<td class="col-md-5" style="text-align: left;">박송송</td>
				</tr>
				<tr>
					<th class="table-light align-middle" style="text-align: center;">주소</th>
					<td style="text-align: left;">{우편번호}<br> {도로주소}<br>
						{상세주소}
					</td>
				</tr>
				<tr>
					<th class="table-light" style="text-align: center;">휴대폰번호</th>
					<td style="text-align: left;">010-1234-5678</td>
				</tr>
				<tr>
					<th class="table-light" style="text-align: center;">배송 요청사항</th>
					<td style="text-align: left;">문 앞 부탁드립니다. 감사합니다.</td>
				</tr>
			</table>
		</div>
		<!-- / 내 배송지 정보. 끝. -->

		<!-- 목록으로 버튼 -->
		<div class="center-container" style="margin-bottom: 50px;">
			<button class="btn btn-secondary"
				onclick="location.href='totalOrder'">목록으로</button>
		</div>

	</div>
</body>
</html>
