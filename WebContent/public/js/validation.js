
function submission() {
	
	var xmlhttp;
	
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	var url = "identity_register";
	xmlhttp.open("POST", url, true);

	//Send the proper header information along with the request
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

	xmlhttp.onreadystatechange = function() {
		//Call a function when the state changes.
	    if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	    	
	    	// parse JSON
	    	var obj_parse = JSON.parse(xmlhttp.responseText);
	    	
	    	
	    	localStorage.clear();
	    	localStorage.setItem("userid_global", obj_parse.userid);
	        localStorage.setItem("username_global", obj_parse.username);
	        localStorage.setItem("access_token_global", obj_parse.access_token);
	        localStorage.setItem("expiry_time_global", obj_parse.expiry_time);
	        //localStorage.setItem("is_expired_global", obj_parse.is_expired);
	        
	        
	        // FOR FCM TOKEN GENERATION
	        localStorage.setItem("first_catalog", 1);
	        
	    
	        // redirect to catalog page
	        window.location.href = "catalog.jsp";
	            
	    }
	}
	
	
	var full_name_blank = document.getElementById("register_fullname").value;
	var username_blank = document.getElementById("register_username").value;
	var email_blank = document.getElementById("register_email").value;
	var pass_blank = document.getElementById("register_password").value;
	var conf_pass_blank = document.getElementById("register_confpassword").value;
	var full_address_blank = document.getElementById("register_fulladdress").value;
	var postal_code_blank = document.getElementById("register_postalcode").value;
	var phone_number_blank = document.getElementById("register_phonenumber").value;
	
	var params = "register_fullname=" + full_name_blank + "&" +
			"register_username=" + username_blank + "&" +
			"register_email=" + email_blank + "&" +
			"register_password=" + pass_blank + "&" +
			"register_fulladdress=" + full_address_blank + "&" +
			"register_postalcode=" + postal_code_blank + "&" +
			"register_phonenumber=" + phone_number_blank;
	
	xmlhttp.send(params);

}

/* REGISTER VALIDATION */

// Username and Email uniqueness validation
function Validation_Unique(field, query) {
	
	var xmlhttp;
	
	if (window.XMLHttpRequest) { 
		// for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else { 
		// for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState != 4 && xmlhttp.status == 200) {
			document.getElementById(field).innerHTML = "Validating..";
		} else if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			document.getElementById(field).innerHTML = xmlhttp.responseText;
		} else {
			document.getElementById(field).innerHTML = "Error Occurred";
		}
	}
	
	xmlhttp.open("GET", "../server/Validate_unique.php?field=" + field + "&query=" + query, false);
	xmlhttp.send();

}

// Full Name validation
function validate_full_name(full_name) {
	var valid_str = "OK";
	
	if (full_name == '') {
		valid_str = "Fullname field is required";
	} else if (full_name.length > 30) {
		valid_str = "Fullname length must not exceed 30 characters";
	} 
	
	return valid_str;
}

// Username validation
function validate_username(username) {
	var valid_str = "OK";
	
	if (username == '') {
		valid_str = "Username field is required";
	} 
	
	return valid_str;
}

// Email validation
function validate_email(email) {
	var valid_str = "OK";
	
	// Regular expression for Email
	var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;

	if (email == '') {
		valid_str = "Email field is required";
	} else if (!email.match(emailReg)) {
		valid_str = "Email format is invalid";
	}
	
	return valid_str;
}

// Password validation
function validate_password(pass) {
	var valid_str = "OK";
	
	if (pass == '') {
		valid_str = "Password field is required";
	} else if (pass.length > 10) {
		valid_str = "Password length must not exceed 10 characters";
	}
	
	return valid_str;
}

// Confirmation Password validation
function validate_conf_password(conf_pass, pass) {
	var valid_str = "OK";
	
	if (conf_pass == '') {
		valid_str = "Confirmation field is required";
	} else if (conf_pass != pass) {
		valid_str = "Confirmation field is not the same as the password";
	}
	
	return valid_str;
}

