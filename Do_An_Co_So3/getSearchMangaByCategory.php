<?php

include "connect.php";

$category_id = $_POST['category_id'];

$query = "SELECT mangas.*,menus.name as categoryBook FROM mangas 
            INNER JOIN menus ON mangas.menu_id = menus.id WHERE category_id = $category_id";
$data = mysqli_query($conn,$query);
$result = array();

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

print_r(json_encode($arr))
?>