<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>SaleProject &dot; Purchase</title>
	<link rel="stylesheet" href="../public/css/user_page.css" />
	<link rel="stylesheet" href="../public/css/purchases.css" />
	
	<script src="../public/js/validation_logout.js"></script>
	<script src="../public/js/purchases.js"></script>
	<script src="../chatTemplate.js"></script>
</head>
<body>
	
	<div class="container">

		<!-- load template: Title, welcome message, logout, and navigation bar -->
		
		<c:import url="../templates/user_page_template.jsp">
			<c:param name="template_title" value="Here are your purchases"/>
		</c:import>
	
		<!-- end of template -->
		
		
		<!-- user page welcome: Hello, <>! -->
		<div id="user_page_welcome">
			<div id="user_page_welcome_desc">
				Hi, <script>document.write(localStorage.getItem("username_global"));</script>!
			</div>
		</div>
		<!-- end of user page welcome -->
		
		
		<!-- Daftar Your Purchases -->
		
			<div id="purchases">
				
				
			
			</div>
		
		<!-- End of Daftar Your Purchases -->
		
		
	</div>
	
	<script>
	
		var username_active = localStorage.getItem("username_global");
		
		document.getElementById("user_page_li_purchases").className = "active";
	
		window.onload = function() {
			
			// set username (active) 
			SetUsernameActive(username_active);
			
			// fetch all purchased products by this user
			SearchAJAX("purchases");
		
		}
	</script>
	
</body>
</html>
