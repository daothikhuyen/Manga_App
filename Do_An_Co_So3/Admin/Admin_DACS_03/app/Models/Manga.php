<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\HasOne;

class Manga extends Model
{
    use HasFactory;

    protected $fillable =[
        'image',
        'name',
        'author',
        'menu_id',
        'category_id',
        'active',
        'suggest',
        'number_like',
        'description',
        'number_reads'
    ];

    public function menu(){
        return $this->hasOne(Menu::class, 'id', 'menu_id');
    }

    public function category(){
        return $this->hasOne(Category::class, 'id', 'category_id');
    }
}
