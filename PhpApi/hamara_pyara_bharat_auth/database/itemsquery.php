<?php 

//build query
$sql = "SELECT * FROM cities";

//Add province filter
if (isset($_GET['state']))
  $province = $_GET['state'];
else if (isset($_POST['state']))
  $province = $_POST['state'];

if (isset($province)) {
  $filter[] = " state = '$state'";
}
// end of cities filter

// create additional filters here 

if (!empty($filter)) {
  $sql .= ' WHERE ' . implode(' AND ', $filter);
}

//Determine sort order
if (isset($_GET['orderby']))
  $orderby = $_GET['orderby'];
else if (isset($_POST['orderby']))
  $orderby = $_POST['orderby'];

//default sort order to cityname column
if (isset($orderby)) {
  $sql .= " ORDER BY " . $orderby;
} else {
  $sql .= " ORDER BY cityname";
}

require_once('connection.php');
$rsCities = mysqli_query($connection, $sql) or 
  die(mysqli_error($connection));

?>
