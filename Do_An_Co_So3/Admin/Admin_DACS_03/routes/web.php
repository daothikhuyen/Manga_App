<?php

use App\Http\Controllers\Admin\Manga\MangaController;
use App\Http\Controllers\Admin\Menu\CategoryController;
use App\Http\Controllers\Admin\Menu\MenuController;
use App\Http\Controllers\Admin\UploadController;
use App\Http\Controllers\Admin\Users\LoginController;
use App\Http\Controllers\Admin\Users\LogoutController;
use App\Http\Controllers\Admin\Users\MainController;
use App\Http\Controllers\Chapter\ChapterController;
use Illuminate\Support\Facades\Route;


Route::get('admin/users/login',[ LoginController::class, 'index'])->name('login');
Route::post('admin/users/login/store', [LoginController::class, 'store']);
Route::get('admin/logout', [LogoutController::class, 'logout'])->name('logout');

Route::middleware(['auth'])->group(function(){
    Route::prefix('admin')->group(function(){

        Route::get('/', [MainController::class,'index'])->name('admin');
        Route::get('/main', [MainController::class,'index']);

        Route::prefix('menus')->group(function(){
            Route::get('add', [MenuController::class,'create']);
            Route::post('add',[MenuController::class, 'store']);
            Route::get('list',[MenuController::class, 'index']);
            Route::DELETE('destroy', [MenuController::class, 'destroy']);
            Route::get('edit/{menu}', [MenuController::class, 'show']);
            Route::post('edit/{menu}', [MenuController::class, 'update']);
        });

        Route::prefix('categories')->group(function(){
            Route::get('add', [CategoryController::class,'create']);
            Route::post('add',[CategoryController::class, 'store']);
            Route::get('list',[CategoryController::class, 'index']);
            Route::DELETE('destroy', [CategoryController::class, 'destroy']);
            Route::get('edit/{category}', [CategoryController::class, 'show']);
            Route::post('edit/{category}', [CategoryController::class, 'update']);
            Route::get('getcategory',[CategoryController::class,'getcategory']);
        });

        Route::prefix('mangas')->group(function(){
            Route::get('add', [MangaController::class,'create']);
            Route::post('add',[MangaController::class, 'store']);
            Route::get('listComic',[MangaController::class, 'indexComic']);
            Route::get('listNovel',[MangaController::class, 'indexNovel']);

            Route::post('listNovel',[MangaController::class, 'searchNovel']);
            Route::post('listComic',[MangaController::class, 'searchComic']);
            Route::DELETE('destroy', [MangaController::class, 'destroy']);
            Route::get('edit/{manga}', [MangaController::class, 'show']);
            Route::post('edit/{manga}', [MangaController::class, 'update']);

            Route::prefix('chapters')->group(function(){
                Route::get('add/{manga}', [ChapterController::class, 'create']);
                Route::post('add/{manga}', [ChapterController::class, 'store']);
                Route::get('listNovel/{manga}', [ChapterController::class, 'index']);
                Route::get('listComic/{manga}', [ChapterController::class, 'index']);
                Route::DELETE('destroy', [ChapterController::class, 'destroy']);
                Route::get('edit/{chapter}', [ChapterController::class,'show']);
                Route::post('edit/{chapter}', [ChapterController::class,'upload']);
            });

        });

        #upload image
        Route::post('upload/services',[UploadController::class,'store']);

    });
});

