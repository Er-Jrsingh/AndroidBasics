<?php

require_once('../database/itemsquery.php');

//Build array of data items
$arCities = array();
while ($row_rsItems = mysqli_fetch_assoc($rsCities)) {
  array_push($arCities, $row_rsItems);
}

//Serialize and deliver as JSON
header('Content-type: application/json');
echo json_encode($arCities);

?>
