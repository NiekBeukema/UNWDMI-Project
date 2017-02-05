<?php
/*
 * Je zou bijna denken dat de example outdated is :(
 * DEZE IS ALLEEN VOOR OCEANIA - CLOUDCOVERAGE.
 * FIXME ALL SQL INJECTIONS
 * TODO allow this file to accept other oceania entries.
 */
require_once 'db_connect.php';

$requestData= $_REQUEST;

$columns = array(
// column index  => database column name
    0 => 'id',
    1 => 'date',
    2=> 'cloudcoverage'
);

$query_params = array();

$query = " SELECT id, date, cloudcoverage FROM oceania";

$totalData = 0;

try
{
    $stmt = $pdo->prepare($query);
    $result = $stmt->execute($query_params);
    $totalData = count($stmt->fetchAll());
}
catch(PDOException $ex)
{
    die();
    header("Location: ../error.php?err=Could not connect to database (conn_DT_getAll)");
}
$totalFiltered = $totalData;
$row = $stmt->fetch();

if(!empty($requestData['search']['value']) ){
    $query = "SELECT id, date, cloudcoverage
    FROM oceania WHERE id LIKE '".$requestData['search']['value']."%'
    OR date LIKE '".$requestData['search']['value']."%'
    OR cloudcoverage LIKE '".$requestData['search']['value']."%'";

    try {
        $stmt = $pdo->prepare($query);
        $result = $stmt->execute($query_params);
    } catch (PDOException $ex) {
        die();
        header("Location: ../error.php?err=Could not connect to database (conn_DT_getSearched)");
    }
} else {
    // load all results if filter is set to: ALL (-1)
    if($requestData['length'] != -1)
        $query = " SELECT id, date, cloudcoverage FROM oceania ORDER BY " . $columns[$requestData['order'][0]['column']] . "   " . $requestData['order'][0]['dir'] . "   LIMIT " . $requestData['start'] . " ," . $requestData['length'] . "  ";
    else
        $query = " SELECT id, date, cloudcoverage FROM oceania ORDER BY " . $columns[$requestData['order'][0]['column']] . "   " . $requestData['order'][0]['dir'];

    $query_params = array();

    try {
        $stmt = $pdo->prepare($query);
        $result = $stmt->execute($query_params);
    } catch (PDOException $ex) {
        die();
        header("Location: ../error.php?err=Could not connect to database (conn_DT_sendAll)");
    }
}
$data = array();
while($row = $stmt->fetch()){
    $nestedData=array();
    $nestedData[] = $row["id"];
    $nestedData[] = $row["date"];
    $nestedData[] = $row["cloudcoverage"];

    $data[] = $nestedData;
}

$json_data = array(
    "draw"            => intval( $requestData['draw'] ),  //Requests clientside
    "recordsTotal"    => intval( $totalData ),  // Total amount of data
    "recordsFiltered" => intval( $totalFiltered ), // Total amount of data after searching, if user didn't search then $filtered = $data
    "data"            => $data   // Total data array
);
echo json_encode($json_data);  // UP UP AND AWAY
?>