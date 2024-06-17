package com.example.do_an_cs3.utils

import com.example.do_an_cs3.Model.User.user

class Utils {
    companion object {
        const val BASE_URL: String = "http://192.168.43.136/Do_An_Co_So3/";
        const val API : String = this.BASE_URL + "Admin/Admin_DACS_03/storage/app/public/"
        var user_current : user = user(0,"","","","","");

    }
}