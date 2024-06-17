package com.example.do_an_cs3.Fragment

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
import com.example.do_an_cs3.Model.book
import com.example.do_an_cs3.Model.book_Model
import com.example.do_an_cs3.activity.BookDetailActivity
import com.example.do_an_cs3.adapter.ListBook.ListBook_Adapter
import com.example.do_an_cs3.databinding.FragmentCategoryBookBinding
import com.example.do_an_cs3.retrofit.ApiSelectMenu
import com.example.do_an_cs3.retrofit.RetrofitClient
import com.example.do_an_cs3.utils.Utils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CategoryBookFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentCategoryBookBinding
    lateinit var compositeDisposable: CompositeDisposable
    lateinit var api: ApiSelectMenu

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
        binding = FragmentCategoryBookBinding.inflate(layoutInflater,container,false)

        compositeDisposable = CompositeDisposable()
        api = RetrofitClient.getInstant(Utils.BASE_URL).create(ApiSelectMenu::class.java)

        if(isConnected(requireContext())){
            pushDataBookCategory()
        }
        return binding.root
    }

    private fun pushDataBookCategory() {
        var list = mutableListOf<book>()
        val getBook: Observable<book_Model>

        val category_id = arguments?.getString("id")
        val name_menu = arguments?.getString("name_menu")?: ""


        getBook = if(category_id ==  null){
            api.getBook(name_menu)
        }else{
            api.getSearchManga(category_id)
        }

        compositeDisposable.add( getBook
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { book_Model ->
                    if (book_Model.success) {

                        list = book_Model.result.toMutableList()

//                        val adapter = ListBook_Adapter(requireContext(),list.toMutableList())
                        val adapter = ListBook_Adapter(requireContext(),list.toMutableList(),false)
                        binding.listBook.adapter = adapter
                        binding.listBook.layoutManager = GridLayoutManager(requireContext(),1,
                            GridLayoutManager.VERTICAL,false)

                        adapter.setOnItemClickListener(object : ListBook_Adapter.onItemClickListener{
                            override fun onItemClick(position: Int) {
                                loadData(ArrayList(list), position,BookDetailActivity::class.java)
                            }
                        })
                        adapter.notifyDataSetChanged();
                    } else {
                        binding.emptyBook.visibility = View.VISIBLE
                        binding.listBook.visibility = View.GONE
                        Toast.makeText(requireContext(),"Thất bại", Toast.LENGTH_LONG).show()
                    }

                },
                { error ->
                    Log.e("loi", "Error fetching categories: ${error.message}")
                    Toast.makeText(requireContext(), "Failed to fetch categories", Toast.LENGTH_LONG).show()
                }
            )
        )
    }

    private fun <T> loadData(ds: ArrayList<book>, position: Int,activityClass: Class<T>){
        val intent = Intent(context,activityClass)

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

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CategoryBookFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun isConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)

        return networkCapabilities?.run {
            hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
        } ?: false
    }
}
