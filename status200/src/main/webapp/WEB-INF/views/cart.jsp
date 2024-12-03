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
				<form method="post" action="/cart" id="cartForm">
					<div class="table-container">
						<h4>장바구니</h4>
						<table class="table justify-content-center align-middle" style="text-align: center">
							<tr>
								<th><input type="checkbox" id="selectAll" name="cart" value="selectall" checked /></th>
								<th colspan="2">상품명</th>
								<th>수량</th>
								<th>가격</th>
								<th>삭제</th>
							</tr>
							<c:forEach var="item" items="${cartInfo}">
								<tr data-product-code="${item.product_code}" data-product-price="${item.product_price}">
									<td class="cart_info_td">
									<input type="hidden" class="hidden_product_code_input" value="${itme.product_code }">
									<input type="hidden" class="hidden_product_name_input" value="${itme.product_name }">
									<input type="hidden" class="hidden_product_price_input" value="${itme.product_price }">
									<input type="hidden" class="hidden_cart_quantity_input" value="${itme.cart_quantity }">
									<input type="hidden" class="hidden_totalPrice_input" value="${itme.product_price * cart_quantity }">
										<input type="checkbox" class="individual_cart_checkbox" name="selectedItems" value="${item.product_code}" checked /> <input type="hidden" name="product_code" value="${item.product_code}"> <input type="hidden" name="product_price" value="${item.product_price}"> <input type="hidden" name="cart_quantity" value="${item.cart_quantity}"> <input type="hidden" name="product_name" value="${item.product_name}">
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
											<a class="quantity_modify_btn">변경</a>
										</div>
									</td>
									<td class="price" data-price="${item.product_price}">
										<fmt:formatNumber value="${item.product_price}" pattern="#,### 원" /> <br>
										<b><fmt:formatNumber value="${item.product_price * item.cart_quantity}" pattern="#,### 원" /></b>
									</td>
									<td>
										<button class="btn btn-outline-secondary delete_btn">삭제</button>
									</td>
								</tr>

							</c:forEach>
						</table>
					</div>
					<div class="table-container d-flex justify-content-end">
						<button type="button" class="btn delete-selected-btn">선택 삭제</button>
						<table>
							<tr>
								<td style="text-align: right; padding-right: 20px">
									총 금액: <span id="finalPriceTag">0</span>
								</td>
								<td>
									<input type="hidden" name="user_id_fk" value="${user_id}">
									<button class="btn order_btn" type="button" style="background: #ff6600; color: #ffffff">구매하기</button>
								</td>
							</tr>
						</table>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- / 장바구니. 끝. -->

<script type="text/javascript">
$(document).ready(function () {
    // 선택 삭제
    $(".delete-selected-btn").on("click", function () {
        const selectedItems = $(".select-item:checked").map(function () {
            return $(this).val();
        }).get();

        if (selectedItems.length === 0) {
            alert("삭제할 항목을 선택해주세요.");
            return;
        }

        $.ajax({
            type: "POST",
            url: "/cart/deleteSelected",
            data: { selectedItems: selectedItems },
            traditional: true,
            success: function () {
                alert("선택한 항목이 삭제되었습니다.");
                location.reload();
            },
            error: function () {
                alert("삭제 중 오류가 발생했습니다.");
            },
        });
    });

    // 선택 구매
    $(".order-btn").on("click", function () {
        const selectedItems = $(".select-item:checked");
        if (selectedItems.length === 0) {
            alert("구매할 항목을 선택해주세요.");
            return;
        }

        let formContents = "";
        selectedItems.each(function () {
            const row = $(this).closest("tr");
            const productCode = $(this).val();
            const cartQuantity = row.find(".quantity").val();
            formContents += `<input type='hidden' name='selectedItems' value='${productCode}'>`;
            formContents += `<input type='hidden' name='cart_quantity_${productCode}' value='${cartQuantity}'>`;
        });

        $(".order-form").html(formContents).submit();
    });
});
</script>
</body>
</html>