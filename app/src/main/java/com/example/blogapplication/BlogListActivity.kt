package com.example.blogapplication

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
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

class BlogListActivity : AppCompatActivity() {

    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var blogAdapter: BlogAdapter
    private lateinit var blogViewModel: BlogListViewModel
    private lateinit var blogListRepository: BlogListRepository
    private lateinit var loadingLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog_list)
        loadingLayout = findViewById(R.id.loading_panel)
        setupViewModel()
        setupObserver()
        recyclerView = findViewById(R.id.blog_list)
        layoutManager = LinearLayoutManager(this)
        blogAdapter = BlogAdapter(this, ArrayList())
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = blogAdapter

    }

    private fun setupObserver() {
        blogViewModel.getBlogs().observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    loadingLayout.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    it.data?.let { blogs -> updateList(blogs) }
                }
                Status.LOADING -> {
                    Log.i("data", "Loading...")
                }
                else -> {
                    Log.e("error", "${it.message}")
                }
            }
        }
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

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val blogListRepository: BlogListRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BlogListViewModel(blogListRepository) as T
    }

}
