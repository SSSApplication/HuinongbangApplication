package com.hnb.huinongbang.ui.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.model.BuyCartData
import com.hnb.huinongbang.logic.model.SearchData
import com.hnb.huinongbang.util.LogUtil
import com.hnb.huinongbang.util.ToastUtil
import kotlinx.android.synthetic.main.activity_cart.*

class SearchResultActivity : AppCompatActivity() {

    val viewModel by lazy { ViewModelProviders.of(this).get(SearchResultViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        val keyword = intent.getStringExtra("keyword")
        val type = intent.getIntExtra("type", -1)


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
                ToastUtil.show(list.toString())
            } else {
                ToastUtil.show("获取失败")
            }
        })
    }
}