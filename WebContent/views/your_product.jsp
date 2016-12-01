<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>SaleProject &dot; Your Product</title>
	<link rel="stylesheet" href="../public/css/user_page.css" />
	<link rel="stylesheet" href="../public/css/your_product.css" />
	
	<script src="../public/js/validation_logout.js"></script>
	<script src="../public/js/yourproduct.js"></script>
	<script src="../public/js/product.js"></script>
</head>
<body>
	
	<div class="container">

		<!-- load template: Title, welcome message, logout, and navigation bar -->
		<%!//public String username = "Verisky";%>
		<%!//public String template_title = "What are you going to sell today?";%>
		
		<c:import url="../templates/user_page_template.jsp">
			<c:param name="template_title" value="What are you going to sell today?"/>
		</c:import>
		<!-- end of template -->
		
	
		<!-- Daftar Your Product -->
		
			<div id="your_products">
				
			</div>
			
		<!-- End of Daftar Your Product -->
	
	</div>	
		
	<script>
	
		// GET the user ID and username (active)
		var userID_active = localStorage.getItem("userid_global");
		var username_active = localStorage.getItem("username_global");
		
		document.getElementById("user_page_li_yourprod").className = "active";

		window.onload = function() {
			
			// Set user ID and username (active)
			SetUserIDActive(userID_active);
			SetUsernameActive(username_active);
		
			// fetch all of this user products
			SearchAJAX("your_products");
		
		}
	</script>

</body>
</html>
