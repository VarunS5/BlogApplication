package com.example.blogapplication

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
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
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class BlogListActivity : AppCompatActivity() {

    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var blogAdapter: BlogAdapter
    private lateinit var blogViewModel: BlogListViewModel
    private lateinit var blogListRepository: BlogListRepository
    private lateinit var loadingLayout: LinearLayout
    private lateinit var greetLayout: LinearLayout
    private lateinit var welcomeLayout: LinearLayout
    private lateinit var cardLayout: CardView
    private lateinit var companyLogo: ImageView
    private lateinit var greetMessage: TextView
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog_list)
        loadingLayout = findViewById(R.id.loading_panel)
        greetLayout = findViewById(R.id.greet_layout)
        welcomeLayout = findViewById(R.id.welcome_layout)
        cardLayout = findViewById(R.id.card_list)
        companyLogo = findViewById(R.id.thoughtworks_logo)
        greetMessage = findViewById(R.id.greet)
        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out)
        scope.launch {
            companyLogo.startAnimation(fadeInAnimation)
            delay(3000)
            companyLogo.startAnimation(fadeOutAnimation)
            delay(3000)
            companyLogo.visibility = View.GONE
            welcomeLayout.visibility = View.GONE
            greetLayout.visibility = View.VISIBLE
        }
        setupViewModel()
        setupObserver()
        recyclerView = findViewById(R.id.blog_list)
        layoutManager = LinearLayoutManager(this)
        blogAdapter = BlogAdapter(this, ArrayList())
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = blogAdapter
        val hourFormat = SimpleDateFormat("HH")
        val currentHour = hourFormat.format(Date()).toDouble()
        greetMessage.text = blogViewModel.getGreetMessage(currentHour)

    }

    private fun setupObserver() {
        blogViewModel.getBlogs().observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    scope.launch {
                        delay(5000)
                        loadingLayout.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                        cardLayout.visibility = View.VISIBLE
                    }
                    it.data?.let { blogs -> updateList(blogs) }
                }
                Status.LOADING -> {
                    loadingLayout.visibility = View.VISIBLE
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
