package com.example.do_an_cs3.adapter.ListBook

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.example.do_an_cs3.Model.link_chapter
import com.example.do_an_cs3.R
import com.example.do_an_cs3.utils.Utils
import com.squareup.picasso.Picasso
import dalvik.system.ZipPathValidator.Callback
import java.lang.Exception
import java.util.Objects

class MySliderChapter_Adapter(private var context: Context, private var linkList: List<link_chapter>):RecyclerView.Adapter<MySliderChapter_Adapter.MenuHold>(){

    inner class MenuHold(itemview: View):RecyclerView.ViewHolder(itemview){
        val images = itemview.findViewById<ImageView>(R.id.imageComic)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHold {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.viewpager_item,parent,false)
        return MenuHold(view)
    }

    override fun getItemCount(): Int {
        return linkList.size
    }

    override fun onBindViewHolder(holder: MenuHold, position: Int) {

        val imageUrl =  Utils.API + linkList[position].image
        if(imageUrl != null){
            Picasso.get().load(imageUrl).into(holder.images, object : com.squareup.picasso.Callback {
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