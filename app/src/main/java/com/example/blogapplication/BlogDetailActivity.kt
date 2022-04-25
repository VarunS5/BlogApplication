package com.example.blogapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.webkit.WebView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blogapplication.adapter.TagsAdapter
import com.example.blogapplication.model.Blog

class BlogDetailActivity : AppCompatActivity() {
    private lateinit var ownerName: TextView
    private lateinit var titleName: TextView
    private lateinit var content: WebView
    private lateinit var tagsList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog_detail)
        val blog = intent.getParcelableExtra<Blog>("blog")
        ownerName = findViewById(R.id.owner_name_detail)
        titleName = findViewById(R.id.title_name_detail)
        content = findViewById(R.id.content_detail)
        tagsList = findViewById(R.id.tags_list)
        tagsList.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        tagsList.adapter = TagsAdapter(this,blog?.tags!!)
        ownerName.text = blog?.author?.name
        titleName.text = blog?.title
        val webContent = "<html>" +
                "<head>" +
                "<style>" +
                "*{margin-left:0px;margin-bottom:2px;margin-right:0px;padding:0px 5px;padding-left:7px;}"+
                "a{color:white;}"+
                "a:visited{color:gold;}"+
                "p{font-size:18px;margin-left:2px;}"+
                "h4{font-size:25px;}"+
                "h3{font-size:28px;margin-bottom:0px;padding-bottom:0px;}"+
                "body{width:95%;color:white;text-align:left;background-image:linear-gradient(to bottom right,rgba(57, 62, 70, 1),rgba(26, 32, 44, 0.3));}" +
                "img{width:100%;aspect-ratio:4/3;}" +
                "</style>" +
                "</head>" +
                "<body>" + blog?.content!! +
                "</body>" +
                "</html>"
        content.loadData(
            Base64.encodeToString(webContent.toByteArray(), Base64.NO_PADDING),
            "text/html",
            "base64"
        )
    }
}