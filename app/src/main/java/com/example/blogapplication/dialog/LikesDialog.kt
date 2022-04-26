package com.example.blogapplication.dialog

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blogapplication.R
import com.example.blogapplication.adapter.LikesAdapter
import com.example.blogapplication.model.Likes

class LikesDialog(private val context: Activity, private val likes: ArrayList<Likes>) :
    Dialog(context) {

    private lateinit var likesList : RecyclerView
    private lateinit var cancelButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.likes_dialog)
        likesList = findViewById(R.id.likes)
        cancelButton = findViewById(R.id.cancel_button_like)
        likesList.layoutManager = LinearLayoutManager(context)
        likesList.adapter = LikesAdapter(context,likes)
        cancelButton.setOnClickListener{
            dismiss()
        }
    }
}