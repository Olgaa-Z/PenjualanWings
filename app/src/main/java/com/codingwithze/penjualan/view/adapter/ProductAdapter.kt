package com.codingwithze.penjualan.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codingwithze.penjualan.data.response.Data
import com.codingwithze.penjualan.data.response.ProductResponse
import com.codingwithze.penjualan.databinding.ItemProductBinding

class ProductAdapter(var listProduct : List<Data>): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    var onClick : ((Data) -> Unit)? = null
    class ViewHolder(var binding : ItemProductBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ItemProductBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataProduct = listProduct[position]
        holder.binding.productName.text = dataProduct.productName
        holder.binding.productPrice.text = "Rp " + dataProduct.price
        Glide.with(holder.itemView).load(dataProduct.image).into(holder.binding.productImage)
        holder.binding.cardProduct.setOnClickListener {
            onClick?.invoke(dataProduct)
        }
        val disc = dataProduct.discount.toInt()
        if (disc > 0){
            holder.binding.productDiscount.text ="Discount : " + dataProduct.discount + "%"
        }else{
            holder.binding.productDiscount.text = ""
        }
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }
}