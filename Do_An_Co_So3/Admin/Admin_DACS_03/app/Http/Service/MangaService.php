<?php

namespace  App\Http\Service;

use App\Models\Category;
use App\Models\Manga;
use App\Models\Menu;
use Illuminate\Support\Facades\Log;
use Illuminate\Support\Facades\Session;

class MangaService{

    public function getAll(){
        return Manga::with('menu','category')->select()->paginate(10);
    }

    public function getMenu(){
        return Menu::where('active', 1)->get();
    }

    public function getCategory(){
        return Category::where('active', 1)->get();
    }

    public function getComic(){

        $novel = Menu::where('name', 'Truyện tranh')->first();
        return Manga::with('menu','category')->where('menu_id', $novel->id)->paginate(10);
    }

    public function getNovel(){

        $novel = Menu::where('name', 'Tiểu thuyết')->first();
        return Manga::with('menu','category')->where('menu_id', $novel->id)->paginate(8);
    }

    public function insert($request){
        // dd($request);
        try {
            $request->except('_token');

            $images = $request->input('image');

            foreach ($images as $key => $image) {
                Manga::create([
                    'image' => (string) $image,
                    'name' => (string) $request->input('name'),
                    'author'=> (string) $request->input('author'),
                    'menu_id'=> (int) $request->input('menu_id'),
                    'category_id'=>(int)$request->input('category_id'),
                    'active' => (int) $request->input('active'),
                    'suggest' => (int) $request->input('suggest'),
                    'number_like'=> 0,
                    'description'=>(string) $request->input('description'),
                    'number_reads' => 0,
                ]);
            }

            Session::flash('success', 'Thêm thành công');

        } catch (\Exception $th) {
            Session::flash('error', $th->getMessage());
            Log::info($th->getMessage());
            return false;
        }
    }

    public function destroy($request){
        $result = Manga::where('id', $request->input('id'))->first();

        if($result){
            return Manga::where('id',$request->input('id'))->delete();
        }
        else{
            return false;
        }
    }

    public function update($manga,$request){
        $manga->fill($request->input());

        if($request->file[0] == null){
            $manga->image = $request->image;
        }else{
            $manga->image = $request->image[0];
        }
        $manga->save();

        Session::flash('success', 'Cập nhập thành công');
        return true;

    }

    public function search($request){

        try {
            $request->except('_token');

            $result  = Manga::where('name', 'like', '%'. $request->input('search').'%')->paginate(10);

            return $result;

        } catch (\Exception $th) {
            Session::flash('error', $th->getMessage());
            Log::info($th->getMessage());
            return false;
        }

    }
}
