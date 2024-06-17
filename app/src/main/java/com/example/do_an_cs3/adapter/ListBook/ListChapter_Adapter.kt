package com.example.do_an_cs3.adapter.ListBook

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.do_an_cs3.Model.chapter
import com.example.do_an_cs3.R

class ListChapter_Adapter(context: Context, var  ds: MutableList<chapter>):RecyclerView.Adapter<ListChapter_Adapter.MenuHold>() {

    private lateinit var mListener: onItemClickListener;

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    inner class MenuHold(itemView: View,clickListener: onItemClickListener):RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_chapter,parent,false)
        return MenuHold(view,mListener)
    }

    override fun getItemCount(): Int {
        return ds.size
    }

    override fun onBindViewHolder(holder: MenuHold, position: Int) {
        holder.itemView.apply {
            val title = findViewById<TextView>(R.id.titleChapter)
            title.text = ds[position].title
        }
    }
}