package com.example.do_an_cs3

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.do_an_cs3.Fragment.HomeFragment
import com.example.do_an_cs3.Fragment.ProfileFragment
import com.example.do_an_cs3.Model.User.user
import com.example.do_an_cs3.activity.ListBookActivity
import com.example.do_an_cs3.databinding.ActivityMainBinding
import com.example.do_an_cs3.utils.Utils
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.paperdb.Paper

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity()  {
//    , NavigationView.OnNavigationItemSelectedListener
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Paper.init(this)
        if(Paper.book().read<user>("user") != null){
            val user : user ?= Paper.book().read<user>("user")
            Utils.user_current = user!!
        }

        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment()).commit()
        }

        binding.bottomNavView.setOnItemSelectedListener {menuItem ->
            when(menuItem.itemId){
                R.id.nav_home -> supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, HomeFragment()).commit()
                R.id.list_book -> startActivity(Intent(this,ListBookActivity::class.java))
                R.id.nav_profile -> supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ProfileFragment()).commit()
                else -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, HomeFragment()).commit()
                }
            }
            true
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
}