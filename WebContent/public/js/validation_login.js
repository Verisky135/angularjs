
/* LOGIN VALIDATION */

// Username and Email existence validation
function Validation_Exist(query) {
	
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
			document.getElementById("login_username_error").innerHTML = "Validating..";
		} else if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			document.getElementById("login_username_error").innerHTML = xmlhttp.responseText;
		} else {
			document.getElementById("login_username_error").innerHTML = "Error Occurred...";
		}
	}
	
	xmlhttp.open("GET", "server/Validate_exist.php?query=" + query, false);
	xmlhttp.send();

}

// Username validation
function validate_username(username) {
	var valid_str = "OK";
	
	if (username == '') {
		valid_str = "Email or username field is required";
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

// Username (email) and password match validation
function validate_match(email, pass) {
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
			document.getElementById("match_status").innerHTML = "Validating";
		} else if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			
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
	        
	        
	        window.location.href = "views/catalog.jsp";
	        
		}
	}
	
	console.log("AJAX validation login");
	
	var url = "identity_login";
	xmlhttp.open("POST", url, true);

	// Send the proper header information along with the request
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

	
	var params = "email=" + email + "&" + "pass=" + pass;

	
	xmlhttp.send(params);

	
}

///////////////////// OnBlur functions ////////////////////////
function ValidationUsername() {
	var valid_ret = '';
	var username = document.getElementById("login_email").value;
	
	// validate username format
	valid_ret = validate_username(username);
	
	if (valid_ret != "OK") {
		
		// invalid username
		document.getElementById("login_username_error").innerHTML = valid_ret;
	} else {
		
		// validate username uniqueness
		document.getElementById("login_username_error").innerHTML = '';
		//Validation_Exist(username);
	}
}

function ValidationPassword() {
	
	var valid_ret = '';
	var pass = document.getElementById("login_password").value;
	
	// validate password
	valid_ret = validate_password(pass);
	
	if (valid_ret != "OK") {
		
		// invalid password
		document.getElementById("login_password_error").innerHTML = valid_ret;
	} else {
		document.getElementById("login_password_error").innerHTML = '';
	}
	
}


function ValidateUserAccess() {
	
	// check the state of user access
	if (localStorage.getItem("username_global") === null &&
			localStorage.getItem("access_token_global") === null &&
			localStorage.getItem("expiry_time_global") === null &&
			localStorage.getItem("is_expired_global") === null) {
		
		console.log("VALIDATE LOGIN ACCESS: TRUE");
		
		return true;
		
	} else {
		
		alert(localStorage.getItem("username_global") + ":" +
				localStorage.getItem("access_token_global") + ":" +
				localStorage.getItem("expiry_time_global") + ":" +
				localStorage.getItem("is_expired_global"));
		
		console.log("VALIDATE LOGIN ACCESS: FALSE");
		
		return false;
		
	}
		
}


function ValidationForm() {
	
	// Notifying blank fields
	var email_blank = document.getElementById("login_email").value;
	var pass_blank = document.getElementById("login_password").value;
	
	// Notifying error fields
	var email = document.getElementById("login_username_error").innerHTML;
	var pass = document.getElementById("login_password_error").innerHTML;
	
	// Trim white space
	email = (email.trim) ? email.trim() : email.replace(/^\s+/,'');
	pass = (pass.trim) ? pass.trim() : pass.replace(/^\s+/,'');
		
	// Check all blank values
	if (email_blank == '' || pass_blank == '') {
		alert("Please fill all fields");
	} else {	
		// Check all values filled by user are Valid or Not. If all fields are invalid then generate alert
		if (email == '' && pass == '') {
			
			// Check whether username (email) and password are match
			validate_match(email_blank, pass_blank);
			
			/*
			var match_status = document.getElementById("match_status").innerHTML;
			
			// Trim white space
			match_status = (match_status.trim) ? match_status.trim() : match_status.replace(/^\s+/,'');
			
			if (match_status == "match") {
				
				//document.getElementById("login_form").submit();
				
				// redirect to catalog page
				window.location.href = "views/catalog.jsp";
				
			} else if (match_status == "not_match") {
				alert("Invalid username and password combination");
			}
			*/
			
		} else {
			alert("Please fill all valid information");	
		} 
	}
	
}