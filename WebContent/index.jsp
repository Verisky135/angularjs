<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<title>SaleProject &dot; Login</title>
	<link rel="stylesheet" href="public/css/login.css">
	<link rel="stylesheet" href="public/css/user_page.css" />
	<script src="public/js/validation_login.js"></script>
	
</head>
<body>
	
	<div class="container2">
			
			<!-- login title: SaleProject -->
			<div id="login_title">
				<div id="login_title_sale">Sale</div>
				<div id="login_title_project">Project</div>
			</div>
			<!-- end of login title -->
			
			<!-- login header -->
			<div id="login_header">Please login
			<hr />
			</div>
			<!-- end of login header -->
			
			<!-- login body -->
			<div id="login_body">
				
				<!-- 
				<form action="server/User_login.php" id="login_form" method="post" name="loginForm">
				-->
				
				<form id="login_form" method="post" name="loginForm">
				
					<div class="login_body_form_elements">
						Email or Username :<br>
						<input type="text" id="login_email" name="username" />
						<div id="login_username_error"></div>
					</div>
					
					<div class="login_body_form_elements">
						Password :<br>
						<input type="password" id="login_password" name="pass" />
						<div id="login_password_error"></div>
					</div>
					
					<div class="login_body_form_button">
						<input type="button" onclick="ValidationForm()" id="login_submit" value="LOGIN" class="submit_button"/>
					</div>
					
					<!-- hidden div for storing the value telling whether username and password are match or not -->
					<div id="match_status"></div>
					<!-- end of hidden div -->
					
				</form> 
			
			</div>
			<!-- end of login body -->
			
			<!-- login footer -->
			<div id="login_footer">
				<div id="footer_question">Don't have an account yet? Register </div>
				<div id="footer_here">&nbsp;<b><a href="views/register.jsp" id="footer_href">here</a></b></div>
			</div>
			<!-- end of login footer -->
			
	</div>	
	
	<!-- CUSTOM JAVASCRIPT -->
	<script>		
	
		// Check the validity of the user access (session)
		if (!ValidateUserAccess()) {
			
			console.log("Invalid access");
			window.location.href = "views/catalog.jsp";
	
		}
	
		console.log("Valid access");
	</script>
	
</body>
</html>
