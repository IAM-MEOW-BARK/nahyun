<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<style>

        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0,0,0); /* Fallback color */
            background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
        }
    
        /* Modal Content/Box */
        .modal-content {
            background-color: #fefefe;
            margin: 15% auto; /* 15% from the top and centered */
            padding: 20px;
            border: 1px solid #888;
            width: 50%; /* Could be more or less, depending on screen size */                          
        }
        /* The Close Button */
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }
        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
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
	<div class="modal">
		<!-- Header -->
		<div class="d-flex justify-content-between align-items-center mb-3">
			<h5 class="m-0">장바구니</h5>
			<span class="close" onclick="window.close()">×</span>
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
	<script type="text/javascript">
	        // Get the modal
        var modal = document.getElementById('myModal');
 
        // Get the button that opens the modal
        var btn = document.getElementById("myBtn");
 
        // Get the <span> element that closes the modal
        var span = document.getElementsByClassName("close")[0];                                          
 
        // When the user clicks on the button, open the modal 
        btn.onclick = function() {
            modal.style.display = "block";
        }
 
        // When the user clicks on <span> (x), close the modal
        span.onclick = function() {
            modal.style.display = "none";
        }
 
        // When the user clicks anywhere outside of the modal, close it
        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
        </script>
	
</body>
</html>