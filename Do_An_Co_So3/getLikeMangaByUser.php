<?php

include "connect.php";

$manga_id = $_POST['manga_id'];
$user_id = $_POST['user_id'];
$result = array();

    $manga_like = "SELECT * FROM `manga_likes` WHERE user_id = $user_id AND manga_id = $manga_id";
    $getMangaLike = mysqli_query($conn,$manga_like);

    while($row = mysqli_fetch_assoc($getMangaLike)){
        $result[] = ($row);
    }

if($getMangaLike){
    $arr = [
        'success'=> true,
        'message' => "Thành công",
        'result' => $result,
    ];
}else{
    $arr = [
        'success'=> false,
        'message' => "Thất bại",
        'result' => $result,
    ];
}

print_r(json_encode($arr))
?>