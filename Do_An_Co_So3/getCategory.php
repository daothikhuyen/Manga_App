<?php

include "connect.php";

if(!empty( $_POST['name_menu']) && $_POST['name_menu'] != ""){
    $name_menu = $_POST['name_menu'];

    $query = "SELECT categories.* from `categories` 
    INNER JOIN `menus` ON categories.menu_id = menus.id WHERE categories.active = 1 AND menus.name = '$name_menu';";

}else{
    $query = "SELECT categories.* from `categories` WHERE active = 1";
}

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