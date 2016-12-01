
var edit_product_name;
var edit_product_desc;
var edit_product_price;
var edit_product_pic;
var fetched_category;

// validates product name
function ValidationName() {
	var product_name = document.getElementById("product_name").value;
	
	if (product_name == '') {
		document.getElementById("add_product_name_error").innerHTML = "Product name is required";
	} else {
		document.getElementById("add_product_name_error").innerHTML = "";
	}
}

// validates product description
function ValidationDesc() {
	var product_desc = document.getElementById("product_desc").value;
	
	if (product_desc == '') {
		document.getElementById("add_product_desc_error").innerHTML = "Product description is required";
	} else {
		
		// must have max 200 characters
		if (product_desc.length > 200) {
			document.getElementById("add_product_desc_error").innerHTML = "Product description max length is 200 chars";
		} else {
			document.getElementById("add_product_desc_error").innerHTML = "";
		}
	}
}

// validates product price
function ValidationPrice() {
	var product_price = document.getElementById("product_price").value;
	
	if (product_price == '') {
		document.getElementById("add_product_price_error").innerHTML = "Product price is required";
	} else {
		document.getElementById("add_product_price_error").innerHTML = "";
	}
}
function ValidationPic(){
	var product_pic = document.getElementById("product_pic").value;
	
	if (product_pic == '') {
		document.getElementById("add_product_pic_error").innerHTML = "Product images is required";
	} else {
		document.getElementById("add_product_pic_error").innerHTML = "";
	}
}


// AJAX for submitting add product values
function SubmitAddProduct() {
	
var xmlhttp;
	
	if (window.XMLHttpRequest) { 
		// for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else { 
		// for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	
	xmlhttp.onreadystatechange = function() {
		
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
		
			if (xmlhttp.responseText == "0") {
				// success - redirect to purchases page
				
				window.location.href = "your_product.jsp";
				
			} else if(xmlhttp.responseText == "-999") {
				
				alert("Invalid access");
				
				// clear the localstorage
				localStorage.clear();
				
				// redirect to login page
				window.location.href = "../index.jsp";
			
			}
			
		}
	}
	
	
	console.log("AJAX for confirm purchase");
	
	
	var url = "marketplace";
	xmlhttp.open("POST", url, true);

	// Send the proper header information along with the request
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

	
	var params = "service=addproduct" + "&" + 
	"username_global=" + localStorage.getItem("username_global") + "&" +
	"access_token_global=" + localStorage.getItem("access_token_global") + "&" +
	"expiry_time_global=" + localStorage.getItem("expiry_time_global") + "&" +
	"is_expired_global=" + localStorage.getItem("is_expired_global") + "&" +
	
	"product_name=" + document.getElementById("product_name").value + "&" +
	"product_desc=" + document.getElementById("product_desc").value + "&" +
	"product_price=" + document.getElementById("product_price").value + "&" + 
	"product_pic=" + document.getElementById("fileName_hidden").value;
	
	
	xmlhttp.send(params);
	
}


// validates all form elements before submitting to the server
function ValidationForm() {

	// Notifying blank fields
	var product_name = document.getElementById("product_name").value;
	var product_desc = document.getElementById("product_desc").value;
	var product_price = document.getElementById("product_price").value;
	var product_pic = document.getElementById("product_pic").value;
	// Notifying error fields
	var product_name_error = document.getElementById("add_product_name_error").innerHTML;
	var product_desc_error = document.getElementById("add_product_desc_error").innerHTML;
	var product_price_error = document.getElementById("add_product_price_error").innerHTML;
	var product_pic_error = document.getElementById("add_product_pic_error").innerHTML;
	
	// Trim white space
	product_name_error = (product_name_error.trim) ? product_name_error.trim() : product_name_error.replace(/^\s+/,'');
	product_desc_error = (product_desc_error.trim) ? product_desc_error.trim() : product_desc_error.replace(/^\s+/,'');
	product_price_error = (product_price_error.trim) ? product_price_error.trim() : product_price_error.replace(/^\s+/,'');
	product_pic_error = (product_pic_error.trim) ? product_pic_error.trim() : product_pic_error.replace(/^\s+/,'');
	
	// Check all blank values
	if (product_name == '' || product_desc == '' || product_price == '' || product_pic == '') {
		alert("Please fill all fields");
	} else {	
		
		// Check all values filled by user are Valid or Not. If all fields are invalid then generate alert
		if (product_name_error == '' && product_desc_error == '' && product_price_error == '' && product_pic_error == '') {
			
			//document.getElementById("product_form").submit();
			
			SubmitAddProduct();
			
		} else {
			alert("Please fill all valid information");	
		}
	}
}


