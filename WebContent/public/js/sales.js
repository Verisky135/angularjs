

/* SCRIPT FOR SALES */ 

var username_active;

var fetched_category;
var fetched_category_OBJ;

// fetched catalog product information
var fetched_category_num;
var product_name;
var product_price;
var product_quantity;
var product_photo;
var product_boughtby;
var product_boughtby_fullname;
var product_fulladdress;
var product_postalcode;
var product_phonenumber;
var product_purchasedtime;
var product_totalprice;


// add sales table dynamically
function AddSalesTable() {
	
	var myTableDiv, table, tableBody, tr, td;
	var row;
	
	myTableDiv = document.getElementById("user_sales");
	
	for (var idx = 0; idx < fetched_category_num; idx++) {
		table = document.createElement("TABLE");
		table.border = 0;
		table.width = 750;
		table.id = "salesProduct" + idx;
		table.style = "margin-bottom: 50px";
		//table.style = "table-layout: fixed; word-wrap: break-word; margin-bottom: 50px";
		
		myTableDiv.appendChild(table);
	
	
		// purchased_time
		row = document.getElementById("salesProduct" + idx).insertRow(0);
		row.insertCell(0);
		
		document.getElementById("salesProduct" + idx).rows[0].cells[0].innerHTML = product_purchasedtime[idx];
		document.getElementById("salesProduct" + idx).rows[0].cells[0].colSpan = 4;
		document.getElementById("salesProduct" + idx).rows[0].cells[0].style = "font-size: 17px";
		
		// horizontal line 1
		row = document.getElementById("salesProduct" + idx).insertRow(1);
		row.insertCell(0);
		
		document.getElementById("salesProduct" + idx).rows[1].cells[0].innerHTML = "<hr align='left'; width='100%'>";
		document.getElementById("salesProduct" + idx).rows[1].cells[0].colSpan = 4;
		
		// image
		row = document.getElementById("salesProduct" + idx).insertRow(2);
		row.insertCell(0);
		
		document.getElementById("salesProduct" + idx).rows[2].cells[0].innerHTML = "<img src='"+ "../images/" + product_photo[idx]+ "' width='120' height='120'>";
		document.getElementById("salesProduct" + idx).rows[2].cells[0].rowSpan = 2;
		document.getElementById("salesProduct" + idx).rows[2].cells[0].style = "width: 120px; height: 120px; vertical-align: top";
	
		/////////////////////////////////////
		
		// product_name
		row.insertCell(1);
		
		document.getElementById("salesProduct" + idx).rows[2].cells[1].innerHTML = "<b>" + product_name[idx] + "</b><br />" + "IDR " + product_totalprice[idx] + "<br />" + product_quantity + " pcs" + "<br />" + "@IDR " + product_price[idx];
		document.getElementById("salesProduct" + idx).rows[2].cells[1].style = "font-size: 22px; word-wrap: break-word; padding-left: 20px; vertical-align: top";
		
		// deliver to ...
		row.insertCell(2);
		
		document.getElementById("salesProduct" + idx).rows[2].cells[2].innerHTML = "Delivery to " + "<b>" + product_boughtby_fullname[idx] + "</b><br />" + product_fulladdress[idx] + "<br />" + product_postalcode[idx] + "<br />" + product_phonenumber[idx];
		document.getElementById("salesProduct" + idx).rows[2].cells[2].style = "width: 280px; font-size: 17px; word-wrap: break-word; vertical-align: top";
		
		// bought from 
		row = document.getElementById("salesProduct" + idx).insertRow(3);
		row.insertCell(0);
		
		document.getElementById("salesProduct" + idx).rows[3].cells[0].innerHTML = "bought by " + "<b>" + product_boughtby[idx] + "</b>";
		document.getElementById("salesProduct" + idx).rows[3].cells[0].colSpan = 3;
		document.getElementById("salesProduct" + idx).rows[3].cells[0].style = "font-size: 17px; padding-top: 20px; padding-left: 20px";
		
		/////////////////////////////////////
		/*
		row.insertCell(2);
		
		document.getElementById("salesProduct" + idx).rows[2].cells[2].innerHTML = "Delivery to " + product_boughtby_fullname[idx];
		document.getElementById("salesProduct" + idx).rows[2].cells[2].style = "font-size: 14px";
		*/
		
		// price
		/*
		row = document.getElementById("salesProduct" + idx).insertRow(3);
		row.insertCell(0);
		row.insertCell(1);
		
		document.getElementById("salesProduct" + idx).rows[3].cells[0].innerHTML = "IDR " + product_totalprice[idx];
		document.getElementById("salesProduct" + idx).rows[3].cells[1].innerHTML = product_fulladdress[idx];
		document.getElementById("salesProduct" + idx).rows[3].cells[0].style = "font-size: 14px";
		//document.getElementById("salesProduct" + idx).rows[3].cells[1].style = "width: 70px; vertical-align: bottom; padding-left: 50px";
		*/
		
		/*
		row = document.getElementById("salesProduct" + idx).insertRow(3);
		row.insertCell(0);
		row.insertCell(1);
		
		document.getElementById("salesProduct" + idx).rows[3].cells[0].innerHTML = "IDR " + product_totalprice[idx];
		document.getElementById("salesProduct" + idx).rows[3].cells[1].innerHTML = "Delivery to " + product_boughtby_fullname[idx];
		document.getElementById("salesProduct" + idx).rows[3].cells[1].colSpan = 2;
		document.getElementById("salesProduct" + idx).rows[3].cells[0].style = "font-size: 19px";
		document.getElementById("salesProduct" + idx).rows[3].cells[1].style = "width: 70px; word-wrap: break-word; vertical-align: bottom; padding-left: 50px";
		
		// quantity
		row = document.getElementById("salesProduct" + idx).insertRow(4);
		row.insertCell(0);
		row.insertCell(1);
		
		document.getElementById("salesProduct" + idx).rows[4].cells[0].innerHTML = product_quantity + " pcs";
		document.getElementById("salesProduct" + idx).rows[4].cells[0].colspan = 2;
		document.getElementById("salesProduct" + idx).rows[4].cells[0].style = "width: 390px; word-wrap: break-word; vertical-align: top";
		
		// full address
		document.getElementById("salesProduct" + idx).rows[4].cells[1].innerHTML = product_fulladdress[idx];
		document.getElementById("salesProduct" + idx).rows[4].cells[1].colSpan = 2;
		document.getElementById("salesProduct" + idx).rows[4].cells[1].style = "height: 15px; word-wrap: break-word; vertical-align: top; padding-left: 50px";
		
		
		// price 
		row = document.getElementById("salesProduct" + idx).insertRow(5);
		row.insertCell(0);
		row.insertCell(1);
		
		document.getElementById("salesProduct" + idx).rows[5].cells[0].innerHTML = "@IDR " + product_price[idx];
		document.getElementById("salesProduct" + idx).rows[5].cells[1].innerHTML = product_postalcode[idx];
		document.getElementById("salesProduct" + idx).rows[5].cells[1].colSpan = 2;
		document.getElementById("salesProduct" + idx).rows[5].cells[0].style = "font-size: 19px";
		document.getElementById("salesProduct" + idx).rows[5].cells[1].style = "width: 70px; vertical-align: bottom; padding-left: 50px";
		
		
		//phone number
		row = document.getElementById("salesProduct" + idx).insertRow(6);
		row.insertCell(0);
		row.insertCell(1);
		
		document.getElementById("salesProduct" + idx).rows[6].cells[1].innerHTML = product_phonenumber[idx];
		document.getElementById("salesProduct" + idx).rows[6].cells[1].colSpan = 2;
		document.getElementById("salesProduct" + idx).rows[6].cells[0].style = "font-size: 19px";
		document.getElementById("salesProduct" + idx).rows[6].cells[1].style = "width: 70px; vertical-align: bottom; padding-left: 50px";
		*/
						
	}
	
}


