package com.codingwithze.penjualan.data.response


import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)