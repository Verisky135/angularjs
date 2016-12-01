<?php 

	// GET the user ID (active)
	$user_active = $_GET["user_active"];
	
	// GET full user info
	require_once('../server/Dbconnect.php');	
	global $conn;
	
	$sql = "SELECT username, full_name, full_address, postal_code, phone_number FROM user WHERE id='$user_active'";
	$result = mysqli_query($conn, $sql);
	
	if ($result) {
	
		// stores the query's result into an array
		$row = mysqli_fetch_array($result, MYSQLI_ASSOC);
		$username = $row["username"];
		$full_name = $row["full_name"];
		$full_address = $row["full_address"];
		$postal_code = $row["postal_code"];
		$phone_number = $row["phone_number"];
		
	} else {
		echo "Error (user_info_template.php): " . $sql . "<br>" . mysqli_error($conn);
	}
	
	// Close the connection
	//mysqli_close($conn);
	

?>