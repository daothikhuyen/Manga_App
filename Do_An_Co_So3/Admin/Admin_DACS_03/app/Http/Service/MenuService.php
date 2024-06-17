<?php

namespace  App\Http\Service;

use App\Models\Menu;
use Illuminate\Support\Facades\Log;
use Illuminate\Support\Facades\Session;

class MenuService{

    public function getAll(){
        return Menu::select()->paginate(20);
    }

    public function insert($request){

        try {
            $request->except('_token');

            $images = $request->input('image');

            foreach ($images as $key => $image) {
                Menu::create([
                    'name' => (string) $request->input('name'),
                    'image'=> (string)  $image,
                    'active'=>(string) $request->input('active')
                ]);
            }

            Session::flash('success', 'Thêm thành công');
        } catch (\Throwable $th) {
            Session::flash('error', 'Thêm menu thất bại');
            Log::info($th->getMessage());
            return false;
        }

    }

    public function destroy($request){
        $menu = Menu::where('id', $request->input('id'))->first();

        if($menu){
            return Menu::where('id', $request->input('id'))->delete();
        }else{
            return false;
        }
    }

    public function update($menu,$request){
        try {
            if($request->file[0] == null){
                $menu->image = $request->image;
            }else{
                $menu->image = $request->image[0];
            }
            $menu->name = $request->name;
            $menu->active = $request->active;
            $menu->save();
            Session::flash('success', 'Sửa thành công');
        } catch (\Throwable $th) {
            Session::flash('error', 'Sửa menu thất bại');
            Log::info($th->getMessage());
            return false;
        }
    }
}
