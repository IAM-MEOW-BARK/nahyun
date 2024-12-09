<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>내가 바로 🐱냥냥멍멍🐶</title>

<style type="text/css">
.swiper {
    width: 100%;
    padding: 20px 0;
}

.swiper-slide {
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>

<%@ include file="include/head.jsp"%>

<!-- Swiper.js CSS -->
<link rel="stylesheet"
	href="https://unpkg.com/swiper/swiper-bundle.min.css" />

<!-- Swiper.js JavaScript -->
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
</head>
<body>


	<%@ include file="include/header.jsp"%>

	<section
		style="display: flex; justify-content: center; align-items: center; background-color: #FFCF84; height: 300px;">

		<a href="noticeList"> <img
			src="${pageContext.request.contextPath}/resources/bootstrap/images/main-banner.png"
			alt="배너 이미지" style="max-width: 100%; height: 300px;" />
		</a>
	</section>

	<!-- 카테고리 -->
	<section class="py-5 overflow-hidden">
		<div class="container-lg">
			<div class="col-md-12">
				<div class="category-carousel swiper d-flex justify-content-center">
					<div
						class="swiper-wrapper gap-3 d-flex justify-content-center align-items-center">
						<!-- 첫 번째 -->
						<a href="category.html" class="nav-link swiper-slide text-center">
							<img
							src="${pageContext.request.contextPath}/resources/bootstrap/images/category-thumb-1.jpg"
							class="rounded-circle" alt="Category Thumbnail"
							style="margin: 0 auto; max-width: 100px;">
							<h4 class="fs-6 mt-3 fw-normal category-title">사료 / 간식</h4>
						</a>
						<!-- 두 번째 항목 -->
						<a href="category.html" class="nav-link swiper-slide text-center">
							<img
							src="${pageContext.request.contextPath}/resources/bootstrap/images/category-thumb-2.jpg"
							class="rounded-circle" alt="Category Thumbnail"
							style="margin: 0 auto; max-width: 100px;">
							<h4 class="fs-6 mt-3 fw-normal category-title">장난감 / 토이</h4>
						</a>
						<!-- 세 번째 항목 -->
						<a href="category.html" class="nav-link swiper-slide text-center">
							<img
							src="${pageContext.request.contextPath}/resources/bootstrap/images/category-thumb-3.jpg"
							class="rounded-circle" alt="Category Thumbnail"
							style="margin: 0 auto; max-width: 100px;">
							<h4 class="fs-6 mt-3 fw-normal category-title">목욕 / 케어</h4>
						</a>
						<!-- 네 번째 항목 -->
						<a href="category.html" class="nav-link swiper-slide text-center">
							<img
							src="${pageContext.request.contextPath}/resources/bootstrap/images/category-thumb-4.jpg"
							class="rounded-circle" alt="Category Thumbnail"
							style="margin: 0 auto; max-width: 100px;">
							<h4 class="fs-6 mt-3 fw-normal category-title">산책 / 훈련</h4>
						</a>
						<!-- 다섯 번째 항목 -->
						<a href="category.html" class="nav-link swiper-slide text-center">
							<img
							src="${pageContext.request.contextPath}/resources/bootstrap/images/category-thumb-5.jpg"
							class="rounded-circle" alt="Category Thumbnail"
							style="margin: 0 auto; max-width: 100px;">
							<h4 class="fs-6 mt-3 fw-normal category-title">의류 / 잡화</h4>
						</a>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- Main 캐러셀 리스트 -->
	<section class="pb-5">
		<div class="container-lg">
			<div class="row">
				<div class="col-md-12">
					<div
						class="section-header d-flex flex-wrap justify-content-between my-4">
						<h2 class="section-title">사료 & 간식</h2>

						<!-- Main 캐러셀 조작 버튼 -->
						<div class="d-flex align-items-center">
							<a href="#" class="btn btn-primary me-2">전체 보기</a>
							<div class="swiper-buttons">
								<button class="swiper-prev btn btn-primary">&lt;</button>
								<button class="swiper-next btn btn-primary">&gt;</button>
							</div>
						</div>
						<!-- / Main 캐러셀 조작 버튼. 끝. -->
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<div class="swiper">
						<div class="swiper-wrapper">

							<!-- 리스트에서 아이템 하나 -->
							<c:forEach var="product01" items="${list01}">
								<div class="swiper-slide">
									<div class="product-item">
										<figure>
											<a href="index.html" title="Product Title"> <img
												src="${pageContext.request.contextPath}/resources/upload/${product01.thumbnail_img}"
												alt="Product Thumbnail" class="tab-image"
												style="width: 200px; height: 150px;" />
											</a>
										</figure>
										<div class="d-flex flex-column text-center">
											<h3 class="fs-6 fw-normal">${product01.product_name}</h3>
											<div
												class="d-flex justify-content-center align-items-center gap-2">
												<span class="text-dark fw-semibold">${product01.product_price}원</span>
											</div>
											<div class="button-area p-3 pt-0">
												<div class="row g-1 mt-2">
													<div class="col-3">
														<input type="number" name="quantity"
															class="form-control border-dark-subtle input-number quantity"
															value="1" />
													</div>
													<div class="col-7">
														<a href="#"
															class="btn btn-primary rounded-1 p-2 fs-7 btn-cart">
															<svg width="18" height="18">
      <use xlink:href="#cart"></use>
      </svg> 장바구니
														</a>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
							<!-- / 리스트에서 아이템 하나 -->
						</div>
						<!-- / product-grid -->
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- / Main 캐러셀 리스트 -->

	<!-- Main 캐러셀 리스트 -->
	<section class="pb-5">
		<div class="container-lg">
			<div class="row">
				<div class="col-md-12">
					<div
						class="section-header d-flex flex-wrap justify-content-between my-4">
						<h2 class="section-title">장난감 & 토이</h2>

						<!-- Main 캐러셀 조작 버튼 -->
						<div class="d-flex align-items-center">
							<a href="#" class="btn btn-primary me-2">전체 보기</a>
							<div class="swiper-buttons">
								<button
									class="swiper-prev products-carousel-prev btn btn-primary">&lt;</button>
								<button
									class="swiper-next products-carousel-next btn btn-primary">&gt;</button>
							</div>
						</div>
						<!-- / Main 캐러셀 조작 버튼. 끝. -->
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<div
						class="product-grid row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-3 row-cols-xl-4 row-cols-xxl-5">

						<!-- 리스트에서 아이템 하나 -->
						<c:forEach var="product02" items="${list02}">
							<div class="col">
								<div class="product-item">
									<figure>
										<a href="index.html" title="Product Title"> <img
											src="${pageContext.request.contextPath}/resources/img/${product02.thumbnail_img}"
											alt="Product Thumbnail" class="tab-image" />
										</a>
									</figure>
									<div class="d-flex flex-column text-center">
										<h3 class="fs-6 fw-normal">${product02.product_name}</h3>
										<div
											class="d-flex justify-content-center align-items-center gap-2">
											<span class="text-dark fw-semibold">${product02.product_price}원</span>
										</div>
										<div class="button-area p-3 pt-0">
											<div class="row g-1 mt-2">
												<div class="col-3">
													<input type="number" name="quantity"
														class="form-control border-dark-subtle input-number quantity"
														value="1" />
												</div>
												<div class="col-7">
													<a href="#"
														class="btn btn-primary rounded-1 p-2 fs-7 btn-cart"> <svg
															width="18" height="18">
      <use xlink:href="#cart"></use>
      </svg> 장바구니
													</a>
												</div>
												<div class="col-2">
													<a href="#" class="btn btn-outline-dark rounded-1 p-2 fs-6">
														<svg width="18" height="18">
      <use xlink:href="#heart"></use>
      </svg>
													</a>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
						<!-- / 리스트에서 아이템 하나 -->
					</div>
					<!-- / product-grid -->
				</div>
			</div>
		</div>
	</section>

	<!-- / Main 캐러셀 리스트 -->

	<!-- Main 캐러셀 리스트 -->
	<section class="pb-5">
		<div class="container-lg">
			<div class="row">
				<div class="col-md-12">
					<div
						class="section-header d-flex flex-wrap justify-content-between my-4">
						<h2 class="section-title">목욕 & 케어</h2>

						<!-- Main 캐러셀 조작 버튼 -->
						<div class="d-flex align-items-center">
							<a href="#" class="btn btn-primary me-2">전체 보기</a>
							<div class="swiper-buttons">
								<button
									class="swiper-prev products-carousel-prev btn btn-primary">&lt;</button>
								<button
									class="swiper-next products-carousel-next btn btn-primary">&gt;</button>
							</div>
						</div>
						<!-- / Main 캐러셀 조작 버튼. 끝. -->
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<div
						class="product-grid row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-3 row-cols-xl-4 row-cols-xxl-5">

						<!-- 리스트에서 아이템 하나 -->
						<c:forEach var="product03" items="${list03}">
							<div class="col">
								<div class="product-item">
									<figure>
										<a href="index.html" title="Product Title"> <img
											src="${pageContext.request.contextPath}/resources/img/${product03.thumbnail_img}"
											alt="Product Thumbnail" class="tab-image" />
										</a>
									</figure>
									<div class="d-flex flex-column text-center">
										<h3 class="fs-6 fw-normal">${product03.product_name}</h3>
										<div
											class="d-flex justify-content-center align-items-center gap-2">
											<span class="text-dark fw-semibold">${product03.product_price}원</span>
										</div>
										<div class="button-area p-3 pt-0">
											<div class="row g-1 mt-2">
												<div class="col-3">
													<input type="number" name="quantity"
														class="form-control border-dark-subtle input-number quantity"
														value="1" />
												</div>
												<div class="col-7">
													<a href="#"
														class="btn btn-primary rounded-1 p-2 fs-7 btn-cart"> <svg
															width="18" height="18">
      <use xlink:href="#cart"></use>
      </svg> 장바구니
													</a>
												</div>
												<div class="col-2">
													<a href="#" class="btn btn-outline-dark rounded-1 p-2 fs-6">
														<svg width="18" height="18">
      <use xlink:href="#heart"></use>
      </svg>
													</a>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
						<!-- / 리스트에서 아이템 하나 -->
					</div>
					<!-- / product-grid -->
				</div>
			</div>
		</div>
	</section>

	<!-- / Main 캐러셀 리스트 -->

	<!-- Main 캐러셀 리스트 -->
	<section class="pb-5">
		<div class="container-lg">
			<div class="row">
				<div class="col-md-12">
					<div
						class="section-header d-flex flex-wrap justify-content-between my-4">
						<h2 class="section-title">산책 & 훈련</h2>

						<!-- Main 캐러셀 조작 버튼 -->
						<div class="d-flex align-items-center">
							<a href="#" class="btn btn-primary me-2">전체 보기</a>
							<div class="swiper-buttons">
								<button
									class="swiper-prev products-carousel-prev btn btn-primary">&lt;</button>
								<button
									class="swiper-next products-carousel-next btn btn-primary">&gt;</button>
							</div>
						</div>
						<!-- / Main 캐러셀 조작 버튼. 끝. -->
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<div
						class="product-grid row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-3 row-cols-xl-4 row-cols-xxl-5">

						<!-- 리스트에서 아이템 하나 -->
						<c:forEach var="product04" items="${list04}">
							<div class="col">
								<div class="product-item">
									<figure>
										<a href="index.html" title="Product Title"> <img
											src="${pageContext.request.contextPath}/resources/img/${product04.thumbnail_img}"
											alt="Product Thumbnail" class="tab-image" />
										</a>
									</figure>
									<div class="d-flex flex-column text-center">
										<h3 class="fs-6 fw-normal">${product04.product_name}</h3>
										<div
											class="d-flex justify-content-center align-items-center gap-2">
											<span class="text-dark fw-semibold">${product04.product_price}원</span>
										</div>
										<div class="button-area p-3 pt-0">
											<div class="row g-1 mt-2">
												<div class="col-3">
													<input type="number" name="quantity"
														class="form-control border-dark-subtle input-number quantity"
														value="1" />
												</div>
												<div class="col-7">
													<a href="#"
														class="btn btn-primary rounded-1 p-2 fs-7 btn-cart"> <svg
															width="18" height="18">
      <use xlink:href="#cart"></use>
      </svg> 장바구니
													</a>
												</div>
												<div class="col-2">
													<a href="#" class="btn btn-outline-dark rounded-1 p-2 fs-6">
														<svg width="18" height="18">
      <use xlink:href="#heart"></use>
      </svg>
													</a>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
						<!-- / 리스트에서 아이템 하나 -->
					</div>
					<!-- / product-grid -->
				</div>
			</div>
		</div>
	</section>

	<!-- / Main 캐러셀 리스트 -->

	<!-- Main 캐러셀 리스트 -->
	<section class="pb-5">
		<div class="container-lg">
			<div class="row">
				<div class="col-md-12">
					<div
						class="section-header d-flex flex-wrap justify-content-between my-4">
						<h2 class="section-title">의류 & 잡화</h2>

						<!-- Main 캐러셀 조작 버튼 -->
						<div class="d-flex align-items-center">
							<a href="#" class="btn btn-primary me-2">전체 보기</a>
							<div class="swiper-buttons">
								<button
									class="swiper-prev products-carousel-prev btn btn-primary">&lt;</button>
								<button
									class="swiper-next products-carousel-next btn btn-primary">&gt;</button>
							</div>
						</div>
						<!-- / Main 캐러셀 조작 버튼. 끝. -->
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<div
						class="product-grid row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-3 row-cols-xl-4 row-cols-xxl-5">

						<!-- 리스트에서 아이템 하나 -->
						<c:forEach var="product05" items="${list05}">
							<div class="col">
								<div class="product-item">
									<figure>
										<a href="index.html" title="Product Title"> <img
											src="${pageContext.request.contextPath}/resources/img/${product05.thumbnail_img}"
											alt="Product Thumbnail" class="tab-image" />
										</a>
									</figure>
									<div class="d-flex flex-column text-center">
										<h3 class="fs-6 fw-normal">${product05.product_name}</h3>
										<div
											class="d-flex justify-content-center align-items-center gap-2">
											<span class="text-dark fw-semibold">${product05.product_price}원</span>
										</div>
										<div class="button-area p-3 pt-0">
											<div class="row g-1 mt-2">
												<div class="col-3">
													<input type="number" name="quantity"
														class="form-control border-dark-subtle input-number quantity"
														value="1" />
												</div>
												<div class="col-7">
													<a href="#"
														class="btn btn-primary rounded-1 p-2 fs-7 btn-cart"> <svg
															width="18" height="18">
      <use xlink:href="#cart"></use>
      </svg> 장바구니
													</a>
												</div>
												<div class="col-2">
													<a href="#" class="btn btn-outline-dark rounded-1 p-2 fs-6">
														<svg width="18" height="18">
      <use xlink:href="#heart"></use>
      </svg>
													</a>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
						<!-- / 리스트에서 아이템 하나 -->
					</div>
					<!-- / product-grid -->
				</div>
			</div>
		</div>
	</section>


	<script
		src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery-1.11.0.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
		crossorigin="anonymous"></script>
	<script
		src="${pageContext.request.contextPath}/resources/bootstrap/js/plugins.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/bootstrap/js/script.js"></script>
		
		<script>
    // Swiper.js 초기화
    const swiper = new Swiper('.swiper', {
        slidesPerView: 5, // 한 번에 보이는 슬라이드 개수
        spaceBetween: 20, // 슬라이드 간격
        navigation: {
            nextEl: '.swiper-next', // 오른쪽 화살표 버튼
            prevEl: '.swiper-prev', // 왼쪽 화살표 버튼
        },
        loop: true, // 무한 루프 여부 (필요에 따라 true로 변경)
    });
</script>
</body>
</html>
