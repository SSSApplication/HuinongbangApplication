package com.hnb.huinongbang.ui.planting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hnb.huinongbang.R
import com.hnb.huinongbang.util.ToastUtil
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
                plantsNewsAdapter = PlantsNewsAdapter(viewModel.plantsNewsList)

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
    }
}