package com.codingwithze.penjualan.data.response


import com.google.gson.annotations.SerializedName

data class DataTransaksi(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("document_code")
    val documentCode: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("product_code")
    val productCode: String,
    @SerializedName("quantity")
    val quantity: String,
    @SerializedName("sub_total")
    val subTotal: String,
    @SerializedName("unit")
    val unit: String,
    @SerializedName("user")
    val user: String
)