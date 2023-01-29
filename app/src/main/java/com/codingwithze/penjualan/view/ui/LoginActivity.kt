package com.codingwithze.penjualan.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.codingwithze.penjualan.databinding.ActivityLoginActiivtyBinding
import com.codingwithze.penjualan.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginActiivtyBinding
    lateinit var loginVM : LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginActiivtyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginVM = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.btnLogin.setOnClickListener {
            val user = binding.etUser.text.toString()
            val pass = binding.etPassword.text.toString()
            doLogin(user,pass)
        }
    }

    fun doLogin(_user : String, _pass : String) {

        loginVM.callPostLogin(_user, _pass)
        loginVM.loginUser.observe(this) {
            if (it != null) {
                if (it.error == false) {
                    startActivity(Intent(this, MainActivity::class.java))
                    Toast.makeText(this, "Welcome ${it.login.user}", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "User & Password Salah", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}