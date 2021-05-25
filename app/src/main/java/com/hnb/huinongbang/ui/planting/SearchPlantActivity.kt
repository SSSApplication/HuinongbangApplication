package com.hnb.huinongbang.ui.planting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hnb.huinongbang.R
import com.hnb.huinongbang.util.LogUtil
import com.hnb.huinongbang.util.ToastUtil
import kotlinx.android.synthetic.main.activity_search_plant.*

class SearchPlantActivity : AppCompatActivity() {
    val viewModel by lazy { ViewModelProviders.of(this).get(SearchPlantViewModel::class.java)}
    private lateinit var plantAdapter: PlantsNewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_plant)
        val keyword = intent.getStringExtra("keyword")
        plantSearch_text.text = keyword
        return_plantSearch.setOnClickListener {
            onBackPressed()
        }
        LogUtil.d("qqqq","qqqq,${keyword.toString()}")
        viewModel.search(keyword.toString())
        viewModel.searchResult.observe(this, { result ->
            val list = result.getOrNull()
            if (list != null) {
                LogUtil.d("wwwwww","qqqqqqqqqqqqqqqqqqqq")
                viewModel.plantList.clear()
                viewModel.plantList.addAll(list)
                plantSearchRecycler.layoutManager = GridLayoutManager(this, 1, RecyclerView.VERTICAL,false)
                plantAdapter = PlantsNewsAdapter(this, viewModel.plantList)
                plantSearchRecycler.adapter = plantAdapter

            } else {
                ToastUtil.show("获取失败")
            }
        })
    }
}