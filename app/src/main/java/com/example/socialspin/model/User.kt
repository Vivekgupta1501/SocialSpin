package com.example.socialspin.model

data class User(
    val name  :String  = " ",
    val email : String  = "",
    val password : String = "",
    val confirmPassword: String ="",
    val isShowingLoginPage : Boolean = true
)