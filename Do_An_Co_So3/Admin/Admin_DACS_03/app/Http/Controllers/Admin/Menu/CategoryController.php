<?php

namespace App\Http\Controllers\Admin\Menu;

use App\Http\Controllers\Controller;
use App\Http\Requests\Category\CategoryRequest;
use App\Http\Service\CategoryService;
use App\Models\Category;
use Illuminate\Http\Request;

class CategoryController extends Controller
{
    protected $categoryService;

    public function __construct(CategoryService $categoryService)
    {
        $this->categoryService = $categoryService;
    }

    public function getcategory(Request $request){

        $result = $this->categoryService->getCategoriesByMenu($request->input("id_menu"));

        if($result){
            return response()->json([
                'error' => true,
                'message' => "Lấy thành công",
                'results' => $result
            ]);
        }else{
            return response()->json([
                'error' => false,
                'message' => 'Thất bại'
            ]);
        }

    }

    public function index(){
        return view('admin.categories.list', [
            'title' => 'Danh sách',
            'categoies' => $this->categoryService->getAll()
        ]);
    }

    public function create(){
        return view('admin.categories.add', [
            'title' => "Thêm thể loại truyện",
            'menus'=> $this->categoryService->getMenu()
        ]);
    }

    public function store(CategoryRequest $request){

        $this->categoryService->insert($request);

        return redirect()->back();
    }

    public function destroy(Request $request){
        // dd($request->input('id'));

        $result =  $this->categoryService->destroy($request);

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

    public function show(Category $category){

        // dd($category);
        return view('admin.categories.edit', [
            'title' => 'Chỉnh sửa',
            'category' => $category,
            'menus'=> $this->categoryService->getMenu()
        ]);
    }

    public function update(Category $category,CategoryRequest $request){

        $this->categoryService->update($category, $request);

        return redirect('/admin/categories/list');
    }

}
