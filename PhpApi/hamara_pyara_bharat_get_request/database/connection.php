<?php

$hostname = "127.0.0.1";
$database = "hamara_pyara_bharat";
$username = "root";
$password = "root";

$connection = new mysqli(
	$hostname, 
	$username, 
	$password,
	$database);
	
if ($connection->connect_errno) {
    echo "Failed to connect to MySQL: (" . $connection->connect_errno . ") " 
    	. $connection->connect_error;
	die();
}

mysqli_set_charset($connection, 'utf8');

?>
