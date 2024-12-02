<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>

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

.product-quantity-control input {
	width: 40px;
	text-align: center;
	padding: 2px;
}

.product-quantity-control button {
	width: 30px;
	padding: 2px;
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
				<!-- 장바구니 -->
				<div class="table-container">
					<h4>장바구니</h4>
					<form action="">
					<table class="table justify-content-center align-middle" style="text-align: center">
						<tr>
							<th class="col-md-1 table-light"><input type="checkbox" name="cart" value="selectall" onclick="selectAll(this)" checked /></th>
							<th class="col-md-6 table-light" colspan="2">상품명</th>
							<th class="col-md-2 table-light">수량</th>
							<th class="col-md-2 table-light">가격</th>
							<th class="col-md-2 table-light">삭제</th>
						</tr>
						<c:forEach var="item" items="${cartInfo}">
							<tr>
								<td>
									<input type='checkbox' name='item' value='${item.product_code}' checked />
								</td>
								<td>
									<img src="${pageContext.request.contextPath}/resources/upload/${item.thumbnail_img}" alt="${item.product_name}" style="width: 30px; height: 30px;">
								</td>
								<td style="text-align: left;">${item.product_name}</td>
								<td>
									<div class="product-quantity-control">
										<button class="btn btn-outline-secondary btn-decrease">-</button>
										<input type="text" class="form-control d-inline quantity" value="${item.cart_quantity}" readonly>
										<button class="btn btn-outline-secondary btn-increase">+</button>
									</div>
								</td>
								<td class="price" data-price="${item.product_price}">
									<fmt:formatNumber value="${item.product_price}" type="number" groupingUsed="true" />
									원
								</td>
								<td>
									<form action="/cart/delete" method="post" class="cart_delete">
										<button class="btn btn-outline-secondary delete_btn">삭제</button>
										<input type="hidden" name="user_id" value="${user_id}"> <input type="hidden" name="product_code" value="${product_code}">
									</form>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>

				<div class="table-container d-flex justify-content-end" style="align-items: flex-end;">
					<table>
						<tr>
							<td style="text-align: right; padding-right: 20px">
								총 금액: <span id="finalPriceTag">0</span>
							</td>
							<td>
								<button class="btn" style="background: #ff6600; color: #ffffff" onclick="location.href='order'">구매하기</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<!-- / 장바구니. 끝. -->

	<script type="text/javascript">
	
	function selectAll(masterCheckbox) {
	    // 마스터 체크박스 상태 확인
	    const isChecked = masterCheckbox.checked;

	    // 모든 개별 체크박스 선택
	    const checkboxes = document.querySelectorAll('input[name="item"]');
	    checkboxes.forEach((checkbox) => {
	        checkbox.checked = isChecked; // 마스터 체크박스 상태에 따라 체크 상태 변경
	    });

	    // 총 금액 업데이트
	    updateFinalPrice();
	}
	
	document.addEventListener("DOMContentLoaded", function () {
	    // 수량 변경 버튼 이벤트 위임
	    document.querySelector("table").addEventListener("click", function (e) {
	        if (e.target.classList.contains("btn-increase")) {
	            updateQuantity(e.target, 1); // 수량 증가
	        } else if (e.target.classList.contains("btn-decrease")) {
	            updateQuantity(e.target, -1); // 수량 감소
	        }
	        updateFinalPrice();
	    });

	    // 수량 업데이트 함수
	    function updateQuantity(button, change) {
	        const row = button.closest("tr");
	        const input = row.querySelector(".quantity");
	        const price = parseInt(row.querySelector(".price").dataset.price);

	        let quantity = parseInt(input.value);
	        quantity = Math.max(1, quantity + change); // 최소 수량 1로 제한
	        input.value = quantity;

	        const totalPriceElement = row.querySelector(".price");
	        totalPriceElement.innerText = (price * quantity).toLocaleString() + "원";

	        updateFinalPrice();
	    }

	    // 총 금액 업데이트
function updateFinalPrice() {
    const rows = document.querySelectorAll("table tr");
    let total = 0;

    rows.forEach((row) => {
        const checkbox = row.querySelector('input[name="item"]');
        if (checkbox && checkbox.checked) {
            const price = parseInt(row.querySelector(".price").dataset.price);
            const quantity = parseInt(row.querySelector(".quantity").value);
            total += price * quantity;
        }
    });

    document.querySelector("#finalPriceTag").innerText = total > 0 ? total.toLocaleString() + "원" : "0원";
}
	    // 체크박스 선택 시 총 금액 업데이트
	    document.querySelectorAll('input[name="item"]').forEach((checkbox) => {
	        checkbox.addEventListener("change", updateFinalPrice);
	    });

	    // 페이지 로드 시 총 금액 계산
	    updateFinalPrice();
	});

</script>
</body>
</html>