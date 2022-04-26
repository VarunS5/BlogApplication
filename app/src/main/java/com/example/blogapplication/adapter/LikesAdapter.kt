package com.example.blogapplication.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.blogapplication.R
import com.example.blogapplication.model.Likes

class LikesAdapter(private val context : Activity,private val likes : ArrayList<Likes>):RecyclerView.Adapter<LikesAdapter.ViewHolder>() {
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val userName : TextView = view.findViewById(R.id.user_name_likes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.like_list_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val like = likes[position]
        holder.userName.text = like.name
    }

    override fun getItemCount(): Int {
        return likes.size
    }
}