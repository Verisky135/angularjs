
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
	<!-- user page title: SaleProject -->
	<div id="user_page_title">
		<div id="title_sale">Sale</div>
		<div id="title_project">Project</div>
	</div>
	<!-- end of user page title -->

	
	
	<!-- user page welcome: Hello, <>! -->
	<!-- 
	<div id="user_page_welcome">
		<div id="user_page_welcome_desc">Hi, <c:out value="${param.username}"/>!</div>
	</div>
	-->
	<!-- end of user page welcome -->

	
	
	<!-- user page logout: logout -->
	<div id="user_page_logout">
		<div id="user_page_logout_desc">
			<a id="user_page_logout_href" onclick="logout()">logout</a>
		</div>
	</div>
	<!-- end of user page logout -->



	<!-- user page navigation bar -->
	<div id="user_page_navbar">
		
		<ul id="user_page_ul">
			<li id="user_page_li_catalog" class="user_page_li"><a href="../views/catalog.jsp">Catalog</a></li>
			<li id="user_page_li_yourprod" class="user_page_li"><a href="../views/your_product.jsp">Your Products</a></li>
			<li id="user_page_li_addprod" class="user_page_li"><a href="../views/add_product.jsp">Add Product</a></li>
			<li id="user_page_li_sales" class="user_page_li"><a href="../views/sales.jsp">Sales</a></li>
			<li id="user_page_li_purchases" class="user_page_li"><a href="../views/purchases.jsp">Purchases</a></li>
		</ul>
		
	</div>
	<!-- end of user page navigation bar -->


	<!-- user page head: Intro message and horizontal line -->
	<div id="user_page_head">
		
		<c:out value="${param.template_title}"/>
		<hr />
	
	</div>
	<!-- end of user page head -->
	