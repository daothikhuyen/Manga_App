package com.example.do_an_cs3.retrofit

import android.util.Log
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitClient {

    companion object {
        private lateinit var instant: Retrofit

        fun getInstant(baseUrl: String): Retrofit {
            if (!::instant.isInitialized) {
                instant = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build()
            }
            return instant
        }
    }
}