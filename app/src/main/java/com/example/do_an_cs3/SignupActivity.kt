package com.example.do_an_cs3

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.do_an_cs3.Model.User.user
import com.example.do_an_cs3.Model.User.user_model
import com.example.do_an_cs3.Model.book
import com.example.do_an_cs3.activity.BookDetailActivity
import com.example.do_an_cs3.adapter.Recommended_Books_Adapter
import com.example.do_an_cs3.databinding.ActivitySignupBinding
import com.example.do_an_cs3.retrofit.ApiSelectMenu
import com.example.do_an_cs3.retrofit.RetrofitClient
import com.example.do_an_cs3.utils.Utils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.regex.Pattern
import kotlin.random.Random

private lateinit var binding: ActivitySignupBinding
class SignupActivity : AppCompatActivity() {

    lateinit var compositeDisposable: CompositeDisposable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        compositeDisposable = CompositeDisposable()

        if(isConnected(this)){
            initControl()
        }

    }

    private fun initControl() {
        binding.chaneToRegister.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java);
            startActivity(intent)
        }

        binding.btnSignUp.setOnClickListener {
            SignUp()
        }
    }

    private fun SignUp() {
        try {
            val str_email : String = binding.edtEmail.text.toString().trim()
            val str_pass : String = binding.edtPassword.text.toString().trim()
            val repass : String = binding.edtRepass.text.toString().trim()

        if(str_email.isEmpty() || str_pass.isEmpty() || repass.isEmpty() ){
            Toast.makeText(this, "Vui lòng không để trống thông tin", Toast.LENGTH_SHORT).show()
        }else if(!Patterns.EMAIL_ADDRESS.matcher(str_email).matches()){
            Toast.makeText(this, "Sai định dạng email", Toast.LENGTH_SHORT).show()
        }
        else{
            if(str_pass.equals(repass)){
                val username : String = str_email.substring(0,6)
                SignUp_User(username ,str_email,str_pass)
            }else{
                Toast.makeText(this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show()
            }
        }
        }catch (e: Exception) {
            Log.e("SignUp", "Error occurred: ${e.message}", e)
            Toast.makeText(this, "Đã xảy ra lỗi: ${e.message}", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }

    }

    private fun SignUp_User( username : String,email : String ,password : String) {

        val apiSign = RetrofitClient.getInstant(Utils.BASE_URL).create(ApiSelectMenu::class.java)
        compositeDisposable.add(apiSign.SignUp(username,email,password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()) // xác định luồng hoạt động
            .subscribe(
                { user_model ->
                    if(user_model.success ){

                        Utils.user_current.email = email
                        Utils.user_current.password = password
                        val intent = Intent(this,LoginActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, user_model.message, Toast.LENGTH_LONG).show()
                    }

                },
                { error ->
                    Log.e("loi", "Error fetching categories: ${error.message}")
                    Toast.makeText(this, "loi dk", Toast.LENGTH_LONG).show()
                }
            )
        )
    }


    fun isConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)

        return networkCapabilities?.run {
            hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
        } ?: false
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

}