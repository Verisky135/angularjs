<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>SaleProject &dot; Sales</title>
	<link rel="stylesheet" href="../public/css/user_page.css" />
	<link rel="stylesheet" href="../public/css/purchases.css" />
	
	<script src="../public/js/validation_logout.js"></script>
	<script src="../public/js/sales.js"></script>
</head>
<body>
	
	<div class="container">

		<!-- load template: Title, welcome message, logout, and navigation bar -->
		
		<c:import url="../templates/user_page_template.jsp">
			<c:param name="template_title" value="Here are your sales"/>
		</c:import>
		
		<!-- end of template -->

		<!-- catalog list of products -->
			<div id="user_sales">
			
				
			
			</div>
		<!-- end of catalog list of products -->
		
		
	</div>

	<!-- Javascript -->
	<script>
		
		var username_active = localStorage.getItem("username_global");
		
		document.getElementById("user_page_li_sales").className = "active";
	
		window.onload = function() {
			
			// set username (active)
			SetUsernameActive(username_active);
			
			// retrieves all sales by this user
			SearchAJAX("user_sales");
		}
	</script>

</body>
</html>

