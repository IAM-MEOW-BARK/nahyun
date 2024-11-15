<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이 냥멍 페이지</title>
</head>
<body>

<!-- 메인 페이지 고정 상단 영역 -->
<div>
${name} 님<br>
오늘도 좋은 하루 되세냥🐱
</div>
<!-- / 메인 페이지 고정 상단 영역. 끝. -->

<!-- 최근 주문 내역 -->
<div>
<p>최근 주문 내역</p>

<table class="table">
	<tr>
		<td>주문일자</td>
		<td>주문번호</td>
		<td>대표제품</td>
		<td>결제금액</td>
	</tr>
	<c:forEach var="order" items="${list }">
	<tr>
		<td>${order.주문일자}</td>
		<td><a href="detail?bno=${order.주문번호}"> ${order.주문번호} </a></td>
		<td>${order.대표제품}</td>
		<td>${order.total금액}</td>
	</tr>
	</c:forEach>
</table>
<button>
전체 주문 내역
</button>
</div>
<!-- / 최근 주문 내역. 끝. -->

<!-- 최근 찜 내역 -->
<div>
<p>최근 찜 내역</p>


<table class="table">
	<tr>
		<td>
			<input type='checkbox'
					name='wish' 
					value='selectall'
					onclick='selectAll(this)'/>
		</td>
		<td colspan="2">제품</td>
		<td>가격</td>
		<td>장바구니 이동</td>
	</tr>
	<c:forEach var="wish" items="${wishlist }">
	<tr>
		<td>
			<input type='checkbox'
			       name='wish' 
			       value='${wish.item}'/> </td>
		<td><a href="detail?bno=${wish.제품번호}"> ${wish.제품이미지} </a></td>
		<td>${wish.제품이름}</td>
		<td>${wish.제품금액}</td>
		<td><button onclick="찜 삭제, 장바구니 이동">장바구니로 이동</button></td>
	</tr>
	</c:forEach>
</table>


</div>
<!-- / 최근 찜 내역. 끝. -->
</body>
</html>