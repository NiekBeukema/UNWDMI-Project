<?php
/*
 * Je zou bijna denken dat de example outdated is :(
 * DEZE IS ALLEEN VOOR ADMIN - EditUser.
 * FIXME ALL SQL INJECTIONS
 * TODO allow this file to accept other oceania entries.
 */
require_once 'db_connect.php';

$requestData= $_REQUEST;

$columns = array(
// column index  => database column name
    0 => 'username',
    1 => 'is_admin'
);

$query_params = array();

$query = " SELECT id, username, is_admin FROM users";

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
    $query = "SELECT id, username, is_admin
    FROM users WHERE username LIKE '".$requestData['search']['value']."%'
    OR password LIKE '".$requestData['search']['value']."%'";

    try {
        $stmt = $pdo->prepare($query);
        $result = $stmt->execute($query_params);
    } catch (PDOException $ex) {
        die();
        header("Location: ../error.php?err=Could not connect to database (conn_DT_getSearched)");
    }
} else {
    $query = " SELECT id, username, is_admin FROM users ORDER BY " . $columns[$requestData['order'][0]['column']] . "   " . $requestData['order'][0]['dir'] . "   LIMIT " . $requestData['start'] . " ," . $requestData['length'] . "  ";

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
    $nestedData[] = $row["username"];
    $nestedData[] = $row["is_admin"];
    $nestedData[] = '<a>Edit</a>';

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