<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 주문 정보</title>
</head>
<body>

	<!-- 마이페이지 공통 헤더 넣기 -->


	<!-- 주문 정보 -->
	<div>
		<h3>주문 정보</h3>
		<table>
			<tr>
				<td>주문 번호</td>
				<td>${order_code}</td>
				<td>주문자(아이디)</td>
				<td>${user_id}(${name})</td>
			</tr>
		</table>
	</div>
	<!-- / 주문 정보. 끝. -->

	<!-- 내 주문 상품 -->
	<div>
		<h3>내 주문 상품</h3>
		<table>
			<tr>
				<td colspan="2">제품명</td>
				<td>수량</td>
				<td>가격</td>
				<td>상태</td>
				<td>구매후기</td>
			</tr>
			<c:forEach var="order" items="${list }">
			<tr>
				<td>${order.thumbnail_img}</td>
				<td>${order.quantity}</td>
				<td>${order.option_price}</td>
				<td>구매 확정</td>
				<td>${order.review}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<!-- / 내 주문 상품. 끝. -->
	
	<!-- 결제 정보 -->
	<div>
		<h3>결제 정보</h3>
		<table>
			<tr>
				<td>결제 방법</td>
				<td>${order_code}</td>
				<td>주문자(아이디)</td>
				<td>${user_id}(${name})</td>
			</tr>
		</table>
	</div>
	<!-- / 결제 정보. 끝. -->
	
	<!-- 내 배송지 정보 -->
	<div>
	
	</div>
	<!-- / 내 배송지 정보. 끝. -->
	
	<button>목록으로</button>
</body>
</html>