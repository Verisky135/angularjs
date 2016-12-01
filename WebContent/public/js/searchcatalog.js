
/* SCRIPT for searching catalog */

//var userID_active;

var search_category = '';
var search_query = '';
var fetched_category;
var fetched_category_OBJ;
var banyakonline;
var yangonline;


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

function deFormatCurrency(currency) {
	// convert money format into normal string
	
	var deformatted = Number(currency.replace(/[^0-9]+/g,""));
	return deformatted;
}

function modifyLIKE(idx_table, idx_product) {
	var xmlhttp_like;
	
	console.log("modifyLike: " + idx_table + " " + idx_product);
	
	if (window.XMLHttpRequest) { 
		// for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp_like = new XMLHttpRequest();
	} else { 
		// for IE6, IE5
		xmlhttp_like = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xmlhttp_like.onreadystatechange = function() {
		if (xmlhttp_like.readyState != 4 && xmlhttp_like.status == 200) {
			
			document.getElementById("catalogProduct" + idx_table).rows[4].cells[1].innerHTML = "Loading...";
		
		} else if (xmlhttp_like.readyState == 4 && xmlhttp_like.status == 200) {
		
			
			var xmlDoc, x, y;
			var numOfSearch;
			var access_valid;
			
			/** response in XML format **/
			
			var parser = new DOMParser();
			xmlDoc = parser.parseFromString(xmlhttp_like.responseText, "text/xml"); 
				
			access_valid = xmlDoc.getElementsByTagName('access-validity')[0].childNodes[0].nodeValue;
			
		
			if (access_valid == 200) {
		
				
				document.getElementById("catalogProduct" + idx_table).rows[4].cells[1].innerHTML = xmlDoc.getElementsByTagName('currlikes')[0].childNodes[0].nodeValue + " likes";
				
				// change the LIKE writing and color
				if (is_liked[idx_table] == "no") {
					
					// if LIKE button has not been clicked yet
					is_liked[idx_table] = "yes";
					likes[idx_table]++;
					document.getElementById("catalogProduct" + idx_table).rows[6].cells[0].innerHTML = "<a onclick='modifyLIKE(" + idx_table + "," + idx_product + ")' style='text-decoration: none; color: inherit;'>LIKED</a>";
					document.getElementById("catalogProduct" + idx_table).rows[6].cells[0].style = "width: 0px; font-size: 19px; font-weight: bold; color: red; height: 60px; vertical-align: middle; padding-left: 50px";
				
				} else {
					
					// if LIKE button has already been clicked (LIKED)
					is_liked[idx_table] = "no";
					likes[idx_table]--;
					document.getElementById("catalogProduct" + idx_table).rows[6].cells[0].innerHTML = "<a onclick='modifyLIKE(" + idx_table + "," + idx_product + ")' style='text-decoration: none; color: inherit;'>LIKE</a>";
					document.getElementById("catalogProduct" + idx_table).rows[6].cells[0].style = "width: 0px; font-size: 19px; font-weight: bold; color: #4F83E9; height: 60px; vertical-align: middle; padding-left: 50px";
				}
		
				
			} else {
				
				alert("Invalid access");
				
				// clear the localstorage
				localStorage.clear();
				
				// redirect to login page
				window.location.href = "../index.jsp";
			
			}
		
			
			
		} else {
			
			// development only
			//document.getElementById("catalogProduct" + idx_table).rows[4].cells[1].innerHTML = "Error";
		
		}
	}
	
	//xmlhttp_like.open("GET", "marketplace?service=modify_like&user_active=" + localStorage.getItem("userid_global") + "&idx_prod=" + idx_product + "&curr_likes=" + likes[idx_table] + "&is_liked=" + is_liked[idx_table], false);
	//xmlhttp_like.open("GET", "marketplace?service=modify_like&user_active=" + localStorage.getItem("userid_global") + "&idx_prod=" + idx_product, false);
	
	var url = "marketplace";
	xmlhttp_like.open("POST", url, true);

	// Send the proper header information along with the request
	xmlhttp_like.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

	
	var params = "service=modify_like" + "&" + 
	"product_id=" + idx_product + "&" +
	"is_liked=" + is_liked[idx_table] + "&" +
	"userid_global=" + localStorage.getItem("userid_global") + "&" +
	"username_global=" + localStorage.getItem("username_global") + "&" +
	"access_token_global=" + localStorage.getItem("access_token_global") + "&" +
	"expiry_time_global=" + localStorage.getItem("expiry_time_global");
	
	//"is_expired_global=" + localStorage.getItem("is_expired_global");
	
	xmlhttp_like.send(params);

}


function ClearCatalogTable() {
	
	var tables = document.getElementsByTagName("TABLE");
	
	for (var i = tables.length - 1; i >= 0; i -= 1) {
		if (tables[i].className.indexOf("catalog_search_table") < 0) { 
			tables[i].parentNode.removeChild(tables[i]);
		}
	}
}

function AddCatalogTable() {
	
	var myTableDiv, table, tableBody, tr, td;
	var row;
	
	
	console.log("AddCatalogTable");
	
	
	myTableDiv = document.getElementById("catalog_products");
	
	for (var idx = 0; idx < fetched_category_num; idx++) {
		table = document.createElement("TABLE");
		table.border = 0;
		table.width = 835;
		table.id = "catalogProduct" + idx;
		table.style = "margin-bottom: 30px";
		//table.style = "table-layout: fixed; word-wrap: break-word; margin-bottom: 50px";
		
		myTableDiv.appendChild(table);
	
		// username
		row = document.getElementById("catalogProduct" + idx).insertRow(0);
		row.insertCell(0);
		
		//document.getElementById("catalogProduct" + idx).rows[0].cells[0].innerHTML = username[idx];
		
		
		console.log("Username penjual adalah : " + username[idx]);
		if(isonline(username[idx])){
			document.getElementById("catalogProduct" + idx).rows[0].cells[0].innerHTML = "online";
			console.log(username[idx] + "sedang online");
		}else{
			document.getElementById("catalogProduct" + idx).rows[0].cells[0].innerHTML = "offline";
			console.log(username[idx] + "sedang offline");
		}
		
		
		//document.getElementById("catalogProduct" + idx).rows[0].cells[0].innerHTML = "offline";
		row.insertCell(1);
		
		
		//document.getElementById("catalogProduct" + idx).rows[0].cells[0].innerHTML = "<a ng-click=" + "\"showChatBox('" + username[idx] + "')\" style='text-decoration: none; color: inherit;'>" + username[idx] + "</a>";
		document.getElementById("catalogProduct" + idx).rows[0].cells[1].innerHTML = "<a ng-click=\"vm.visible=true; vm.username='" + username[idx] + "'\" style='text-decoration: none; color: inherit;'>" + username[idx] + "</a>";
		document.getElementById("catalogProduct" + idx).rows[0].cells[1].colSpan = 3;
		document.getElementById("catalogProduct" + idx).rows[0].cells[1].style = "font-weight: bold";
		
		// added_time
		row = document.getElementById("catalogProduct" + idx).insertRow(1);
		row.insertCell(0);
		
		document.getElementById("catalogProduct" + idx).rows[1].cells[0].innerHTML = "added this on " + added_time[idx];
		document.getElementById("catalogProduct" + idx).rows[1].cells[0].colSpan = 4;
		
		// horizontal line 1
		row = document.getElementById("catalogProduct" + idx).insertRow(2);
		row.insertCell(0);
		
		document.getElementById("catalogProduct" + idx).rows[2].cells[0].innerHTML = "<hr align='left'; width='89%'>";
		document.getElementById("catalogProduct" + idx).rows[2].cells[0].colSpan = 4;
		
		// image
		row = document.getElementById("catalogProduct" + idx).insertRow(3);
		row.insertCell(0);
		
		document.getElementById("catalogProduct" + idx).rows[3].cells[0].innerHTML = "<img src='"+ "../images/" + photo[idx]+ "' width='120' height='120'>";
		document.getElementById("catalogProduct" + idx).rows[3].cells[0].rowSpan = 4;
		document.getElementById("catalogProduct" + idx).rows[3].cells[0].style = "width: 120px; height: 120px";
	
		// product_name
		row.insertCell(1);
		
		document.getElementById("catalogProduct" + idx).rows[3].cells[1].innerHTML = product_name[idx];
		document.getElementById("catalogProduct" + idx).rows[3].cells[1].colSpan = 3;
		document.getElementById("catalogProduct" + idx).rows[3].cells[1].style = "font-size: 18px; font-weight: bold";
		
		// price
		row = document.getElementById("catalogProduct" + idx).insertRow(4);
		row.insertCell(0);
		row.insertCell(1);
		
		document.getElementById("catalogProduct" + idx).rows[4].cells[0].innerHTML = "IDR " + price[idx];
		document.getElementById("catalogProduct" + idx).rows[4].cells[1].innerHTML = likes[idx] + " likes";
		document.getElementById("catalogProduct" + idx).rows[4].cells[1].colSpan = 2;
		document.getElementById("catalogProduct" + idx).rows[4].cells[0].style = "font-size: 19px";
		document.getElementById("catalogProduct" + idx).rows[4].cells[1].style = "width: 70px; vertical-align: bottom; padding-left: 50px";
		
		// description
		row = document.getElementById("catalogProduct" + idx).insertRow(5);
		row.insertCell(0);
		row.insertCell(1);
		
		document.getElementById("catalogProduct" + idx).rows[5].cells[0].innerHTML = description[idx];
		document.getElementById("catalogProduct" + idx).rows[5].cells[0].rowSpan = 2;
		document.getElementById("catalogProduct" + idx).rows[5].cells[0].style = "width: 390px; word-wrap: break-word; vertical-align: top";
		
		// purchases
		document.getElementById("catalogProduct" + idx).rows[5].cells[1].innerHTML = purchases[idx] + " purchases";
		document.getElementById("catalogProduct" + idx).rows[5].cells[1].colSpan = 2;
		document.getElementById("catalogProduct" + idx).rows[5].cells[1].style = "height: 15px; vertical-align: top; padding-left: 50px";
		
		// LIKE & BUY
		row = document.getElementById("catalogProduct" + idx).insertRow(6);
		row.insertCell(0);
		row.insertCell(1);
		
		// the original CODE (uncomment this)
		//document.getElementById("catalogProduct" + idx).rows[6].cells[0].innerHTML = "<a onclick='modifyLIKE(" + idx + "," + product_id[idx] + ")' style='text-decoration: none; color: inherit;'>LIKE</a>";
		//document.getElementById("catalogProduct" + idx).rows[6].cells[0].style = "width: 0px; font-size: 19px; font-weight: bold; color: #4F83E9; height: 60px; vertical-align: middle; padding-left: 50px";
		// end of the original CODE
		
		
		// development LIKE
		
		if (is_liked[idx] == "no") {
		
			document.getElementById("catalogProduct" + idx).rows[6].cells[0].innerHTML = "<a onclick='modifyLIKE(" + idx + "," + product_id[idx] + ")' style='text-decoration: none; color: inherit;'>LIKE</a>";
			document.getElementById("catalogProduct" + idx).rows[6].cells[0].style = "width: 0px; font-size: 19px; font-weight: bold; color: #4F83E9; height: 60px; vertical-align: middle; padding-left: 50px";
		
		} else {
		
			document.getElementById("catalogProduct" + idx).rows[6].cells[0].innerHTML = "<a onclick='modifyLIKE(" + idx + "," + product_id[idx] + ")' style='text-decoration: none; color: inherit;'>LIKED</a>";
			document.getElementById("catalogProduct" + idx).rows[6].cells[0].style = "width: 0px; font-size: 19px; font-weight: bold; color: red; height: 60px; vertical-align: middle; padding-left: 50px";
			
		}
		
		/*
		document.getElementById("catalogProduct" + idx).rows[6].cells[0].innerHTML = "<a onclick='modifyLIKE(" + idx + "," + product_id[idx] + ")' style='text-decoration: none; color: inherit;'>LIKE</a>";
		document.getElementById("catalogProduct" + idx).rows[6].cells[0].style = "width: 0px; font-size: 19px; font-weight: bold; color: red; height: 60px; vertical-align: middle; padding-left: 50px";
		*/
		
		
		// end of development LIKE
		
		
		
		//document.getElementById("catalogProduct" + idx).rows[6].cells[1].innerHTML = "<a onclick='ConfirmAJAX(" + product_id[idx] + ", " + username[idx] + ")" + "' style='text-decoration: none; color: inherit;'>BUY</a>";
		
		document.getElementById("catalogProduct" + idx).rows[6].cells[1].innerHTML = "<a onclick='ConfirmAJAX(" + product_id[idx] + ")" + "' style='text-decoration: none; color: inherit;'>BUY</a>";
		
		document.getElementById("catalogProduct" + idx).rows[6].cells[1].style = "font-size: 19px; font-weight: bold; color: green; vertical-align: middle; padding-left: 70px";
		
		// horizontal line 2
		row = document.getElementById("catalogProduct" + idx).insertRow(7);
		row.insertCell(0);
		
		document.getElementById("catalogProduct" + idx).rows[7].cells[0].innerHTML = "<hr align='left'; width='89%'>";
		document.getElementById("catalogProduct" + idx).rows[7].cells[0].colSpan = 4;
					
	}
	
}

function SearchAJAX(field, query) {
	
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
			
			/** response in XML format **/
			
			var parser = new DOMParser();
			xmlDoc = parser.parseFromString(xmlhttp.responseText, "text/xml"); 
				
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
				
				// declares the fetched products
				//x = xmlDoc.getElementsByTagName('product'+idx)[0];
				
				product_id.push(xmlDoc.getElementsByTagName('id'+idx)[0].childNodes[0].nodeValue);
				username.push(xmlDoc.getElementsByTagName('username'+idx)[0].childNodes[0].nodeValue);
				product_name.push(xmlDoc.getElementsByTagName('name'+idx)[0].childNodes[0].nodeValue);
				description.push(xmlDoc.getElementsByTagName('description'+idx)[0].childNodes[0].nodeValue);
				price.push(xmlDoc.getElementsByTagName('price'+idx)[0].childNodes[0].nodeValue);
				photo.push(xmlDoc.getElementsByTagName('photo'+idx)[0].childNodes[0].nodeValue);
				likes.push(xmlDoc.getElementsByTagName('likes'+idx)[0].childNodes[0].nodeValue);
				purchases.push(xmlDoc.getElementsByTagName('purchases'+idx)[0].childNodes[0].nodeValue);
				added_time.push(xmlDoc.getElementsByTagName('added_time'+idx)[0].childNodes[0].nodeValue);
				
				//is_liked.push("no");
				
				
				// development LIKE
				console.log("IS_LIKED " + idx + ": " + xmlDoc.getElementsByTagName('is_liked'+idx)[0].childNodes[0].nodeValue);
				
				if (xmlDoc.getElementsByTagName('is_liked'+idx)[0].childNodes[0].nodeValue == 0) {
					is_liked.push("no");
					
					console.log("push no");
					
				} else {
					is_liked.push("yes");
					
					console.log("push yes");
					
				}
				// end of development LIKE
				
				
			}
		
			document.getElementById(field).innerHTML = "";
			
			console.log("length: " + numOfSearch);
			
			if (numOfSearch > 0) {
				
				console.log("numOfSearch > 0");
				
				// clear the catalog table
				ClearCatalogTable();
			
				// create catalogs table dynamically	
				AddCatalogTable();
			
			} 
		
			
		} else {
			
			// error condition
			//document.getElementById(field).innerHTML = "ErrorS Occurred";
		
		}
	}
	
	
	xmlhttp.open("GET", "marketplace?service=catalog" + "&user_active=" + localStorage.getItem("userid_global") + "&query=" + query + "&cat=" + search_category, false);
	xmlhttp.send();

}


