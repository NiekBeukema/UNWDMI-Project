<?php
/**
 * Created by PhpStorm.
 * User: David
 * Date: 27-1-2017
 * Time: 13:29
 */

$db = array(
    'host' => 'localhost',
    'dbname' => 'unwdmi',
    'username' => 'root',
    'password' => ''
);

try {
    $db = new PDO('mysql:host=' . $db['host'] . ';dbname=' . $db['dbname'], $db['username'], $db['password']);
    $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
}
catch(PDOException $e)
{
    $sMsg = "Fout Opgetreden, line:".$e->getLine()." - File:".$e->getFile()." - Error:".$e->getMessage();

    trigger_error($sMsg);
}