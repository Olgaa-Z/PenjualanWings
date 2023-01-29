package com.codingwithze.penjualan.view.ui

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.codingwithze.penjualan.R
import com.codingwithze.penjualan.databinding.ActivityDetailProductBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class DetailProductActivity : AppCompatActivity() {
    lateinit var binding : ActivityDetailProductBinding
    var count = 0
    lateinit var productCode : String
    lateinit var productName : String
    lateinit var productDimension : String
    lateinit var productImage : String
    lateinit var unitProduct : String
    lateinit var currencyProduct : String
    var price: Int? = 0
    var discount: Int? = 0
    var quantity : Int? = 0
    var priceAfterDiscount : Int? = 0
    var subTotal : Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setCount()
        getDetail()
        setToXml()
        binding.btnBuyNow.setOnClickListener {
            if (count == 0 || count < 0 ){
                Toast.makeText(this, "Masukkan Jumlah Beli : Minimal 1", Toast.LENGTH_SHORT).show()
            }else{
                goToCheckout()
            }

        }
    }

    fun getDetail(){
        val detailProduct = intent.extras
        if (detailProduct != null){
            productCode = detailProduct.getString("productCode").toString()
            productName = detailProduct.getString("productName").toString()
            productImage = detailProduct.getString("image").toString()
            productDimension = detailProduct.getString("dimension").toString()
            unitProduct = detailProduct.getString("unit").toString()
            currencyProduct = detailProduct.getString("currency").toString()
            price = detailProduct.getString("price")?.toInt()
            discount = detailProduct.getString("discount")?.toInt()
            quantity = count
            val persen = 0.01 * discount!!.toInt()
            val countDisc = (persen *  price!!.toInt())
            priceAfterDiscount = price!!.toInt() - countDisc.toInt()
        }
    }

    fun setToXml(){
            binding.detailPrice.text = "Rp. " + price.toString()
            binding.detailName.text = productName
            binding.detailDimension.text = productDimension
            binding.detailUnit.text = "Unit : " + unitProduct
            binding.priceAfterDicount.text = "Rp. " + priceAfterDiscount.toString()
            Glide.with(this).load(productImage).into(binding.detailImage)

            if (discount!!.toInt() > 0){
                binding.detaiDiscount.text = "Diskon " + discount + "%"
                binding.detaiDiscount.isVisible
                binding.detailPrice.paintFlags =binding.detailPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }else{
                binding.detaiDiscount.text =""
                binding.priceAfterDicount.text =""
            }
    }


    fun setCount(){
        binding.incNum.setOnClickListener {
            count++
            binding.jumlah.text = count.toString()
        }
        binding.decNum.setOnClickListener {
            count--
            binding.jumlah.text = count.toString()
        }
    }

    fun goToCheckout(){
        val bundle = Bundle()
        bundle.putString("productCode",productCode)
        bundle.putString("pricefix",priceAfterDiscount.toString())
        bundle.putString("img",productImage)
        bundle.putString("name",productName)
        bundle.putString("quantity",count.toString())
        bundle.putString("unit",unitProduct)
        bundle.putString("currency",currencyProduct)
        bundle.putString("pricefix",priceAfterDiscount.toString())

        val move = Intent(this, CheckOutActivity::class.java)
        move.putExtras(bundle)
        startActivity(move)
    }
}