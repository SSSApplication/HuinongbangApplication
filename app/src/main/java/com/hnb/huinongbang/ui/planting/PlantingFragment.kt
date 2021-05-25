package com.hnb.huinongbang.ui.planting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
import com.hnb.huinongbang.ui.common.SearchResultActivity
import com.hnb.huinongbang.ui.planting.doctormodule.DoctorActivity
import com.hnb.huinongbang.util.ToastUtil
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.fragment_donate.*
import kotlinx.android.synthetic.main.fragment_planting.*
import kotlinx.android.synthetic.main.fragment_shopping.*

class PlantingFragment : Fragment() {

    val viewModel by lazy { ViewModelProviders.of(this).get(PlantingViewModel::class.java) }

    private lateinit var plantingCategoryAdapter: PlantingCategoryAdapter
    private lateinit var plantsNewsAdapter: PlantsNewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_planting, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        refreshdata()
        refreshBanner()
        //专家按钮的点击事件
        doctorButton.setOnClickListener {
            val intent: Intent = Intent(this.context,DoctorActivity::class.java)
            this.startActivity(intent)
        }

        plant_search.setOnClickListener {
            val intent = Intent(HNBApplication.context, SearchPlantActivity::class.java).apply {
                putExtra("keyword",plant_searchView.text.toString())
            }
            startActivity(intent)
        }

        //分类
        viewModel.plantingcategoriesLiveData.observe(this, Observer { result ->
            val plantingCategories = result.getOrNull()
            if (plantingCategories != null) {
                viewModel.plantingcategoryList.clear()
                viewModel.plantingcategoryList.addAll(plantingCategories)

                //设置为一行显示
                //shoppingRecycler.layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
                //设置为两行显示
                plantingcategoryRecycler.layoutManager =
                    GridLayoutManager(this.context, 1, RecyclerView.HORIZONTAL, false)
                plantingCategoryAdapter =
                    PlantingCategoryAdapter(this, viewModel.plantingcategoryList)

                plantingcategoryRecycler.adapter = plantingCategoryAdapter
            } else {
                ToastUtil.show("没有分类")
                result.exceptionOrNull()?.printStackTrace()
            }
//停止加载数据
            plantRefresh.isRefreshing = false
        })


        //最新
        viewModel.plantsNewsLiveData.observe(this, Observer { result ->
            val plantsNews = result.getOrNull()
            if (plantsNews != null) {
                viewModel.plantsNewsList.clear()
                viewModel.plantsNewsList.addAll(plantsNews)

                //设置为一行显示
                //shoppingRecycler.layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false)
                plantsnewsRecycler.layoutManager =
                    GridLayoutManager(this.context, 1, RecyclerView.VERTICAL, false)
                plantsNewsAdapter = PlantsNewsAdapter(activity!!, viewModel.plantsNewsList)

                plantsnewsRecycler.adapter = plantsNewsAdapter
            } else {
                ToastUtil.show("没有分类")
                result.exceptionOrNull()?.printStackTrace()
            }
            //停止加载数据
            plantRefresh.isRefreshing = false
        })


        //下拉刷新
        plantRefresh.setColorSchemeResources(R.color.colorPrimary)
        plantRefresh.setOnRefreshListener {
            refreshdata()
        }

    }

    fun refreshdata() {
        viewModel.getdata(0)
        plantRefresh.isRefreshing = true
        refreshBanner()
    }

    fun refreshBanner(){
        //轮播图
        var banner: Banner<BannerDataBean, BannerImageAdapter<BannerDataBean>> = activity!!.findViewById(R.id.plantingBanner)
        banner.setAdapter(object : BannerImageAdapter<BannerDataBean>(BannerDataBean.plantingData) {
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