function isonline(namauser){

	console.log("Masuk ke fungsi isonline");
	console.log("Banyak online adalah " + banyakonline);
	var ada = false; 

	for (var idx = 0; idx < banyakonline; idx++) {
		console.log("yangonline["+idx+"] :" + yangonline[idx]);
		if (yangonline[idx]==namauser){
			ada=true;
			console.log(namauser + "ternyata online bro ");
		}
	}
	return ada;
}

function SearchOnline() {
	
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
			
			var xmlDoc, x, y;
			var numOfSearch;
			
			/** response in XML format **/
			
			var parser = new DOMParser();
			xmlDoc = parser.parseFromString(xmlhttp.responseText, "text/xml"); 
				
			numOfSearch = xmlDoc.getElementsByTagName('numOfSearch')[0].childNodes[0].nodeValue;
			banyakonline = numOfSearch;
			console.log("Num Of Searchnya adalah " + numOfSearch +"Banyakonline adalah " + banyakonline)
			
			// empty the arrays
			yangonline = [];
			
			
			// assign the fetched product information into variables
			for (var idx = 0; idx < numOfSearch; idx++) {
				
				// declares the fetched products
				//x = xmlDoc.getElementsByTagName('product'+idx)[0];
				
				yangonline.push(xmlDoc.getElementsByTagName('username'+idx)[0].childNodes[0].nodeValue);
				
			}	
			
		} else {
			
			// error condition
			//document.getElementById(field).innerHTML = "ErrorS Occurred";
		
		}
	}
	
	
	xmlhttp.open("GET", "marketplace?service=daftaronline" , false);
	xmlhttp.send();

}


