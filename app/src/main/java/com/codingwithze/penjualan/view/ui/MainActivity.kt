package com.codingwithze.penjualan.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.codingwithze.penjualan.R
import com.codingwithze.penjualan.data.response.Data
import com.codingwithze.penjualan.databinding.ActivityMainBinding
import com.codingwithze.penjualan.view.adapter.ProductAdapter
import com.codingwithze.penjualan.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var productVM : ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        productVM = ViewModelProvider(this).get(ProductViewModel::class.java)
        getProduct()
    }

    fun getProduct(){
        productVM.callGetProduct()
        productVM.allProduct.observe(this){
            if (it != null){
                showProduct(it.data)
            }
        }
    }

    fun showProduct(data : List<Data>){
        val productAdapter = ProductAdapter(data)
        binding.rvProduct.adapter = productAdapter
        val layoutManager = GridLayoutManager(this, 2)
        binding.rvProduct.layoutManager = layoutManager

        productAdapter.onClick ={
            val send = Intent(this, DetailProductActivity::class.java)
            val bundle = Bundle()
            bundle.putString("productCode", it.productCode)
            bundle.putString("productName", it.productName)
            bundle.putString("unit", it.unit)
            bundle.putString("price", it.price)
            bundle.putString("image", it.image)
            bundle.putString("discount", it.discount)
            bundle.putString("dimension", it.dimension)
            bundle.putString("currency", it.currency)
            send.putExtras(bundle)
            startActivity(send)
        }
    }
}