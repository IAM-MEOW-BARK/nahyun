<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴</title>
<%@ include file="include/head.jsp"%>
<style>
.center-container {
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column; /* 세로 방향으로 정렬 */
}

.content-container {
	width: 1000px;
	margin: 20px; /* 표 간 간격 */
	justify-content: center;
}

.radio-form {
	display: flex;
	align-items: center;
	gap: 10px;
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
				<div class="content-container">
					<div style="text-align: center;">
						<h4>회원 정보 탈퇴신청</h4>
					</div>

					<div style="color: #aaaaaa; line-height: 1.5; text-align: center;">
						저희 쇼핑몰의 부족했던 점과 아쉬웠던 점을 적어주세요.<br> 더 좋은 모습으로 발전하도록 최선을
						다하겠습니다.<br> 앞으로 더 나은 모습으로 회원님을 다시 만날 수 있도록 노력하겠습니다.<br>
						그동안 이용해주셔서 진심으로 감사드립니다.<br>
					</div>
					<div
						style="color: #333333; margin-bottom: 20px; text-align: center;">
						탈퇴 사유를 적어주시면 냥멍몰 운영에 적극 반영하겠습니다.<br>
					</div>
					<div style="text-align: center;">
						<h4>탈퇴 사유</h4>
					</div>

					<div style="gap: 20px; max-width: 600px; margin: 0 auto;">
						<!-- 탈퇴 후 재가입 -->
						<div class="radio-form">
							<input class="form-check-input" type="radio" name="leave"
								id="rejoin" onclick="toggleTextarea(false)"> <label
								class="form-check-label" for="rejoin">탈퇴 후 재가입</label>
						</div>

						<!-- 배송 불만 -->
						<div class="radio-form">
							<input class="form-check-input" type="radio" name="leave"
								id="delivery_issue" onclick="toggleTextarea(false)"> <label
								class="form-check-label" for="delivery_issue">배송 불만</label>
						</div>

						<!-- 상품 다양성/가격품질 불만 -->
						<div class="radio-form">
							<input class="form-check-input" type="radio" name="leave"
								id="product_issue" onclick="toggleTextarea(false)"> <label
								class="form-check-label" for="product_issue">상품 다양성/가격품질
								불만</label>
						</div>

						<!-- 이용빈도 낮음 -->
						<div class="radio-form">
							<input class="form-check-input" type="radio" name="leave"
								id="low_usage" onclick="toggleTextarea(false)"> <label
								class="form-check-label" for="low_usage">이용빈도 낮음</label>
						</div>

						<!-- 사이트 이용 불편 -->
						<div class="radio-form">
							<input class="form-check-input" type="radio" name="leave"
								id="site_inconvenience" onclick="toggleTextarea(false)">
							<label class="form-check-label" for="site_inconvenience">사이트
								이용 불편</label>
						</div>

						<!-- 직접 입력 -->
						<div class="radio-form">
							<input class="form-check-input" type="radio" name="leave"
								id="other_reason" onclick="toggleTextarea(true)"> <label
								class="form-check-label" for="other_reason">직접 입력</label><br>
						</div>
						<div id="customReasonContainer"
							style="display: none; margin-top: 10px;">
							<textarea class="form-control" id="custom_reason"
								name="custom_reason" rows="2"
								style="width: 400px; height: 60px; margin-left: 30px; resize: none;"
								placeholder="탈퇴 사유를 입력해주세요"></textarea>
						</div>
					</div>
					<!-- 텍스트 입력창 -->
				</div>
				<div class="text-center">
					<input type="submit" class="btn btn-submit"
						style="background: #ff6600; color: #ffffff" value="회원 탈퇴">
					<button class="btn btn-outline-secondary">취소</button>
				</div>
			</div>
		</div>

	</div>
	<script>
		// 텍스트 입력창 표시/숨기기 함수
		function toggleTextarea(show) {
			const textareaContainer = document
					.getElementById('customReasonContainer');
			if (show) {
				textareaContainer.style.display = 'block'; // 텍스트창 표시
			} else {
				textareaContainer.style.display = 'none'; // 텍스트창 숨기기
			}
		}
	</script>
</body>
</html>