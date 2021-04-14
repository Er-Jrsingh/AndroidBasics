<?php

require_once('../database/itemsquery.php');

//Build array of data cities
$arRows = array();
while ($row = mysqli_fetch_assoc($rsCities)) {
  array_push($arRows, $row);
}

// build root XML element
$itemsElement = new SimpleXMLElement('<cities></cities>');

// loop data and build data structure
for ($i = 0; $i < count($arRows); $i++) {
  $itemElement = $itemsElement->addChild('city');
  $itemRow = $arRows[$i];
  
  $itemElement->addChild('id', $itemRow['id']);
  $itemElement->addChild('cityname', $itemRow['cityname']);
  $itemElement->addChild('rank', $itemRow['rank']);
  $itemElement->addChild('population', $itemRow['population']);
  $itemElement->addChild('state', $itemRow['state']);
  $itemElement->addChild('description', $itemRow['description']);
  $itemElement->addChild('image', $itemRow['image']);
}

mysqli_free_result($rsCities); 

//format for pretty printing
$dom = new DOMDocument('1.0', 'UTF-8');
$dom->preserveWhiteSpace = false;
$dom->formatOutput = true;
$dom->loadXML($itemsElement->asXML());
 
//Send to browser
header('Content-type: text/xml');
echo $dom->saveXML();

?>
