var totalprice;


/* SCRIPT FOR CONFIRMATION PURCHASE */

// counts the total price
function countQuantity(pcs) {
	
	var price = document.getElementById("price_not_formatted").value;
	
	var totalPrice = pcs * price;
	
	document.getElementById("form_confirm_totalprice").innerHTML = "IDR " + totalPrice.toString().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1.");
}

// validates the consignee field
function ValidationConsignee() {
	var field = document.getElementById("form_confirm_consignee").value;
	
	if (field == '') {
		document.getElementById("conf_purch_consignee_error").innerHTML = "Consignee is required";
	} else {
		if (field.length > 30) {
			document.getElementById("conf_purch_consignee_error").innerHTML = "Consignee length must not exceed 30 characters";
		} else {
			document.getElementById("conf_purch_consignee_error").innerHTML = "";
		}
	}
}

// validates the full address field
function ValidationFullAddress() {
	var field = document.getElementById("form_confirm_fulladdress").value;
	
	if (field == '') {
		document.getElementById("conf_purch_fulladdr_error").innerHTML = "Full address is required";	
	} else { 
		if (field.length > 60) {
			document.getElementById("conf_purch_fulladdr_error").innerHTML = "Full address length must not exceed 60 characters";
		} else {
			document.getElementById("conf_purch_fulladdr_error").innerHTML = "";	
		}
	}
}

// validates the postal code field
function ValidationPostalCode() {
	var field = document.getElementById("form_confirm_postalcode").value;
	
	if (field == '') {
		document.getElementById("conf_purch_postalcode_error").innerHTML = "Postal code is required";	
	} else {
		if (field.length > 10) {
			document.getElementById("conf_purch_postalcode_error").innerHTML = "Postal code length must not exceed 10 characters";
		} else {
			document.getElementById("conf_purch_postalcode_error").innerHTML = "";	
		}
	}
}

// validates the phone number field
function ValidationPhoneNumber() {
	var field = document.getElementById("form_confirm_phonenumber").value;
	
	if (field == '') {
		document.getElementById("conf_purch_phone_error").innerHTML = "Phone number is required";
	} else {
		if (field.length > 20) {
			document.getElementById("conf_purch_phone_error").innerHTML = "Phone number length must not exceed 20 characters";
		} else {
			document.getElementById("conf_purch_phone_error").innerHTML = "";
		}
	} 
}

// validates the credit card input
function ValidationCreditCard() {
	var field = document.getElementById("form_confirm_cardnumber").value;

	if (field == '') {
		document.getElementById("conf_purch_cardnumber_error").innerHTML = "Credit card number is required";
	} else {
		// check whether the number of digit is 12 or not
		if (field.length != 12) {
			document.getElementById("conf_purch_cardnumber_error").innerHTML = "Credit card number must be 12 for the length";
		} else {
			document.getElementById("conf_purch_cardnumber_error").innerHTML = "";
		}
	}
}

// validates the card verification input
function ValidationCardVerification() {
	var field = document.getElementById("form_confirm_verifyvalue").value;
	
	if (field == '') {
		document.getElementById("conf_purch_cardverif_error").innerHTML = "Card verification value is required";
	} else {
		// check whether the number of digit is 3 or not
		if (field.length != 3) {
			document.getElementById("conf_purch_cardverif_error").innerHTML = "Card verification value must be 3 for the length";
		} else {
			document.getElementById("conf_purch_cardverif_error").innerHTML = "";
		}
	}
}


