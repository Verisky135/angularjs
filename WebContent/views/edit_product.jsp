<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>SaleProject &dot; Edit Product</title>
	<link rel="stylesheet" href="../public/css/user_page.css" />
	<link rel="stylesheet" href="../public/css/add_product.css" />
	
	<script src="../public/js/validation_logout.js"></script>
	<script src="../public/js/product.js"></script>
</head>
<body>
	
	<div class="container">

		<!-- load template: Title, welcome message, logout, and navigation bar -->
		
		<c:import url="../templates/user_page_template.jsp">
			<c:param name="username" value="Verisky"/>
			<c:param name="template_title" value="Please update your product here"/>
		</c:import>
		<!-- end of template -->
		
		<!--Form-->
		<div id="add_product_body">
			
			<!--
			<form action="../server/Modify_product.php" method="post" name="productForm" id="product_form">
			-->
			
			<form name="productForm" id="product_form">
			
				<!-- send user ID (active) and hidden -->
				
				<div class="add_product_form_elements">
					<div class="element_desc">Name</div>
					<input type="text" id="product_name" onblur="ValidationName()" value="" name="product_name">
					<div id="add_product_name_error"></div>
				</div>
				<div class="add_product_form_elements">
					<div class="element_desc">Description (max 200 chars)</div>
					<textarea id="product_desc" onblur="ValidationDesc()" name="product_desc"></textarea>
					<div id="add_product_desc_error"></div>
				</div>
				<div class="add_product_form_elements">
					<div class="element_desc">Price(IDR)</div>
					<input type="text" id="product_price" onblur="ValidationPrice()" value="" name="product_price" onkeypress="return isNumberKey(event)">
					<div id="add_product_price_error"></div>
				</div>
				<div class="add_product_form_elements">
					<div class="element_desc">Photo</div>
					<label class="upload_button">
						<span>Choose File</span></span>
					</label>
					<span class="element_desc" id="product_pic"></span>
					<div id="add_product_img_error"></div>
				</div>			
				<div class="add_product_form_button">
					<input type="button" class="cancel_button" value="CANCEL" onclick="history.go(-1)"/>
					<input type="button" value="UPDATE" class="submit_button" id="button_2" onclick="ValidationFormEdit()">
				</div>
			</form>
			<!-- End of form-->
		</div>
	</div>	
	
	
	<!-- JAVASCRIPT -->
	<script>
		
		window.onload = function() {
			
			document.getElementById("product_name").value = localStorage.getItem("product_edit_name");
			document.getElementById("product_desc").value = localStorage.getItem("product_edit_desc");
			document.getElementById("product_price").value = localStorage.getItem("product_edit_price");
			document.getElementById("product_pic").value = localStorage.getItem("product_edit_photo");
			
		}
	
	</script>
	
</body>
</html>
