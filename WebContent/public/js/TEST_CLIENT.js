function tes_client_comm() {
	
	var xmlhttp;
	
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	var url = "chat_service";
	xmlhttp.open("POST", url, true);

	//Send the proper header information along with the request
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

	xmlhttp.onreadystatechange = function() {
		//Call a function when the state changes.
	    if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	
	    	if (xmlhttp.responseText == "200") {
	    		console.log("Successfully send the message\n");
	    	} else {
	    		console.log("Failed to send the message\n");
	    	}
	    	
	    }
	}
	
	// presume that we want to send the message to user18
	var params = "sender_username=" + localStorage.getItem("username_global") + "&" +
			"receiver_username=user18" + "&" +
			"message=Hello World!" + "&" +
			"client_purpose=send"; 
	
	xmlhttp.send(params);
	
}