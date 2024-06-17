package com.example.do_an_cs3.adapter.ListBook

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.do_an_cs3.Model.type_menu
import com.example.do_an_cs3.R

class ListBook_Menu_Adapter(val context: Context, var ds: MutableList<type_menu>):RecyclerView.Adapter<ListBook_Menu_Adapter.MenuHolder>() {

    private lateinit var mListener : onItemClickListener
    private var selectItem = -1

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    inner class MenuHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val id = itemView.findViewById<TextView>(R.id.type_id)
        val name = itemView.findViewById<TextView>(R.id.text_type_product)
        val image = itemView.findViewById<ImageView>(R.id.type_product_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sanpham,parent,false)
        return MenuHolder(view)
    }

    override fun getItemCount(): Int {
        return ds.size
    }

    fun uploadSelectItem(position: Int){
        selectItem = position
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged", "ResourceAsColor")
    override fun onBindViewHolder(holder: MenuHolder, position: Int) {
        val type_menu = ds[position]
        holder.image.visibility = View.GONE
        holder.name.textSize = 16f
//        val left: Int = 0
        holder.name.setPadding(5,1,5,1)
        holder.name.layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT

        holder.id.text = ds[position].id
        holder.name.text = ds[position].name

        if(position == selectItem){
            holder.name.setTextColor(R.color.lavender)
            holder.name.setTypeface(null, Typeface.BOLD)
        }else{
            holder.name.setTextColor(Color.BLACK)
            holder.name.setTypeface(null, Typeface.NORMAL)
        }

        holder.itemView.setOnClickListener {
            uploadSelectItem(position)
            notifyDataSetChanged() // upload lại recycleView
            mListener.onItemClick(position)
        }

    }
}