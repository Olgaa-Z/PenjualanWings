package com.codingwithze.penjualan.data.response


import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("password")
    val password: String,
    @SerializedName("user")
    val user: String
)