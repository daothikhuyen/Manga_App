<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Chapter extends Model
{
    use HasFactory;
    protected $fillable = [
        'title',
        'manga_id',
    ];

    public function link(){
        return $this->hasMany(Link::class, 'chapter_id', 'id');
    }
}
