<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Category extends Model
{
    use HasFactory;
    protected $fillable = [
        'name',
        'image',
        'active',
        'menu_id'
    ];

    public function menu()
    {
        return $this->hasMany(Menu::class,'menu_id','id');
    }
}
