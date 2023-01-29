package com.codingwithze.penjualan.data.response


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("dimension")
    val dimension: String,
    @SerializedName("discount")
    val discount: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("product_code")
    val productCode: String,
    @SerializedName("product_name")
    val productName: String,
    @SerializedName("unit")
    val unit: String
)