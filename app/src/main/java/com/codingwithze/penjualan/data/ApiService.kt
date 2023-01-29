package com.codingwithze.penjualan.data


import com.codingwithze.penjualan.data.response.Login
import com.codingwithze.penjualan.data.response.LoginResponse
import com.codingwithze.penjualan.data.response.ProductResponse
import com.codingwithze.penjualan.data.response.TransaksiDetailResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("login.php")
    fun postLogin(
        @Field("user") user : String,
        @Field("password") password : String
    ):Call<LoginResponse>

    @GET("restapi.php?function=getProduct")
    fun getProduct(): Call<ProductResponse>

    @FormUrlEncoded
    @POST("restapi.php?function=postTransactionDetail")
    fun postTransaksiDetail(
        @Field("currency") currency : String,
        @Field("date") date : String,
        @Field("document_code") documentCode : String,
        @Field("price") price : Int,
        @Field("product_code") productCode : String,
        @Field("quantity") quantity : Int,
        @Field("sub_total") subTotal : Int,
        @Field("unit") unit : String,
        @Field("user") user : String,
    ):Call<TransaksiDetailResponse>

    @FormUrlEncoded
    @POST("restapi.php?function=postTransactionDetail")
    fun postTransaksiHeader(
        @Field("currency") currency : String,
        @Field("date") date : String,
        @Field("document_code") documentCode : String,
        @Field("price") price : Int,
        @Field("product_code") productCode : String,
        @Field("quantity") quantity : Int,
        @Field("sub_total") subTotal : Int,
        @Field("unit") unit : String,
        @Field("user") user : String,
    ):Call<TransaksiDetailResponse>

}