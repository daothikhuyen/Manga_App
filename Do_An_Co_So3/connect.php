<?php
    $host = "localhost";
    $user = "root";
    $pass = "";
    $database = "do_an_cs3";

    $conn = mysqli_connect($host,$user,$pass,$database);
    mysqli_set_charset($conn,"utf8");

    if($conn){
        // echo "ok kết nối";
    }
?>