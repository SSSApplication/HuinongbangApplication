package com.hnb.huinongbang.ui.common

import android.content.Intent
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
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.AddCartData
import com.hnb.huinongbang.logic.model.PayForDonationData
import com.hnb.huinongbang.logic.network.ServiceCreator
import com.hnb.huinongbang.util.LogUtil
import com.hnb.huinongbang.util.ToastUtil
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.activity_product.*
import kotlinx.android.synthetic.main.fragment_shopping.*

class ProductActivity : AppCompatActivity() {
    val viewModel by lazy { ViewModelProviders.of(this).get(ProductViewModel::class.java)}
    lateinit var detailImageAdapter: DetailImageAdapter
    lateinit var propertyValuesAdapter: PropertyValuesAdapter
    lateinit var reviewAdapter: ReviewAdapter
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

                    //立即购买按钮显示
                    buyNow.visibility = View.VISIBLE
                    //加入购物车按钮显示
                    addToCart.visibility = View.VISIBLE
                    //我需要按钮隐藏
                    needNow.visibility = View.GONE
                    //加入清单按钮隐藏
                    addToList.visibility = View.GONE
                }else {
                    shoppingmoney.visibility = View.GONE
                    donatemoney.visibility = View.VISIBLE

                    //立即购买按钮隐藏
                    buyNow.visibility = View.GONE
                    //加入购物车按钮隐藏
                    addToCart.visibility = View.GONE
                    //我需要按钮显示
                    needNow.visibility = View.VISIBLE
                    //加入清单按钮显示
                    addToList.visibility = View.VISIBLE
                }
                //产品属性

                //详情图
                val layoutManager = LinearLayoutManager(this)
                productRecycler.layoutManager = layoutManager
                detailImageAdapter = DetailImageAdapter(this, viewModel.productDetailImages)
                productRecycler.adapter = detailImageAdapter

                //设置点击事件监听器
                setClickListener()
            }else {
                ToastUtil.show("没有分类")
                result.exceptionOrNull()?.printStackTrace()
            }
            //评论
            if (product?.type == 0){
                viewModel.reviewsLiveData.observe(this, Observer { result ->
                    val reviews = result.getOrNull()
                    if (reviews != null){
                        viewModel.reviews.clear()
                        viewModel.reviews.addAll(reviews)

                        //评价
                        val layoutManager = LinearLayoutManager(this)
                        reviewRecycler.layoutManager = layoutManager
                        reviewAdapter = ReviewAdapter(this, viewModel.reviews)
                        reviewRecycler.adapter = reviewAdapter

                    }else{
                        noreview.visibility = View.VISIBLE
                        ToastUtil.show("没有评价")
                        result.exceptionOrNull()?.printStackTrace()
                    }

                })
            }
            productRefresh.isRefreshing = false
        })

        viewModel.propertyValuesLiveData.observe(this, Observer { result ->
            val propertyValues = result.getOrNull()
            if (propertyValues != null){
                viewModel.propertyValues.clear()
                viewModel.propertyValues.addAll(propertyValues)

                //属性
                val layoutManager = LinearLayoutManager(this)
                propertyRecycler.layoutManager = layoutManager
                propertyValuesAdapter = PropertyValuesAdapter(this, viewModel.propertyValues)
                propertyRecycler.adapter = propertyValuesAdapter
            }else{
                ToastUtil.show("没有属性")
                result.exceptionOrNull()?.printStackTrace()
            }
        })
        //下拉刷新
        productRefresh.setColorSchemeResources(R.color.colorPrimary)
        productRefresh.setOnRefreshListener {
            refreshdata()
        }

        return_btn.setOnClickListener {
            onBackPressed()
        }

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
    //设置监听器函数
    fun setClickListener() {
        //立即购买
        buyNow.setOnClickListener {
            createOrder()
        }
        //加入购物车
        addToCart.setOnClickListener {
            addCart()
        }
        //我需要
        needNow.setOnClickListener {
            createOrder()
        }
        //加入计划清单
        addToList.setOnClickListener {
            addCart()
        }
    }

    fun createOrder(){
        val intent = Intent(HNBApplication.context, CreateOrderActivity::class.java).apply {
            putExtra("pid", viewModel.product.id)
            putExtra("type", viewModel.product.type)
        }
        startActivity(intent)
    }
    fun addCart(){
        //加入购物车或清单
        viewModel.addCart(
            AddCartData(
                viewModel.product.id.toString(),
                viewModel.product.type.toString(),
                Repository.getUser().user_ID.toString(),
                1.toString()
            )
        )
        //监听加入购物车或清单返回结果
        viewModel.addCartResult.observe(this, Observer { result ->
            val response = result.getOrNull()
//            LogUtil.d("加入购物车", "${response}")
            ToastUtil.show(response.toString())
//            if (response != null){
//                ToastUtil.show("加入成功")
//            }else{
//                ToastUtil.show("加入失败")
//                result.exceptionOrNull()?.printStackTrace()
//            }
        })
    }
}