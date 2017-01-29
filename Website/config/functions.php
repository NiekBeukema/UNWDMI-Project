<?php

include_once 'db_conf.php';

//Some of the functions below are based on Peter Bradley's secure session code. TODO: REMOVE ECHO EN DEBUG

function sec_session_start() {
    //TODO Create error pages
    $session_name = 'sec_session_id';   // Set a custom session name
    /*Sets the session name.
     *This must come before session_set_cookie_params due to an undocumented bug/feature in PHP.
     */
    session_name($session_name);

    $secure = true;
    // This stops JavaScript being able to access the session id.
    $httponly = true;
    // Forces sessions to only use cookies.
    if (ini_set('session.use_only_cookies', 1) === FALSE) {
        header("Location: ../error.php?err=Could not initiate a safe session (ini_set)");
        exit();
    }
    // Gets current cookies params.
    $cookieParams = session_get_cookie_params();
    session_set_cookie_params($cookieParams["lifetime"],
        $cookieParams["path"],
        $cookieParams["domain"],
        $secure,
        $httponly);

    session_start();            // Start the PHP session
    session_regenerate_id(true);    // regenerated the session, delete the old one.
}

function login($username, $password, $pdo) {
    $query = " SELECT id, username, password FROM users 
            WHERE 
                username = :username 
        ";
    $query_params = array(
        ':username' => $_POST['username']
    );

    try
    {
        // Execute the query against the database
        $stmt = $pdo->prepare($query);
        $result = $stmt->execute($query_params);
    }
    catch(PDOException $ex)
    {
        // Note: On a production website, you should not output $ex->getMessage().
        // It may provide an attacker with helpful information about your code.
        die("Failed to run login query: " . $ex->getMessage());
    }
    $row = $stmt->fetch();


    if($row)
    {
            $user_id = $row['id'];
            $db_password = $row['password'];
            $username = $row['username'];
            // If the user exists we check if the account is locked
            // from too many login attempts

            if (checkbrute($user_id, $pdo) == true) {
                // Account is locked
                // Send an email to user saying their account is locked
                echo 'bruteforce check fout!';
                return false;
            } else {
                // Check if the password in the database matches
                // the password the user submitted. We are using
                // the password_verify function to avoid timing attacks.
                if (password_verify($password, $row['password'])) {
                    // Password is correct!
                    // Get the user-agent string of the user.
                    $user_browser = $_SERVER['HTTP_USER_AGENT'];
                    // XSS protection as we might print this value
                    $user_id = preg_replace("/[^0-9]+/", "", $user_id);
                    $_SESSION['user_id'] = $user_id;
                    // XSS protection as we might print this value
                    $username = preg_replace("/[^a-zA-Z0-9_\-]+/",
                        "",
                        $username);
                    $_SESSION['username'] = $username;
                    $_SESSION['login_string'] = hash('sha512',
                        $db_password . $user_browser);
                    // Login successful.
                    return true;
                } else {
                    // Password is not correct
                    // We record this attempt in the database TODO: Fix this
                    $now = time();
                    $pdo->execute("INSERT INTO login_attempts(user_id, time)
                                    VALUES ('$user_id', '$now')");
                    return false;
                    echo 'login verify error';
                }
            }
        } else {
            // No user exists.
            return false;

    }
}

function checkbrute($user_id, $pdo) {
    // Get timestamp of current time
    $now = time();

    // All login attempts are counted from the past 2 hours.
    $valid_attempts = $now - (2 * 60 * 60);

    $query_params = array(
        ':user_id' => $user_id
    );

    try
    {
        $stmt = $pdo->prepare("SELECT time 
                             FROM login_attempts 
                             WHERE user_id = :user_id 
                            AND time > '$valid_attempts'");
        $result = $stmt->execute($query_params);
    }
    catch(PDOException $ex)
    {
        // Note: On a production website, you should not output $ex->getMessage().
        // It may provide an attacker with helpful information about your code.
        die("Failed to run bruteforce query: " . $ex->getMessage());
    }

    $row = $stmt->fetch();

    if ($row) {
        // If there have been more than 5 failed logins
        if ($row->num_rows > 5) {
            return true;
        } else {
            return false;
        }
    }
}

function esc_url($url) {

    if ('' == $url) {
        return $url;
    }

    $url = preg_replace('|[^a-z0-9-~+_.?#=!&;,/:%@$\|*\'()\\x80-\\xff]|i', '', $url);

    $strip = array('%0d', '%0a', '%0D', '%0A');
    $url = (string) $url;

    $count = 1;
    while ($count) {
        $url = str_replace($strip, '', $url, $count);
    }

    $url = str_replace(';//', '://', $url);

    $url = htmlentities($url);

    $url = str_replace('&amp;', '&#038;', $url);
    $url = str_replace("'", '&#039;', $url);

    if ($url[0] !== '/') {
        // We're only interested in relative links from $_SERVER['PHP_SELF']
        return '';
    } else {
        return $url;
    }
}

function login_check($pdo) {
    //
    if (isset($_SESSION['user_id'],
        $_SESSION['username'],
        $_SESSION['login_string'])) {

        $user_id = $_SESSION['user_id'];
        $login_string = $_SESSION['login_string'];

        // Get the user-agent string of the user.
        $user_browser = $_SERVER['HTTP_USER_AGENT'];

        //Query
        $query_params = array(
            ':user_id' => $user_id
        );
        try
        {
            // Execute the query against the database
            $stmt = $db->prepare("SELECT password 
                                      FROM users 
                                      WHERE id = :user_id LIMIT 1");
            $result = $stmt->execute($query_params);
        }
        catch(PDOException $ex)
        {
            // Note: On a production website, you should not output $ex->getMessage().
            // It may provide an attacker with helpful information about your code.
            die("Failed to run check query: " . $ex->getMessage());
        }

        $row = $stmt->fetch();

        if ($row) {
                $login_check = hash('sha512', $row['password'] . $user_browser);

                if (hash_equals($login_check, $login_string) ){
                    // Logged In!!!!
                    return true;
                } else {
                    // Not logged in
                    return false;
                }
            } else {
                // Not logged in
                return false;
            }
        } else {
            // Not logged in
            return false;
        }
        //end

}