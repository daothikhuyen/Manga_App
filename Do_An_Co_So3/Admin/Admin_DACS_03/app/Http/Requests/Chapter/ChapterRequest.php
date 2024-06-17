<?php

namespace App\Http\Requests\Chapter;

use Illuminate\Foundation\Http\FormRequest;

class ChapterRequest extends FormRequest
{
    /**
     * Determine if the user is authorized to make this request.
     */
    public function authorize(): bool
    {
        return true;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array<string, \Illuminate\Contracts\Validation\ValidationRule|array<mixed>|string>
     */
    public function rules(): array
    {
        return [
            'title' => 'required',
            'image' => 'required_if:content,null',
            'content' => 'required_if:image,null',
        ];
    }

    public function messages()
    {
        return [
            'title.required' => 'Vui lòng không để trống tên tiêu đề',
            'image.required_if' => 'Bạn chưa chọn ảnh',
            'content.required_if' => 'Vui lòng nhập nội dung',
        ];
    }
}
