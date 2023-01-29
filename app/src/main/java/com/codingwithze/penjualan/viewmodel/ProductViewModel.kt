package com.codingwithze.penjualan.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codingwithze.penjualan.data.ApiService
import com.codingwithze.penjualan.data.response.*
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(val api : ApiService): ViewModel() {

    private val _allProduct = MutableLiveData<ProductResponse>()
    val allProduct : LiveData<ProductResponse> = _allProduct

    private val _transaksiDetail = MutableLiveData<TransaksiDetailResponse>()
    val transaksiDetail : LiveData<TransaksiDetailResponse> = _transaksiDetail

    fun  callGetProduct(){
        api.getProduct().enqueue(object : Callback<ProductResponse>{
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                if (response.isSuccessful){
                    val data = response.body()
                    if (data != null){
                        _allProduct.postValue(data)
                    }
                }else{
                    Log.e("Error: ", "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Log.e("Error: ", "onFailure : ${t.message}")
            }

        })
    }

    fun sendTransaksiDetail(
        qurrency : String, date : String, dcode : String, price : Int, pcode : String, qty : Int, subTotal : Int, unit : String, user : String
    ){
        api.postTransaksiDetail(qurrency,date,dcode,price,pcode,qty,subTotal,unit,user)
            .enqueue(object : Callback<TransaksiDetailResponse>{
            override fun onResponse(
                call: Call<TransaksiDetailResponse>,
                response: Response<TransaksiDetailResponse>
            ) {
                if (response.isSuccessful){
                    val data = response.body()
                    if (data != null){
                        _transaksiDetail.postValue(response.body())
                    }
                }else{
                    Log.e("Error: ", "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TransaksiDetailResponse>, t: Throwable) {
                Log.e("Error: ", "onFailure : ${t.message}")
            }

        })
    }
}