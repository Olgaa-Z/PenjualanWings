package com.codingwithze.penjualan.data.response


import com.google.gson.annotations.SerializedName

data class TransaksiDetailResponse(
    @SerializedName("data")
    val `data`: DataTransaksi,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)