package com.hnb.huinongbang.ui.shopping

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.hnb.huinongbang.HNBApplication
import com.hnb.huinongbang.R
import com.hnb.huinongbang.ui.common.BannerDataBean
import com.hnb.huinongbang.ui.common.CategoryAdapter
import com.hnb.huinongbang.ui.common.ProductAdapter
import com.hnb.huinongbang.ui.common.SearchResultActivity
import com.hnb.huinongbang.ui.my.CartActivity
import com.hnb.huinongbang.util.LogUtil
import com.hnb.huinongbang.util.ToastUtil
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.fragment_shopping.*

class ShoppingFragment : Fragment() {

    val viewModel by lazy { ViewModelProviders.of(this).get(ShoppingViewModel::class.java)}

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var productAdapter: ProductAdapter

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
        refreshdata()
        viewModel.categoriesLiveData.observe(this, Observer { result ->
            val categories = result.getOrNull()
            if (categories != null){
                viewModel.categoryList.clear()
                viewModel.categoryList.addAll(categories)
                /*val categorylayoutManager = LinearLayoutManager(activity)
                shoppingRecycler.layoutManager = categorylayoutManager
                categoryAdapter = CategoryAdapter(this, viewModel.categoryList)*/

                //设置为一行显示
                //shoppingRecycler.layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
                //设置为两行显示
                shoppingRecycler.layoutManager = GridLayoutManager(this.context, 2, RecyclerView.HORIZONTAL,false)
                categoryAdapter = CategoryAdapter(this, viewModel.categoryList)

                shoppingRecycler.adapter = categoryAdapter
            }else {
                ToastUtil.show("没有分类")
                result.exceptionOrNull()?.printStackTrace()
            }
        })
        viewModel.productsLiveData.observe(this, Observer { result ->
            val products = result.getOrNull()
            if (products != null){
                viewModel.productList.clear()
                viewModel.productList.addAll(products)
                LogUtil.d("sssssss", "dddddddddddddd+${viewModel.productList[5]}")
                /*val productlayoutManager = LinearLayoutManager(activity)
                sproductRecycler.layoutManager = productlayoutManager*/


                //以2列形式显示
                sproductRecycler.layoutManager = GridLayoutManager(this.context, 2, RecyclerView.VERTICAL,false)

                productAdapter = ProductAdapter(this, viewModel.productList)
                sproductRecycler.adapter = productAdapter
            }else {
                ToastUtil.show("没有产品")
                result.exceptionOrNull()?.printStackTrace()
            }
            shoppingRefresh.isRefreshing = false
        })

        //下拉刷新
        shoppingRefresh.setColorSchemeResources(R.color.colorPrimary)
        shoppingRefresh.setOnRefreshListener {
            refreshdata()
            refreshBanner()
        }

        //搜索按钮监听
        search.setOnClickListener {
            val intent = Intent(HNBApplication.context, SearchResultActivity::class.java).apply {
                putExtra("keyword", searchView.text.toString())
                putExtra("type", 0)
            }
            startActivity(intent)
        }
    }
    fun refreshdata(){
        viewModel.getdata(0)
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