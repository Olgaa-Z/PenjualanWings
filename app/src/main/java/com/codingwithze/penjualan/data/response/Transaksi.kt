package com.codingwithze.penjualan.data.response

import java.util.Date

data class Transaksi(
    var currency : String,
    var date: String,
    var documentCode : String,
    var price : Int,
    var productCode : String,
    var quantity : Int,
    var subTotal : Int,
    var unit : String,
    var user : String
)
