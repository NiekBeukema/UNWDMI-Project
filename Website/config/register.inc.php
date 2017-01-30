<?php
include_once 'db_connect.php';
include_once 'db_conf.php';
require_once 'lib/random.php';
$error = 0;
$success = 0;
function generate_pass(
    $length,
    $keyspace = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&**()'
) {
    $str = '';
    $max = mb_strlen($keyspace, '8bit') - 1;
    if ($max < 1) {
        throw new Exception('$keyspace must be at least two characters long');
    }
    for ($i = 0; $i < $length; ++$i) {
        $str .= $keyspace[random_int(0, $max)];
    }
    return $str;
}

if (isset($_POST['createUser'], $_POST['username'] )) {
    // Sanitize and validate the data passed in
    $username = filter_input(INPUT_POST, 'username', FILTER_SANITIZE_STRING);
    $password = generate_pass(10);

    if(isset($_POST['is_admin'])) {
        $is_admin = 1;
    } else {
        $is_admin = 0;
    }
    // Username validity and password validity have been checked client side.

    $query = "SELECT 1 FROM users WHERE username = :username";
    $query_params = array(
        ':username' => $username
    );

    try
    {
        $stmt = $pdo->prepare($query);
        $result = $stmt->execute($query_params);
    }
    catch(PDOException $ex)
    {
        $error = 1;
        die();
        header("Location: ../error.php?err=Could not connect to database (conn_check_alreadyReg)");
    }

    $row = $stmt->fetch();

    // Check existing
    if ($row) {
        // A user with this username  already exists
        $error = 1;
        die();
        header("Location: ../error.php?err=Username already exists");
    }



    if (empty($error_msg)) {

        // Create hashed password using the password_hash function.
        $passwordns = $password;
        $password = password_hash($password, PASSWORD_BCRYPT);
        $expired = 1;

        $query = "
            INSERT INTO users (
                username,
                password,
                is_admin,
                expired
            ) VALUES (
                :username,
                :password,
                :is_admin,
                :expired
            )
        ";

        $query_params = array(
            ':username' => $username,
            ':password' => $password,
            ':is_admin' => $is_admin,
            ':expired' => $expired
        );

        $success = 3;
        try
        {
            // Execute the query to create the user
            $stmt = $pdo->prepare($query);
            $result = $stmt->execute($query_params);
        }
        catch(PDOException $ex)
        {
            $success = 2;
            die();
        }
    }
}


if (isset($_POST['changepassword'], $_POST['newpassword'], $_POST['username'])) {
    // Sanitize and validate the data passed in
    $username = filter_input(INPUT_POST, 'username', FILTER_SANITIZE_STRING);
    $password = filter_input(INPUT_POST, 'newpassword', FILTER_SANITIZE_STRING);


    $query = "SELECT 1 FROM users WHERE username = :username";
    $query_params = array(
        ':username' => $username
    );

    try
    {
        $stmt = $pdo->prepare($query);
        $result = $stmt->execute($query_params);
    }
    catch(PDOException $ex)
    {
        $error = 1;
        die();
        header("Location: ../error.php?err=Could not connect to database (conn_check_alreadyReg)");
    }

    $row = $stmt->fetch();

    // Check existing
    if (!$row) {
        // A user with this username  already exists
        $error = 1;
        die();
        header("Location: ../error.php?err=Username already exists");
    }



    if (empty($error_msg)) {

        // Create hashed password using the password_hash function.
        $password = password_hash($password, PASSWORD_BCRYPT);
        $expired = 0;

        $query = "
            UPDATE USERS
            SET
              password = :password,
              expired = :expired
            WHERE
              username = :username
        ";

        $query_params = array(
            ':username' => $username,
            ':password' => $password,
            ':expired' => $expired
        );

        $success = 3;
        try
        {
            // Execute the query to create the user
            $stmt = $pdo->prepare($query);
            $result = $stmt->execute($query_params);
        }
        catch(PDOException $ex)
        {
            $success = 2;
            die();
        }
    }
}
?>