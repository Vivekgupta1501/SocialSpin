package com.example.socialspin.model

data class Post(
    val autherId : String = "",
    val autherName  :String = "",
    val postId : String = "",
    val timeStamp: Long = 0,
    val postText: String = ""
)
