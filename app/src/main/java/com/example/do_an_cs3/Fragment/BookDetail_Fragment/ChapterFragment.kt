package com.example.do_an_cs3.Fragment.BookDetail_Fragment

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.do_an_cs3.LoginActivity
import com.example.do_an_cs3.Model.chapter
import com.example.do_an_cs3.activity.Chapter.ChapterActivity
import com.example.do_an_cs3.adapter.ListBook.ListChapter_Adapter
import com.example.do_an_cs3.databinding.FragmentChapterBinding
import com.example.do_an_cs3.retrofit.ApiSelectMenu
import com.example.do_an_cs3.retrofit.RetrofitClient
import com.example.do_an_cs3.utils.Utils
import io.paperdb.Paper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var binding: FragmentChapterBinding

class ChapterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentChapterBinding.inflate(layoutInflater,container,false)

        compositeDisposable = CompositeDisposable()
        Paper.init(requireContext())

        PushDataChapter()
        return binding.root
    }

    private fun PushDataChapter() {
        var ds = mutableListOf<chapter>();

        val manga_id = arguments?.getString("id") ?: ""


        val apiGetChapter = RetrofitClient.getInstant(Utils.BASE_URL).create(ApiSelectMenu::class.java)

        compositeDisposable.add(apiGetChapter.getChapter(manga_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { chapter_Model ->
                    if(chapter_Model.success) {
                        ds = chapter_Model.result.toMutableList()
                        if(ds.size > 0){
                            val adapter = ListChapter_Adapter(requireContext(), ds)
                            binding.listChapter.adapter = adapter
                            binding.listChapter.layoutManager = GridLayoutManager(
                                requireContext(),
                                1,
                                GridLayoutManager.VERTICAL,
                                false
                            )

                            adapter.setOnItemClickListener(object : ListChapter_Adapter.onItemClickListener{
                                override fun onItemClick(position: Int) {

                                    if(Paper.book().read<String>("user") != null){

                                        val intent = Intent(context,ChapterActivity::class.java)
                                        intent.putExtra("chapter_id", ds[position].id.toString())
                                        intent.putExtra("title",ds[position].title)
                                        intent.putExtra("menu_id",ds[position].menu_id.toString())
                                        intent.putExtra("position", position.toString())
                                        intent.putExtra("size", ds.size.toString())
                                        intent.putExtra("manga_id",ds[position].manga_id.toString())

                                        startActivity(intent)
                                    }else{
                                        Toast.makeText(requireContext(), "Bạn chưa đăng nhập tài khoản", Toast.LENGTH_SHORT).show()
                                        val intent = Intent(requireContext(), LoginActivity::class.java);
                                        startActivity(intent)

                                    }
                                }
                            })

                        }else{
                            binding.listChapter.visibility = View.GONE
                            binding.emptyChapter.visibility = View.VISIBLE
                        }
                    }
                    else{
                        binding.listChapter.visibility = View.GONE
                        binding.emptyChapter.visibility = View.VISIBLE
                    }
                },
                { error ->
                    Log.e("loi", "Error fetching categories: ${error.message}")
                    Toast.makeText(requireContext(), "Failed to fetch categories", Toast.LENGTH_LONG).show()
                }
            )
        )
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChapterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}