function ConfirmAJAX(PROD_ID) {
	
	var xmlhttp;
	
	if (window.XMLHttpRequest) { 
		// for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else { 
		// for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	
	console.log("ConfirmAJAX");
	
	
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
			
				var product_data_id = PROD_ID;
				var product_data_name = xmlDoc.getElementsByTagName('name')[0].childNodes[0].nodeValue;
				var product_data_price = xmlDoc.getElementsByTagName('price')[0].childNodes[0].nodeValue;
				var product_data_photo = xmlDoc.getElementsByTagName('photo')[0].childNodes[0].nodeValue;
				
				
				var buying_username = xmlDoc.getElementsByTagName('username')[0].childNodes[0].nodeValue;
				var buying_fullname = xmlDoc.getElementsByTagName('fullname')[0].childNodes[0].nodeValue;
				var buying_fulladdress = xmlDoc.getElementsByTagName('fulladdress')[0].childNodes[0].nodeValue;
				var buying_postalcode = xmlDoc.getElementsByTagName('postalcode')[0].childNodes[0].nodeValue;
				var buying_phonenumber = xmlDoc.getElementsByTagName('phonenumber')[0].childNodes[0].nodeValue;
				
				//alert("AJAX OK");
				
				localStorage.setItem("product_data_id", product_data_id);
				localStorage.setItem("product_data_name", product_data_name);
				localStorage.setItem("product_data_price", product_data_price);
				localStorage.setItem("product_data_photo", product_data_photo);
				localStorage.setItem("buying_fullname", buying_fullname);
				localStorage.setItem("buying_fulladdress", buying_fulladdress);
				localStorage.setItem("buying_postalcode", buying_postalcode);
				localStorage.setItem("buying_phonenumber", buying_phonenumber);
				
				//localStorage.setItem("boughtfrom_username", BOUGHTFROM_USERNAME);
				localStorage.setItem("product_data_quantity", 1);
				
				
				// redirect to the confirm_purchase page
				/*
				window.location.href = "confirm_purchase.jsp?name=" + product_data_name +
										"&price=" + product_data_price +
										"&photo=" + product_data_photo;
				*/
				
				window.location.href = "confirm_purchase.jsp";
				
			} else {
				
				// redirect to login page
				alert("Invalid access");
				window.location.href = "../index.jsp";
				
			}
			
		}
	}
	
	var url = "marketplace";
	xmlhttp.open("POST", url, true);

	// Send the proper header information along with the request
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

	
	var params = "service=fetch_confirm_purchase" + "&" + 
	"product_id=" + PROD_ID + "&" +
	"username_global=" + localStorage.getItem("username_global") + "&" +
	"access_token_global=" + localStorage.getItem("access_token_global") + "&" +
	"expiry_time_global=" + localStorage.getItem("expiry_time_global") + "&" +
	"is_expired_global=" + localStorage.getItem("is_expired_global");
	
	xmlhttp.send(params);

}



