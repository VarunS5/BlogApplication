package com.example.blogapplication.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.blogapplication.BlogDetailActivity
import com.example.blogapplication.R
import com.example.blogapplication.model.Comments

class CommentsAdapter(
    private val context: Activity,
    private val comments: ArrayList<Comments>
) : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userComment: TextView = view.findViewById(R.id.user_comment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.comment_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = comments[position]
        holder.userComment.text = comment.text
    }

    override fun getItemCount(): Int {
        return comments.size
    }
}