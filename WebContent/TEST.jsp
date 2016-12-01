<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Tes dulu gan!</title>
	
	<!--
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    -->

    <script src="public/js/jquery-1.11.1.min.js"></script>    	
</head>
<body>

	<h1 id="testid">TEST WebService</h1>
	<h2>Berhasil jika muncul 2 kata yang diinput</h2>
	<p>
		<h2 id="judul">Layanan yang tersedia:</h2>
		<p>
		<h2>SEARCH CATALOG</h2>
		<form id="formCatalog" method="post">
		
			Query: <input type="text" name="searchCat_query" />
			Category: <input type="text" name="searchCat_category" />
			<!--
			<input type="hidden" name="typeofservice" value="catalog" />
			-->
			<input type="submit" value="SEARCH" />
			
		</form>
		</p>
		
		<div id="fetched_query"></div>
		<div id="fetched_category"></div>
		
	</p>

	<!-- Javascript -->
	<script>		  
		$(document).ready(function() {
			
			var frm = $('#formCatalog');
		    frm.submit(function (ev) {
		        $.ajax({
		        	cache: false,
		            type: 'POST',
		            url: 'requestWS?service=catalog',
		            data: frm.serialize(),
		            success: function (response) {

		            	var query = $(response).find("query").text();
		            	var category = $(response).find("category").text();
		            	
		            	$('#fetched_query').html(query);
		            	$('#fetched_category').html(category);
		            	
		            },
		            error: function (jqXHR, exception) {      	 	
		            	var msg = '';
		                if (jqXHR.status === 0) {
		                    msg = 'Not connect.\n Verify Network.';
		                } else if (jqXHR.status == 404) {
		                    msg = 'Requested page not found. [404]';
		                } else if (jqXHR.status == 500) {
		                    msg = 'Internal Server Error [500].';
		                } else if (exception === 'parsererror') {
		                    msg = 'Requested JSON parse failed.';
		                } else if (exception === 'timeout') {
		                    msg = 'Time out error.';
		                } else if (exception === 'abort') {
		                    msg = 'Ajax request aborted.';
		                } else {
		                    msg = 'Uncaught Error.\n' + jqXHR.responseText;
		                }
		                $('#judul').html(msg);
			        },
		        });
	
		        ev.preventDefault();
		    });

		});
	</script>
	
</body>
</html>