/*
function SetUserIDActive(val) {
	userID_active = val;
}
*/	

function SetSearchCategory(category) {
	if (category == "product") {
		// product
		search_category = "p";
	} else if (category == "store") {
		// store
		search_category = "s";
	} else if (category == "ret_all") {
		// ret_all
		search_category = "ret_all";
	} else {
		search_category = "";
	}
}

/*
function SearchCatalog(query) {
	
	if (search_category == "") {
	
		alert("Please choose one category");
	
	} else {
		
		search_query = query;
		
		if (query != '') {
			
			var timer;
			
			clearTimeout(timer);
			timer = setTimeout(function() {
									SearchAJAX("catalog_products", query)
								}, 0);
		} else {
			
			document.getElementById("catalog_products").innerHTML = '';
			
		}
	}
	
}
*/

function SearchCatalogFromGO() {
	if (search_category == "") {
		alert("Please choose one category");
	} else {
		
		search_query = document.getElementById("catalog_search_text").value;
		
		if (search_query == "") {
			document.getElementById("catalog_products").innerHTML = '';
		} else {
		
			console.log("QUERY TEXT: " + search_query);
			SearchAJAX("catalog_products", search_query)
			
		}
	}
}

/*
	NB:

	fetched_category = xmlhttp.responseText;
	fetched_category_OBJ = JSON.parse(fetched_category);
	
	alert(JSON.stringify(fetched_category_OBJ[0]));		
*/
