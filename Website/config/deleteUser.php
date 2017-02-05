<?php

    if(isset($_POST['removeSelected'])) {

        try {
            $user_id = $_POST['removeSelected'];
            $sql = "DELETE FROM users WHERE id = :user_id";
            $deleteUser = $pdo->prepare($sql);
            $deleteUser->bindParam('user_id', $user_id);
            $deleteUser->execute();
        } catch(PDOException $ex) {
            die();
            header("Location: ../error.php?err=Could not remove user - Try again later");
        }
    }

?>