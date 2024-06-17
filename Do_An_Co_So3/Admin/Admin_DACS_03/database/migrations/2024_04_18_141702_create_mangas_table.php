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
        Schema::create('mangas', function (Blueprint $table) {
            $table->id();
            $table->string('image');
            $table->string('name');
            $table->string('author');
            $table->bigInteger('menu_id')->unsigned();
            $table->bigInteger('category_id')->unsigned();
            $table->integer('active')->comment('1:hoạt đông, 0 là không hoạt đông');
            $table->integer('suggest')->comment('1:đề xuất, 0: không đề xuất');
            $table->integer('number_like');
            $table->string('description');
            $table->timestamps();

            $table->foreign('menu_id')->references('id')->on('menus');
            $table->foreign('category_id')->references('id')->on('categories');

        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('mangas');
    }
};
