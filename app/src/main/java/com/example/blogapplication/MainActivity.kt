package com.example.blogapplication

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blogapplication.adapter.BlogAdapter
import com.example.blogapplication.api.BlogService
import com.example.blogapplication.model.Blog
import com.example.blogapplication.repository.BlogListRepository
import com.example.blogapplication.util.Status
import com.example.blogapplication.viewmodel.BlogListViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var blogAdapter: BlogAdapter
    private lateinit var blogViewModel: BlogListViewModel
    private lateinit var blogListRepository: BlogListRepository
    private lateinit var likesCount : TextView
    private lateinit var commentsCount : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupObserver()
        recyclerView = findViewById(R.id.blog_list)
        layoutManager = LinearLayoutManager(this)
        blogAdapter = BlogAdapter(this, ArrayList())
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = blogAdapter

    }

    private fun setupObserver() {
        blogViewModel.getBlogs().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { blogs -> updateList(blogs) }
                }
                Status.LOADING -> {
                    Log.i("data", "Loading...")
                }
            }
        })
    }

    private fun updateList(blogs: List<Blog>) {
        blogAdapter.setUpdatedList(blogs)
    }

    private fun setupViewModel() {
        val openDialog = Dialog(this)
        openDialog.setContentView(R.layout.blog_list_item)
        blogListRepository = BlogListRepository(BlogService())
        blogViewModel = ViewModelProvider(
            this,
            ViewModelFactory(blogListRepository)
        ).get(BlogListViewModel::class.java)
    }
}

class ViewModelFactory(
    val blogListRepository: BlogListRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BlogListViewModel(blogListRepository) as T
    }

}
