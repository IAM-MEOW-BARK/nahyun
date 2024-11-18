<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
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
		<div class="table-container">
			<table class="table" style="text-align: center;">
				<tr>
					<th class="table-light">이름</th>
					<td>박송송</td>
				</tr>
				<tr>
					<th class="table-light">아이디</th>
					<td>song0920@mbc.com</td>
				</tr>
				<tr>
					<th class="table-light">새 비밀번호</th>
					<td>
						<input type="text">
					</td>
				</tr>
				<tr>
					<th class="table-light">새 비밀번호 확인</th>
					<td>
						<input type="text">
					</td>
				</tr>
				<tr>
					<th class="table-light">휴대전화 번호</th>
					<td>
						<input type="text">
					</td>
				</tr>
				<tr>
					<th class="table-light">주소</th>
					<td>
						<input type="text">
						<button value="우편번호 검색"></button>
						<br> <input type="text"><br>
						<input type="text">
					</td>
				</tr>
			</table>
			<a href="deleteUser"><span>회원 탈퇴</span></a>
		</div>
		<button class="btn btn-outline-secondary" onclick="location.href='mypage'">취소</button>
		<input type="submit" class="btn btn-outline-secondary" value="수정">
	</div>
</body>
</html>