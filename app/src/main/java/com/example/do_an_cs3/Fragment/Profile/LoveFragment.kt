package com.example.do_an_cs3.Fragment.Profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.do_an_cs3.Model.book
import com.example.do_an_cs3.R
import com.example.do_an_cs3.activity.BookDetailActivity
import com.example.do_an_cs3.adapter.Profile.BookCollection_Adapter
import com.example.do_an_cs3.adapter.Recommended_Books_Adapter
import com.example.do_an_cs3.databinding.FragmentLoveBinding
import com.example.do_an_cs3.retrofit.ApiSelectMenu
import com.example.do_an_cs3.retrofit.RetrofitClient
import com.example.do_an_cs3.utils.Utils
import io.paperdb.Paper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

private lateinit var binding: FragmentLoveBinding
class LoveFragment : Fragment(R.layout.fragment_love) {

    lateinit var compositeDisposable: CompositeDisposable
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoveBinding.inflate(inflater, container, false)
        compositeDisposable = CompositeDisposable()
        Paper.init(requireContext())

        pushData()
        return binding.root
    }


    private fun pushData() {
        var ds = mutableListOf<book>()
        val apiGetCollection = RetrofitClient.getInstant(Utils.BASE_URL).create(ApiSelectMenu::class.java)
        compositeDisposable.add(apiGetCollection.getLikeCollection("1",Utils.user_current.id.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ book_Model ->
                if(book_Model.success){
                    ds = book_Model.result.take(8).toMutableList()
                    val adapterr = BookCollection_Adapter(ds)
                    binding.recyclebooklike.adapter = adapterr
                    binding.recyclebooklike.layoutManager = GridLayoutManager(context,1,GridLayoutManager.HORIZONTAL,false)

                    adapterr.setOnItemClickListener(object : BookCollection_Adapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent = Intent(context, BookDetailActivity::class.java)

                            intent.putExtra("id",ds[position].id.toString())
                            intent.putExtra("name",ds[position].name)
                            intent.putExtra("image",ds[position].image)
                            intent.putExtra("author",ds[position].author)
                            intent.putExtra("number_like", ds[position].number_like.toString())
                            intent.putExtra("description", ds[position].description)
                            intent.putExtra("menu_id",ds[position].menu_id.toString())
                            intent.putExtra("categoryBook", ds[position].categoryBook)

                            startActivity(intent)
                        }
                    })

                }else{
                    Toast.makeText(requireContext(),"Thất bại", Toast.LENGTH_LONG).show()
                }
            },
                { error ->
                    Log.e("loi", "Error fetching book love collection: ${error.message}")
                    Toast.makeText(requireContext(), "Failed to fetch book love collection", Toast.LENGTH_LONG).show()
                }
            )
        )
    }
}