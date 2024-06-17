package com.example.do_an_cs3.activity.profile

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.GridLayoutManager
import com.example.do_an_cs3.Fragment.Profile.LoveFragment
import com.example.do_an_cs3.Fragment.ProfileFragment
import com.example.do_an_cs3.LoginActivity
import com.example.do_an_cs3.Model.User.user_model
import com.example.do_an_cs3.Model.book
import com.example.do_an_cs3.R
import com.example.do_an_cs3.activity.BookDetailActivity
import com.example.do_an_cs3.adapter.Recommended_Books_Adapter
import com.example.do_an_cs3.databinding.ActivitySetUpProfileBinding
import com.example.do_an_cs3.retrofit.ApiSelectMenu
import com.example.do_an_cs3.retrofit.RetrofitClient
import com.example.do_an_cs3.utils.Utils
import com.github.dhaval2404.imagepicker.ImagePicker
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import io.paperdb.Paper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

private lateinit var binding: ActivitySetUpProfileBinding
class SetUpProfileActivity : AppCompatActivity() {

    lateinit var compositeDisposable: CompositeDisposable
    var ds : String ="" ;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetUpProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Paper.init(this)
        compositeDisposable = CompositeDisposable()

        ActionToorBar()
        pushDataUser()

        binding.floatingActionButton.setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start()
        }

        binding.uploadButton.setOnClickListener {
            val message : String? = "Bạn có chắn chắn muốn thay đổi thông tin"
            showCustomDialogBox(message)
        }
    }

    private fun showCustomDialogBox(message: String?) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_custom_dailog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val txtMessage = dialog.findViewById<TextView>(R.id.tvMessage)
        val btnYes = dialog.findViewById<Button>(R.id.btnYes)
        val btnNo = dialog.findViewById<Button>(R.id.btnNo)

        txtMessage.text = message
        btnYes.setOnClickListener {
            uploadUser()
        }

        btnNo.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

    }

    private fun uploadUser() {
        val id : String = Utils.user_current.id.toString()
        val name = binding.edtName.text.toString().trim()
        val email = binding.edtEmail.text.toString().trim()
        val password = binding.editPassword.text.toString().trim()
        val avatar: String = ds.trim()

        val apiUpload = RetrofitClient.getInstant(Utils.BASE_URL).create(ApiSelectMenu::class.java)

        compositeDisposable.add(apiUpload.uploadInfoUser(id,name,email,password,avatar)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()) // xác định luồng hoạt động
            .subscribe(
                { user_model  ->
                    if(user_model.success ){
                        Toast.makeText(this, user_model.message, Toast.LENGTH_LONG).show()
                        Paper.book().delete("user")

                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)

                    }
                },
                { error ->
                    Log.e("loi", "Error upload user: ${error.message}")
                    Toast.makeText(this, "Error upload user", Toast.LENGTH_LONG).show()
                }
            )
        )
    }


    private fun pushDataUser() {
        binding.edtName.setText(Utils.user_current.name)
        binding.edtEmail.setText(Utils.user_current.email)
        binding.editPassword.setText(Utils.user_current.password)
        val uri : String = Utils.user_current.avatar.trim()

        if(uri != ""){
            binding.imgAvatar.setImageURI(uri.toUri())
        }



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val uri: Uri = data?.data!!
            binding.imgAvatar.setImageURI(uri)
            ds = uri.toString()
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    private fun ActionToorBar() {
        setSupportActionBar(binding.toolbar2)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbar2.setNavigationOnClickListener(View.OnClickListener {
//            val intent = Intent(this, ProfileFragment::class.java)
//            startActivity(intent)
//            finish()
            onBackPressed()
        })
    }
}