
/* SCRIPT for searching catalog */

var userID_active;
var username_active;

var fetched_category;
var fetched_category_OBJ;

// fetched catalog product information
var fetched_category_num;
var product_id;
var username;
var product_name;
var description;
var price;
var photo;
var likes;
var purchases;
var added_time;
var is_liked;


function AddYourProductTable() {
	
	var myTableDiv, table, tableBody, tr, td;
	var row;
	
	myTableDiv = document.getElementById("your_products");
	
	
	for (var idx = 0; idx < fetched_category_num; idx++) {
		table = document.createElement("TABLE");
		table.border = 0;
		table.width = 835;
		table.id = "tabyourproduct" + idx;
		table.style = "margin-bottom: 30px";
		//table.style = "table-layout: fixed; word-wrap: break-word; margin-bottom: 50px";
		
		myTableDiv.appendChild(table);
	
		// added_time
		row = document.getElementById("tabyourproduct" + idx).insertRow(0);
		row.insertCell(0);
		
		document.getElementById("tabyourproduct" + idx).rows[0].cells[0].innerHTML = added_time[idx];
		document.getElementById("tabyourproduct" + idx).rows[0].cells[0].colSpan = 4;
		document.getElementById("tabyourproduct" + idx).rows[0].cells[0].style = "font-size: 17px";
		
		// horizontal line 1
		row = document.getElementById("tabyourproduct" + idx).insertRow(1);
		row.insertCell(0);
		
		document.getElementById("tabyourproduct" + idx).rows[1].cells[0].innerHTML = "<hr align='left'; width='89%'>";
		document.getElementById("tabyourproduct" + idx).rows[1].cells[0].colSpan = 4;
		
		// image
		row = document.getElementById("tabyourproduct" + idx).insertRow(2);
		row.insertCell(0);
		
		document.getElementById("tabyourproduct" + idx).rows[2].cells[0].innerHTML = "<img src='"+ "../images/" + photo[idx]+ "' width='120' height='120'>";
		document.getElementById("tabyourproduct" + idx).rows[2].cells[0].rowSpan = 4;
		document.getElementById("tabyourproduct" + idx).rows[2].cells[0].style = "width: 120px; height: 120px";
	
		// product_name
		row.insertCell(1);
		
		document.getElementById("tabyourproduct" + idx).rows[2].cells[1].innerHTML = product_name[idx];
		document.getElementById("tabyourproduct" + idx).rows[2].cells[1].colSpan = 3;
		document.getElementById("tabyourproduct" + idx).rows[2].cells[1].style = "font-size: 22px; font-weight: bold";
		
	
		// price
		row = document.getElementById("tabyourproduct" + idx).insertRow(3);
		row.insertCell(0);
		row.insertCell(1);
		
		document.getElementById("tabyourproduct" + idx).rows[3].cells[0].innerHTML = "IDR " + price[idx];
		document.getElementById("tabyourproduct" + idx).rows[3].cells[1].innerHTML = likes[idx] + " likes";
		document.getElementById("tabyourproduct" + idx).rows[3].cells[1].colSpan = 2;
		document.getElementById("tabyourproduct" + idx).rows[3].cells[0].style = "font-size: 19px";
		document.getElementById("tabyourproduct" + idx).rows[3].cells[1].style = "width: 70px; vertical-align: bottom; padding-left: 50px";
		
		// description
		row = document.getElementById("tabyourproduct" + idx).insertRow(4);
		row.insertCell(0);
		row.insertCell(1);
		
		document.getElementById("tabyourproduct" + idx).rows[4].cells[0].innerHTML = description[idx];
		document.getElementById("tabyourproduct" + idx).rows[4].cells[0].rowSpan = 2;
		document.getElementById("tabyourproduct" + idx).rows[4].cells[0].style = "width: 390px; word-wrap: break-word; vertical-align: top";
		
		// purchases
		document.getElementById("tabyourproduct" + idx).rows[4].cells[1].innerHTML = purchases[idx] + " purchases";
		document.getElementById("tabyourproduct" + idx).rows[4].cells[1].colSpan = 2;
		document.getElementById("tabyourproduct" + idx).rows[4].cells[1].style = "height: 15px; vertical-align: top; padding-left: 50px";
		
		// LIKE & BUY
		row = document.getElementById("tabyourproduct" + idx).insertRow(5);
		row.insertCell(0);
		row.insertCell(1);
		
		
		/*
		document.getElementById("tabyourproduct" + idx).rows[5].cells[0].innerHTML = 
		"<form action='../views/edit_product.jsp' method='post' name='edit_form' id='edit_form'>" +
		"<input type='hidden' id='product_ID' value='" + product_id[idx] + "' name='product_ID' />" +
		"<input type='submit' style='text-decoration: none; color: inherit; font-size: 17px; font-weight: bold; color: #FEBB00; vertical-align: middle; text-align: center; border: none; background: none; padding-left: 45px' value ='EDIT'></form>";
		*/
		
		document.getElementById("tabyourproduct" + idx).rows[5].cells[0].innerHTML = "<a onclick='editproduct(" + product_id[idx] + ")'>EDIT</a>";
		document.getElementById("tabyourproduct" + idx).rows[5].cells[0].style = "text-decoration: none; color: inherit; font-size: 17px; font-weight: bold; color: #FEBB00; vertical-align: middle; text-align: center; border: none; background: none; padding-left: 45px";
			
			
		document.getElementById("tabyourproduct" + idx).rows[5].cells[1].innerHTML = "<a onclick='konfirmasi(" + product_id[idx] + ")'>DELETE</a>";
		document.getElementById("tabyourproduct" + idx).rows[5].cells[1].style = "font-size: 19px; font-weight: bold; color: #980000; height: 60px; vertical-align: middle; padding-right: 55px";
		
		// horizontal line 2
		row = document.getElementById("tabyourproduct" + idx).insertRow(6);
		row.insertCell(0);
		
		document.getElementById("tabyourproduct" + idx).rows[6].cells[0].innerHTML = "<hr align='left'; width='89%'>";
		document.getElementById("tabyourproduct" + idx).rows[6].cells[0].colSpan = 4;
					
	}
	
}

