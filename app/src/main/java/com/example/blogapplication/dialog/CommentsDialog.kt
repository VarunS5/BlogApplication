package com.example.blogapplication.dialog

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blogapplication.R
import com.example.blogapplication.adapter.CommentsAdapter
import com.example.blogapplication.model.Comments

class CommentsDialog(private val context : Activity, private val comments:ArrayList<Comments>):
    Dialog(context) {

    private lateinit var commentsList : RecyclerView
    private lateinit var cancelButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comments_dialog)
        commentsList = findViewById(R.id.comments)
        cancelButton = findViewById(R.id.cancel_button_dialog)
        commentsList.layoutManager = LinearLayoutManager(context)
        commentsList.adapter = CommentsAdapter(context,this.comments)
        cancelButton.setOnClickListener{
            dismiss()
        }
    }
}