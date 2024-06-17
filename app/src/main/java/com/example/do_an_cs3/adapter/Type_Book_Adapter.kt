package com.example.do_an_cs3.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.do_an_cs3.Model.type_book
import com.example.do_an_cs3.R
import com.example.do_an_cs3.utils.Utils
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class Type_Book_Adapter(val context: Context, var ds: MutableList<type_book>): RecyclerView.Adapter<Type_Book_Adapter.MenuHolder>(){

    inner class MenuHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_type_book,parent,false)

        return MenuHolder(view)
    }

    override fun getItemCount(): Int {
        return ds.size
    }

    override fun onBindViewHolder(holder: MenuHolder, position: Int) {
        holder.itemView.apply {
            val id = findViewById<TextView>(R.id.id_typeBook)
            val images = findViewById<ImageView>(R.id.image_TypeBook)

            id.text = ds[position].id.toString()

            val imageUrl =  ds[position].image // Thay thế bằng đường dẫn URL bạn nhận được từ XAMPP
            Picasso.get().load(imageUrl).into(images, object : Callback {
                override fun onSuccess() {
                    // Ảnh đã được tải thành công
                }

                override fun onError(e: Exception?) {
                    // Đã xảy ra lỗi khi tải ảnh
                    Log.e("Picasso", "Error loading image: ${e?.message}")
                }
            })
        }
    }
}