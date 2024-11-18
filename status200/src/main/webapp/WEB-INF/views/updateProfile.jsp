<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<!-- 마이페이지 -->
	<div class="container-lg my-5">
		<div class="row">
			<!-- 왼쪽 내비게이션 -->
			<%@ include file="include/mypagenav.jsp"%>

			<!-- 오른쪽 콘텐츠 -->
			<div class="col-md-9">
				<div class="table-container">
				<h4>내 정보 수정</h4>
					<table class="table" style="text-align: center;">
						<tr>
							<th class="table-light col-md-1">이름</th>
							<td class="col-md-6">
								<div style="text-align: center; max-width: 15em;">
									<input class="form-control" type="text" placeholder="박송송"
										readonly>
								</div>
							</td>
						</tr>
						<tr>
							<th class="table-light">아이디</th>
							<td>
								<div style="text-align: center; max-width: 15em;">
									<input class="form-control" type="text"
										placeholder="song@mbc.com" readonly>
								</div>
							</td>
						</tr>
						<tr>
							<th class="table-light">새 비밀번호</th>
							<td><div style="text-align: center; max-width: 15em;">
									<input class="form-control" type="text">
								</div></td>
						</tr>
						<tr>
							<th class="table-light">새 비밀번호 확인</th>
							<td><div style="text-align: center; max-width: 15em;">
									<input class="form-control" type="text">
								</div></td>
						</tr>
						<tr>
							<th class="table-light">휴대전화 번호</th>
							<td><div style="text-align: center; max-width: 15em;">
									<input class="form-control" type="text">
								</div></td>
						</tr>
						<tr>
							<th class="table-light align-middle" style="text-align: center;">주소</th>
							<td>
								<div
									style="display: flex; align-items: center; gap: 10px; max-width: 18em;">
									<input class="form-control" type="text" id="postcode"
										name="zipcode" readonly>
									<button class="btn"
										style="background-color: #ff6600; color: #ffffff; white-space: nowrap; padding: 5px 10px; margin-bottom: 5px;"
										onclick="checkPost()">우편번호 검색</button>
								</div>
								<div style="text-align: center; max-width: 30em; margin: 5px;">
									<input class="form-control" type="text" id="address"
										name="addr1" size="50" placeholder="주소" readonly>
								</div>
								<div style="text-align: center; max-width: 30em; margin: 5px;">
									<input class="form-control" type="text" id="detailAddress"
										name="addr2" size="50" placeholder="상세주소">
								</div>
							</td>
						</tr>
					</table>
					<div class="d-flex justify-content-end">
					<a href="deleteUser"><span>회원 탈퇴</span></a>
					</div>
				</div>
				<div class="d-flex justify-content-center">
				<button class="btn btn-outline-secondary"
					onclick="location.href='mypage'" style="margin: 10px">취소</button>
				<input type="submit" class="btn" style="background-color: #ff6600; color: #ffffff; margin: 10px;" value="수정">
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		function checkPost() {
			new daum.Postcode(
					{
						oncomplete : function(data) {
							// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

							// 각 주소의 노출 규칙에 따라 주소를 조합한다.
							// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
							var addr = ''; // 주소 변수
							var extraAddr = ''; // 참고항목 변수

							//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
							if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
								addr = data.roadAddress;
							} else { // 사용자가 지번 주소를 선택했을 경우(J)
								addr = data.jibunAddress;
							}

							// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
							if (data.userSelectedType === 'R') {
								// 법정동명이 있을 경우 추가한다. (법정리는 제외)
								// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
								if (data.bname !== ''
										&& /[동|로|가]$/g.test(data.bname)) {
									extraAddr += data.bname;
								}
								// 건물명이 있고, 공동주택일 경우 추가한다.
								if (data.buildingName !== ''
										&& data.apartment === 'Y') {
									extraAddr += (extraAddr !== '' ? ', '
											+ data.buildingName
											: data.buildingName);
								}
								// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
								if (extraAddr !== '') {
									extraAddr = ' (' + extraAddr + ')';
								}
								// 조합된 참고항목을 해당 필드에 넣는다.
								document.getElementById("detailAddress").value = extraAddr;

							} else {
								document.getElementById("detailAddress").value = '';
							}

							// 우편번호와 주소 정보를 해당 필드에 넣는다.
							document.getElementById('postcode').value = data.zonecode;
							document.getElementById("address").value = addr;
							// 커서를 상세주소 필드로 이동한다.
							document.getElementById("detailAddress").focus();
						}
					}).open();
		}
	</script>
</body>
</html>