package com.example.blogapplication

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.webkit.WebView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blogapplication.adapter.CommentsAdapter
import com.example.blogapplication.adapter.TagsAdapter
import com.example.blogapplication.dialog.CommentsDialog
import com.example.blogapplication.dialog.LikesDialog
import com.example.blogapplication.model.Blog
import kotlinx.android.synthetic.main.activity_blog_detail.*

class BlogDetailActivity : AppCompatActivity() {
    private lateinit var ownerName: TextView
    private lateinit var titleName: TextView
    private lateinit var content: WebView
    private lateinit var tagsList: RecyclerView
    private lateinit var commentsCount : TextView
    private lateinit var commentLayout : LinearLayout
    private lateinit var likesCount : TextView
    private lateinit var likesLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog_detail)
        val blog = intent.getParcelableExtra<Blog>("blog")
        ownerName = findViewById(R.id.owner_name_detail)
        titleName = findViewById(R.id.title_name_detail)
        content = findViewById(R.id.content_detail)
        tagsList = findViewById(R.id.tags_list)
        commentsCount = findViewById(R.id.comment_count_detail)
        commentLayout = findViewById(R.id.comment_layout_detail)
        likesCount = findViewById(R.id.like_count_detail)
        likesLayout = findViewById(R.id.like_layout_detail)
        commentLayout.setOnClickListener{
            val commentsDialog = CommentsDialog(this,blog?.comments!!)
            commentsDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            commentsDialog.show()
        }
        likesLayout.setOnClickListener{
            val likesDialog = LikesDialog(this,blog?.likes!!)
            likesDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            likesDialog.show()
        }
        tagsList.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        tagsList.adapter = TagsAdapter(this,blog?.tags!!)
        ownerName.text = blog?.author?.name
        titleName.text = blog?.title
        likesCount.text = blog.likes.size.toString()
        commentsCount.text = blog.comments.size.toString()
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
        content.setBackgroundColor(0)
    }
}