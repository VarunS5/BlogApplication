package com.example.blogapplication.api

import android.util.Log
import com.example.blogapplication.model.Blog
import com.example.blogapplication.util.BlogListObserver
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BlogService {
    var remoteBlogs: ArrayList<Blog> = ArrayList<Blog>()
    lateinit var blogListObserver: BlogListObserver

    fun getRemoteBlogs(): List<Blog> {
        val blogs: Call<ArrayList<Blog>> = Api.Companion.create().getBlogs()

        blogs.enqueue(object : Callback<ArrayList<Blog>> {
            override fun onResponse(
                call: Call<ArrayList<Blog>>,
                response: Response<ArrayList<Blog>>
            ) {
                remoteBlogs = response.body()!!
                blogListObserver.onBlogListUpdate(remoteBlogs)
            }

            override fun onFailure(call: Call<ArrayList<Blog>>, t: Throwable) {
                Log.e("List", "error loading")
            }

        })
        return remoteBlogs
    }

    fun setBlogObserver(blogListObserver: BlogListObserver) {
        this.blogListObserver = blogListObserver
    }
}