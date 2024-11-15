<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이 냥멍 페이지</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <meta charset="utf-8" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/vendor.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/style.css">
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css"
    />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
      crossorigin="anonymous"
    />

    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Nunito:wght@400;700&family=Open+Sans:ital,wght@0,400;0,700;1,400;1,700&display=swap"
      rel="stylesheet"
    />
</head>
<body>

<%@ include file="include/header.jsp" %>

<!-- 메인 페이지 고정 상단 영역 -->
<div style="background-color: #f0f0f0;
	 padding: 50px">
{name} 님<br>
오늘도 좋은 하루 되세냥🐱
</div>
<!-- / 메인 페이지 고정 상단 영역. 끝. -->

<!-- 최근 주문 내역 -->
<div>
<h3>최근 주문 내역</h3>

<table class="table" style="border: 1px;
			  border-collapse : collapse;">
	<tr>
		<td>주문일자</td>
		<td>주문번호</td>
		<td>대표제품</td>
		<td>결제금액</td>
	</tr>
	<c:forEach var="order" items="{list }">
	<tr>
		<td>{order.주문일자}</td>
		<td><a href="detail?bno={order.주문번호}"> {order.주문번호} </a></td>
		<td>{order.대표제품}</td>
		<td>{order.total금액}</td>
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
<h3>최근 찜 내역</h3>


<table class="table" style="border: 1px;">
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
	<c:forEach var="wish" items="{wishlist }">
	<tr>
		<td>
			<input type='checkbox'
			       name='wish' 
			       value='{wish.item}'/> </td>
		<td><a href="detail?bno={wish.제품번호}"> {wish.제품이미지} </a></td>
		<td>{wish.제품이름}</td>
		<td>{wish.제품금액}</td>
		<td><button onclick="찜 삭제, 장바구니 이동">장바구니로 이동</button></td>
	</tr>
	</c:forEach>
</table>


</div>

<script type="text/javascript">

function selectAll(selectAll)  {
	  const checkboxes 
	     = document.querySelectorAll('input[type="checkbox"]');
	  
	  checkboxes.forEach((checkbox) => {
	    checkbox.checked = selectAll.checked
	  })
	}
</script>

<!-- / 최근 찜 내역. 끝. -->
</body>
</html>