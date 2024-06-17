package com.example.do_an_cs3.retrofit

import com.example.do_an_cs3.Model.Comment.comment_Model
import com.example.do_an_cs3.Model.Like.mangaLike_Model
import com.example.do_an_cs3.Model.User.user_model
import com.example.do_an_cs3.Model.book_Model
import com.example.do_an_cs3.Model.chapter_Model
import com.example.do_an_cs3.Model.linkChapter_Model
import com.example.do_an_cs3.Model.type_menu_Model
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiSelectMenu {
    @GET("getMenu.php")
    fun getMenu(): Observable<type_menu_Model>

    @POST("getBookHot.php")
    @FormUrlEncoded
    fun getBookHot(
        @Field("name_request") name_request : String
    ):Observable<book_Model>

    @POST("getBook.php")
    @FormUrlEncoded
    fun getBook(
        @Field("name_menu") name_menu : String
    ):Observable<book_Model>

    @POST("getCategory.php")
    @FormUrlEncoded
    fun getCategory(
        @Field("name_menu") name_menu : String
    ):Observable<type_menu_Model>

    @POST("getSearchMangaByCategory.php")
    @FormUrlEncoded
    fun getSearchManga(
        @Field("category_id") id : String,
    ):Observable<book_Model>

    @POST("getChapter.php")
    @FormUrlEncoded
    fun getChapter(
       @Field("id") id : String
    ):Observable<chapter_Model>

    @POST("getLinkChapter.php")
    @FormUrlEncoded
    fun getLinkChapter(
        @Field("menu_id") menuId: String,
        @Field("chapter_id") chapterId: String
    ):Observable<linkChapter_Model>

    @POST("getSearch.php")
    @FormUrlEncoded
    fun getSearch(
        @Field("search") search : String
    ):Observable<book_Model>

    @POST("SignUp.php")
    @FormUrlEncoded
    fun SignUp(
        @Field("username") username : String,
        @Field("email") email : String,
        @Field("password") password : String
    ):Observable<user_model>

    @POST("Login.php")
    @FormUrlEncoded
    fun Login(
        @Field("email") email : String,
        @Field("password") password : String
    ):Observable<user_model>

    @POST("getLikeMangaByUser.php")
    @FormUrlEncoded
    fun getLikeMangaByUser(
        @Field("manga_id") manga_id : String,
        @Field("user_id") user_id : String,
    ):Observable<mangaLike_Model>

    @POST("insertLikeManga.php")
    @FormUrlEncoded
    fun insertLikeManga(
        @Field("manga_id") manga_id : String,
        @Field("user_id") user_id : String,
    ):Observable<mangaLike_Model>

    @POST("getComment.php")
    @FormUrlEncoded
    fun getComment(
        @Field("manga_id") manga_id : String,
    ): Observable<comment_Model>

    @POST("postComment.php")
    @FormUrlEncoded
    fun postComment(
        @Field("user_id") user_id: String,
        @Field("manga_id") manga_id : String,
        @Field("comment") comment : String,
    ): Observable<comment_Model>

    @POST("getLikeCollection.php")
    @FormUrlEncoded
    fun getLikeCollection(
        @Field("manga_like") manga_like : String,
        @Field("user_id") user_id: String,
    ): Observable<book_Model>

    @POST("uploadInfoUser.php")
    @FormUrlEncoded
    fun uploadInfoUser(
        @Field("id") id : String,
        @Field("name") name : String,
        @Field("email") email : String,
        @Field("password") password : String,
        @Field("avatar") avtar : String,
    ):Observable<user_model>

    @POST("updateNumber_Reads.php")
    @FormUrlEncoded
    fun uploadNumber_Reads(
        @Field("id_manga") id : Int,
    ):Observable<user_model>

    @POST("deleteComment.php")
    @FormUrlEncoded
    fun deleteComment(
        @Field("id_comment") id_comment : String,
        @Field("id_user") id_user : String,
    ):Observable<comment_Model>

}