// Full Address validation
function validate_full_address(full_address) {
	var valid_str = "OK";
	
	if (full_address == '') {
		valid_str = "Full address field is required";
	} else if (full_address.length > 60) {
		valid_str = "Full address length must not exceed 60 characters";
	}
	
	return valid_str;
}

// Postal Code validation
function validate_postal_code(postal_code) {
	var valid_str = "OK";
	
	if (postal_code == '') {
		valid_str = "Postal code field is required";
	} else if (postal_code.length > 10) {
		valid_str = "Postal code length must not exceed 10 characters";
	}
	
	return valid_str;
}

// Phone Number validation
function validate_phone_number(phone_number) {
	var valid_str = "OK";
	
	if (phone_number == '') {
		valid_str = "Phone number field is required";
	} else if (phone_number.length > 20) {
		valid_str = "Phone number length must not exceed 20 characters";
	}
	
	return valid_str;
}

//////////////////////////////// OnBlur function /////////////////////////////////////////
function ValidationFullName() {
	
	var valid_ret = '';
	var full_name = document.getElementById("register_fullname").value;
	
	// validate full name format
	valid_ret = validate_full_name(full_name);
	if (valid_ret != "OK") {
		
		// invalid fullname
		document.getElementById("register_fullname_error").innerHTML = valid_ret;
	} else {
		document.getElementById("register_fullname_error").innerHTML = '';
	}
	
}

function ValidationUsername(field, query) {
	
	var valid_ret = '';
	var username = document.getElementById("register_username").value;
	
	// validate username format
	valid_ret = validate_username(username);
	if (valid_ret != "OK") {
		
		// invalid username
		document.getElementById("register_username_error").innerHTML = valid_ret;
	} else {
		
		// validate username uniqueness
		document.getElementById("register_username_error").innerHTML = '';
		
		// commented for tubes 2
		//Validation_Unique(field, query);
	}
}

function ValidationEmail(field, query) {
	
	var valid_ret = '';
	var email = document.getElementById("register_email").value;
	
	// validate email format
	valid_ret = validate_email(email);
	if (valid_ret != "OK") {

		// invalid email
		document.getElementById("register_email_error").innerHTML = valid_ret;
	} else {
		
		// validate email uniqueness
		document.getElementById("register_email_error").innerHTML = '';		
		
		// commented for tubes 2
		//Validation_Unique(field, query);
	}
	
}

function ValidationPassword() {
	
	var valid_ret = '';
	var pass = document.getElementById("register_password").value;
	
	// validate password
	valid_ret = validate_password(pass);
	if (valid_ret != "OK") {
		
		// invalid password
		document.getElementById("register_password_error").innerHTML = valid_ret;
	} else {
		document.getElementById("register_password_error").innerHTML = '';
	}
	
}

function ValidationConfPassword() {
	
	var valid_ret = '';
	var pass = document.getElementById("register_password").value;
	var conf_pass = document.getElementById("register_confpassword").value;
	
	// validate conf password
	valid_ret = validate_conf_password(conf_pass, pass);
	if (valid_ret != "OK") {
	
		// invalid conf password
		document.getElementById("register_confpassword_error").innerHTML = valid_ret;
	} else {
		document.getElementById("register_confpassword_error").innerHTML = '';
	}
	
}

function ValidationFullAddress() {
	
	var valid_ret = '';
	var full_address = document.getElementById("register_fulladdress").value;
	
	// validate full address
	valid_ret = validate_full_address(full_address);
	if (valid_ret != "OK") {
	
		// invalid full address
		document.getElementById("register_fulladdress_error").innerHTML = valid_ret;
	} else {
		document.getElementById("register_fulladdress_error").innerHTML = '';
	}
	
}

