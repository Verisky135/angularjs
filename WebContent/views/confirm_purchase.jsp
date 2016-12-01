<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>SaleProject &dot; Confirmation Purchase</title>
	<link rel="stylesheet" href="../public/css/user_page.css" />
	<link rel="stylesheet" href="../public/css/confirm_purchase.css" />
	
	<script src="../public/js/validation_logout.js"></script>
	<script src="../public/js/confirm_purchase.js"></script>
</head>
<body>
		
	<div class="container">

		<!-- load template: Title, welcome message, logout, and navigation bar -->
		
		<c:import url="../templates/user_page_template.jsp">
		
			<c:param name="template_title" value="Please confirm your purchase"/>
		</c:import>
		
		<!-- end of template -->
		
		<!-- user page welcome: Hello, <>! -->
		<div id="user_page_welcome">
			<div id="user_page_welcome_desc">
				Hi, <script>document.write(localStorage.getItem("username_global"));</script>!
			</div>
		</div>
		<!-- end of user page welcome -->
			
		
		<!-- confirm purchase form -->	
		<!-- 
		<form action="../server/Confirm_purchase.php" id="form_confirm_purchase" method="post" name="confirmPurchaseForm">
		-->
		
		<form id="form_confirm_purchase" method="post" name="confirmPurchaseForm">
		
			<!-- boughtfrom username (hidden) -->
			<!-- 
			<input type="text" id="form_confirm_boughtfrom_username" name="confirm_product_boughtfrom_username" />
			-->
			
			<!-- send boughtby id (hidden) -->
			<input type="text" id="form_confirm_product_boughtby_id" name="confirm_product_boughtby_id" />
			
			<!-- send boughtby username (hidden) -->
			<input type="text" id="form_confirm_product_boughtby" name="confirm_product_boughtby" />
			
			
			<!-- send product's id (hidden) -->
			<input type="text" id="form_confirm_product_id" name="confirm_product_id" />
			
			<!-- send product's photo (hidden) -->
			<input type="text" id="form_confirm_product_image" name="confirm_product_image" />
			
			
			<table id="form_confirm_table">
				
				<tr>
					<td class="form_confirm_table_desc">Product</td>
					<td class="form_confirm_table_colon">:</td>
					<td id="form_confirm_table_fetched_name"></td>
					<input type="text" id="form_confirm_product_name" name="confirm_product_name" />
				</tr>
				
				
				<tr>
					<td class="form_confirm_table_desc">Price</td>
					<td class="form_confirm_table_colon">:</td>
					<td>
						<div id="form_confirm_price"></div>
						<input type="text" id="price_not_formatted" name="confirm_product_price" />
					</td>
				</tr>
				
				
				<tr>
					<td class="form_confirm_table_desc">Quantity</td>
					<td class="form_confirm_table_colon">:</td>
					<td>
						<input type="text" id="form_confirm_quantity" onblur="countQuantity(this.value)" value="1" name="confirm_product_quantity" /> pcs
					</td>
				</tr>
				
				
				<tr>
					<td class="form_confirm_table_desc">Total Price</td>
					<td class="form_confirm_table_colon">:</td>
					<td>
						<div id="form_confirm_totalprice"></div>
					</td>
				</tr>
				
				
				<tr>
					<td class="form_confirm_table_desc">Delivery to</td>
					<td class="form_confirm_table_colon">:</td>
				</tr>
				
				
			</table>
			
			<div class="form_confirm_elements">
				Consignee<br />
				
				<input type="text" id="form_confirm_consignee" onblur="ValidationConsignee()" name="confirm_consignee" />
				
				<div id="conf_purch_consignee_error"></div>
			</div>
			
			<div class="form_confirm_elements">
				Full Address<br />
				
				<textarea id="form_confirm_fulladdress" onblur="ValidationFullAddress()" name="confirm_fulladdress"></textarea>
				
				<div id="conf_purch_fulladdr_error"></div>
			</div>
				
			<div class="form_confirm_elements">
				Postal Code<br />
				
				<input type="text" id="form_confirm_postalcode" onblur="ValidationPostalCode()" name="confirm_postalcode" />
				
				<div id="conf_purch_postalcode_error"></div>
			</div>
			
			<div class="form_confirm_elements">
				Phone Number<br />
				
				<input type="text" id="form_confirm_phonenumber" onblur="ValidationPhoneNumber()" name="confirm_phonenumber" />
				
				<div id="conf_purch_phone_error"></div>
			</div>
			
			<div class="form_confirm_elements">
				12 Digits Credit Card Number<br />
				<input type="text" id="form_confirm_cardnumber" onblur="ValidationCreditCard()" name="confirm_cardnumber" />
				<div id="conf_purch_cardnumber_error"></div>
			</div>
			
			<div class="form_confirm_elements">
				3 Digits Card Verification Value<br />
				<input type="text" id="form_confirm_verifyvalue" onblur="ValidationCardVerification()" name="confirm_verifvalue" />
				<div id="conf_purch_cardverif_error"></div>
			</div>
			
			<div class="form_confirm_button">	
				<input type="button" class="cancel_button" onclick="history.go(-1)" value="CANCEL"/>	
				<input type="button" class="submit_button" onclick="ConfirmHandling()" value="CONFIRM" />
			</div>
			
		</form>
		<!-- end of confirm purchase form -->
	</div>

	
	<!-- Javascript -->
	<script>
	
	
		//function getParameterByName(name, url) {
			/*
			if (!url) {
				url = window.location.href;
			}
			
			name = name.replace(/[\[\]]/g, "\\$&");
			
			var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)");
			var results = regex.exec(url);
			
			if (!results) return null;
			if (!results[2]) return '';
			
			return decodeURIComponent(results[2].replace(/\+/g, " "));
			
		}
	*/
	
		window.onload = function() {
			
			document.getElementById("form_confirm_product_boughtby").value = localStorage.getItem("username_global");
			//document.getElementById("form_confirm_product_boughtby_full").value = localStorage.getItem("buying_fullname");
			document.getElementById("form_confirm_product_id").value = localStorage.getItem("product_data_id");
			document.getElementById("form_confirm_product_image").value = localStorage.getItem("product_data_photo");
			
			document.getElementById("form_confirm_table_fetched_name").innerHTML = localStorage.getItem("product_data_name");
			document.getElementById("form_confirm_price").innerHTML = "IDR " + localStorage.getItem("product_data_price");
			
			document.getElementById("form_confirm_totalprice").innerHTML = "IDR " + localStorage.getItem("product_data_price");
			
			document.getElementById("form_confirm_consignee").value = localStorage.getItem("buying_fullname");
			document.getElementById("form_confirm_fulladdress").innerHTML = localStorage.getItem("buying_fulladdress");
			document.getElementById("form_confirm_postalcode").value = localStorage.getItem("buying_postalcode");
			document.getElementById("form_confirm_phonenumber").value = localStorage.getItem("buying_phonenumber");
			

			
			/*
			var price_not_fomatted = Number(localStorage.getItem("product_data_price").replace(/\D/g,""));
			console.log("before0: " + localStorage.getItem("product_data_price"));
			console.log("before1: " + localStorage.getItem("product_data_price").replace(/\D/g,""));
			console.log("before2: " + Number(localStorage.getItem("product_data_price").replace(/\D/g,"")));
			console.log("price not formatted: " + price_not_formatted);
			document.getElementById("price_not_formatted").value = price_not_formatted;
			*/
			
		}
	</script>
	
	

</body>
</html>

