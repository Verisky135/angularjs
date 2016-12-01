<?php 

	// GET the user ID (active)
	$user_active = $_GET["user_active"];
	
	// GET the username for template 'Hello, name!'
	require_once('../server/Dbconnect.php');	
	global $conn;
	
	$sql = "SELECT username FROM user WHERE id='$user_active'";
	$result = mysqli_query($conn, $sql);
	
	if ($result) {
	
		// stores the query's result into an array
		$row = mysqli_fetch_array($result, MYSQLI_ASSOC);
		$username = $row["username"];
		
	} else {
		echo "Error (hello_name_template.php): " . $sql . "<br>" . mysqli_error($conn);
	}
	
	// Close the connection
	mysqli_close($conn);
	

?>