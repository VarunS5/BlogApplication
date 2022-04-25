package com.example.blogapplication.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.blogapplication.BlogDetailActivity
import com.example.blogapplication.BlogListActivity
import com.example.blogapplication.R
import com.example.blogapplication.model.Blog

class BlogAdapter(private val context: BlogListActivity, private var blogList: ArrayList<Blog>) :
    RecyclerView.Adapter<BlogAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userName: TextView = view.findViewById(R.id.user_name)
        val blogTitle: TextView = view.findViewById(R.id.blog_title)
        val publishedDate: TextView = view.findViewById(R.id.published_date)
        val likesCount: TextView = view.findViewById(R.id.like_count)
        val commentsCount: TextView = view.findViewById(R.id.comment_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.blog_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val blog = this.blogList.get(position)
        holder.userName.text = blog.author.name
        holder.blogTitle.text = blog.title
        holder.publishedDate.text = blog.published_date
        holder.likesCount.text = blog.likes.size.toString()
        holder.commentsCount.text = blog.comments.size.toString()
        holder.itemView.setOnClickListener {
            val intent = Intent(context, BlogDetailActivity::class.java)
            intent.putExtra("blog", blog)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return this.blogList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setUpdatedList(blogs: List<Blog>) {
        this.blogList = blogs as ArrayList<Blog>
        notifyDataSetChanged()
    }
}