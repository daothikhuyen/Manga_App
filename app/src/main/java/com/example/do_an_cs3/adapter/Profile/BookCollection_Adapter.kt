package com.example.do_an_cs3.adapter.Profile

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.do_an_cs3.Model.book
import com.example.do_an_cs3.R
import com.example.do_an_cs3.utils.Utils
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class BookCollection_Adapter(var ds:List<book>): RecyclerView.Adapter<BookCollection_Adapter.viewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    inner class viewHolder(itemView : View,clickListener : onItemClickListener) : RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_like, parent, false)
        return viewHolder(view,mListener)
    }

    override fun getItemCount(): Int {
       return ds.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.itemView.apply {
            val name = findViewById<TextView>(R.id.nameBookLike)
            val images = findViewById<ImageView>(R.id.image_book)

            name.text = ds[position].name

            val imageUrl = Utils.API + ds[position].image
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