package com.example.do_an_cs3.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.do_an_cs3.Fragment.BookDetail_Fragment.ChapterFragment
import com.example.do_an_cs3.Fragment.BookDetail_Fragment.DetailFragment
import com.example.do_an_cs3.MainActivity
import com.example.do_an_cs3.R
import com.example.do_an_cs3.databinding.ActivityBookDetailBinding
import com.example.do_an_cs3.utils.Utils
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

private lateinit var binding: ActivityBookDetailBinding
class BookDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ActionToorBar()
        if(savedInstanceState == null){

            val detailFragment = DetailFragment()
            val bundle = Bundle()

            bundle.putString("id", intent.getStringExtra("id"))
            bundle.putString("number_like",intent.getStringExtra("number_like"))
            bundle.putString("description",intent.getStringExtra("description"))
            bundle.putString("name",intent.getStringExtra("name"))

            detailFragment.arguments = bundle // arguments truyền dữ liệu từ activity qua fragment

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, detailFragment).commit()
        }

        PushData()

        binding.btnDetail.setOnClickListener {

            binding.viewChapter.visibility= View.GONE
            binding.viewDetail.visibility = View.VISIBLE

            val detailFragment = DetailFragment()
            val bundle = Bundle()
            bundle.putString("id", intent.getStringExtra("id"))
            bundle.putString("number_like",intent.getStringExtra("number_like"))
            bundle.putString("description",intent.getStringExtra("description"))
            bundle.putString("name",intent.getStringExtra("name"))

            detailFragment.arguments = bundle
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container,detailFragment).commit()
        }

        binding.btnChapter.setOnClickListener {
            binding.viewChapter.visibility = View.VISIBLE
            binding.viewDetail.visibility = View.GONE

            val chapterFragment = ChapterFragment()
            val bundle = Bundle()
            bundle.putString("id", intent.getStringExtra("id"))
            bundle.putString("name", intent.getStringExtra("name"))
            bundle.putString("menu_id",intent.getStringExtra("menu_id"))
            chapterFragment.arguments = bundle

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container,chapterFragment).commit()
        }

    }

    @SuppressLint("SetTextI18n")
    private fun PushData() {
        binding.nameBook.text = intent.getStringExtra("name")
        binding.authorBook.text =  "Tác giả: " +intent.getStringExtra("author")
        binding.Category.text = intent.getStringExtra("categoryBook")

        val imageUrl =  Utils.API + intent.getStringExtra("image") // Thay thế bằng đường dẫn URL bạn nhận được từ XAMPP
        Picasso.get().load(imageUrl).into(binding.imgBookDetail1, object : Callback {
            override fun onSuccess() {
                // Ảnh đã được tải thành công
            }

            override fun onError(e: Exception?) {
                // Đã xảy ra lỗi khi tải ảnh
                Log.e("Picasso", "Error loading image: ${e?.message}")
            }
        })

        Picasso.get().load(imageUrl).into(binding.imgBookDetail, object : Callback {
            override fun onSuccess() {
                // Ảnh đã được tải thành công
            }

            override fun onError(e: Exception?) {
                // Đã xảy ra lỗi khi tải ảnh
                Log.e("Picasso", "Error loading image: ${e?.message}")
            }
        })

    }

    private fun ActionToorBar() {
        setSupportActionBar(binding.toolbarDetail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbarDetail.setNavigationOnClickListener(View.OnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()
            onBackPressed()
        })
    }
}