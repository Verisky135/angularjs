<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>SaleProject &dot; Registration</title>
	<link rel="stylesheet" href="../public/css/register.css" />
	<link rel="stylesheet" href="../public/css/user_page.css" />
	
	<script src="../public/js/validation.js"></script>
	
	<!-- JQuery -->
	<!-- 
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    -->
    
    <script src="../public/js/jquery-1.11.1.min.js"></script>
</head>
<body>
	
		<div class="container2">
		
		<!-- registration title: SaleProject -->
		<div id="register_title">
			<div id="title_sale">Sale</div>
			<div id="title_project">Project</div>
		</div>
		<!-- end of registration title -->
		
		<!-- registration head: Please register and horizontal line -->
		<div id="register_head">
			<div id="head_register">Please register</div>
			<hr />
		</div>
		<!-- end of registration head -->
		
		<!-- registration body: Full name, Username, Email, etc -->
		<div id="register_body">
			
			<!--
			<form action="identity_register" id="register_form" method="post" name="registerForm">
			-->
			<form id="register_form" method="post" name="registerForm">
				<div class="register_body_form_elements">
					<div class="element_desc">Full Name</div>
					<input type="text" id="register_fullname" onblur="ValidationFullName()" name="register_fullname" />
					<div id="register_fullname_error"></div>
				</div>
				<div class="register_body_form_elements">
					<div class="element_desc">Username</div>
					<input type="text" id="register_username" onblur="ValidationUsername('register_username_error', this.value)" name="register_username" />
					<div id="register_username_error"></div>
				</div>
				<div class="register_body_form_elements">
					<div class="element_desc">Email</div>
					<input type="text" id="register_email" onblur="ValidationEmail('register_email_error', this.value)" name="register_email" />
					<div id="register_email_error"></div>
				</div>
				<div class="register_body_form_elements">
					<div class="element_desc">Password</div>
					<input type="password" id="register_password" onblur="ValidationPassword()" name="register_password" />
					<div id="register_password_error"></div>
				</div>
				<div class="register_body_form_elements">
					<div class="element_desc">Confirm Password</div>
					<input type="password" id="register_confpassword" onblur="ValidationConfPassword()" name="register_confpassword" />
					<div id="register_confpassword_error"></div>
				</div>
				<div class="register_body_form_elements">
					<div class="element_desc">Full Address</div>
					<textarea id="register_fulladdress" onblur="ValidationFullAddress()" name="register_fulladdress"></textarea>
					<div id="register_fulladdress_error"></div>
				</div>
				<div class="register_body_form_elements">
					<div class="element_desc">Postal Code</div>
					<input type="text" id="register_postalcode" onblur="ValidationPostalCode()" name="register_postalcode" 
					onkeypress="return isNumberKey(event)"/>
					<div id="register_postalcode_error"></div>
				</div>
				<div class="register_body_form_elements">
					<div class="element_desc">Phone Number</div>
					<input type="text" id="register_phonenumber" onblur="ValidationPhoneNumber()" name="register_phonenumber" 
					onkeypress="return isNumberKey(event)"/>
					<div id="register_phonenumber_error"></div>
				</div>
				
				<div class="register_body_form_button">
					<input class="submit_button" onclick="ValidationForm()" type="button" value="REGISTER" />
				</div>
				
			</form>
			
		</div>
		<!-- end of registration body -->
		
		<!-- registration footer: Already registered? Login here -->
		<div id="register_footer">
			<div id="footer_question">Already registered? Login</div>
			<div id="footer_here">&nbsp;<b><a href="../index.jsp" id="footer_href">here</a></b></div>
		</div>
		
	</div>
	
	
	<!-- JAVASCRIPT -->
	<script>
	
		window.onload = function() {
			
			if (!ValidateUserAccess()) {
				
				window.location.href = "catalog.jsp";
				
			}
			
		}
	
	</script>
	
</body>
</html>
