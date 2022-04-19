package com.example.blogapplication.util

import com.example.blogapplication.model.Blog

interface BlogListObserver {
    fun onBlogListUpdate(blogList: ArrayList<Blog>)
}