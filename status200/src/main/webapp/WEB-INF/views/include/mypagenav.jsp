<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 네비게이션</title>
<style>
.list-group-item.active {
	background-color: #ff6600; /* 배경색: 주황색 */
	color: #ffffff; /* 글씨색: 흰색 */
	font-weight: bold; /* 글씨 굵게 */
}
</style>
</head>
<body>
	<nav class="col-md-2 mb-2">
		<div class="list-group">
			<!-- 현재 페이지가 'orderList'일 경우 활성화 클래스 추가 -->
			<c:set var="currentPage" value="${param.page}" />
			<a href="mypage" class="list-group-item list-group-item-action ${currentPage == 'mypage' ? 'active' : ''}"> 마이페이지 </a>
			<a href="totalOrder" class="list-group-item list-group-item-action ${currentPage == 'totalOrder' ? 'active' : ''}"> 주문 내역 </a>
			<a href="totalWish" class="list-group-item list-group-item-action ${currentPage == 'wish' ? 'active' : ''}"> 찜 내역 </a>
			<a href="updateProfile" class="list-group-item list-group-item-action ${currentPage == 'updateProfile' ? 'active' : ''}"> 정보 수정 </a>
			<a href="questionList" class="list-group-item list-group-item-action ${currentPage == 'questionList' ? 'active' : ''}"> 문의 내역 </a>
			<a href="returnExchange" class="list-group-item list-group-item-action ${currentPage == 'returnExchange' ? 'active' : ''}"> 반품/교환 </a>
		</div>
	</nav>
</body>
</html>