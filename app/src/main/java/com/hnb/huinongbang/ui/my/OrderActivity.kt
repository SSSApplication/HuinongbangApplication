package com.hnb.huinongbang.ui.my

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.GetCartData
import com.hnb.huinongbang.logic.model.GetOrderData
import com.hnb.huinongbang.util.ToastUtil

class OrderActivity : AppCompatActivity() {

    val viewModel by lazy { ViewModelProviders.of(this).get(OrderViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        //获取用户类
        val user = Repository.getUser()

        val type = intent.getIntExtra("type", -1)
        ToastUtil.show(type.toString())

        viewModel.getOrder(
            GetOrderData(
                user.user_ID.toString(),
                type.toString()
            )
        )
        //监听提交结果
        viewModel.getOrderResult.observe(this, { result ->
            val list = result.getOrNull()
            if (list != null) {
                ToastUtil.show(list.toString())
            } else {
                ToastUtil.show("获取失败")
            }
        })
    }
}