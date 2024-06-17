<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('manga_likes', function (Blueprint $table) {
            $table->id();
            $table->bigInteger('manga_id')->unsigned();
            $table->bigInteger('user_id')->unsigned();
            $table->integer('like')->comment('1:like , 0: ko like');
            $table->timestamps();

            $table->foreign('manga_id')->references('id')->on('mangas');
            $table->foreign('user_id')->references('id')->on('users');
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('manga_likes');
    }
};
