package com.example.blogapplication.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.blogapplication.BlogDetailActivity
import com.example.blogapplication.BlogListActivity
import com.example.blogapplication.R
import com.example.blogapplication.model.Blog
import com.example.blogapplication.model.Tags

class BlogAdapter(private val context: BlogListActivity, private var blogList: ArrayList<Blog>) :
    RecyclerView.Adapter<BlogAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userName = view.findViewById<TextView>(R.id.user_name)
        val blogTitle = view.findViewById<TextView>(R.id.blog_title)
        val publishedDate = view.findViewById<TextView>(R.id.published_date)
        val likesCount = view.findViewById<TextView>(R.id.like_count)
        val commentsCount = view.findViewById<TextView>(R.id.comment_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.blog_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var blog = this.blogList.get(position)
        holder.userName.text = blog.author.username
        holder.blogTitle.text = blog.title
        holder.publishedDate.text = blog.published_date
        holder.likesCount.text = blog.likes.size.toString()
        holder.commentsCount.text = blog.comments.size.toString()
        holder.itemView.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, BlogDetailActivity::class.java)
            intent.putExtra("blog", blog)
            context.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return this.blogList.size
    }

    fun setUpdatedList(blogs: List<Blog>) {
        this.blogList = blogs as ArrayList<Blog>
        notifyDataSetChanged()
    }
}