function SearchAJAX(field) {
	
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
				product_id = [];
				username = [];
				product_name = [];
				description = [];
				price = [];
				photo = [];
				likes = [];
				purchases = [];
				added_time = [];
				is_liked = [];
				
				// assign the fetched product information into variables
				for (var idx = 0; idx < numOfSearch; idx++) {
					
					product_id.push(xmlDoc.getElementsByTagName('id'+idx)[0].childNodes[0].nodeValue);
					username.push(xmlDoc.getElementsByTagName('username'+idx)[0].childNodes[0].nodeValue);
					product_name.push(xmlDoc.getElementsByTagName('name'+idx)[0].childNodes[0].nodeValue);
					description.push(xmlDoc.getElementsByTagName('description'+idx)[0].childNodes[0].nodeValue);
					price.push(xmlDoc.getElementsByTagName('price'+idx)[0].childNodes[0].nodeValue);
					photo.push(xmlDoc.getElementsByTagName('photo'+idx)[0].childNodes[0].nodeValue);
					likes.push(xmlDoc.getElementsByTagName('likes'+idx)[0].childNodes[0].nodeValue);
					purchases.push(xmlDoc.getElementsByTagName('purchases'+idx)[0].childNodes[0].nodeValue);
					added_time.push(xmlDoc.getElementsByTagName('added_time'+idx)[0].childNodes[0].nodeValue);
					
				}
			
				document.getElementById(field).innerHTML = "";
				
				console.log("length yourproduct.js: " + numOfSearch);
				
				if (numOfSearch > 0) {
				
					// create catalogs table dynamically	
					AddYourProductTable();
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
			// document.getElementById(field).innerHTML = "ErrorS Occurred";
		
		}
	}
	
	//xmlhttp.open("GET", "../server/Your_product.php?username_active=" + username_active + "&prod_id=" + id, false);
	/*
	xmlhttp.open("GET", "marketplace?service=yourproduct"+"&username_active=" + username_active, false);
	xmlhttp.send();
	*/
	
	var url = "marketplace";
	xmlhttp.open("POST", url, true);

	// Send the proper header information along with the request
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

	
	var params = "service=yourproduct" + "&" + 
	"username_global=" + localStorage.getItem("username_global") + "&" +
	"access_token_global=" + localStorage.getItem("access_token_global") + "&" +
	"expiry_time_global=" + localStorage.getItem("expiry_time_global") + "&" +
	"is_expired_global=" + localStorage.getItem("is_expired_global");
	
	xmlhttp.send(params);
	
}


function SetUserIDActive(val) {
	userID_active = val;
}


function SetUsernameActive(val) {
	username_active = val;
}


function editproduct(p_id) {
	
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
			
			var xmlDoc, x, y;
			var numOfSearch;
			var access_valid;
			
			/** response in XML format **/
			
			var parser = new DOMParser();
			xmlDoc = parser.parseFromString(xmlhttp.responseText, "text/xml"); 
				
			access_valid = xmlDoc.getElementsByTagName('access-validity')[0].childNodes[0].nodeValue;
			
		
			if (access_valid == 200) {
			
				var product_edit_id = xmlDoc.getElementsByTagName('id')[0].childNodes[0].nodeValue;
				var product_edit_name = xmlDoc.getElementsByTagName('name')[0].childNodes[0].nodeValue;
				var product_edit_desc = xmlDoc.getElementsByTagName('description')[0].childNodes[0].nodeValue;
				var product_edit_price = xmlDoc.getElementsByTagName('price')[0].childNodes[0].nodeValue;
				var product_edit_photo = xmlDoc.getElementsByTagName('photo')[0].childNodes[0].nodeValue;
				
				localStorage.setItem("product_edit_id", product_edit_id);
				localStorage.setItem("product_edit_name", product_edit_name);
				localStorage.setItem("product_edit_desc", product_edit_desc);
				localStorage.setItem("product_edit_price", product_edit_price);
				localStorage.setItem("product_edit_photo", product_edit_photo);
				
				window.location.href = "edit_product.jsp";
				
			} else {
				
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

	
	var params = "service=yourproduct_edit" + "&" +
	"product_id=" + p_id + "&" +
	"username_global=" + localStorage.getItem("username_global") + "&" +
	"access_token_global=" + localStorage.getItem("access_token_global") + "&" +
	"expiry_time_global=" + localStorage.getItem("expiry_time_global") + "&" +
	"is_expired_global=" + localStorage.getItem("is_expired_global");
	
	xmlhttp.send(params);

}



function konfirmasi(p_id) {
	var a = confirm("Apakah anda yakin ingin menghapus produk ini?");
	
	if (a == true){
	
		//alert("Barang anda telah dihapus ");
	
		
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
					alert("Barang Anda berhasil dihapus");
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

		
		var params = "service=yourproduct_delete" + "&" +
		"product_id=" + p_id + "&" +
		"username_global=" + localStorage.getItem("username_global") + "&" +
		"access_token_global=" + localStorage.getItem("access_token_global") + "&" +
		"expiry_time_global=" + localStorage.getItem("expiry_time_global") + "&" +
		"is_expired_global=" + localStorage.getItem("is_expired_global");
		
		xmlhttp.send(params);
		
	}
}
