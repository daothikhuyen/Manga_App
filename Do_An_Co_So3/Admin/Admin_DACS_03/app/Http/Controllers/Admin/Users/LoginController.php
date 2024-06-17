<?php

namespace App\Http\Controllers\Admin\Users;

use App\Http\Controllers\Controller;
use App\Http\Requests\User\LoginRequest;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Session;

class LoginController extends Controller
{
    public function index(){

        return view('admin.users.login', [
            'title' => 'Đăng nhập hệ thống'
        ]);
    }

    public function store(LoginRequest $request){

        if(Auth::attempt([
            'email' => $request->input('email'),
            'password' => $request->input('password'),
            'status' => '0'
        ],$request->input('remember'))){
            return redirect()->route('admin');
        };

        Session::flash('error', 'Email hoặc mật khẩu không đúng');
        return redirect()->back();

    }
}
