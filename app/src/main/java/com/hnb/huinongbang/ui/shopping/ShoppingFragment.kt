package com.hnb.huinongbang.ui.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.hnb.huinongbang.HNBApplication
import com.hnb.huinongbang.R
import com.hnb.huinongbang.ui.common.BannerDataBean
import com.hnb.huinongbang.ui.common.CategoryAdapter
import com.hnb.huinongbang.util.ToastUtil
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.fragment_shopping.*

class ShoppingFragment : Fragment() {

    val viewModel by lazy { ViewModelProviders.of(this).get(ShoppingViewModel::class.java)}

    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shopping, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        refreshBanner()
        refreshCategories()
        viewModel.categoriesLiveData.observe(this, Observer { result ->
            val categories = result.getOrNull()
            if (categories != null){
                viewModel.categoryList.clear()
                viewModel.categoryList.addAll(categories)
                val layoutManager = LinearLayoutManager(activity)
                shoppingRecycler.layoutManager = layoutManager
                categoryAdapter = CategoryAdapter(this, viewModel.categoryList)
                shoppingRecycler.adapter = categoryAdapter
            }else {
                ToastUtil.show("没有分类")
                result.exceptionOrNull()?.printStackTrace()
            }
            shoppingRefresh.isRefreshing = false
        })

        //下拉刷新
        shoppingRefresh.setColorSchemeResources(R.color.colorPrimary)
        shoppingRefresh.setOnRefreshListener {
            refreshCategories()
            refreshBanner()
        }

        //分类

    }
    fun refreshCategories(){
        viewModel.categories(0)
        shoppingRefresh.isRefreshing = true
    }
    fun refreshBanner(){
        //轮播图
        var banner: Banner<BannerDataBean, BannerImageAdapter<BannerDataBean>> = activity!!.findViewById(R.id.sbanner)
        banner.setAdapter(object : BannerImageAdapter<BannerDataBean>(BannerDataBean.shoppingData) {
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