<?php
/**
 * Created by PhpStorm.
 * User: niek
 * Date: 31-1-2017
 * Time: 21:29
 */
require_once 'db_connect.php';

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
    $row = $stmt->fetch();

    $dataset = array();
    $allshizzle = 0;
    $counter = 0;
    while ($row = $stmt->fetch()) {
        $allshizzle += $row['cloudcoverage'];
        $counter++;

    }
    $now = getdate();
    $dataset[] = array($now['year'] . "-" . $now['mon'] . "-" . $now['mday'] . " " . $now['hours'] . ":" . $now["minutes"] . ":" . $now["seconds"], ($allshizzle / $counter));
    echo json_encode($dataset);  // UP UP AND AWAY
