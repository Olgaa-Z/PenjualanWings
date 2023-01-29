package com.codingwithze.penjualan.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.codingwithze.penjualan.databinding.ActivityCheckOutBinding
import com.codingwithze.penjualan.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckOutActivity : AppCompatActivity() {
    lateinit var binding : ActivityCheckOutBinding
    lateinit var transaksiVM : ProductViewModel
    lateinit var name : String
    lateinit var img : String
    lateinit var pcode : String
    lateinit var unit : String
    lateinit var currencyProduct : String
    var quantity: Int? = 0
    var price: Int? = 0
    var total: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckOutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        transaksiVM = ViewModelProvider(this).get(ProductViewModel::class.java)

        getProductDetail()
        setToXml()
        binding.btnConfirm.setOnClickListener {
            doTransaction()
        }


    }

    fun getProductDetail(){
        val detail = intent.extras
        if (detail!= null){
            pcode = detail.getString("productCode").toString()
            price = detail.getString("pricefix")?.toInt()
            quantity = detail.getString("quantity")?.toInt()
            unit = detail.getString("unit").toString()
            currencyProduct = detail.getString("currency").toString()
            img = detail.getString("img").toString()
            name = detail.getString("name").toString()
            total = quantity!!.toInt() * price!!.toInt()
        }

    }

    fun setToXml(){
        binding.ckName.text = name
        binding.ckJumlah.text = "Jumlah Beli : " + quantity.toString()
        binding.unit.text = unit
        binding.ckPrice.text = "Harga/$unit : " + "Rp. " +  price.toString()
        binding.total.text = "Rp. " + total.toString()
        Glide.with(this).load(img).into(binding.ckImage)
    }


    fun doTransaction(){
        transaksiVM.sendTransaksiDetail(currencyProduct,"2022-01-12","TRX",price!!,pcode,quantity!!, total!!, unit,"Hendra")
        transaksiVM.transaksiDetail.observe(this){
            if (it != null){
                Toast.makeText(this, "Transaksi Berhasil", Toast.LENGTH_SHORT).show()
            }
        }
    }


}