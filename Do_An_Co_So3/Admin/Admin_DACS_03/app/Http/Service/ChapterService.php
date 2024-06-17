<?php

namespace  App\Http\Service;

use App\Models\Chapter;
use App\Models\Link;
use App\Models\Manga;
use App\Models\Menu;
use Illuminate\Support\Facades\Log;
use Illuminate\Support\Facades\Session;

class ChapterService {

    public function getchapterByid($id){

        return Chapter::where('manga_id', (int) $id)->paginate(10);
    }

    public function getLink($id){
        try {
            return Chapter::with('link')->where('id', $id)->get();
        } catch (\Throwable $th) {
            Log::info($th->getMessage());
            Session::flash('error', 'chapter đã lỗi');
            return false;
        }

    }

    public function insert($request){
        try {
            $chapter =  Chapter::create([
                'title' => (string) $request->input('title'),
                'manga_id' => (int) $request->input('manga_id'),
            ]);

            // dd($chapter->id);
            if($request->input('content') == null){
                $images = $request->input('image');

                foreach ($images as $key => $image) {
                    Link::create([
                        'content' => '',
                        'image' => $image,
                        'chapter_id' => $chapter->id,
                    ]);
                }
            }else{
                Link::create([
                    'content' => $request->input('content'),
                    'image' => '',
                    'chapter_id' => $chapter->id,
                ]);
            }

            Session::flash('success', 'Thêm chapter / chương thành công');
            return true;
        } catch (\Throwable $th) {
            Log::info($th->getMessage());
            Session::flash('error', 'Thêm chapter / chương  thất bại');
            return false;
        }

    }

    public function destroy($request){
        $result = Chapter::where('id', $request->input('id'))->first();

        if($result){
            return Chapter::where('id', $request->input('id'))->delete();
        }
        return false;
    }
    public function update($request){
        try {
            $chapter = Chapter::find($request->chapter_id);
            $chapter->title = $request->title;
            $chapter->save();

            if($chapter){

                if($request->image == null){
                    for ($i=0; $i < count($request->link_id); $i++) {

                                $link = Link::find($request->link_id[$i]);
                                $link->content = $request->content === null ?"":$request->content;
                                $link->save();
                    }
                }else{
                    if(count($request->link_id) >= count($request->image)){
                        for ($i=0; $i < count($request->image); $i++) {

                            $link = Link::find($request->link_id[$i]);
                            $link->content = $request->content === null ?"":$request->content;
                            $link->image = $request->image[$i]== null ?"": $request->image[$i];
                            $link->save();
                        }
                        for ($j = count($request->image); $j < count($request->link_id); $j++) {
                            Link::where('id',$request->link_id[$j])->delete();
                        }
                    }

                    if(count($request->link_id) < count($request->image)){

                        for ($j = count($request->link_id); $j < count($request->image); $j++) {
                            Link::create([
                                'content' => "",
                                'image' => $request->image[$j],
                                'chapter_id' => $request->chapter_id,
                            ]);
                        }
                    }
                }


                Session::flash('success', 'Update thành công');
                return true;
            }

        } catch (\Throwable $th) {
            Log::info($th->getMessage());
            Session::flash('error', 'update lỗi');
            return false;
        }
    }
}
