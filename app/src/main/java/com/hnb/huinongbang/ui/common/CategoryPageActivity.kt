package com.hnb.huinongbang.ui.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.hnb.huinongbang.R
import com.hnb.huinongbang.util.LogUtil
import com.hnb.huinongbang.util.ToastUtil
import kotlinx.android.synthetic.main.activity_category_page.*

class CategoryPageActivity : AppCompatActivity() {

    val viewModel by lazy { ViewModelProviders.of(this).get(CategoryPageViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_page)

        //获取传递过来的分类id
        val cid = intent.extras!!.getInt("cid")
        //请求数据
        viewModel.getProducts(cid)

        //监听请求结果
        viewModel.productsLiveData.observe(this, {result ->
            val products = result.getOrNull()
            if(products != null){
                LogUtil.d("获取到的产品", "${products}")

                //瀑布流形式
                itemsRecycler.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
                //网格形式
                //itemsRecycler.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL,false)
                itemsRecycler.adapter = CategoryPageItemsAdapter(this, products)
            }else{
                ToastUtil.show("请求失败")
            }
        })

        //下拉刷新
        myRefresh.setColorSchemeResources(R.color.colorPrimary)
        myRefresh.setOnRefreshListener {
            getData()
        }
    }

    fun getData() {

        myRefresh.isRefreshing = false
    }
}