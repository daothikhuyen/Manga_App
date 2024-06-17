<?php

namespace  App\Http\Service;

use App\Models\Category;
use App\Models\Menu;
use Brick\Math\BigInteger;
use Illuminate\Support\Facades\Log;
use Illuminate\Support\Facades\Session;

class CategoryService {


    public function getAll(){
        return Category::orderbyDesc('id')->paginate(10);
    }

    public function getMenu(){
        return Menu::where('active',1)->get();
    }

    public function  getCategoriesByMenu($menu_id) {

        return Category::where('menu_id', $menu_id)->get();
    }

    public function insert($request){
        try {
            $request->except('_token');

            $images = $request->input('image');

            foreach ($images as $key => $image) {
                Category::create([
                    'name' => (string) $request->input('name'),
                    'image'=> (string) $image,
                    'active' => (int) $request->input('active'),
                    'menu_id' => (int) $request->input('menu_id')
                ]);
            }

            Session::flash('success', 'Thêm thể loại thành công');

        } catch (\Throwable $th) {
           Session::flash('error', 'Thêm thể loại thất bại');
            Log::info($th->getMessage());
            return false;
        }

        return true;
    }

    public function destroy($request){
        // dd($request);
        $id = (int) $request->input('id');

        $category = Category::where('id', $id)->first();


        if($category){
            return Category::where('id', $id)->delete();
        }
        return false;
    }

    public function update($category,$request){
        // dd($request->file);
        if($request->file[0] == null){
            $category->image = $request->image;
        }else{
            $category->image = $request->image[0];
        }
            $category->name = $request->name;
            $category->menu_id = $request->menu_id;
            $category->active = $request->active;
            $category->save();

        Session::flash('success', 'Cập nhập thành công');
        return true;
    }

}
