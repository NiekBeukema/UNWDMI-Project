<?php
include_once 'config/db_connect.php';
include_once 'config/functions.php';

sec_session_start(); // Initiate safe session

if (isset($_POST['username'], $_POST['password'])) {
    $username = $_POST['username'];
    $password = $_POST['password']; // The hashed password.

    if (login($username, $password, $pdo) == true) {
        // Login success TODO: REDIRECT
        echo 'Login Valid';
    } else {
        // Login failed
        echo 'DEBUG: Login failed';
    }
} else {
    // The correct POST variables were not sent to this page.
    echo 'Invalid Request???';
}