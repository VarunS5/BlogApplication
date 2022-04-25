package com.example.blogapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.blogapplication.BlogDetailActivity
import com.example.blogapplication.R
import com.example.blogapplication.model.Tags

class TagsAdapter(private val context:BlogDetailActivity, private val tags : ArrayList<Tags>):RecyclerView.Adapter<TagsAdapter.ViewHolder>() {
    class ViewHolder(view : View):RecyclerView.ViewHolder(view) {
        val tagText : TextView = view.findViewById(R.id.tag)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.tag_list_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tag = this.tags[position]
        holder.tagText.text = tag.name
    }

    override fun getItemCount(): Int {
        return this.tags.size
    }
}