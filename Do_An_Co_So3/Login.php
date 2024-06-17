<?php 
    include "connect.php";

    $email = $_POST['email'];
    $password = $_POST['password'];

    $pass = md5($password);
    $query = "SELECT * FROM `users` WHERE email = '".$email."'  AND status = 1 AND password = '".$pass."' ";
    $data = mysqli_query($conn,$query);
    $num_row = mysqli_num_rows($data);

    $result = array();
    if($num_row > 0){
        while($row = mysqli_fetch_assoc($data)){
            $result[] = ($row);
        }

        $result[0]['password']=$password;
    }

if(!empty($result)){
    $arr = [
        'success'=> true,
        'message' => "Đăng nhập thành công",
        'result' => $result
    ];
}else{
    $arr = [
        'success'=> false,
        'message' => "Email hoặc Password bị sai",
        'result' => $result
    ];
}

print_r(json_encode($arr))

?>