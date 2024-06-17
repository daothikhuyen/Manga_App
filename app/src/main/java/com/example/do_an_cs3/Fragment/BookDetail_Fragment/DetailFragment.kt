package com.example.do_an_cs3.Fragment.BookDetail_Fragment

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.do_an_cs3.LoginActivity
import com.example.do_an_cs3.Model.Comment.comment
import com.example.do_an_cs3.Model.Like.mangaLike
import com.example.do_an_cs3.Model.chapter
import com.example.do_an_cs3.R
import com.example.do_an_cs3.activity.Chapter.ChapterActivity
import com.example.do_an_cs3.adapter.Comment.Comments_Adapter
import com.example.do_an_cs3.databinding.FragmentDetailBinding
import com.example.do_an_cs3.retrofit.ApiSelectMenu
import com.example.do_an_cs3.retrofit.RetrofitClient
import com.example.do_an_cs3.utils.Utils
import io.paperdb.Paper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var binding: FragmentDetailBinding

class DetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var dialog: AlertDialog
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
        binding = FragmentDetailBinding.inflate(inflater,container,false)
        Paper.init(requireContext())
        compositeDisposable = CompositeDisposable()
        api = RetrofitClient.getInstant(Utils.BASE_URL).create(ApiSelectMenu::class.java);

        val manga_id = arguments?.getString("id").toString()
        val user_id = Utils.user_current.id.toString()

        pushData(manga_id,user_id)
        pushComment(manga_id)

        binding.btnLike.setOnClickListener {
            likeManga(manga_id,user_id)
        }

        binding.btnStartRead.setOnClickListener {
            ReadFirstChapter(manga_id)
        }

        var isDownUp = false
        binding.btnReviewManga.setOnClickListener {
            binding.txtSummary.maxLines = 1000
            binding.txtSummary.ellipsize = null
            isDownUp = !isDownUp
            if(isDownUp){
                binding.iconUpDown.setImageResource(R.drawable.baseline_keyboard_arrow_up_24)
            }else{
                binding.txtSummary.maxLines = 6
                binding.txtSummary.ellipsize = TextUtils.TruncateAt.END
                binding.iconUpDown.setImageResource(R.drawable.baseline_keyboard_arrow_down_24)
            }
        }

        binding.btnComment.setOnClickListener {
            showListComment(manga_id,user_id)
        }

        return binding.root
    }

    private fun showListComment(manga_id: String,user_id: String) {
        val nameManga = arguments?.getString("name") ?: ""
        val build = AlertDialog.Builder(requireContext(),R.style.Themecustom)
        val view = layoutInflater.inflate(R.layout.comments_dialog,null)
        build.setView(view)
        // declare
        val btnClose = view.findViewById<LinearLayout>(R.id.linearName)
        val btnSend = view.findViewById<LinearLayout>(R.id.btnSend)
        val edtComment = view.findViewById<EditText>(R.id.edtComment)
        val name = view.findViewById<TextView>(R.id.mangaName)

        name.text = nameManga
        // close view comment
        btnClose.setOnClickListener {
            dialog.dismiss();
        }
        // Gửi bình luận
        btnSend.setOnClickListener {

            if(user_id != "0"){
                val edtComment = edtComment.text.toString()
                insertComment(view,edtComment,manga_id,user_id)
                view.findViewById<EditText>(R.id.edtComment).setText("")
            }else{
                Toast.makeText(requireContext(), "Vui lòng đăng nhập để xem bình luận" , Toast.LENGTH_LONG).show()
            }

        }

        pushDialogComments(view,manga_id)
        dialog = build.create()
        dialog = build.show()

        binding.recyclerComment.layoutManager = LinearLayoutManager(requireContext())

        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setGravity(Gravity.BOTTOM)
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation


    }

    private fun insertComment(view: View,edtComment: String, mangaId: String,user_id: String) {
        var ds = mutableListOf<comment>()
        val recycleComment = view.findViewById<RecyclerView>(R.id.recyclerComment)

        compositeDisposable.add(api.postComment(user_id,mangaId,edtComment)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { comment_Model ->
                    if(comment_Model.success){
                        Toast.makeText(requireContext(), comment_Model.message, Toast.LENGTH_LONG).show()
                        pushDialogComments(view,mangaId)
                    }
                },{
                    error ->
                        Log.e("loi","Loi bình luận ${error.message}")
                    Toast.makeText(requireContext(), "Loi bình luận" , Toast.LENGTH_LONG).show()
                }
            )
        )
    }

    private fun pushComment(mangaId: String) {
        var ds = mutableListOf<comment>()

        compositeDisposable.add(api.getComment(mangaId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ comment_Model ->
                if(comment_Model.success){
                    ds = comment_Model.result.take(5).toMutableList()
                    val adapter = Comments_Adapter(requireContext(),ds)
                    binding.recyclerComment.adapter = adapter
                    binding.recyclerComment.layoutManager = GridLayoutManager(requireContext(),1,GridLayoutManager.VERTICAL,false)
                    binding.endComment.visibility = View.VISIBLE
                    binding.btnComment.text = comment_Model.result.size.toString() + " Bình luận"

                    adapter.setOnClickListener(object : Comments_Adapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                        showListComment(mangaId,ds[position].user_id.toString())
                        }

                    })

                }else{
                    binding.btnComment.text = " 0 Bình luận"
                    binding.emptyComment.visibility = View.VISIBLE
                    binding.recyclerComment.visibility = View.GONE
                }

            },
                { error ->
                    Log.e("loi", "list comments Failed: ${error.message}")
                    Toast.makeText(requireContext(), "list comments Failed", Toast.LENGTH_LONG).show()
                }
            )
        )
    }

    private fun pushDialogComments(view: View,manga_id: String) {

        val id_user : String = Utils.user_current.id.toString()
        var ds = mutableListOf<comment>()
        val recycleComment = view.findViewById<RecyclerView>(R.id.recyclerComment)

        compositeDisposable.add(api.getComment(manga_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ comment_Model ->
                if(comment_Model.success ){
                    ds = comment_Model.result.toMutableList()

                    val adapter = Comments_Adapter(requireContext(),ds)
                    recycleComment.adapter = adapter
                    recycleComment.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                    if (!ds.isNotEmpty()) {
                        recycleComment.visibility = View.GONE
                    } else {
                        recycleComment.visibility = View.VISIBLE
                    }
                    adapter.notifyDataSetChanged()

                    adapter.setOnClickListener(object : Comments_Adapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            if(ds[position].user_id.toString() === id_user){
                                showDialog_ChangeComment(view,ds[position].id.toString(),ds[position].user_id.toString(),manga_id)
                            }

                        }

                    })
                } else {
                    binding.emptyComment.visibility = View.VISIBLE
                    recycleComment.visibility = View.GONE
                }
            },
                { error ->
                    Log.e("loi", "list comments Failed: ${error.message}")
                    Toast.makeText(requireContext(), "list comments Failed", Toast.LENGTH_LONG).show()
                }
            )
        )
    }

    private fun showDialog_ChangeComment( viewComment: View, id_comment: String,id_user: String,manga_id: String){
        val build = AlertDialog.Builder(requireContext(),R.style.Themecustom)
        val view = layoutInflater.inflate(R.layout.change_comment_dialog,null)
        build.setView(view)

        val btnDelte = view.findViewById<LinearLayout>(R.id.layoutDelete);

        btnDelte.setOnClickListener {
            dialog.dismiss();
            Delete_Comment(viewComment,id_comment,id_user,manga_id)
        }

        dialog = build.create()
        dialog = build.show()
        dialog.window?.setGravity(Gravity.CENTER)
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation

    }

    private fun Delete_Comment(viewComment : View,id_comment: String, id_user: String,manga_id: String){

        compositeDisposable.add(api.deleteComment(id_comment,id_user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { comment_Model ->
                    if(comment_Model.success){
                        Toast.makeText(requireContext(),"Đã Xoá", Toast.LENGTH_LONG).show()
                        pushDialogComments(viewComment,manga_id)
                        pushComment(manga_id)
                    }
                },{
                        error ->
                    Log.e("loi","Loi bình luận ${error.message}")
                    Toast.makeText(requireContext(), "Loi bình luận" , Toast.LENGTH_LONG).show()
                }
            )
        )
    }

    private fun ReadFirstChapter(manga_id:String) {
        var ds = mutableListOf<chapter>()

        compositeDisposable.add(api.getChapter(manga_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { chapter_Model ->
                    if(chapter_Model.success) {

                        ds = chapter_Model.result.toMutableList()
                        if(Paper.book().read<String>("user") != null){
                            val intent = Intent(context,ChapterActivity::class.java)
                            intent.putExtra("chapter_id", ds[0].id.toString())
                            intent.putExtra("title",ds[0].title)
                            intent.putExtra("menu_id",ds[0].toString())
                            intent.putExtra("position", "0")
                            intent.putExtra("size", ds.size.toString())
                            intent.putExtra("manga_id",ds[0].manga_id)

                            startActivity(intent)
                        }else{
                            Toast.makeText(requireContext(), "Bạn chưa đăng nhập tài khoản", Toast.LENGTH_SHORT).show()
                            val intent = Intent(requireContext(), LoginActivity::class.java);

                            startActivity(intent)
                        }
                    }else{
                        Toast.makeText(requireContext(), "Đang trong quá trình upload", Toast.LENGTH_SHORT).show()
                    }

                },
                { error ->
                    Log.e("loi", "Lỗi đọc chapter đầu tiên: ${error.message}")
                    Toast.makeText(requireContext(), "Lỗi đọc chapter đầu tiên", Toast.LENGTH_SHORT).show()
                }
            )
        )
    }

    private fun likeManga(manga_id:String, user_id:String) {
        var ds = mutableListOf<mangaLike>()
        var isLike = false;

        if(Paper.book().read<String>("user") != null){
            var numberlike : Int = 0;

            compositeDisposable.add(api.insertLikeManga(manga_id,user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                    {mangaLike_Model ->
                        if(mangaLike_Model.success){

                           ds = mangaLike_Model.result.toMutableList()

                            ds.forEach{ likeItem ->

                                if(likeItem.manga_id.toInt() == manga_id.toInt() && likeItem.user_id.toInt() == user_id.toInt()){
                                    isLike = !isLike

                                }

                                numberlike++;
                            }

                            if(isLike){
                                binding.btnLike.setImageResource(R.drawable.baseline_favorite_24)
                            }else{
                                binding.btnLike.setImageResource(R.drawable.baseline_favorite_border_24)
                            }

                            binding.likes.text =  numberlike.toString()
                        }else{
                            binding.btnLike.setImageResource(R.drawable.baseline_favorite_border_24)
//                            Toast.makeText(requireContext(), mangaLike_Model.message, Toast.LENGTH_LONG).show()
                        }
                    },{
                        error ->
                        Log.e("loi", "Thất bại like: ${error.message}")
//                        Toast.makeText(requireContext(), "Thất bại like", Toast.LENGTH_LONG).show()
                    }
                )
            )

        }else{
            Toast.makeText(requireContext(), "Bạn chưa đăng nhập", Toast.LENGTH_SHORT).show();
            startActivity(Intent(requireContext(),LoginActivity::class.java))
        }

    }

    private fun pushData(manga_id:String, user_id:String) {
        binding.txtSummary.text = arguments?.getString("description") ?: ""
        binding.likes.text = arguments?.getString("number_like") ?: "0"


        compositeDisposable.add(api.getLikeMangaByUser(manga_id,user_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ mangaLike_Model ->
                if(mangaLike_Model.success){
                    if(mangaLike_Model.result[0].like == 1){
                        binding.btnLike.setImageResource(R.drawable.baseline_favorite_24)
                    }
                }else{
                    Toast.makeText(requireContext(), "Thất bại", Toast.LENGTH_LONG).show()
                }
            },
            { error ->
                Log.e("loi", "Thất bại number_like: ${error.message}")

            })
        )

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}