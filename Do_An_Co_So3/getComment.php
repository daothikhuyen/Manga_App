<?php 
    include "connect.php";
    
    $manga_id = $_POST['manga_id'];
    $result = array();

    $query = "SELECT comments.*, users.name , users.avatar FROM `comments` 
            INNER JOIN `users` 
            ON comments.user_id = users.id 
            WHERE comments.manga_id = $manga_id 
            ORDER BY comments.id DESC;";
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

print_r(json_encode($arr))

?>