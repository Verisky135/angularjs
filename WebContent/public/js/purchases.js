
/* SCRIPT for searching catalog */

var username_active;

var fetched_category;
var fetched_category_OBJ;

// fetched catalog product information
var fetched_category_num;

//var product_boughtby;
var product_boughtfrom;
var product_name;
var price;
var totalprice;
var photo;
var quantity;
var consignee;
var full_address;
var postal_code;
var phone_number;
var added_time;
			
function AddPurchasesTable() {
	
	var myTableDiv, table, tableBody, tr, td;
	var row;
	
	myTableDiv = document.getElementById("purchases");
	
	
	for (var idx = 0; idx < fetched_category_num; idx++) {
		table = document.createElement("TABLE");
		table.border = 0;
		table.width = 750;
		table.id = "tab" + idx;
		table.style = "margin-bottom: 50px";
		//table.style = "table-layout: fixed; word-wrap: break-word; margin-bottom: 50px";
		
		myTableDiv.appendChild(table);
	
		// added_time
		row = document.getElementById("tab" + idx).insertRow(0);
		row.insertCell(0);
		
		document.getElementById("tab" + idx).rows[0].cells[0].innerHTML = added_time[idx];
		document.getElementById("tab" + idx).rows[0].cells[0].colSpan = 4;
		document.getElementById("tab" + idx).rows[0].cells[0].style = "font-size: 17px";
		
		// horizontal line 1
		row = document.getElementById("tab" + idx).insertRow(1);
		row.insertCell(0);
		
		document.getElementById("tab" + idx).rows[1].cells[0].innerHTML = "<hr align='left'; width='100%' />";
		document.getElementById("tab" + idx).rows[1].cells[0].colSpan = 4;
		
		// image
		row = document.getElementById("tab" + idx).insertRow(2);
		row.insertCell(0);
		
		document.getElementById("tab" + idx).rows[2].cells[0].innerHTML = "<img src='"+ "../images/" + photo[idx]+ "' width='120' height='120'>";
		document.getElementById("tab" + idx).rows[2].cells[0].rowSpan = 2;
		document.getElementById("tab" + idx).rows[2].cells[0].style = "width: 120px; height: 120px; vertical-align: top";
	
		
		//////////////////////
		row.insertCell(1);
		
		document.getElementById("tab" + idx).rows[2].cells[1].innerHTML = "<b>" + product_name[idx] + "</b>" + "<br />" + "IDR " + totalprice[idx] + "<br />" + quantity[idx] + " pcs" + "<br />" + "@IDR " + price[idx];
		document.getElementById("tab" + idx).rows[2].cells[1].style = "font-size: 22px; word-wrap: break-word; padding-left: 20px; vertical-align: top";
		
		row.insertCell(2);
		
		document.getElementById("tab" + idx).rows[2].cells[2].innerHTML = "Delivery to " + "<b>" + consignee[idx] + "</b>" + "<br />" + full_address[idx] + "<br />" + postal_code[idx] + "<br />" + phone_number[idx];
		document.getElementById("tab" + idx).rows[2].cells[2].style = "width: 280px; font-size: 17px; word-wrap: break-word; vertical-align: top";
		
		// bought from 
		row = document.getElementById("tab" + idx).insertRow(3);
		row.insertCell(0);
		
		document.getElementById("tab" + idx).rows[3].cells[0].innerHTML = "bought from " + "<b>" + product_boughtfrom[idx] + "</b>";
		document.getElementById("tab" + idx).rows[3].cells[0].colSpan = 3;
		document.getElementById("tab" + idx).rows[3].cells[0].style = "font-size: 17px; padding-top: 20px; padding-left: 20px";
		
		//////////////////////
		
		
		/*
		// product_name
		row.insertCell(1);
		
		document.getElementById("tab" + idx).rows[2].cells[1].innerHTML = product_name[idx];
		//document.getElementById("tab" + idx).rows[2].cells[1].colSpan = 3;
		document.getElementById("tab" + idx).rows[2].cells[1].style = "font-size: 18px; font-weight: bold";
		
		// delivery to
		row.insertCell(2);
		document.getElementById("tab" + idx).rows[2].cells[2].innerHTML = "Delivery to "+ consignee[idx];
		//document.getElementById("tab" + idx).rows[3].cells[1].colSpan = 2;
		document.getElementById("tab" + idx).rows[2].cells[2].style = "width: 200px; word-wrap: break-word; vertical-align: bottom";
		
		
	
		// total price
		row = document.getElementById("tab" + idx).insertRow(3);
		row.insertCell(0);
		row.insertCell(1);
		
		document.getElementById("tab" + idx).rows[3].cells[0].innerHTML = "IDR " + totalprice[idx];
		document.getElementById("tab" + idx).rows[3].cells[1].innerHTML = "Delivery to "+ consignee[idx];
		document.getElementById("tab" + idx).rows[3].cells[1].colSpan = 2;
		document.getElementById("tab" + idx).rows[3].cells[0].style = "font-size: 19px";
		document.getElementById("tab" + idx).rows[3].cells[1].style = "width: 70px; word-wrap: break-word; vertical-align: bottom";
		
		// quantity
		row = document.getElementById("tab" + idx).insertRow(4);
		row.insertCell(0);
		row.insertCell(1);
		
		document.getElementById("tab" + idx).rows[4].cells[0].innerHTML = quantity[idx] + " pcs";
		document.getElementById("tab" + idx).rows[4].cells[0].colspan = 2;
		document.getElementById("tab" + idx).rows[4].cells[0].style = "width: 390px; word-wrap: break-word; vertical-align: top";
		
		// full_address
		document.getElementById("tab" + idx).rows[4].cells[1].innerHTML = full_address[idx];
		document.getElementById("tab" + idx).rows[4].cells[1].colSpan = 2;
		document.getElementById("tab" + idx).rows[4].cells[1].style = "height: 15px; word-wrap: break-word; vertical-align: top";
		
		// price 
		row = document.getElementById("tab" + idx).insertRow(5);
		row.insertCell(0);
		row.insertCell(1);
		
		document.getElementById("tab" + idx).rows[5].cells[0].innerHTML = "@IDR " + price[idx];
		document.getElementById("tab" + idx).rows[5].cells[1].innerHTML = postal_code[idx];
		document.getElementById("tab" + idx).rows[5].cells[1].colSpan = 2;
		document.getElementById("tab" + idx).rows[5].cells[0].style = "font-size: 19px";
		document.getElementById("tab" + idx).rows[5].cells[1].style = "width: 70px; vertical-align: bottom; padding-left: 50px";
		
		//phone number
		row = document.getElementById("tab" + idx).insertRow(6);
		row.insertCell(0);
		row.insertCell(1);
		
		//document.getElementById("tab" + idx).rows[6].cells[0].innerHTML = "@IDR " + price[idx];
		document.getElementById("tab" + idx).rows[6].cells[1].innerHTML = phone_number[idx];
		document.getElementById("tab" + idx).rows[6].cells[1].colSpan = 2;
		document.getElementById("tab" + idx).rows[6].cells[0].style = "font-size: 19px";
		document.getElementById("tab" + idx).rows[6].cells[1].style = "width: 70px; vertical-align: bottom; padding-left: 50px";
		*/
			
	}
	
}

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
				//product_boughtby = [];
				product_boughtfrom = [];
				product_name = [];
				price = [];
				totalprice = [];
				photo = [];
				quantity = [];
				consignee = [];
				full_address = [];
				postal_code = [];
				phone_number = [];
				added_time = [];
				
				
				// assign the fetched product information into variables
				for (var idx = 0; idx < numOfSearch; idx++) {
					
					//product_boughtby.push(fetched_category_OBJ[idx]["product_boughtby"]);
					product_boughtfrom.push(xmlDoc.getElementsByTagName('product_boughtfrom'+idx)[0].childNodes[0].nodeValue);
					product_name.push(xmlDoc.getElementsByTagName('product_name'+idx)[0].childNodes[0].nodeValue);
					price.push(xmlDoc.getElementsByTagName('price'+idx)[0].childNodes[0].nodeValue);
					totalprice.push(xmlDoc.getElementsByTagName('totalprice'+idx)[0].childNodes[0].nodeValue);
					photo.push(xmlDoc.getElementsByTagName('photo'+idx)[0].childNodes[0].nodeValue);
					quantity.push(xmlDoc.getElementsByTagName('quantity'+idx)[0].childNodes[0].nodeValue);
					consignee.push(xmlDoc.getElementsByTagName('consignee'+idx)[0].childNodes[0].nodeValue);
					full_address.push(xmlDoc.getElementsByTagName('full_address'+idx)[0].childNodes[0].nodeValue);
					postal_code.push(xmlDoc.getElementsByTagName('postal_code'+idx)[0].childNodes[0].nodeValue);
					phone_number.push(xmlDoc.getElementsByTagName('phone_number'+idx)[0].childNodes[0].nodeValue);
					added_time.push(xmlDoc.getElementsByTagName('added_time'+idx)[0].childNodes[0].nodeValue);
				
				}
			
			
				document.getElementById(field).innerHTML = "";
				
				console.log("length purchases.js: " + numOfSearch);
				
				if (numOfSearch > 0) {
					
					AddPurchasesTable();
				
				}
			
			} else {
				
				alert("Invalid access");
				
				// clear the localstorage
				localStorage.clear();
				
				// redirect to login page
				window.location.href = "../index.jsp";
			
			}
			
		} else {

		}
	}
	
	/*
	xmlhttp.open("GET", "marketplace?service=purchases"+"username_active=" + username_active, false);
	xmlhttp.send();
	*/
	
	
	var url = "marketplace";
	xmlhttp.open("POST", url, true);

	// Send the proper header information along with the request
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

	
	var params = "service=purchases" + "&" + 
	"username_global=" + localStorage.getItem("username_global") + "&" +
	"access_token_global=" + localStorage.getItem("access_token_global") + "&" +
	"expiry_time_global=" + localStorage.getItem("expiry_time_global") + "&" +
	"is_expired_global=" + localStorage.getItem("is_expired_global");
	
	xmlhttp.send(params);
	
}

function SetUsernameActive(val) {
	username_active = val;
}
		