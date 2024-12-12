<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 주문 내역</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">

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

.modal-content {
	border-radius: 10px;
	padding: 20px;
	background-color: #f9f9f9;
}

.modal-header {
	border-bottom: none;
	justify-content: center;
}

.modal-footer {
	border-top: none;
	justify-content: center;
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
				<!-- 주문 정보 -->
				<div class="table-container">
					<table class="table" style="text-align: center;">
						<tr>
							<th class="col-md-1 table-light">주문 번호</th>
							<td class="col-md-2">${orderDetail.orderCode}</td>
							<th class="col-md-1 table-light">주문자(아이디)</th>
							<td class="col-md-2">${orderDetail.name}(${orderDetail.userId})</td>
						</tr>
					</table>
				</div>
				<!-- / 주문 정보. 끝. -->

				<!-- 내 주문상품 정보 -->
				<div class="table-container">
					<h5>내 주문상품 정보</h5>
					<table class="table" style="text-align: center;">
						<tr>
							<th class="table-light" colspan="2">제품명</th>
							<th class="table-light">수량</th>
							<th class="table-light">가격</th>
							<th class="table-light">구매후기</th>
						</tr>

						<c:forEach var="item" items="${orderItemDetail}">

							<tr>
								<td class="col-md-1" style="vertical-align: middle;">
									<img src="${pageContext.request.contextPath}/resources/upload/${item.thumbnailImg}" alt="${item.productName}" style="width: 50px; height: 50px;">
								</td>
								<td class="col-md-2" style="vertical-align: middle;">${item.productName}</td>
								<td class="col-md-1" style="vertical-align: middle;">${item.orderQuantity}개</td>
								<td class="col-md-1" style="vertical-align: middle;">
									<fmt:formatNumber value="${item.productPrice}" pattern="#,###원" />
									<br>
									<fmt:formatNumber value="${item.totalPrice}" pattern="#,###원" />
								</td>
								<!-- 구매후기 버튼 -->
								<td class="col-md-1" style="vertical-align: middle;">
									<c:choose>
										<c:when test="${item.review}">
											<button class="btn btn-secondary" disabled>작성 완료</button>
										</c:when>
										<c:otherwise>
											<button class="btn btn-outline-secondary btn-review" data-product-code="${item.productCode}" data-user-id="${orderDetail.userId}">리뷰 작성</button>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<!-- / 내 주문상품 정보. 끝. -->

				<!-- 결제 정보 -->
				<div class="table-container">
					<h5>결제 정보</h5>
					<table class="table" style="text-align: center;">
						<tr>
							<th class="table-light col-md-1">결제 방법</th>
							<td>신용카드</td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<th class="table-light col-md-1">결제금액</th>
							<td class="col-md-2">
								<fmt:formatNumber value="${totalCost}" pattern="#,###원" />
							</td>
							<th class="table-light col-md-1">결제 처리일</th>
							<td class="col-md-2">${orderDetail.orderedAt}</td>
						</tr>
					</table>
				</div>
				<!-- / 결제 정보. 끝. -->

				<!-- 내 배송지 정보 -->
				<div class="table-container">
					<h5>내 배송지 정보</h5>
					<table class="table">
						<tr>
							<th class="table-light col-md-1" style="text-align: center;">받으실 분</th>
							<td class="col-md-5" style="text-align: left;">${orderDetail.name}</td>
						</tr>
						<tr>
							<th class="table-light align-middle" style="text-align: center;">주소</th>
							<td style="text-align: left;">
								${orderDetail.zipcode}<br> ${orderDetail.address}<br> ${orderDetail.detailAddress}
							</td>
						</tr>
						<tr>
							<th class="table-light" style="text-align: center;">휴대폰번호</th>
							<td style="text-align: left;">${orderDetail.phoneNum}</td>
						</tr>
					</table>
				</div>
				<!-- / 내 배송지 정보. 끝. -->

				<!-- 목록으로 버튼 -->
				<div class="center-container" style="margin-bottom: 50px;">
					<button class="btn btn-secondary" onclick="location.href='totalOrder'">목록으로</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 모달 -->
	<div class="modal fade" id="reviewModal" tabindex="-1" aria-labelledby="reviewModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="reviewModalLabel">
						<b>리뷰 작성</b>
					</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form id="reviewForm" enctype="multipart/form-data" action="regReview" method="post">
						<div class="review-header" style="text-align: center;">
							<img id="productImg" src="${pageContext.request.contextPath}/resources/upload/${thumbnailImg}" alt="상품 이미지" style="width: 120px; height: 120px; margin-bottom: 10px;">
							<div class="product-info">
								<input type="text" id="productName" name="product_name" class="form-control" readonly><br> <input type="hidden" id="productCode" name="product_code"> <input type="hidden" id="userId" name="user_id">
							</div>
						</div>
						<div class="mb-3">
							<label><b>상품은 만족하셨나요?</b></label>
							<div class="review-stars">
								<input type="hidden" id="reviewScore" name="review_score" value="0">
								<h1 style="color: #dddddd">
									<span data-score="1">&#9733;</span> <span data-score="2">&#9733;</span> <span data-score="3">&#9733;</span> <span data-score="4">&#9733;</span> <span data-score="5">&#9733;</span>
								</h1>
							</div>
						</div>

						<div class="mb-3">
							<label><b>어떤 점이 좋았나요?</b></label>
							<textarea class="form-control" id="reviewContent" name="review_content" placeholder="최소 10자 이상 입력해주세요."></textarea>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
							<button type="submit" class="btn btn-primary" onclick="submitReview(event)">리뷰 제출</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>


	<!-- 모달 끝. -->

	<script type="text/javascript">
    // 리뷰 버튼 클릭
    $(document).on("click", ".btn-review", function () {
        const productCode = $(this).data("product-code");
        const userId = $(this).data("user-id");

        console.log("☆☆☆☆☆☆☆☆☆☆☆☆☆");
        // Ajax로 상품 정보를 가져와 모달에 반영
        $.ajax({
            type: "GET",
            url: "/getProductInfo",
            data: { product_code: productCode, user_id: userId },
            success: function (response) {
                // 모달에 데이터 세팅
                console.log("모달 열었어요 뿌우 ><");
                $("#productImg").attr("src", "/resources/upload/" + response.thumbnail_img);
                $("#productName").val(response.product_name);
                $("#productCode").val(productCode);
                $("#userId").val(userId);

                // 모달 열기
                const reviewModal = new bootstrap.Modal(document.getElementById("reviewModal"));
                reviewModal.show();
            },
            error: function () {
                alert("상품 정보를 불러오는 중 오류가 발생했습니다.");
            },
        });
    });
    
 // 리뷰 제출
 
function submitReview(event) {
        event.preventDefault();

        // 리뷰 폼에서 입력 값 가져오기
        const productName = document.getElementById('productName').value;
        const productCode = document.getElementById('productCode').value;
        const userId = document.getElementById('userId').value;
        const reviewScore = document.getElementById('reviewScore').value;
        const reviewContent = document.getElementById('reviewContent').value;

        // 입력 값 디버깅용 출력
        console.log("Product Name:", productName);
        console.log("Product Code:", productCode);
        console.log("User ID:", userId);
        console.log("Review Score:", reviewScore);
        console.log("Review Content:", reviewContent);

        // 필수 입력값 확인
        if (!productCode || !reviewScore || !reviewContent) {
            alert("필수 정보를 모두 입력해주세요.");
            return;
        }

        // FormData 객체 생성 및 값 추가
        const formData = new FormData();
        formData.append('product_code', productCode);
        formData.append('user_id', userId);
        formData.append('review_score', reviewScore);
        formData.append('review_content', reviewContent);

        // 서버로 데이터 전송
        fetch('regReview', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json' // JSON 형식으로 데이터 전송
            },
            body: JSON.stringify({
                product_code: productCode,
                user_id: userId,
                review_score: reviewScore,
                review_content: reviewContent
            })
        })
        .then(response => {
            if (response.ok) {
                alert('리뷰가 성공적으로 등록되었습니다!');
                window.location.reload(); // 페이지 새로고침
            } else {
                alert('리뷰 등록에 실패했습니다. 다시 시도해주세요.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('서버 요청 중 오류가 발생했습니다.');
        });
    }
    // 리뷰 제출. 끝

    
    // 모달 닫힐 때 초기화
    $("#reviewModal").on("hidden.bs.modal", function () {
        $("#reviewForm")[0].reset(); // 폼 초기화
        $("#productImg").attr("src", ""); // 이미지 초기화
        $(".review-stars span").css("color", "#dddddd"); // 별점 초기화
        $("#reviewScore").val(0); // 별점 값 초기화
    });

    // 별점 선택
    $(document).on("click", ".review-stars span", function () {
        const score = $(this).data("score");
        $("#reviewScore").val(score);

        // 별점 색상 업데이트
        $(".review-stars span").each(function (index) {
            $(this).css("color", index < score ? "#ff6600" : "#dddddd");
        });
    });
</script>


</body>
</html>
