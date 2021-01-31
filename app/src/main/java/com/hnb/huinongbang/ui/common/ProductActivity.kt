package com.hnb.huinongbang.ui.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.hnb.huinongbang.HNBApplication
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.network.ServiceCreator
import com.hnb.huinongbang.util.ToastUtil
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity() {
    val viewModel by lazy { ViewModelProviders.of(this).get(ProductViewModel::class.java)}
    lateinit var detailImageAdapter: DetailImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        if (viewModel.pid == 0){
            viewModel.pid = intent.getIntExtra("pid", 1)
        }
        refreshdata()
        viewModel.productLiveData.observe(this, Observer { result ->
            val product = result.getOrNull()
            if (product != null){
                viewModel.product = product
                //详情图片
                viewModel.productDetailImages.clear()
                viewModel.productDetailImages.addAll(product.productDetailImages)
                //展示轮播图
                viewModel.bannerData.clear()
                for (productImage in product.productSingleImages){
                    viewModel.bannerData.add(BannerDataBean(ServiceCreator.productSingleImage+productImage.id+".jpg", null, 1))
                }
                refreshBanner(viewModel.bannerData)
                //产品信息
                name.text = product.name
                promotePrice.text = product.promotePrice.toString()
                if (product.type == 0){
                    shoppingmoney.visibility = View.VISIBLE
                    donatemoney.visibility = View.GONE
                }else {
                    shoppingmoney.visibility = View.GONE
                    donatemoney.visibility = View.VISIBLE
                }
                //产品属性

                //详情图
                val layoutManager = LinearLayoutManager(this)
                productRecycler.layoutManager = layoutManager
                detailImageAdapter = DetailImageAdapter(this, viewModel.productDetailImages)
                productRecycler.adapter = detailImageAdapter
            }else {
                ToastUtil.show("没有分类")
                result.exceptionOrNull()?.printStackTrace()
            }
            productRefresh.isRefreshing = false
        })
    }
    fun refreshdata(){
        viewModel.getdata(viewModel.pid)
        productRefresh.isRefreshing = true
    }
    fun refreshBanner(bannerData: List<BannerDataBean>){
        //轮播图
        var banner: Banner<BannerDataBean, BannerImageAdapter<BannerDataBean>> = this.findViewById(R.id.singlebanner)
        banner.setAdapter(object : BannerImageAdapter<BannerDataBean>(bannerData) {
            override fun onBindView(
                holder: BannerImageHolder,
                data: BannerDataBean,
                position: Int,
                size: Int
            ) {
                Glide.with(holder.itemView)
                    .load(data.imageUrl)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
                    .into(holder.imageView)
            }
        }).addBannerLifecycleObserver(this).setIndicator(CircleIndicator(HNBApplication.context))
    }
}