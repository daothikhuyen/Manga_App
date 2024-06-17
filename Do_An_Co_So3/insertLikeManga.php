<?php

use function PHPUnit\Framework\isEmpty;

include "connect.php";

$manga_id =$_POST['manga_id'];
$user_id = $_POST['user_id'];
$number_of_likes = 0;
$result = array();

    $manga_like = "SELECT * FROM `manga_likes` WHERE user_id = $user_id AND manga_id = $manga_id";
    $getMangaLike = mysqli_query($conn,$manga_like);

    if(mysqli_num_rows($getMangaLike) > 0){
        while($row = mysqli_fetch_assoc($getMangaLike)){
            $query = "DELETE FROM `manga_likes` WHERE id =".$row['id'];
        }

    }else{
        $query = "INSERT INTO `manga_likes`(`manga_id`, `user_id`,`like`) VALUES ('$manga_id','$user_id','1')";
    }
    $data = mysqli_query($conn,$query);
    
    $numberLike = mysqli_query($conn, "SELECT * FROM `manga_likes` WHERE `manga_id` = $manga_id AND `like` = 1");
    
    while($row = mysqli_fetch_assoc($numberLike)){
        $result[] = ($row);
        $number_of_likes += 1;
    }

    mysqli_query($conn, "UPDATE `mangas` SET `number_like`='$number_of_likes' WHERE `id` = $manga_id");

    

if($data){
    $arr = [
        'success'=> true,
        'message' => "Cảm ơn bạn đã chú ý chúng tôi",
        'result'=> $result
    ];
}else{
    $arr = [
        'success'=> false,
        'message' => "Bạn đã yêu thích thất bại rôi 😒",
        'result'=> $result
    ];
}

print_r(json_encode($arr))
?>