<?php 

    include "connect.php";

    $id = $_POST['id_manga'];

    $query = "SELECT * FROM `mangas` WHERE id = $id";
    $number_reads = mysqli_query($conn,$query);

    $number = 0;
    while($row = mysqli_fetch_assoc($number_reads)){
        $number = $row['number_reads'] + 1;

        $result = mysqli_query($conn, "UPDATE `mangas` SET `number_reads`='$number' WHERE `id` = $id");
       
    }
   

   

if($result){
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

print_r(json_encode($arr))

?>