<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>마이 냥멍 페이지</title>

<%@ include file="include/head.jsp"%>
</head>
<body>
	<%@ include file="include/header.jsp"%>
	<%@ include file="include/mypageheader.jsp"%>
	<!-- 최근 주문 내역 -->
	<div class="justify-content-center" style="text-align: center; margin: 50px; width: 1000px">
		<h4>최근 주문 내역</h4>
		<div class="justify-content-center">
			<table class="table justify-content-center mx-auto" style="text-align: center">
				<tr>
					<th class="col-md-1 table-light">주문일자</th>
					<th class="col-md-1 table-light">주문번호</th>
					<th class="col-md-3 table-light">대표제품</th>
					<th class="col-md-2 table-light">결제금액</th>
				</tr>
				<c:forEach var="order" items="{list }">
					<tr>
						<td>{order.주문일자}</td>
						<td>
							<a href="detailOrder"> {order.주문번호} </a>
						</td>
						<td>{order.대표제품}</td>
						<td>{order.total금액}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<button class="btn btn-outline-secondary justify-content-end" onclick="location.href='totalOrder'">전체 주문 내역</button>
	</div>
	<!-- / 최근 주문 내역. 끝. -->

	<!-- 최근 찜 내역 -->
	<div class="justify-content-center" style="text-align: center; margin: 50px; width: 1000px">
		<h4>최근 찜 내역</h4>
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
							<button class="btn btn-outline-secondary" onclick="location.href='찜 삭제, 장바구니 이동'">장바구니로 이동</button>
						</td>
					</tr>
				</c:forEach>
			</table>
			<button class="btn btn-outline-secondary" onclick="location.href='totalWish'">전체 찜 내역</button>
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
	<!-- / 최근 찜 내역. 끝. -->
</body>
</html>