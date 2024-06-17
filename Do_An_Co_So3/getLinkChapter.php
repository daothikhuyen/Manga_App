<?php

include "connect.php";

$menu_id = $_POST['menu_id'];
$chapter_id = $_POST['chapter_id'];

$result = array();

$query = "SELECT * FROM `chapters` 
                INNER JOIN `links` 
                ON chapters.id = links.chapter_id 
                WHERE links.chapter_id = $chapter_id";
    $data = mysqli_query($conn,$query);

while($row = mysqli_fetch_assoc($data)){
    $result[] = ($row);

}

if(!empty($result)){
    $arr = [
        'success'=> true,
        'message' => "thanh cong",
        'result' => $result
    ];
}else{
    $arr = [
        'success'=> false,
        'message' => "That bai",
        'result' => $result
    ];
}

// header('Content-Type: application/json');

print_r(json_encode($arr))
?>