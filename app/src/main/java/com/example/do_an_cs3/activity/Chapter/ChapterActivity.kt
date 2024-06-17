package com.example.do_an_cs3.activity.Chapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.do_an_cs3.Model.chapter
import com.example.do_an_cs3.Model.link_chapter
import com.example.do_an_cs3.adapter.ListBook.MySliderChapter_Adapter
import com.example.do_an_cs3.databinding.ActivityChapterBinding
import com.example.do_an_cs3.retrofit.ApiSelectMenu
import com.example.do_an_cs3.retrofit.RetrofitClient
import com.example.do_an_cs3.utils.Utils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

private lateinit var binding: ActivityChapterBinding
class ChapterActivity : AppCompatActivity() {

    lateinit var compositeDisposable: CompositeDisposable
    private lateinit var  api : ApiSelectMenu
    var position : Int = 0
    var size : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChapterBinding.inflate(layoutInflater)
        setContentView(binding.root)

       compositeDisposable = CompositeDisposable()
        api = RetrofitClient.getInstant(Utils.BASE_URL).create(ApiSelectMenu::class.java)

        if(savedInstanceState == null){
            binding.txtChapterName.text = intent.getStringExtra("title")

            val menu_id = intent.getStringExtra("menu_id").toString()
            val chapter_id = intent.getStringExtra("chapter_id").toString()

            PushData_ChapterBook(menu_id, chapter_id)
        }

        position = intent.getStringExtra("position")?.toInt() ?: 0
        size = intent.getStringExtra("size")?.toInt() ?: 0

        binding.next.setOnClickListener {
            if (position < size -1){
                position++
                reloadChapter(position)
            }else{
                Toast.makeText(this, "Đây là chapter cuối cùng", Toast.LENGTH_LONG).show()
            }
        }

        binding.prev.setOnClickListener {
            if (position > 0) {
                position--
                reloadChapter(position)
            } else {
                Toast.makeText(this, "Đây là chapter đầu tiên", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun PushData_ChapterBook(menu_id: String, chapter_id: String){

        var ds = mutableListOf<link_chapter>()

        compositeDisposable.add(api.getLinkChapter(menu_id,chapter_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { linkChapter_Model ->
                    if(linkChapter_Model.success){
                        ds = linkChapter_Model.result.toMutableList()

                        if(ds.get(0).content != ""){
                            binding.linerLayoutNovel.visibility = View.VISIBLE
                            binding.listChapter.visibility = View.GONE
                            binding.ListNovel.text = ds[0].content
                        }else{

                            val adapter = MySliderChapter_Adapter(this,ds)
                            binding.listChapter.adapter = adapter
                            binding.listChapter.layoutManager = GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false)
                        }

                    }else{
                        Toast.makeText(this, "Failed to fetch chapterName", Toast.LENGTH_LONG).show()
                    }
                }
            )
        )
    }

    fun reloadChapter(position: Int){
        var ds = mutableListOf<chapter>()
        val positions : Int = position

        compositeDisposable.add(api.getChapter(intent.getStringExtra("manga_id").toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { chapter_Model ->
                    if(chapter_Model.success) {
                        ds = chapter_Model.result.toMutableList()
                        binding.txtChapterName.text = ds[positions].title
                        PushData_ChapterBook(ds[positions].menu_id.toString(),ds[positions].id.toString())

                    }
                    else{
                        Toast.makeText(this, "Chưa có nội dung", Toast.LENGTH_LONG).show()
                    }
                },
                { error ->
                    Log.e("loi", "Error fetching categories: ${error.message}")
                    Toast.makeText(this, "Lỗi", Toast.LENGTH_LONG).show()
                }
            )
        )
    }

}