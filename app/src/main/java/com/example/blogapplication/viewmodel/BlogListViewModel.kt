package com.example.blogapplication.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.blogapplication.model.Blog
import com.example.blogapplication.repository.BlogListRepository
import com.example.blogapplication.util.BlogListObserver
import com.example.blogapplication.util.Resource
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class BlogListViewModel(
    private val blogListRepository: BlogListRepository,
) : ViewModel(),
    BlogListObserver {
    private val blogs = MutableLiveData<Resource<List<Blog>>>()

    init {
        blogListRepository.blogListObserver = this
        fetchBlogs()
    }

    private fun fetchBlogs() {
        blogs.postValue(Resource.loading(null))
        blogListRepository.getBlogs()

    }

    fun getBlogs(): LiveData<Resource<List<Blog>>> {
        return blogs
    }

    override fun onCleared() {
        super.onCleared()
    }

    override fun onBlogListUpdate(blogList: ArrayList<Blog>) {
        for (blog in blogList) {
            blog.published_date = blog.published_date.substring(0, 10)
        }
        blogs.postValue(Resource.success(blogList))
    }

    @SuppressLint("SimpleDateFormat")
    fun getGreetMessage(): String {
        val hourFormat = SimpleDateFormat("HH")
        val currentHour = hourFormat.format(Date())
        return when {
            currentHour.toDouble() >= 15 -> "Good Evening"
            currentHour.toDouble() >= 12 -> "Good Afternoon"
            currentHour.toDouble() >= 0 && currentHour.toDouble() <12 -> "Good Morning"
            else -> "Good Morning"
        }

    }
}