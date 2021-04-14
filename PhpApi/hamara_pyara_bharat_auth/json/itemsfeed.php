<?php
if( ( isset($_SERVER['PHP_AUTH_USER'] ) && ( $_SERVER['PHP_AUTH_USER'] == "root" ) ) AND
      ( isset($_SERVER['PHP_AUTH_PW'] ) && ( $_SERVER['PHP_AUTH_PW'] == "root" )) )
    {
        require_once('../database/itemsquery.php');

        //Build array of data items
        $arCities = array();
        while ($row_rsItems = mysqli_fetch_assoc($rsCities)) {
        array_push($arCities, $row_rsItems);
        }

        //Serialize and deliver as JSON
        header('Content-type: application/json');
        echo json_encode($arCities);
    }
    else
    {
        //Send headers to cause a browser to request
        //username and password from user
        header("WWW-Authenticate: " .
            "Basic realm=\"Protected Area\"");
        header("HTTP/1.0 401 Unauthorized");

        // Show failure text, which browsers usually
        // show only after several failed attempts
        print("This page is protected by HTTP " .
            "Authentication.<br>\nUse <b>root</b> " .
            "for the username, and <b>root</b> " .
            "for the password.<br>\n");
        exit();
    }
?>
