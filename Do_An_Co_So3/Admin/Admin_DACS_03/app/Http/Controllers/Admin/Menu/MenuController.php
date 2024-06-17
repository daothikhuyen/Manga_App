<?php

namespace App\Http\Controllers\Admin\Menu;

use App\Http\Controllers\Controller;
use App\Http\Requests\Menu\MenuRequest;
use App\Http\Service\MenuService;
use App\Models\Menu;
use Illuminate\Http\Request;

class MenuController extends Controller
{
    protected $menuService;

    public function __construct(MenuService $menuService)
    {
        $this->menuService = $menuService;
    }

    public function create(){
        return view('admin.menu.add', [
            'title'=> 'Thêm menu',
        ]);
    }

    public function store(MenuRequest $request){
        $this->menuService->insert($request);
        return redirect()->back();
    }

    public function index(){
        return view('admin.menu.list', [
            'title'=> 'Danh sách menu',
            'menus'=> $this->menuService->getAll()
        ]);
    }

    public function destroy(Request $request){
        $result = $this->menuService->destroy($request);

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

    public function show(Menu $menu){
        return view('admin.menu.edit', [
            'title' => 'Chỉnh sửa',
            'menu' => $menu
        ]);
    }

    public function update(Menu $menu, MenuRequest $request){
        $this->menuService->update($menu,$request);

        return redirect('/admin/menus/list');
    }
}
