package com.example.do_an_cs3.activity

import android.content.Intent
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.do_an_cs3.Fragment.CategoryBookFragment
import com.example.do_an_cs3.MainActivity
import com.example.do_an_cs3.Model.type_menu
import com.example.do_an_cs3.R
import com.example.do_an_cs3.adapter.ListBook.ListBook_Menu_Adapter
import com.example.do_an_cs3.databinding.ActivityListbookBinding
import com.example.do_an_cs3.retrofit.ApiSelectMenu
import com.example.do_an_cs3.retrofit.RetrofitClient
import com.example.do_an_cs3.utils.Utils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

private lateinit var binding: ActivityListbookBinding
class ListBookActivity : AppCompatActivity() {

    lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListbookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        compositeDisposable = CompositeDisposable()

        ActionToorBar()
        CategoryBook()

        if(savedInstanceState == null){
            val categoryBookFragment = CategoryBookFragment()
            val bundle = Bundle()
            bundle.putString("name_menu",intent.getStringExtra("name_menu"))

            categoryBookFragment.arguments = bundle
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, categoryBookFragment).commit()
        }
        binding.toolbarListBook.title = intent.getStringExtra("name_menu") ?: "Tủ truyện"

    }

    private fun CategoryBook() {
        var ds = mutableListOf<type_menu>()
        val apiShowMenu = RetrofitClient.getInstant(Utils.BASE_URL).create(ApiSelectMenu::class.java)

        val name_menu = intent.getStringExtra("name_menu") ?: ""

        compositeDisposable.add(apiShowMenu.getCategory(name_menu)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { type_menu_Model ->
                    if (type_menu_Model.success) {

                        ds = type_menu_Model.result.toMutableList()
                        val adapter = ListBook_Menu_Adapter(this,ds)
                        binding.listMenuBook.adapter = adapter
                        binding.listMenuBook.layoutManager = GridLayoutManager(
                            this,
                            1,
                            GridLayoutManager.HORIZONTAL,
                            false
                        )

                        adapter.setOnItemClickListener(object : ListBook_Menu_Adapter.onItemClickListener{

                            override fun onItemClick(position: Int) {

                                val categoryBookFragment = CategoryBookFragment()
                                val bundle = Bundle()
                                bundle.putString("id",ds[position].id)

                                categoryBookFragment.arguments = bundle

                                supportFragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container,categoryBookFragment).commit()
                            }

                        })

                    } else {
                        Toast.makeText(this,"Thất bại",Toast.LENGTH_LONG).show()
                    }

                },
                { error ->
                    Log.e("loi", "Error fetching categories: ${error.message}")
                    Toast.makeText(this, "Failed to fetch categories", Toast.LENGTH_LONG).show()
                }
            )
        )
    }

    private fun ActionToorBar() {
        setSupportActionBar(binding.toolbarListBook)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbarListBook.setNavigationOnClickListener(View.OnClickListener {
//            val intent = Intent(this,MainActivity::class.java)
//            startActivity(intent)
//            finish()
            onBackPressed()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toobar_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.actionsearch -> {
                val intent = Intent(this,SearchActivity::class.java)
                startActivity(intent)
            }
            else->{
                Toast.makeText(this,"Thất bại",Toast.LENGTH_LONG).show()
            }
        }
        return false
    }
}