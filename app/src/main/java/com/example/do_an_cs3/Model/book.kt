package com.example.do_an_cs3.Model

open class book(
    val id: Int = 0,
    val image : String = "",
    val name : String,
    val description: String,
    val suggest : Int,
    val menu_id : Int,
    val category_id: Int,
    val icon_rating :String,
    val number_like: Int,
    val number_reads: Int,
    val author: String,
    val categoryBook: String
){}
