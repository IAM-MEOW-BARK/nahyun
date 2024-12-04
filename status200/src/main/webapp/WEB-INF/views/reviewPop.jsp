<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>상품 리뷰 작성</title>
<%@ include file="include/head.jsp"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
h5 {
	margin-bottom: 5px; /* 제목과 별점 사이의 여백 축소 */
}

.review-container {
	width: 500px;
	margin: 30px auto;
	padding: 20px;
	border: 1px solid #ddd;
	border-radius: 10px;
	background-color: #f9f9f9;
}

.review-header {
	display: flex;
	align-items: center;
	margin-bottom: 20px;
}

.review-header img {
	width: 80px;
	height: 80px;
	border: 1px solid #ccc;
	margin-right: 15px;
}

.review-header .product-info {
	font-size: 16px;
}

.review-stars {
	display: flex;
	gap: 5px;
	margin: -20px 0 10px 0;
}

.review-stars span {
	font-size: 70px;
	color: #dddddd;
	cursor: pointer;
}

.review-stars:hover {
	color: #ff6600;
}

.review-textarea {
	width: 100%;
	height: 150px;
	margin: 10px 0;
	padding: 10px;
	border-radius: 5px;
	border: 1px solid #ccc;
	resize: none;
}

.upload-section {
	display: flex;
	justify-content: center;
	align-items: center;
	border: 2px dashed #ddd;
	padding: 20px;
	border-radius: 5px;
	margin-bottom: 15px;
	cursor: pointer;
}

.upload-section:hover {
	background-color: #f1f1f1;
}

.btn-container {
	display: flex;
	justify-content: space-between;
}

.btn-cancel, .btn-submit {
	width: 48%;
	padding: 10px;
	font-size: 16px;
}

/* .btn-container {
	display: flex;
	justify-content: center;
}

.btn-cancel, .btn-submit {
	width: 100%;
	padding: 10px;
	font-size: 16px;
} */
</style>
</head>
<body>

	<div class="review-container">
		<b>구매한 제품</b>
		<div class="review-header">

			<img
				src="${pageContext.request.contextPath}/resources/upload/${thumbnail_img}"
				alt="상품 이미지">
			<div class="product-info">
				<input type="text" value="${product_name}" readonly> <input
					type="hidden" value="${product_code}"> <input type="hidden"
					value="${user_id}">
			</div>
		</div>

		<b>상품은 만족하셨나요?</b>

		<div class="review-stars">
			<span>&#9733;</span> <span>&#9733;</span> <span>&#9733;</span> <span>&#9733;</span>
			<span>&#9733;</span>
		</div>


		<b>어떤 점이 좋았나요?</b>

		<textarea class="review-textarea"
			placeholder="사용하시면서 좋았던 점을 작성해주세요. (최소 10자 이상)"></textarea>

		<b>(선택) 사진 첨부하기</b>

		<div class="upload-section">

			<form name="fileForm" method="post" enctype="multipart/form/data"
				action="fileupload01.jsp">
				<input type="file" name="reviewpic">
			</form>

		</div>

		<div class="btn-container">
			<button class="btn btn-secondary btn-cancel" onclick="self_close()">취소</button>
			<button class="btn btn-submit"
				style="background: #ff6600; color: #ffffff">리뷰 제출</button>
		</div>
	</div>

	<script type="text/javascript">
	
    // 별점 Hover 및 클릭 동작
    const stars = document.querySelectorAll('.review-stars span');
    let clickedIndex = -1; // 클릭된 별점 인덱스를 저장

    // 별점 Hover 시 색상 변경
    stars.forEach((star, index) => {
        star.addEventListener('mouseover', () => {
            for (let i = 0; i < stars.length; i++) {
                stars[i].style.color = i <= index ? '#ff6600' : '#dddddd';
            }
        });

        // Hover 해제 시 색상 초기화 또는 클릭된 상태 유지
        star.addEventListener('mouseout', () => {
            for (let i = 0; i < stars.length; i++) {
                stars[i].style.color = i <= clickedIndex ? '#ff6600' : '#dddddd';
            }
        });

        // 별 클릭 시 선택된 별점 색상 유지
        star.addEventListener('click', () => {
            clickedIndex = index; // 클릭된 별점 인덱스 저장
            for (let i = 0; i < stars.length; i++) {
                stars[i].style.color = i <= clickedIndex ? '#ff6600' : '#dddddd';
            }
        });
    });
        
});

function self_close() {
	window.open('', '_self');
	window.close();
	self.close();
}
</script>
</body>
</html>
