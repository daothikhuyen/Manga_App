<?php 
    include "connect.php";

    $name = $_POST['name'] ;
    $email = $_POST['email'];
    $password = $_POST['password'];
    $avatar = $_POST['avatar']; 
    $id = $_POST['id'] ;
    $result = array();

    $pass = md5($password);
    $query = "UPDATE `users` SET `name`='$name', `email`='$email',
                `password`='$pass',`avatar`=' $avatar' WHERE id = $id";
    $data = mysqli_query($conn,$query);

    if($data){
        $select = "SELECT * FROM `users` WHERE id = $id";
        $resultData = mysqli_query($conn,$select);

        while($row = mysqli_fetch_assoc($resultData)){
            $result[] = ($row);
        }
    }

if($resultData){
    $arr = [
        'success'=> true,
        'message' => "Cập nhập thông tin thành công",
        'result' => $result
    ];
}else{
    $arr = [
        'success'=> false,
        'message' => "Cập nhập thông tin thất bại",
        'result' => $result
    ];
}

print_r(json_encode($arr))

?>