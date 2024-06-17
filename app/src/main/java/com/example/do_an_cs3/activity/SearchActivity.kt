package com.example.do_an_cs3.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.do_an_cs3.Model.book
import com.example.do_an_cs3.adapter.ListBook.ListBook_Adapter
import com.example.do_an_cs3.databinding.ActivitySearchBinding
import com.example.do_an_cs3.retrofit.ApiSelectMenu
import com.example.do_an_cs3.retrofit.RetrofitClient
import com.example.do_an_cs3.utils.Utils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

private lateinit var binding: ActivitySearchBinding
class SearchActivity : AppCompatActivity() {

    lateinit var compositeDisposable: CompositeDisposable
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySearchBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        compositeDisposable = CompositeDisposable()
        ActionBar()
        initView()

    }

    private fun initView() {
        val layoutManager : LinearLayoutManager = LinearLayoutManager(this)
        binding.recyclerSearch.setHasFixedSize(true)
        binding.recyclerSearch.layoutManager = layoutManager

        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                getDataSearch()
            }

        })
    }

    private fun getDataSearch() {
        var ds = mutableListOf<book>()
        val str_search : String = binding.edtSearch.text.toString().trim()

        val apiSearch = RetrofitClient.getInstant(Utils.BASE_URL).create(ApiSelectMenu::class.java)
        compositeDisposable.add(apiSearch.getSearch(str_search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ book_hot_Model ->
                if(book_hot_Model.success){

                    ds = book_hot_Model.result.toMutableList()
                    val adapter =  ListBook_Adapter(this,ds,false)
                    binding.recyclerSearch.adapter = adapter
                    binding.recyclerSearch.layoutManager = GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false)

                    adapter.setOnItemClickListener(object : ListBook_Adapter.onItemClickListener {
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@SearchActivity,BookDetailActivity::class.java)

                            intent.putExtra("id",ds[position].id.toString())
                            intent.putExtra("name",ds[position].name)
                            intent.putExtra("image",ds[position].image)
                            intent.putExtra("author",ds[position].author)
                            intent.putExtra("number_like", ds[position].number_like.toString())
                            intent.putExtra("description", ds[position].description)
                            intent.putExtra("menu_id",ds[position].menu_id.toString())
                            intent.putExtra("categoryBook", ds[position].categoryBook)
                            startActivity(intent)
                        }
                    })

                }else{
                    Toast.makeText(this,"Thất bại", Toast.LENGTH_LONG).show()
                }
            },
                { error ->
                    Log.e("loi", "Error fetching categories: ${error.message}")
                    Toast.makeText(this, "Failed to fetch categories", Toast.LENGTH_LONG).show()
                }
            )
        )
    }

    private fun ActionBar() {
        binding.btnCancel.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
            onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}