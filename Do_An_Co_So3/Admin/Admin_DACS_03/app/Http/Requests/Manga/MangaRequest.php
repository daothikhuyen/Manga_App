<?php

namespace App\Http\Requests\Manga;

use Illuminate\Foundation\Http\FormRequest;

class MangaRequest extends FormRequest
{
    /**
     * Determine if the user is authorized to make this request.
     */
    public function rules(): array
    {
        return [
            'name'=> 'required',
            'image'=> 'required',
            'author'=> 'required',
            'menu_id'=> 'required',
            'category_id'=> 'required',
        ];
    }

    public function messages()
    {
        return [
            'name.required' => 'Vui lòng không để trống tên ảnh',
            'image.required' => "Bạn chưa chọn ảnh cho thể loại",
            'author.required' => "Vui lòng không để trống tên tác giả",
            'menu_id.required' => "Vui lòng chọn menu phù hợp",
            'category_id.required' => "Vui lòng chọn thể loại truyện",
        ];
    }
}
