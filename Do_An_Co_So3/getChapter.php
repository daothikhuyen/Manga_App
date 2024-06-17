<?php

include "connect.php";

$manga_id = $_POST['id'];

$query = "SELECT chapters.*,mangas.name,mangas.menu_id FROM `chapters` 
        INNER JOIN `mangas` ON chapters.manga_id = mangas.id WHERE manga_id = $manga_id";
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

header('Content-Type: application/json');

print_r(json_encode($arr))
?>