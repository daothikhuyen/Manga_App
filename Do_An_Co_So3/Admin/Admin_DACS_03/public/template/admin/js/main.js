$.ajaxSetup({
    headers: {
        'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
    }
});

// upload file
$('#upload').change(function(){
    console.log("Uploading file...");
    const form = new FormData();
    for (let index = 0; index < $(this)[0].files.length; index++) {

        const element = $(this)[0].files[index];
        console.log(element)
        form.append('file[]', element);

    }

    $.ajax({
        processData: false,
        contentType: false,
        type: 'POST',
        dataType: 'JSON',
        data: form,
        url: '/admin/upload/services',
        success: function(result){
            if(result.error == false){
                const div = $('<div class="d-flex" ></div>');
                result.url.forEach(element => {

                    const html = '<a href="/storage/'+element+'"><img src="/storage/'+element+'" alt="" style="width:50px;" class="mx-1 border-1"><input type="hidden" name="image[]" id="image" value = "'+element+'"></a>'
                    console.log(html)
                    div.append(html)

                    // $('#image_show').html('<a href="/storage/'+result.url+'"> <img src="/storage/'+result.url+'" alt="" style="width:100px;"> </a>')

                });
                $('#image_show').html(div)
                // $('#image').val(result.url);
            }else{
                alert('Upload File lỗi')
            }
        }
    })
})

// Delete

function removeRow (id,url){
    console.log(url);

    if(confirm('Xoá mà không thể khôi phục. Bạn chắc chắn ?')){
        $.ajax({
            type: 'DELETE',
            dataType: 'JSON',
            data: {id},
            url: url,
            success: function(result){
                if(result.error == true){
                    alert(result.message)
                    location.reload()
                }else{
                    alert('Xoá thất bại')
                }
            }
        })
    }
}

// selected add mangas
document.getElementById("menu_Add_Mangas").addEventListener('change', function(){

    const categories = document.getElementById("category_Add_Mangas")
    categories.innerHTML = '<option value="0">Danh mục thể loại</option>'

    const id_menu = this.value
    $.ajax({
        type: 'GET',
        dataType: 'JSON',
        data: {id_menu},
        url: "/admin/categories/getcategory",
        success: function(result){
            if(result.error == true){
                result.results.forEach(element => {
                    var option = document.createElement('option');
                        option.value = element.id;
                        option.textContent = element.name;
                        categories.appendChild(option);
                });
            }else{
                alert('Lấy thể loại thất bại')
            }
        }
    })
})
