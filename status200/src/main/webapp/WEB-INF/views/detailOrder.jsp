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
					<td class="col-md-2">{주문번호}</td>
					<th class="col-md-1 table-light">주문자(아이디)</th>
					<td class="col-md-2">{name}{user_id}</td>
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
					<th class="col-md-3 table-light" colspan="2">제품명</th>
					<th class="col-md-1 table-light">수량</th>
					<th class="col-md-2 table-light">가격</th>
					<th class="col-md-2 table-light">상태</th>
					<th class="col-md-1 table-light">구매후기</th>
				</tr>
				<tr>
					<td>제품이미지</td>
					<td>제품명</td>
					<td>수량</td>
					<td>가격</td>
					<td>상태</td>
					<td>구매후기</td>
				</tr>
			</table>
		</div>
	</div>
	<!-- / 내 주문상품 정보. 끝. -->
</body>
</html>