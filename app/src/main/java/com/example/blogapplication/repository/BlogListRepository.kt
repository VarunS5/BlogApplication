package com.example.blogapplication.repository

import com.example.blogapplication.api.BlogService
import com.example.blogapplication.model.Blog
import com.example.blogapplication.util.BlogListObserver

class BlogListRepository(private val blogService: BlogService):BlogListObserver {
    var blogListObserver : BlogListObserver? = null

    init{
        blogService.setBlogObserver(this)
    }

    fun getBlogs():List<Blog>{
        return blogService.getRemoteBlogs()
    }

    override fun onBlogListUpdate(blogList: ArrayList<Blog>) {
        blogListObserver?.onBlogListUpdate(blogList)
    }
}