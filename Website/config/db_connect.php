<?php
/**
 * DB Connection file
 */
include_once 'db_conf.php';
$options = array(PDO::MYSQL_ATTR_INIT_COMMAND => 'SET NAMES utf8');

try {
    $pdo = new PDO('mysql:host=' . HOST . ';dbname=' . DATABASE, USER, PASSWORD);
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
}
catch(PDOException $e)
{
    $sMsg = "Something went wrong:, line:".$e->getLine()." - File:".$e->getFile()." - Error:".$e->getMessage();

    trigger_error($sMsg);
}