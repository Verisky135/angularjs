
function logout() {
	
	// clear the localstorage and redirect to login page

	/*
	String uname = localStorage.getItem("username_global");
	
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
			
		
		} else if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			
		} else {

		}
	}
	
	
	xmlhttp.open("GET", "marketplace?service=hapusonline" + "&user_active=" + uname, false);
	xmlhttp.send();

	*/

	localStorage.clear();
	
	// redirect to login page
	window.location.href = "../index.jsp";
	
}