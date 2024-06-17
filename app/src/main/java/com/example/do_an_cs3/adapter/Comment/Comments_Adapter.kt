package com.example.do_an_cs3.adapter.Comment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.do_an_cs3.Model.Comment.comment
import com.example.do_an_cs3.R

class Comments_Adapter(var context: Context, var ds : List<comment>):RecyclerView.Adapter<Comments_Adapter.MenuHolder>() {

    lateinit var mListener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    inner class MenuHolder(itemView: View,clickListener: onItemClickListener):RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_comment, parent, false)
        return MenuHolder(view,mListener)
    }

    override fun getItemCount(): Int {
        return ds.size
    }

    override fun onBindViewHolder(holder: MenuHolder, position: Int) {
        holder.itemView.apply {
            val username = findViewById<TextView>(R.id.UserName)
            val content = findViewById<TextView>(R.id.content)
            val dateComment = findViewById<TextView>(R.id.dateComment)
            val avatar = findViewById<ImageView>(R.id.avatar)


            username.text = ds[position].name
            content.text = ds[position].comment
            dateComment.text = ds[position].created_at

            if(ds[position].avatar != ""){
                avatar.setImageURI(ds[position].avatar.trim().toUri())
            }
        }


    }
}