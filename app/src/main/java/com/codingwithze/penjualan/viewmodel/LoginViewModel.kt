package com.codingwithze.penjualan.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codingwithze.penjualan.data.ApiService
import com.codingwithze.penjualan.data.response.Login
import com.codingwithze.penjualan.data.response.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val api : ApiService) : ViewModel() {

    private val _loginUser = MutableLiveData<LoginResponse>()
    val loginUser : LiveData<LoginResponse> = _loginUser

   fun  callPostLogin(user : String, password : String){
        api.postLogin(user, password).enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful){
                    val data = response.body()
                    if (data != null){
                        _loginUser.postValue(data)
                    }
                }else{
                    Log.e("Error : ", "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("Error : ", "onFailure : ${t.message}")
            }

        })
    }
}