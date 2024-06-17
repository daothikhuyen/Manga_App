<?php

include "connect.php";

$user_id = $_POST['user_id'];
$manga_id = $_POST['manga_id'];
$comment = $_POST['comment'];
$date = date('Y-m-d');
$result = array();


$query = "INSERT INTO `comments`(`comment`, `user_id`, `manga_id`, `created_at`, `updated_at`) 
                VALUES ('$comment','$user_id','$manga_id','$date','$date') ";
$data = mysqli_query($conn,$query);

if($data == true){
    $arr = [
        'success'=> true,
        'message' => "Cảm ơn bạn đã bình luận ",
    ];
}else{
    $arr = [
        'success'=> false,
        'message' => "That bai",
    ];
}

print_r(json_encode($arr))


?>