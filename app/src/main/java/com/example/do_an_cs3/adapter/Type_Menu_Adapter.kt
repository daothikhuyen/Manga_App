package com.example.do_an_cs3.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.do_an_cs3.Model.type_menu
import com.example.do_an_cs3.R
import com.example.do_an_cs3.utils.Utils
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class Type_Menu_Adapter(val context: Context, var ds:List<type_menu>):RecyclerView.Adapter<Type_Menu_Adapter.MenuHolder>(){

    lateinit var mListener : onItemClickListener

    interface  onItemClickListener {
        fun onItemclick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    inner class MenuHolder(itemView : View,clickListener: onItemClickListener) :RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener {
                clickListener.onItemclick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sanpham,parent,false)

        return MenuHolder(view,mListener)
    }

    override fun getItemCount(): Int {
        return ds.size
    }

    override fun onBindViewHolder(holder: MenuHolder, position: Int) {
        holder.itemView.apply {
            val id = findViewById<TextView>(R.id.type_id)
            val images = findViewById<ImageView>(R.id.type_product_img)
            val nameMenu = findViewById<TextView>(R.id.text_type_product)

            id.text = ds[position].id
            nameMenu.text = ds[position].name

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
        }
    }
}