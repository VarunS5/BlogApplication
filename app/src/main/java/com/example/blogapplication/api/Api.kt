package com.example.blogapplication.api

import com.example.blogapplication.model.Blog
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Api {
    @GET("Sample.json")
    fun getBlogs(): Call<ArrayList<Blog>>

    companion object{
        val BASE_URL = "https://raw.githubusercontent.com/Ysunil016/Public/main/"

        fun create(): Api {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(Api::class.java)
        }
    }
}