function SubmitEditProduct() {
	
	var xmlhttp;
	//var id = 1;
	
	if (window.XMLHttpRequest) { 
		// for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else { 
		// for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xmlhttp.onreadystatechange = function() {
		
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			
			if (xmlhttp.responseText == "0") {
				// success - redirect to purchases page
				
				window.location.href = "your_product.jsp";
				
			} else if(xmlhttp.responseText == "-999") {
				
				alert("Invalid access");
				
				// clear the localstorage
				localStorage.clear();
				
				// redirect to login page
				window.location.href = "../index.jsp";
			
				
			}
			
		}
	}
	
	
	var url = "marketplace";
	xmlhttp.open("POST", url, true);

	// Send the proper header information along with the request
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

	
	var params = "service=editproduct" + "&" + 
	"username_global=" + localStorage.getItem("username_global") + "&" +
	"access_token_global=" + localStorage.getItem("access_token_global") + "&" +
	"expiry_time_global=" + localStorage.getItem("expiry_time_global") + "&" +
	"is_expired_global=" + localStorage.getItem("is_expired_global") + "&" +
	
	"product_id=" + localStorage.getItem("product_edit_id") + "&" + 
	"product_name=" + document.getElementById("product_name").value + "&" +
	"product_desc=" + document.getElementById("product_desc").value + "&" +
	"product_price=" + document.getElementById("product_price").value + "&" +
	"product_photo=" + document.getElementById("product_pic").value;
	
	
	xmlhttp.send(params);
	
}


function ValidationFormEdit() {

	// Notifying blank fields
	var product_name = document.getElementById("product_name").value;
	var product_desc = document.getElementById("product_desc").value;
	var product_price = document.getElementById("product_price").value;
	// Notifying error fields
	var product_name_error = document.getElementById("add_product_name_error").innerHTML;
	var product_desc_error = document.getElementById("add_product_desc_error").innerHTML;
	var product_price_error = document.getElementById("add_product_price_error").innerHTML;
	
	// Trim white space
	product_name_error = (product_name_error.trim) ? product_name_error.trim() : product_name_error.replace(/^\s+/,'');
	product_desc_error = (product_desc_error.trim) ? product_desc_error.trim() : product_desc_error.replace(/^\s+/,'');
	product_price_error = (product_price_error.trim) ? product_price_error.trim() : product_price_error.replace(/^\s+/,'');
	
	// Check all blank values
	if (product_name == '' || product_desc == '' || product_price == '') {
		alert("Please fill all fields");
	} else {	
		
		// Check all values filled by user are Valid or Not. If all fields are invalid then generate alert
		if (product_name_error == '' && product_desc_error == '' && product_price_error == '') {
			
			//document.getElementById("product_form").submit();
			
			SubmitEditProduct();
			
		} else {
			alert("Please fill all valid information");	
		}
	}
}

// Numeric character validation (only numeric character allowed)
function isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}

// Get name of the chosen file to be upload
function getFileName() {
	var x = document.getElementById("product_pic");
	if (x != null) {
		document.getElementById("file_desc").innerHTML = x.files[0].name;
		//document.getElementById("fileName_hidden").value = x.files[0].name;
	}
}

function goToPreviousPage() {
		
}