function ValidationPostalCode() {
	
	var valid_ret = '';
	var postal_code = document.getElementById("register_postalcode").value;
	
	// validate postal code
	valid_ret = validate_postal_code(postal_code);
	if (valid_ret != "OK") {
	
		// invalid postal code
		document.getElementById("register_postalcode_error").innerHTML = valid_ret;
	} else {
		document.getElementById("register_postalcode_error").innerHTML = '';
	}
	
}

function ValidationPhoneNumber() {
	
	var valid_ret = '';
	var phone_number = document.getElementById("register_phonenumber").value;

	// validate phone number
	valid_ret = validate_phone_number(phone_number);
	if (valid_ret != "OK") {
		
		// invalid phone number
		document.getElementById("register_phonenumber_error").innerHTML = valid_ret;
	} else {
		document.getElementById("register_phonenumber_error").innerHTML = '';
	}
		
}


function ValidateUserAccess() {
	
	// check the state of user access
	if (localStorage.getItem("username_global") === null &&
			localStorage.getItem("access_token_global") === null &&
			localStorage.getItem("expiry_time_global") === null &&
			localStorage.getItem("is_expired_global") === null) {
		
		return true;
		
	} else {
		
		return false;
		
	}
	
}


// Validate all form elements before submitting to the server
function ValidationForm() {

	// Notifying blank fields
	var full_name_blank = document.getElementById("register_fullname").value;
	var username_blank = document.getElementById("register_username").value;
	var email_blank = document.getElementById("register_email").value;
	var pass_blank = document.getElementById("register_password").value;
	var conf_pass_blank = document.getElementById("register_confpassword").value;
	var full_address_blank = document.getElementById("register_fulladdress").value;
	var postal_code_blank = document.getElementById("register_postalcode").value;
	var phone_number_blank = document.getElementById("register_phonenumber").value;

	// Notifying error fields
	var full_name = document.getElementById("register_fullname_error").innerHTML;
	var username = document.getElementById("register_username_error").innerHTML;
	var email = document.getElementById("register_email_error").innerHTML;
	var pass = document.getElementById("register_password_error").innerHTML;
	var conf_pass = document.getElementById("register_confpassword_error").innerHTML;
	var full_address = document.getElementById("register_fulladdress_error").innerHTML;
	var postal_code = document.getElementById("register_postalcode_error").innerHTML;
	var phone_number = document.getElementById("register_phonenumber_error").innerHTML;
	
	// Trim white space
	full_name = (full_name.trim) ? full_name.trim() : full_name.replace(/^\s+/,'');
	username = (username.trim) ? username.trim() : username.replace(/^\s+/,'');
	email = (email.trim) ? email.trim() : email.replace(/^\s+/,'');
	pass = (pass.trim) ? pass.trim() : pass.replace(/^\s+/,'');
	conf_pass = (conf_pass.trim) ? conf_pass.trim() : conf_pass.replace(/^\s+/,'');
	full_address = (full_address.trim) ? full_address.trim() : full_address.replace(/^\s+/,'');
	postal_code = (postal_code.trim) ? postal_code.trim() : postal_code.replace(/^\s+/,'');
	phone_number = (phone_number.trim) ? phone_number.trim() : phone_number.replace(/^\s+/,'');

	// Check all blank values
	if (full_name_blank == '' || username_blank == '' || email_blank == '' || pass_blank == '' || conf_pass_blank == '' || full_address_blank == '' || postal_code_blank == '' || phone_number_blank == '') {
		alert("Please fill all fields");
	} else {	
		// Check all values filled by user are Valid or Not. If all fields are invalid then generate alert
		if (full_name == '' && username == '' && email == '' && pass == '' && conf_pass == '' && full_address == '' && postal_code == '' && phone_number == '') {
			
			
			submission();
			
			//document.getElementById("register_form").submit();
						
		} else {
			alert("Please fill all valid information");	
		} 
	}
	
}

//Numeric character validation (only numeric character allowed)
function isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}
