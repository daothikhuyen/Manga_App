package com.example.do_an_cs3.adapter.ListBook

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.do_an_cs3.Model.book
import com.example.do_an_cs3.R
import com.example.do_an_cs3.utils.Utils
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ListBook_Adapter (val context: Context, var ds:List<book>,private  val  icon_rating: Boolean): RecyclerView.Adapter<ListBook_Adapter.MenuHolder>(){

    lateinit var mListener: onItemClickListener

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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_book, parent, false)
        return MenuHolder(view, mListener)
    }

    override fun getItemCount(): Int {
        return ds.size
    }

    override fun onBindViewHolder(holder: MenuHolder, position: Int) {
        holder.itemView.apply {
            val id = findViewById<TextView>(R.id.id)
            val images = findViewById<ImageView>(R.id.imgBook)
            val name = findViewById<TextView>(R.id.nameBook)
            val description = findViewById<TextView>(R.id.descriptionBook)
            val rank_image = findViewById<ImageView>(R.id.rank_image)
            val rank_number = findViewById<TextView>(R.id.rank_number)
            val number_like = findViewById<TextView>(R.id.number_likes)
            val number_reads = findViewById<TextView>(R.id.number_reads);
            val rattingLove = findViewById<LinearLayout>(R.id.rattingLove)
            val rattingWatch = findViewById<LinearLayout>(R.id.rattingWatch)

            if(icon_rating){
                rattingLove.visibility = View.VISIBLE
                rattingWatch.visibility = View.GONE
                if(position == 0){
                    rank_image.setImageResource(R.drawable.top1)
                }
                else{
                    if(position == 1){
                        rank_image.setImageResource(R.drawable.top2)
                    }else{
                        if(position < 100){
                            rank_image.visibility = View.GONE
                            rank_number.visibility = View.VISIBLE
                            rank_number.text = (position + 1).toString()
                        }else{
                            rank_image.visibility = View.GONE
                            rank_number.visibility = View.GONE
                        }
                    }
                }
            }else{
                rank_image.visibility = View.GONE
                rattingLove.visibility = View.GONE
                rattingWatch.visibility = View.VISIBLE

                number_reads.text = ds[position].number_reads.toString()
            }

            id.text = ds[position].id.toString()
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

            name.text = ds[position].name
            description.text = ds[position].description
            number_like.text = ds[position].number_like.toString()


        }
    }
}