// retrieves all sales by the corresponding user
function SearchAJAX(field) {
	
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
			
			document.getElementById(field).innerHTML = "Loading...";
		
		} else if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			
			var xmlDoc, x, y;
			var numOfSearch;
			var access_valid;
			
			/** response in XML format **/
			
			var parser = new DOMParser();
			xmlDoc = parser.parseFromString(xmlhttp.responseText, "text/xml"); 
				
			access_valid = xmlDoc.getElementsByTagName('access-validity')[0].childNodes[0].nodeValue;
			
		
			if (access_valid == 200) {
			
				
				numOfSearch = xmlDoc.getElementsByTagName('numOfSearch')[0].childNodes[0].nodeValue;
				
				fetched_category_num = numOfSearch;
			
				
				// empty the arrays
				product_boughtby = [];
				product_boughtby_fullname = [];
				product_name = [];
				product_price = [];
				product_photo = [];
				product_quantity = [];
				product_fulladdress = [];
				product_postalcode = [];
				product_phonenumber = [];
				product_purchasedtime = [];
				product_totalprice = [];
				
				// assign the fetched product information into variables
				for (var idx = 0; idx < numOfSearch; idx++) {
					
					product_boughtby.push(xmlDoc.getElementsByTagName('product_boughtby'+idx)[0].childNodes[0].nodeValue);
					product_boughtby_fullname.push(xmlDoc.getElementsByTagName('consignee'+idx)[0].childNodes[0].nodeValue);
					product_name.push(xmlDoc.getElementsByTagName('product_name'+idx)[0].childNodes[0].nodeValue);
					product_price.push(xmlDoc.getElementsByTagName('price'+idx)[0].childNodes[0].nodeValue);
					product_photo.push(xmlDoc.getElementsByTagName('photo'+idx)[0].childNodes[0].nodeValue);
					product_quantity.push(xmlDoc.getElementsByTagName('quantity'+idx)[0].childNodes[0].nodeValue);
					product_fulladdress.push(xmlDoc.getElementsByTagName('full_address'+idx)[0].childNodes[0].nodeValue);
					product_postalcode.push(xmlDoc.getElementsByTagName('postal_code'+idx)[0].childNodes[0].nodeValue);	
					product_phonenumber.push(xmlDoc.getElementsByTagName('phone_number'+idx)[0].childNodes[0].nodeValue);
					product_purchasedtime.push(xmlDoc.getElementsByTagName('added_time'+idx)[0].childNodes[0].nodeValue);
					product_totalprice.push(xmlDoc.getElementsByTagName('totalprice'+idx)[0].childNodes[0].nodeValue);
			
				}
			
				document.getElementById(field).innerHTML = "";
				
				console.log("length sales.js: " + numOfSearch);
				
				if (numOfSearch > 0) {
					
					// create sales table dynamically
					AddSalesTable();
					
				}
			
			} else {
				
				alert("Invalid access");
				
				// clear the localstorage
				localStorage.clear();
				
				// redirect to login page
				window.location.href = "../index.jsp";
			
			}
			
			
			
		} else {
			
			// error condition
			//document.getElementById(field).innerHTML = "ErrorS Occurred";
		
		}
	}
	
	
	/*
	xmlhttp.open("GET", "marketplace?service=sales.php"+"username_active=" + username_active, false);
	xmlhttp.send();
	*/

	
	var url = "marketplace";
	xmlhttp.open("POST", url, true);

	// Send the proper header information along with the request
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

	
	var params = "service=sales" + "&" + 
	"username_global=" + localStorage.getItem("username_global") + "&" +
	"access_token_global=" + localStorage.getItem("access_token_global") + "&" +
	"expiry_time_global=" + localStorage.getItem("expiry_time_global") + "&" +
	"is_expired_global=" + localStorage.getItem("is_expired_global");
	
	xmlhttp.send(params);
	
	
}

function SetUsernameActive(val) {
	username_active = val;
}
		