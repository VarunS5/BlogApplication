package com.example.blogapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Blog(
    var title: String,
    var content: String,
    var author: Author,
    var published_date: String,
    var comments: ArrayList<Comments>,
    var tags: ArrayList<Tags>,
    var likes: ArrayList<Likes>,
    var featured_image_url: String?
) : Parcelable

@Parcelize
data class Likes(var name: String, var email: String) : Parcelable

@Parcelize
data class Tags(var name: String, var created_at: String) : Parcelable

@Parcelize
data class Comments(var text: String, var created_at: String) : Parcelable

@Parcelize
data class Author(var name: String, var username: String, var email: String) : Parcelable
