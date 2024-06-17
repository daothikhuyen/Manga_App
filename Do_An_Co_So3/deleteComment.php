<?php 

    include "connect.php";

    $id_comment = $_POST['id_comment'];
    $id_user = $_POST['id_user'];

    $query = "DELETE FROM `comments` WHERE id = $id_comment AND user_id = $id_user";
    $request = mysqli_query($conn,$query);

    if($request){
        $arr = [
            'success'=> true,
            'message' => "thanh cong",
        ];
    }else{
        $arr = [
            'success'=> false,
            'message' => "Thât bai",
        ];
    }

    print_r(json_encode($arr));


