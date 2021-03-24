package com.hnb.huinongbang.ui.my

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.GetCartData
import com.hnb.huinongbang.util.ToastUtil

class ListActivity : AppCompatActivity() {

    val viewModel by lazy { ViewModelProviders.of(this).get(CartViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        //获取用户类
        val user = Repository.getUser()

        viewModel.getCart(
            GetCartData(
                user.user_ID.toString(),
                1.toString()
            )
        )
        //监听提交结果
        viewModel.getCartResult.observe(this, { result ->
            val list = result.getOrNull()
            if (list != null) {
                ToastUtil.show(list.toString())
            } else {
                ToastUtil.show("获取失败")
            }
        })
    }
}