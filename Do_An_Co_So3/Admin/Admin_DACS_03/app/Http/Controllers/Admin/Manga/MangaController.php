<?php

namespace App\Http\Controllers\Admin\Manga;

use App\Http\Controllers\Controller;
use App\Http\Requests\Manga\MangaRequest;
use App\Http\Service\MangaService;
use App\Models\Manga;
use App\Models\Menu;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Session;
use Psy\Readline\Hoa\Console;

class MangaController extends Controller
{

    protected $mangaService;

    public function __construct(MangaService $mangaService)
    {
        $this->mangaService = $mangaService;
    }

    public function create(){
        return view('admin.mangas.add',[
            "title" => 'Thêm truyện, sách',
            'menus' => $this->mangaService->getMenu(),
            'categories' => $this->mangaService->getCategory()
        ]);
    }

    public function store(MangaRequest $request){
        $this->mangaService->insert($request);

        return redirect()->back();
    }

    public function indexComic(){
        return view('admin.mangas.listComic', [
            'title' => 'Danh sách tranh',
            'mangas' => $this->mangaService->getComic()
        ]);
    }


    public function indexNovel(){
        return view('admin.mangas.listNovel', [
            'title' => 'Danh sách chữ',
            'mangas' => $this->mangaService->getNovel()
        ]);
    }

    public function destroy(Request $request){
        $result = $this->mangaService->destroy($request);

        if($result){
            return response()->json([
                'error' => true,
                'message' => 'Xoá thành công'
            ]);
        }else{
            return response()->json([
                'error' => false,
                'message' => 'Xoá thất bại'
            ]);
        }
    }

    public function show(Manga $manga){
        return view('admin.mangas.edit', [
            'title' => $manga->name,
            'manga' => $manga,
            'menus' => $this->mangaService->getMenu(),
            'categories' => $this->mangaService->getCategory()
        ]);
    }

    public function update(Manga $manga ,MangaRequest $request){
        // dd($request);
        $this->mangaService->update($manga,$request);

        return redirect()->back();
    }

    // Tìm kiếm cho truyện chữ
    public function searchNovel(Request $request){

        $result = $this->mangaService->search($request);

        if(count($result) > 0){
            return view('admin.mangas.listNovel', [
                'title' => 'Tìm kiếm',
                'mangas' =>$result
            ]);
        }else{
            Session::flash('error',"Truyện không tồn tại");
            return redirect('/admin/mangas/listNovel');
        }
    }

    // Tìm kiếm cho truyện trang
    public function searchComic(Request $request){

        $result = $this->mangaService->search($request);

        if(count($result) > 0){
            return view('admin.mangas.listComic', [
                'title' => 'Tìm kiếm',
                'mangas' =>$result
            ]);
        }else{
            Session::flash('error',"Truyện không tồn tại");
            return redirect('/admin/mangas/listComic');
        }
    }
}
