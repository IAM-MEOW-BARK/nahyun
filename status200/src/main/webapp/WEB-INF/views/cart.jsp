<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

					<table class="table justify-content-center align-middle" style="text-align: center">
						<tr>
							<th class="col-md-1 table-light"><input type='checkbox' name='cart' value='selectall' onclick='selectAll(this)' /></th>
							<th class="col-md-6 table-light" colspan="2">상품명</th>
							<th class="col-md-2 table-light">수량</th>
							<th class="col-md-2 table-light">가격</th>
							<th class="col-md-2 table-light">삭제</th>
						</tr>
						<c:forEach var="item" items="${cartInfo}">
							<tr>
								<td>
									<input type='checkbox' name='item' value='${item.product_code}' />
								</td>
								<td><img src="${pageContext.request.contextPath}/resources/upload/${item.thumbnail_img}" alt="${item.product_name }" style="width: 30px; height: 30px;" ></td>
								<td style="text-align: left;">${item.product_name }</td>
								<td>
									<div class="product-quantity-control">
										<button type="button" class="btn btn-outline-secondary" onclick="decreaseQuantity(this)">-</button>
										<input type="text" class="form-control d-inline" value="${item.cart_quantity }" readonly>
										<button type="button" class="btn btn-outline-secondary" onclick="increaseQuantity(this)">+</button>
									</div>
								</td>
								<td>${item.totalPrice}원</td>
								<!-- 구매 수량 늘어나면 가격 자동 변경 -->
								<td>
									<button class="btn btn-outline-secondary">삭제</button>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>

				<div class="table-container d-flex justify-content-end" style="align-items: flex-end;">
					<table>
						<tr>
							<td style="text-align: right; padding-right: 20px">
								총 {n}개 상품 금액 <br>{nn,nnn}원
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

function selectAll(selectAll)  {
	  const checkboxes 
	     = document.querySelectorAll('input[type="checkbox"]');
	  
	  checkboxes.forEach((checkbox) => {
	    checkbox.checked = selectAll.checked
	  })
	}
	
function decreaseQuantity(button) {
    const input = button.nextElementSibling;
    let quantity = parseInt(input.value);
    if (quantity > 1) {
        input.value = quantity - 1;
    }
}

function increaseQuantity(button) {
    const input = button.previousElementSibling;
    let quantity = parseInt(input.value);
    input.value = quantity + 1;
}
	
</script>

</body>
</html>