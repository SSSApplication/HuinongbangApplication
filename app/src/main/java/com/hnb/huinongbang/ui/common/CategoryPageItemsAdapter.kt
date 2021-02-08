package com.hnb.huinongbang.ui.common

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hnb.huinongbang.HNBApplication
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.model.Product
import com.hnb.huinongbang.logic.network.ServiceCreator
import com.hnb.huinongbang.util.LogUtil

class CategoryPageItemsAdapter (private val activity: Activity, val productsList: List<Product>) : RecyclerView.Adapter<CategoryPageItemsAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val firstProductImage: ImageView = view.findViewById(R.id.firstProductImage)
        val name: TextView = view.findViewById(R.id.name)
        val promotePrice: TextView = view.findViewById(R.id.promotePrice)
        val shoppingmoney: TextView = view.findViewById(R.id.shoppingmoney)
        val donatemoney: ImageView = view.findViewById(R.id.donatemoney)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_recycle_item, parent, false)
        val holder = ViewHolder(view)
        holder.firstProductImage.setOnClickListener {
            val position = holder.adapterPosition
            LogUtil.d("productListposition", "${position} ${productsList[position]}")
            val product = productsList[position]
            val intent = Intent(parent.context, ProductActivity::class.java).apply {
                putExtra("pid", product.id)
            }
            this.activity.startActivity(intent)
        }
        holder.name.setOnClickListener {
            val position = holder.adapterPosition
            LogUtil.d("productListposition", "${position} ${productsList[position]}")
            val product = productsList[position]
            val intent = Intent(parent.context, ProductActivity::class.java).apply {
                putExtra("pid", product.id)
            }
            this.activity.startActivity(intent)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productsList[position]
        val imgUrl = ServiceCreator.firstProductImage+product.firstProductImage.id+".jpg"
        LogUtil.d("imgUrl:", imgUrl)
        Glide.with(HNBApplication.context).load(imgUrl).into(holder.firstProductImage)
        holder.name.text = product.name //显示名称字数
        holder.promotePrice.text = product.promotePrice.toString()
        if (product.type == 0){
            holder.shoppingmoney.visibility = View.VISIBLE
            holder.donatemoney.visibility = View.GONE
        }else {
            holder.shoppingmoney.visibility = View.GONE
            holder.donatemoney.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return productsList.size
    }
}