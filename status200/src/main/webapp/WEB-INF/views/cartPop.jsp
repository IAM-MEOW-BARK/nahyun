<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
.popup {
	width: 500px;
	margin: 50px auto;
	border: 1px solid #ccc;
	border-radius: 8px;
	background: #fff;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	padding: 20px;
}

.close-btn {
	font-size: 1.5rem;
	cursor: pointer;
}

.product-image {
	width: 100px;
	height: 100px;
	background: #ddd;
	border-radius: 5px;
}

.btn-orange {
	background-color: #ff6600;
	border-color: #ff6600;
	color: white;
}

.btn-orange:hover {
	background-color: #e65c00;
	border-color: #e65c00;
}
</style>
</head>
<body>
	<div class="popup">
		<!-- Header -->
		<div class="d-flex justify-content-between align-items-center mb-3">
			<h5 class="m-0">장바구니</h5>
			<span class="close-btn" onclick="window.close()">×</span>
		</div>

		<!-- Product Info -->
		<div class="d-flex mb-3">
			<div class="product-image me-3"></div>
			<div>
				<h6 class="mb-2">알록달록 장난감 제품명</h6>
				<p class="fw-bold mb-0">상품 금액</p>
			</div>
		</div>

		<!-- Options -->
		<div class="mb-3">
			<select class="form-select" aria-label="옵션 선택">
				<option value="" disabled selected>옵션을 선택하세요.</option>
				<option value="option1">옵션 1</option>
				<option value="option2">옵션 2</option>
			</select>
		</div>

		<!-- Add to Cart Button -->
		<div class="text-center">
			<button class="btn btn-orange px-4" onclick="alert('장바구니에 추가되었습니다.')">장바구니</button>
		</div>
	</div>

	<!-- Bootstrap JS (Optional) -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>