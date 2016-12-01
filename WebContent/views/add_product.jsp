<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>SaleProject &dot; Add Product</title>
	<link rel="stylesheet" href="../public/css/user_page.css"/>
	<link rel="stylesheet" href="../public/css/add_product.css"/>
	
	<script src="../public/js/validation_logout.js"></script>
	<script src="../public/js/product.js"></script>
</head>
<body>
	
	<div class="container">

		<!-- load template: Title, welcome message, logout, and navigation bar -->
		
		<c:import url="../templates/user_page_template.jsp">
			<c:param name="template_title" value="Please add your product here"/>
		</c:import>
		
		<!-- end of template -->
		
		<!--Form-->
		<div id="add_product_body">
			
			<!-- 
			<form action="../server/Add_product.php" method="post" id="product_form" name="productForm" enctype="multipart/form-data">
			-->
			
			<form method="post" id="product_form" name="productForm" enctype="multipart/form-data">
				
				<!-- send user ID and username (active and hidden) -->

				<div class="add_product_form_elements">
					<div class="element_desc">Name</div>
					<input type="text" id="product_name" onblur="ValidationName()" name="product_name">
					<div id="add_product_name_error"></div>
				</div>
				<div class="add_product_form_elements">
					<div class="element_desc">Description (max 200 chars)</div>
					<textarea type="text" id="product_desc" onblur="ValidationDesc()" name="product_desc"></textarea>
					<div id="add_product_desc_error"></div>
				</div>
				<div class="add_product_form_elements">
					<div class="element_desc">Price(IDR)</div>
					<input type="text" id="product_price" onblur="ValidationPrice()" name="product_price" onkeypress="return isNumberKey(event)">
					<div id="add_product_price_error"></div>
				</div>
				<div class="add_product_form_elements">
					<div class="element_desc">Photo</div>
					<label class="upload_button">
						<input type="file" id="product_pic" name="product_pic" onchange="getFileName()" accept="image/*">
						<span>Choose File</span>
					</label>
					
					<!-- add hidden field -->
					<input type="hidden" id="fileName_hidden" name="product_pic_hidden" />
					<!-- end of hidden field -->
					
					<span class="element_desc" id="file_desc">No file chosen</span>
					<div id="add_product_pic_error"></div>
				</div>
				<div class="add_product_form_button">
					<input type="button" class="cancel_button" value="CANCEL" onclick="history.go(-1)"/>
					<input type="button" value="ADD" class="submit_button" id="button_2" onclick="ValidationForm()">
				</div>
			</form>
			<!-- End of form -->
		</div>
		
	</div>
	
	<!-- Javascript -->
	<script>
	
		window.onload = function() {
			
			document.getElementById("user_page_li_addprod").className = "active";
		
		}
		
	</script>
	
</body>
</html>
