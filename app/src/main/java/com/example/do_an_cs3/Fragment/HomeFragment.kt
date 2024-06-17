    package com.example.do_an_cs3.Fragment

    import android.annotation.SuppressLint
    import android.content.Context
    import android.content.Intent
    import android.net.ConnectivityManager
    import android.net.NetworkCapabilities
    import android.os.Bundle
    import android.util.Log
    import androidx.fragment.app.Fragment
    import android.view.LayoutInflater
    import android.view.Menu
    import android.view.MenuInflater
    import android.view.MenuItem
    import android.view.View
    import android.view.ViewGroup
    import android.view.animation.Animation
    import android.view.animation.AnimationUtils
    import android.widget.ImageView
    import android.widget.RelativeLayout
    import android.widget.Toast
    import android.widget.ViewFlipper
    import androidx.appcompat.app.AppCompatActivity
    import androidx.core.view.MenuProvider
    import androidx.recyclerview.widget.GridLayoutManager
    import androidx.recyclerview.widget.StaggeredGridLayoutManager.*
    import com.example.do_an_cs3.Model.book
    import com.example.do_an_cs3.Model.type_book
    import com.example.do_an_cs3.Model.type_menu
    import com.example.do_an_cs3.R
    import com.example.do_an_cs3.activity.BookDetailActivity
    import com.example.do_an_cs3.activity.ListBookActivity
    import com.example.do_an_cs3.activity.SearchActivity
    import com.example.do_an_cs3.activity.pages.RatingBookActivity
    import com.example.do_an_cs3.adapter.Recommended_Books_Adapter
    import com.example.do_an_cs3.adapter.Fairy_tale_Adapter
    import com.example.do_an_cs3.adapter.ListBook.ListBook_Adapter
    import com.example.do_an_cs3.adapter.Type_Book_Adapter
    import com.example.do_an_cs3.adapter.Type_Menu_Adapter
    import com.example.do_an_cs3.databinding.FragmentHomeBinding
    import com.example.do_an_cs3.retrofit.ApiSelectMenu
    import com.example.do_an_cs3.retrofit.RetrofitClient
    import com.example.do_an_cs3.utils.Utils
    import com.squareup.picasso.Callback
    import com.squareup.picasso.Picasso
    import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
    import io.reactivex.rxjava3.disposables.CompositeDisposable
    import io.reactivex.rxjava3.schedulers.Schedulers

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private const val ARG_PARAM1 = "param1"
    private const val ARG_PARAM2 = "param2"
    @SuppressLint("StaticFieldLeak")
    private lateinit var binding: FragmentHomeBinding

    class HomeFragment : Fragment(), MenuProvider {
        // TODO: Rename and change types of parameters
        private var param1: String? = null
        private var param2: String? = null

        lateinit var viewFilper : ViewFlipper
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

        ): View {
            binding = FragmentHomeBinding.inflate(inflater,container,false)
            (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

            compositeDisposable = CompositeDisposable()
            api = RetrofitClient.getInstant(Utils.BASE_URL).create(ApiSelectMenu::class.java)

            // giao tiếp với hoạt động (Activity) từ một Fragment, có thể là để thêm một nguồn dữ liệu cho menu
            // hoặc thực hiện các tác vụ khác liên quan đến việc quản lý menu trong ứng dụng

            if(isConnected(requireContext())){
                Toast.makeText(requireContext(),"Kết Nối Thành Công 😘", Toast.LENGTH_SHORT).show()

                ActionViewFilper()
                ListViewMenu()
                BookRecommended()
                BookHot()
                TypeBook()
                Fairy_tale()

                binding.btnXemThemLove.setOnClickListener {
                    val intent = Intent(requireContext(),RatingBookActivity::class.java)
                    intent.putExtra("action_name", "Yêu Thích")
                    startActivity(intent)
                }

                binding.btnXemThemReads.setOnClickListener {
                    val intent = Intent(requireContext(),RatingBookActivity::class.java)
                    intent.putExtra("action_name", "Lượt Đọc")
                    startActivity(intent)
                }

            }else{
                Toast.makeText(requireContext(),"ko internet", Toast.LENGTH_SHORT).show()
            }
            return binding.root

        }

        private fun Fairy_tale() {
            var ds = mutableListOf<book>()
            var index = 0;

            compositeDisposable.add(api.getBook("")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { book_Model ->
                    if (book_Model.success) {
                        ds = book_Model.result.take(9).toMutableList()

                        val adapter = Fairy_tale_Adapter(requireContext(), ds)
                        binding.listBookFairyTable.adapter = adapter
                        binding.listBookFairyTable.layoutManager = GridLayoutManager(
                            requireContext(),
                            3,
                            GridLayoutManager.VERTICAL,
                            false
                        )

                        adapter.setOnItemClickListener(object : Fairy_tale_Adapter.onItemClickListener{
                            override fun onItemClick(position: Int) {
                                loadData(ArrayList(ds), position,BookDetailActivity::class.java)
                            }
                        })
                        adapter.notifyDataSetChanged()
                    }
                }
            )
        }

        private fun TypeBook() {
            val ds = mutableListOf<type_book>()
            ds.add(type_book(1,R.drawable.nu_cuong,"hi"))
            ds.add(type_book(1,R.drawable.tong_tai,"hi"))
            ds.add(type_book(1,R.drawable.lich_su ,"hi"))
            ds.add(type_book(1,R.drawable.co_tich ,"hi"))

            val adapter = Type_Book_Adapter(requireContext(),ds)
            binding.listTypeBook.adapter = adapter
            binding.listTypeBook.layoutManager = GridLayoutManager(requireContext(),2,GridLayoutManager.VERTICAL,false)
        }

        // Yêu thích theo lượt tim
        private fun BookHot() {
            var ds = mutableListOf<book>()
            var list = mutableListOf<book>()

            compositeDisposable.add(api.getBookHot("Yêu Thích")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ book_Model ->
                    if(book_Model.success){
                        ds  = book_Model.result.take(3).toMutableList()

                        val adapter = ListBook_Adapter(requireContext(),ds,true)
                        binding.listBookHot.adapter = adapter

                        binding.listBookHot.layoutManager = GridLayoutManager(
                            requireContext(),
                            1,
                            GridLayoutManager.VERTICAL,
                            false
                        )
                        adapter.setOnItemClickListener(object : ListBook_Adapter.onItemClickListener{
                            override fun onItemClick(position: Int) {
                                loadData(ArrayList(ds), position,BookDetailActivity::class.java)
                                adapter.notifyDataSetChanged()
                            }
                        })
                        adapter.notifyDataSetChanged()
                    }
                },
                    { error ->
                        Log.e("loi", "Error fetching categories: ${error.message}")
                        Toast.makeText(requireContext(), "Failed to fetch categories", Toast.LENGTH_LONG).show()
                    }
                )
            )
        }

        // đề xuất các truyện có lượt đọc lớn
        private fun BookRecommended() {
            var ds = mutableListOf<book>()
            var index = 0;

            compositeDisposable.add(api.getBookHot("Lượt Đọc")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // xác định luồng hoạt động
                .subscribe(
                    { book_Model ->
                        if(book_Model.success ){
                            ds = book_Model.result.take(10).toMutableList()
                            val adapter = Recommended_Books_Adapter(requireContext(), ds)
                            binding.listViewRecom.adapter = adapter
                            binding.listViewRecom.layoutManager = GridLayoutManager(
                                requireContext(),
                                1,
                                GridLayoutManager.HORIZONTAL,
                                false
                            )
                            adapter.setOnItemClickListener(object : Recommended_Books_Adapter.onItemClickListener{
                                override fun onItemClick(position: Int) {
                                    loadData(ArrayList(ds), position,BookDetailActivity::class.java)
                                    adapter.notifyDataSetChanged()
                                }
                            })

                        }else{
                            Toast.makeText(requireContext(),"Thất bại",Toast.LENGTH_LONG).show()
                        }
                        index ++;
                    },
                    { error ->
                        Log.e("loi", "Error fetching categories: ${error.message}")
                        Toast.makeText(requireContext(), "Failed to fetch categories", Toast.LENGTH_LONG).show()
                    }
                )
            )

        }

        private fun <T> loadData(ds: ArrayList<book>, position: Int,activityClass: Class<T>){

            updateNumber_Reads(ds[position].id)
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

        private fun updateNumber_Reads(id_manga: Int){
            compositeDisposable.add(api.uploadNumber_Reads(id_manga)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // xác định luồng hoạt động
                .subscribe(
                    { book_Model ->
                        if (!book_Model.success){
                            Toast.makeText(requireContext(), "Update lượt đọc thất bại", Toast.LENGTH_SHORT).show()
                        }
                    }
                    ,
                    { error ->
                        // Handle error case
                        Log.e("loi", "Error updating read count: ${error.message}")
                        Toast.makeText(requireContext(), "Error updating read count", Toast.LENGTH_LONG).show()
                    }
                )
            )

        }

        private fun ListViewMenu() {
            var list = mutableListOf<type_menu>()

            compositeDisposable.add(api.getMenu()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { type_menu_Model ->
                        if (type_menu_Model.success) {
                            list = type_menu_Model.result.toMutableList()
                            val adapter = Type_Menu_Adapter(requireContext(), list)
                            binding.listmenu.adapter = adapter
                            binding.listmenu.layoutManager = GridLayoutManager(requireContext(),1,GridLayoutManager.HORIZONTAL,false)

                            adapter.setOnItemClickListener(object : Type_Menu_Adapter.onItemClickListener{
                                override fun onItemclick(position: Int) {
                                    when(list[position].name){
                                        "Truyện tranh" -> {
                                            val intent = Intent(requireContext(),ListBookActivity::class.java)
                                            intent.putExtra("name_menu", "Truyện tranh")
                                            startActivity(intent)
                                        }
                                        "Tiểu thuyết" -> {
                                            val intent = Intent(requireContext(),ListBookActivity::class.java)
                                            intent.putExtra("name_menu", "Tiểu Thuyết")
                                            startActivity(intent)
                                        }
                                        "Phân loại" -> startActivity(Intent(requireContext(), ListBookActivity::class.java))
                                        "Xếp hạng" -> {
                                            val intent = Intent(requireContext(),RatingBookActivity::class.java)
                                                intent.putExtra("action_name", "Yêu Thích")
                                            startActivity(intent)

                                        }
                                    }
                                }

                            })

                        } else {
                            Toast.makeText(requireContext(),"Thất bại",Toast.LENGTH_LONG).show()
                        }
                    },
                    { error ->
                        Log.e("loi", "Error fetching ListViewMenu: ${error.message}")
                        Toast.makeText(requireContext(), "Failed to fetch ListViewMenu", Toast.LENGTH_LONG).show()
                    }
                )
            )
        }

        private fun ActionViewFilper() {
            Log.d("MyFragment", "ActionViewFilper() called")

            val slide_in: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.slider_home_in_right)
            val slide_out: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_home_out_left)
            binding.viewLipper.inAnimation = slide_in
            binding.viewLipper.outAnimation = slide_out

            val quangcao: ArrayList<String> = arrayListOf(
                Utils.BASE_URL + "asserst/image/banner_TieuThuyet/banner_doremon.jpg",
                Utils.BASE_URL + "asserst/image/banner_TieuThuyet/banner_history.jpg",
                Utils.BASE_URL +"asserst/image/banner_TieuThuyet/banner_tauhai.jpg",
                Utils.BASE_URL +"asserst/image/banner_TieuThuyet/manga-anime-la-gi.jpg"
            )

            for (imageUrl in quangcao) {
                val imageView = ImageView(requireContext())
                Picasso.get().load(imageUrl).into(imageView, object : Callback {
                    override fun onSuccess() {
                        // Ảnh đã được tải thành công
                    }
                    override fun onError(e: Exception?) {
                        // Đã xảy ra lỗi khi tải ảnh
                        Log.e("Picasso", "Error loading image: ${e?.message}")
                    }
                })

                imageView.layoutParams = RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT
                )
                imageView.scaleType = ImageView.ScaleType.FIT_XY

                binding.viewLipper.addView(imageView)
            }
            binding.viewLipper.flipInterval = 3000
            binding.viewLipper.isAutoStart = true


        }

        companion object {
            // TODO: Rename and change types and number of parameters
            @JvmStatic
            fun newInstance(param1: String, param2: String) =
                HomeFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
        }

        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(R.menu.toobar_menu,menu)
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            // triển khai hành động menu ở đây
            when(menuItem.itemId){
                R.id.actionsearch -> {
                    val intent = Intent(requireContext(), SearchActivity::class.java)
                    startActivity(intent)
                }
                else->{
                    Toast.makeText(requireContext(),"Thất bại",Toast.LENGTH_LONG).show()
                }
            }
            return false
        }

        fun isConnected(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val network = connectivityManager.activeNetwork
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)

            return networkCapabilities?.run {
                hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
            } ?: false
        }
    }
