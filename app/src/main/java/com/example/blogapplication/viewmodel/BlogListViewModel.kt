package com.example.blogapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.blogapplication.model.Blog
import com.example.blogapplication.repository.BlogListRepository
import com.example.blogapplication.util.BlogListObserver
import com.example.blogapplication.util.Resource

class BlogListViewModel(private val blogListRepository: BlogListRepository) : ViewModel(),
    BlogListObserver {
    private val blogs = MutableLiveData<Resource<List<Blog>>>()

    init {
        blogListRepository.blogListObserver = this
        fetchBlogs();
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
        blogs.postValue(Resource.success(blogList))
    }
}