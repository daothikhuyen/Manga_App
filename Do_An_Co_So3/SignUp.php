<?php

include "connect.php";

$username = $_POST['username'];
$email = $_POST['email'];
$password = $_POST['password'];


$queryEmail = "SELECT * FROM `users` WHERE email = '$email' ";
$data = mysqli_query($conn,$queryEmail);
$numrow = mysqli_num_rows($data);

if($numrow > 0){
    $arr = [
        'success'=> false,
        'message' => "Email đã tồn tại",
    ];
}else{
    $pass = md5($password);

    $query = "INSERT INTO `users`( `name`, `email`, `password`,`status`) VALUES ('$username','$email','$pass',1)";
    $data = mysqli_query($conn,$query);
    
    if($data == true){
        $arr = [
            'success'=> true,
            'message' => "thanh cong",
        ];
    }else{
        $arr = [
            'success'=> false,
            'message' => "That bai",
        ];
    }
    
}

print_r(json_encode($arr))


?>