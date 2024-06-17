<?php 

    include "connect.php";

    $manga_like_save = $_POST['manga_like'];
    $user_id = $_POST['user_id'];

    $query = "SELECT mangas.* FROM mangas INNER JOIN manga_likes
                ON mangas.id = manga_likes.manga_id 
                WHERE manga_likes.user_id = $user_id AND manga_likes.like =  $manga_like_save";

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