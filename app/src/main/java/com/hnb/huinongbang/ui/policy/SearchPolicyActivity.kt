package com.hnb.huinongbang.ui.policy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hnb.huinongbang.R
import com.hnb.huinongbang.ui.planting.PlantsNewsAdapter
import com.hnb.huinongbang.ui.planting.SearchPlantViewModel
import com.hnb.huinongbang.util.LogUtil
import com.hnb.huinongbang.util.ToastUtil
import kotlinx.android.synthetic.main.activity_search_plant.*
import kotlinx.android.synthetic.main.activity_search_policy.*

class SearchPolicyActivity : AppCompatActivity() {
    val viewModel by lazy { ViewModelProviders.of(this).get(SearchPolicyViewModel::class.java)}
    private lateinit var policyAdapter: PolicyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_policy)
        val keyword = intent.getStringExtra("keyword")
        policySearch_text.text = keyword
        return_policySearch.setOnClickListener {
            onBackPressed()
        }
        viewModel.search(keyword.toString())
        viewModel.searchResult.observe(this, { result ->
            val list = result.getOrNull()
            if (list != null) {
                LogUtil.d("policySearch","qqqqqqqqqqqqqqqqqqqq")
                viewModel.policyList.clear()
                viewModel.policyList.addAll(list)
                policySearchRecycler.layoutManager = GridLayoutManager(this, 1, RecyclerView.VERTICAL,false)
                policyAdapter = PolicyAdapter(this, viewModel.policyList)
                policySearchRecycler.adapter = policyAdapter

            } else {
                ToastUtil.show("获取失败")
            }
        })
    }
}