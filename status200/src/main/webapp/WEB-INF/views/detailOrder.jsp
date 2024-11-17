<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 주문 내역</title>
<%@ include file="include/head.jsp"%>
</head>
<body>

	<%@ include file="include/header.jsp"%>
	<%@ include file="include/mypageheader.jsp"%>

	<!-- 주문 정보 -->
	<div class="justify-content-center" style="margin: 50px; width: 1000px">
		<h4>주문 정보</h4>
		<div class="justify-content-center">
			<table class="table justify-content-center" style="text-align: center">
				<tr>
					<th class="col-md-1 table-light">주문 번호</th>
					<td class="col-md-2">20241117</td>
					<th class="col-md-1 table-light">주문자(아이디)</th>
					<td class="col-md-2">박송송(song)</td>
				</tr>
			</table>
		</div>
	</div>
	<!-- / 주문 정보. 끝. -->
	
	<!-- 내 주문상품 정보 -->
	<div class="justify-content-center" style="margin: 50px; width: 1000px">
		<h4>내 주문상품 정보</h4>
		<div class="justify-content-center">
			<table class="table justify-content-center" style="text-align: center">
				<tr>
					<th class="table-light" colspan="2">제품명</th>
					<th class="table-light">수량</th>
					<th class="table-light">가격</th>
					<th class="table-light">상태</th>
					<th class="table-light">구매후기</th>
				</tr>
				<tr>
					<td class="col-md-1">제품이미지</td>
					<td class="col-md-2">제품명</td>
					<td class="col-md-1">수량</td>
					<td class="col-md-1">가격</td>
					<td class="col-md-1">상태</td>
					<td class="col-md-1">
					<button class="btn btn-outline-secondary" onclick="location.href='찜 삭제, 장바구니 이동'">구매후기</button>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<!-- / 내 주문상품 정보. 끝. -->
</body>
</html>