package com.example.blogapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.TextView
import com.example.blogapplication.model.Blog

class BlogDetailActivity : AppCompatActivity() {
    lateinit var ownerName: TextView
    lateinit var userName: TextView
    lateinit var titleName: TextView
    lateinit var content: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog_detail)
        val blog = intent.getParcelableExtra<Blog>("blog")
        ownerName = findViewById(R.id.owner_name_detail)
        userName = findViewById(R.id.user_name_detail)
        titleName = findViewById(R.id.title_name_detail)
        content = findViewById(R.id.content_detail)
        ownerName.text = blog?.author?.name
        userName.text = blog?.author?.username
        titleName.text = blog?.title
        content.loadDataWithBaseURL(null, blog?.content!!, "text/html", "utf-8", null)
    }
}