// AJAX for submitting purchase
function submitPurchase() {
	
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
				
				window.location.href = "purchases.jsp";
				
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

	
	var params = "service=confirm_purchase" + "&" + 
	"username_global=" + localStorage.getItem("username_global") + "&" +
	"access_token_global=" + localStorage.getItem("access_token_global") + "&" +
	"expiry_time_global=" + localStorage.getItem("expiry_time_global") + "&" +
	"is_expired_global=" + localStorage.getItem("is_expired_global") + "&" +
	
	"product_id=" + localStorage.getItem("product_data_id") + "&" +
	"product_boughtby=" + localStorage.getItem("username_global") + "&" +
	//"product_boughtfrom=" + localStorage.getItem("boughtfrom_username") + "&" +
	"product_name=" + localStorage.getItem("product_data_name") + "&" +
	"price=" + localStorage.getItem("product_data_price") + "&" +
	"totalprice=" + localStorage.getItem("product_data_price") + "&" + 
	"photo=" + localStorage.getItem("product_data_photo") + "&" +
	"quantity=" + localStorage.getItem("product_data_quantity") + "&" +
	"consignee=" + localStorage.getItem("buying_fullname") + "&" +
	"full_address=" + localStorage.getItem("buying_fulladdress") + "&" +
	"postal_code=" + localStorage.getItem("buying_postalcode") + "&" +
	"phone_number=" + localStorage.getItem("buying_phonenumber");
	
	
	xmlhttp.send(params);
	
					
}



// validates the form before submitting
function ConfirmHandling() {
	
	// Notifying blank fields
	var consignee_blank = document.getElementById("form_confirm_consignee").value;
	var fulladdress_blank = document.getElementById("form_confirm_fulladdress").value;
	var postalcode_blank = document.getElementById("form_confirm_postalcode").value;
	var phonenumber_blank = document.getElementById("form_confirm_phonenumber").value;
	var cardnumber_blank = document.getElementById("form_confirm_cardnumber").value;
	var cardverif_blank = document.getElementById("form_confirm_verifyvalue").value;
	
	// Notifying error fields
	var consignee_error = document.getElementById("conf_purch_consignee_error").innerHTML;
	var fulladdress_error = document.getElementById("conf_purch_fulladdr_error").innerHTML;
	var postalcoode_error = document.getElementById("conf_purch_postalcode_error").innerHTML;
	var phonenumber_error = document.getElementById("conf_purch_phone_error").innerHTML;
	var cardnumber_error = document.getElementById("conf_purch_cardnumber_error").innerHTML;
	var cardverif_error = document.getElementById("conf_purch_cardverif_error").innerHTML;
	
	// Trim white space
	consignee_error = (consignee_error.trim) ? consignee_error.trim() : consignee_error.replace(/^\s+/,'');
	fulladdress_error = (fulladdress_error.trim) ? fulladdress_error.trim() : fulladdress_error.replace(/^\s+/,'');
	postalcoode_error = (postalcoode_error.trim) ? postalcoode_error.trim() : postalcoode_error.replace(/^\s+/,'');
	phonenumber_error = (phonenumber_error.trim) ? phonenumber_error.trim() : phonenumber_error.replace(/^\s+/,'');
	cardnumber_error = (cardnumber_error.trim) ? cardnumber_error.trim() : cardnumber_error.replace(/^\s+/,'');
	cardverif_error = (cardverif_error.trim) ? cardverif_error.trim() : cardverif_error.replace(/^\s+/,'');
	
	// Check all blank values
	if (consignee_blank == '' || fulladdress_blank == '' || postalcode_blank == '' || phonenumber_blank == '' || cardnumber_blank == '' || cardverif_blank == '') {
		alert("Please fill all fields");
	} else {	
		// Check all values filled by user are Valid or Not. If all fields are invalid then generate alert
		if (consignee_error == '' && fulladdress_error == '' && postalcoode_error == '' && phonenumber_error == '' && cardnumber_error == '' && cardverif_error == '') {
			
			// User confirmation
			var user_confirm = confirm("Apakah data yang Anda masukkan sudah benar?");
			if (user_confirm) {
				
				//document.getElementById("form_confirm_purchase").submit();
				
				submitPurchase();
				
			}
		} else {
			alert("Please fill all valid information");	
		} 
	}
	
}

// cancels the purchase
function CancelHandling() {
	
	// redirect back to the catalog page
	
	
}
