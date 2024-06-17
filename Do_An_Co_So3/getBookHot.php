<?php 

    include "connect.php";

    $name_request = $_POST['name_request'];

    if($name_request == 'Yêu Thích'){
        $query = "SELECT * FROM `mangas` ORDER BY number_like DESC;";
    }
    if($name_request == 'Lượt Đọc'){
        $query = "SELECT * FROM `mangas` ORDER BY number_reads DESC;";
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
        'message' => "Thât bai",
        'result' => $result
    ];
}

print_r(json_encode($arr))

?>