<?php
/**
 * This file retrieves the historical data for the graph on the oceania page
 */
require_once 'db_connect.php';

$query = "SELECT id, date, cloudcoverage FROM oceaniaaverage where date between date_sub(now(),INTERVAL 1 DAY) and now()";
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
while ($row = $stmt->fetch()) {
    $dataset[] = array($row['date'], $row['cloudcoverage']);
}

echo json_encode($dataset);  // ENCODE JSON