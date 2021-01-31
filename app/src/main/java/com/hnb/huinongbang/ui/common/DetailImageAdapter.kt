package com.hnb.huinongbang.ui.common

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hnb.huinongbang.HNBApplication
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.model.ProductImage
import com.hnb.huinongbang.logic.network.ServiceCreator
import com.hnb.huinongbang.util.LogUtil

class DetailImageAdapter(private val activity: Activity, val detailImageList: List<ProductImage>) : RecyclerView.Adapter<DetailImageAdapter.ViewHolder>(){
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val detailImage: ImageView = view.findViewById(R.id.detailImage)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailImageAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.detailimage_recycle_item, parent, false)
        val holder = ViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: DetailImageAdapter.ViewHolder, position: Int) {
        val productImage = detailImageList[position]
        val imgUrl = ServiceCreator.productDetailImage+productImage.id+".jpg"
        LogUtil.d("imgUrl:", imgUrl)
        Glide.with(HNBApplication.context).load(imgUrl).into(holder.detailImage)
        LogUtil.d("详情图片", "${position} ${detailImageList[position]}")
    }

    override fun getItemCount() = detailImageList.size
}