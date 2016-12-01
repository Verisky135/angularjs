<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="app">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>SaleProject &dot; Catalog</title>
	<link rel="stylesheet" href="../public/css/user_page.css" />
	
	<!-- Manifest -->
  	<link rel="manifest" href="../manifest.json">
  	
	<!-- Chat styling tools -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="../public/css/bootstrap.min.css" />
	<link rel="stylesheet" href="../public/css/chat-style.css" />	
	<link rel="stylesheet" href="../public/css/chat-theme.css" /> 
	<link rel="stylesheet" href="../public/css/chat-style-c.css" />	
	<link rel="stylesheet" href="../public/css/chatbox_irontec.css" />

	<!-- Custom Javascript -->
	<script src="../public/js/validation_logout.js"></script>
	<script src="../public/js/searchcatalog.js"></script>

</head>
<body>

	<div class="container" id="supportFCM" ng-controller="Shell as vm">

		<!-- load template: Title, welcome message, logout, and navigation bar -->
		
		<c:import url="../templates/user_page_template.jsp">
			<c:param name="template_title" value="What are you going to buy today?"/>
		</c:import>
		
		<!-- end of template -->
		
		<!-- user page welcome: Hello, <>! -->
		<div id="user_page_welcome">
			<div id="user_page_welcome_desc">
				Hi, <script>document.write(localStorage.getItem("username_global"));</script>!
			</div>
		</div>
		
		<!-- catalog search bar -->
			
			<div id="catalog_search">
			
				<form action="#" id="form_catalog_search">
			
					<div id="catalog_search_text_btn">
						<input type="text" id="catalog_search_text" name="catalog_search_text" placeholder="Search catalog ..." />
						<input type="button" id="catalog_search_btn" onclick="SearchCatalogFromGO()" name="catalog_search_btn" value="GO" />
					</div>	
					
					<div id="catalog_search_by_prod_st">
						<table class="catalog_search_table">
							<tr>
								<td id="catalog_search_by" rowspan="2">
									by
								</td>
								<td id="catalog_search_prod">
									<input type="radio" id="catalog_search_product" onblur="SetSearchCategory('product')" name="catalog_search_radio" />product
								</td>
							</tr>
							<tr>
								<td id="catalog_search_st">
									<input type="radio" id="catalog_search_store" onblur="SetSearchCategory('store')" name="catalog_search_radio" />store
								</td>
							</tr>
						</table>
					</div>
				
				</form>
				
			</div>
			
		<!-- end of catalog search bar -->
	
		<!-- catalog list of products -->
			<div id="catalog_products">
			
				
			
			</div>
		<!-- end of catalog list of products -->
	
		<!-- CHAT BOX AREA -->		
				
			<!--
			<h1>{{ user_target }}</h1>
			
			<chat-directive user-target="{{ user_target }}"></chat-directive>
			-->
			
			<!-- Import chat box template
			<c:import url="../templates/chat_box_template_irontec.html">
			
			</c:import>
			 -->
			<div class="chat-container" id="chat-here">
				<chat-directive
			            messages='vm.messages'
			            username='vm.username'
			            input-placeholder-text='You can write here'
			            submit-button-text='Send your message'
			            title='vm.username'
			            theme='material'
			            submit-function='vm.sendMessage'
			            visible='vm.visible'>
			    </chat-directive>
			</div>

		<!-- END OF CHAT BOX AREA -->

	</div>

	<!-- BUILT IN JAVASCRIPT -->
	
	<!-- jQuery and AngularJS -->
	<script src="../public/js/lodash.js"></script>
	<script src="../public/js/jquery-1.11.1.min.js"></script>
  	<script src="../public/js/angular.min.js"></script>
	
	
	<!-- CUSTOM JS -->
	<script src="../public/modules/chat_module.js"></script>
	<!--
	<script src="../public/modules/chatTemplate.js"></script>
	-->
	<script src="../app.js"></script>
	
		
	<!-- Firebase -->
	<!-- commented for debugging purpose
	<script src="https://www.gstatic.com/firebasejs/3.6.1/firebase.js"></script>
	
	<script src="https://www.gstatic.com/firebasejs/3.6.1/firebase.js"></script>
	
	<!-- FCM Token Manager -->
	<!-- commented for debugging purpose
	<script src="../public/js/fcm_token.js"></script>
	
	<script src="../public/js/fcm_token.js"></script>
	-->


	<!-- THIS LINE IS FOR DEBUGGING ONLY -->
	
	<script>
		// GET user ID (active) from global variables
		var user_active = localStorage.getItem("username_global");
			
		document.getElementById("user_page_li_catalog").className = "active";
			
		console.log("Catalog page");
		console.log("user_active: " + user_active);
		
		// SET user ID (active)
		//SetUserIDActive(user_active);
		
		// ret_all is a special keyword for retreiving all catalog products
		SetSearchCategory("ret_all");
		SearchOnline();
		SearchAJAX("catalog_products", "ret_all");
		
		// reset the search category
		SetSearchCategory("");
	</script>
	
	<!-- END OF DEBUGGING LINE -->

	
</body>
</html>
