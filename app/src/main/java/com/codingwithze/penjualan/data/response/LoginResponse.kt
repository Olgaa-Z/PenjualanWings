package com.codingwithze.penjualan.data.response


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("login")
    val login: Login
)