<?php
/**
 * This file retrieves data for the graph on the oceania page
 */
require_once 'db_connect.php';

    //Try to retrieve data.
    $query = "SELECT id, date, cloudcoverage FROM oceania where date between date_sub(now(),INTERVAL 1 DAY) and now()";
    $query_params = array();
    try {
        // Execute the query against the database
        $stmt = $pdo->prepare($query);
        $result = $stmt->execute($query_params);
    } catch (PDOException $ex) {
        // To debug: . $ex->getMessage().
        die();
        header("Location: ../error.php?err=Could not connect to database (conn_chrt_get)");
    }
    //Fetch all the data
    $row = $stmt->fetch();

    //Calculate averages
    $dataset = array();
    $allshizzle = 0;
    $counter = 0;
    while ($row = $stmt->fetch()) {
        $allshizzle += $row['cloudcoverage'];
        $counter++;

    }
    $now = getdate();
    $dataset[] = array($now['year'] . "-" . $now['mon'] . "-" . $now['mday'] . " " . $now['hours'] . ":" . $now["minutes"] . ":" . $now["seconds"], ($allshizzle / $counter));
    echo json_encode($dataset);  // Encode JSON
