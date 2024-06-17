<?php

namespace App\Http\Controllers\Chapter;

use App\Http\Controllers\Controller;
use App\Http\Requests\Chapter\ChapterRequest;
use App\Http\Service\ChapterService;
use App\Models\Chapter;
use App\Models\Link;
use App\Models\Manga;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Session;

class ChapterController extends Controller
{
    protected $chapter;

    public function __construct(ChapterService $chapter)
    {
        $this->chapter = $chapter;
    }

    public function create(Manga $manga){
        return view('admin.mangas.chapter.add', [
            'title' => 'Thêm chapter/chương truyện',
            'manga' => $manga
        ]);
    }

    public function index(Manga $manga){

        $result = $this->chapter->getchapterByid($manga->id);

        return view('admin.mangas.chapter.list', [
            'title' => 'Danh sách chapter',
            'manga_id' => $manga->id,
            'chapters' => $result
        ]);

    }

    public function store(ChapterRequest $request){
        $this->chapter->insert($request);

        if($request->input('content') == null){
            return redirect("/admin/mangas/chapters/listComic/".$request->input('manga_id'));
        }else{
            return redirect("/admin/mangas/chapters/listNovel/".$request->input('manga_id'));
        }

    }

    public function destroy(Request $request){

        $result =  $this->chapter->destroy($request);

        if($result){
            return response()->json([
                'error' => true,
                'message' => 'Xoá thành công'
            ]);
        }else{
            return response()->json([
                'error' => false,
                'message' => 'Xoá thành công'
            ]);
        }
    }

    public function show(Request $request, Chapter $chapter) {

        $result = $this->chapter->getLink($chapter->id);

        if($result){

            return view('admin.mangas.chapter.edit', [
                'title' => $chapter->title,
                'chapters' => $result,
            ]);
        }
        return redirect()->back();
    }

    public function upload(ChapterRequest $request){
        // dd($request);

        $this->chapter->update($request);

        return redirect()->back();
    }

}
