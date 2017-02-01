<?php
include_once 'config/functions.php';
sec_session_start();

// Remove all session variables.
$_SESSION = array();

// Get session params
$params = session_get_cookie_params();

// Exterminate cookie
setcookie(session_name(),
    '', time() - 42000,
    $params["path"],
    $params["domain"],
    $params["secure"],
    $params["httponly"]);

// Destroy session and redirect
session_destroy();
header('Location: ../login.php');