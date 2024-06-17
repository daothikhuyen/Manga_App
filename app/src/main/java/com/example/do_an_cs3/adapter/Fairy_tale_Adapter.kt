package com.example.do_an_cs3.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.do_an_cs3.Model.book
import com.example.do_an_cs3.Model.type_book
import com.example.do_an_cs3.R
import com.example.do_an_cs3.utils.Utils
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class Fairy_tale_Adapter  (val context: Context, var ds:List<book>): RecyclerView.Adapter<Fairy_tale_Adapter.MenuHolder>(){

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    inner class MenuHolder(itemView : View,clickListener: onItemClickListener) :RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book_02, parent, false)
        return MenuHolder(view,mListener)
    }

    override fun getItemCount(): Int {
        return ds.size
    }

    override fun onBindViewHolder(holder: MenuHolder, position: Int) {
        holder.itemView.apply {
            val id = findViewById<TextView>(R.id.id_book2)
            val images = findViewById<ImageView>(R.id.image_book2)
            val name = findViewById<TextView>(R.id.nameBoook2)

            id.text = ds[position].id.toString()

            val imageUrl = Utils.API + ds[position].image // Thay thế bằng đường dẫn URL bạn nhận được từ XAMPP
            Picasso.get().load(imageUrl).into(images, object : Callback {
                override fun onSuccess() {
                    // Ảnh đã được tải thành công
                }

                override fun onError(e: Exception?) {
                    // Đã xảy ra lỗi khi tải ảnh
                    Log.e("Picasso", "Error loading image: ${e?.message}")
                }
            })

            name.text = ds[position].name

        }
    }


}