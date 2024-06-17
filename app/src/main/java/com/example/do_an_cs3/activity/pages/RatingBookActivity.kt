package com.example.do_an_cs3.activity.pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.do_an_cs3.Fragment.BookDetail_Fragment.ChapterFragment
import com.example.do_an_cs3.Fragment.BookDetail_Fragment.DetailFragment
import com.example.do_an_cs3.Model.book
import com.example.do_an_cs3.R
import com.example.do_an_cs3.activity.BookDetailActivity
import com.example.do_an_cs3.adapter.ListBook.ListBook_Adapter
import com.example.do_an_cs3.databinding.ActivityRatingBookBinding
import com.example.do_an_cs3.retrofit.ApiSelectMenu
import com.example.do_an_cs3.retrofit.RetrofitClient
import com.example.do_an_cs3.utils.Utils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

private lateinit var binding: ActivityRatingBookBinding
class RatingBookActivity : AppCompatActivity() {

    lateinit var compositeDisposable: CompositeDisposable
    lateinit var api : ApiSelectMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRatingBookBinding.inflate(layoutInflater)
        setContentView(binding.root)
        compositeDisposable = CompositeDisposable()
        api= RetrofitClient.getInstant(Utils.BASE_URL).create(ApiSelectMenu::class.java)

        ActionToorBar()

        val action_name = intent.getStringExtra("action_name")

        when(action_name){
            "Yêu Thích" ->{
                dataRatingLove()
            }
            "Lượt Đọc" -> {
                binding.viewWatch.visibility= View.VISIBLE
                binding.viewLove.visibility = View.GONE

                dataRatting_Reads()
            }
        }

        binding.btnLove.setOnClickListener {

            binding.viewWatch.visibility= View.GONE
            binding.viewLove.visibility = View.VISIBLE
            dataRatingLove()
        }

        binding.btnWatch.setOnClickListener {
            binding.viewWatch.visibility= View.VISIBLE
            binding.viewLove.visibility = View.GONE

            dataRatting_Reads()
        }

    }

    private fun dataRatting_Reads() {

        var ds = mutableListOf<book>()

        compositeDisposable.add(
            api.getBookHot("Lượt Đọc")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ book_Model ->
                    if (book_Model.success) {
                        ds  = book_Model.result.toMutableList()
                        val adapter = ListBook_Adapter(this,ds,false)
                        binding.recycleRating.adapter = adapter

                        binding.recycleRating.layoutManager = GridLayoutManager(
                            this,
                            1,
                            GridLayoutManager.VERTICAL,
                            false
                        )

                        adapter.notifyDataSetChanged()

                        adapter.setOnItemClickListener(object : ListBook_Adapter.onItemClickListener {
                            override fun onItemClick(position: Int) {
                                loadData(ArrayList(ds), position,BookDetailActivity::class.java)
                            }
                        })
                    }
                })
        )
    }

    private fun dataRatingLove() {
        var ds = mutableListOf<book>()

        compositeDisposable.add(api.getBookHot("Yêu Thích")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    book_Model ->
                if(book_Model.success ){
                    ds  = book_Model.result.toMutableList()
                    val adapter = ListBook_Adapter(this,ds,true)
                    binding.recycleRating.adapter = adapter

                    binding.recycleRating.layoutManager = GridLayoutManager(
                        this,
                        1,
                        GridLayoutManager.VERTICAL,
                        false
                    )

                    adapter.notifyDataSetChanged()

                    adapter.setOnItemClickListener(object : ListBook_Adapter.onItemClickListener {
                        override fun onItemClick(position: Int) {
                            loadData(ArrayList(ds), position,BookDetailActivity::class.java)
                        }
                    })
                }else{
                    Toast.makeText(this,"Thất bại",Toast.LENGTH_LONG).show()
                }
            },
                { error ->
                    Log.e("loi", "Error fetching rating book: ${error.message}")
                    Toast.makeText(this, "Failed to fetch rating book", Toast.LENGTH_LONG).show()
                }
            )
        )
    }

    private fun <T> loadData(ds: ArrayList<book>, position: Int,activityClass: Class<T>){

        val intent = Intent(this@RatingBookActivity,activityClass)

        intent.putExtra("id",ds[position].id.toString())
        intent.putExtra("name",ds[position].name)
        intent.putExtra("image",ds[position].image)
        intent.putExtra("author",ds[position].author)
        intent.putExtra("number_like", ds[position].number_like.toString())
        intent.putExtra("description", ds[position].description)
        intent.putExtra("menu_id",ds[position].menu_id.toString())

        startActivity(intent)
    }

    private fun ActionToorBar() {
        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.setNavigationOnClickListener(View.OnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()
            onBackPressed()
        })
    }
}