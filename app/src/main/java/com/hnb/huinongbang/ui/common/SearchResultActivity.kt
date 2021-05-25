package com.hnb.huinongbang.ui.common

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hnb.huinongbang.HNBApplication
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.model.BuyCartData
import com.hnb.huinongbang.logic.model.SearchData
import com.hnb.huinongbang.util.LogUtil
import com.hnb.huinongbang.util.ToastUtil
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_search_result.*
import kotlinx.android.synthetic.main.fragment_shopping.*

class SearchResultActivity : AppCompatActivity() {

    val viewModel by lazy { ViewModelProviders.of(this).get(SearchResultViewModel::class.java)}
    private lateinit var productAdapter: CategoryPageItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        val keyword = intent.getStringExtra("keyword")
        val type = intent.getIntExtra("type", -1)
        search_text.text = keyword
        return_search.setOnClickListener {
            onBackPressed()
        }
        viewModel.search(
            SearchData(
                keyword.toString(),
                type.toString()
            )
        )
        //监听提交结果
        viewModel.searchResult.observe(this, { result ->
            val list = result.getOrNull()
            if (list != null) {
                viewModel.productList.clear()
                viewModel.productList.addAll(list)
                searchRecycler.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL,false)
                productAdapter = CategoryPageItemsAdapter(this, viewModel.productList)
                searchRecycler.adapter = productAdapter

            } else {
                ToastUtil.show("获取失败")
            }
        })
    }
}