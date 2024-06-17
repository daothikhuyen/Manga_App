package com.example.do_an_cs3

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.do_an_cs3.Model.User.user
import com.example.do_an_cs3.databinding.ActivityLoginBinding
import com.example.do_an_cs3.retrofit.ApiSelectMenu
import com.example.do_an_cs3.retrofit.RetrofitClient
import com.example.do_an_cs3.utils.Utils
import io.paperdb.Paper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

private lateinit var binding: ActivityLoginBinding
class LoginActivity : AppCompatActivity() {

    lateinit var compositeDisposable: CompositeDisposable
    var isLogin : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater);
        setContentView(binding.root)

        Paper.init(this)
        if(Paper.book().read<String>("email") != null && Paper.book().read<String>("password") != null ){
            binding.edtEmail.setText(Paper.book().read<String>("email"))
            binding.edtPassword.setText(Paper.book().read<String>("password"))
        }

        compositeDisposable = CompositeDisposable()
        if(isConnected(this)){
            initControl()
        }
    }

    private fun initControl() {

        binding.LoginRedirectText.setOnClickListener {
            val intent = Intent(this,SignupActivity::class.java);
            startActivity(intent)
        }

        binding.loginButton.setOnClickListener {
            loginManga()
        }
    }

    private fun loginManga() {
        val str_email = binding.edtEmail.text.toString().trim()
        val password = binding.edtPassword.text.toString().trim()

        if(str_email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Vui lòng không để trống thông tin", Toast.LENGTH_SHORT).show()
        }else{
            if(!Patterns.EMAIL_ADDRESS.matcher(str_email).matches()){
                Toast.makeText(this, "Sai định dạng email", Toast.LENGTH_SHORT).show()
            }
            else{
                loginUser(str_email,password)
            }
        }
    }

    private fun loginUser(email : String, password: String) {
        try {

            Paper.book().write("emai", email);
            Paper.book().write("password", password);

            val apiSign = RetrofitClient.getInstant(Utils.BASE_URL).create(ApiSelectMenu::class.java)
            compositeDisposable.add(apiSign.Login(email,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({ user_model ->
                    if(user_model.success){

                        isLogin = true
                        Paper.book().write("isLogin", isLogin)
                        Utils.user_current = user_model.result[0]
                        // lưu thông tin người dùng
                        Paper.book().write("user", user_model.result[0])

                        val intent = Intent(applicationContext,MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else{
                        Toast.makeText(this, user_model.message, Toast.LENGTH_SHORT).show()
                    }

                }, { error ->
                    Log.e("loi", "Lỗi đăng nhập người dùng: ${error.message}")
                    Toast.makeText(this, "Lỗi đăng nhập người dùng", Toast.LENGTH_SHORT).show()
                }
                )
            )
        }catch (e : Exception){
            Log.e("SignUp", "Error occurred: ${e.message}", e)
            Toast.makeText(this, "Đã xảy ra lỗi: ${e.message}", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }

    fun isConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)

        return networkCapabilities?.run {
            hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
        } ?: false
    }

    override fun onResume() {
        super.onResume()
        try {
            if (Utils.user_current.email != null || Utils.user_current.password != null){
                binding.edtEmail.setText(Utils.user_current.email)
                binding.edtPassword.setText(Utils.user_current.password)
            }
        }catch (e : Exception){
            Log.e("Login", "Error occurred: ${e.message}", e)
            Toast.makeText(this, "Đã xảy ra lỗi: ${e.message}", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}