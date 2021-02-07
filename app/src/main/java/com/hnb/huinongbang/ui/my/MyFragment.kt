package com.hnb.huinongbang.ui.my

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.hnb.huinongbang.BottomActivity
import com.hnb.huinongbang.HNBApplication
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.network.ServiceCreator
import com.hnb.huinongbang.ui.login.LoginActivity
import com.hnb.huinongbang.ui.shopping.ShoppingFragment
import com.hnb.huinongbang.util.LogUtil
import kotlinx.android.synthetic.main.fragment_my.*
import kotlinx.android.synthetic.main.fragment_shopping.*

class MyFragment : Fragment() {

    val viewModel by lazy { ViewModelProviders.of(this).get(MyViewModel::class.java)}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //获取数据
        getData()

        //下拉刷新
        myRefresh.setColorSchemeResources(R.color.colorPrimary)
        myRefresh.setOnRefreshListener {
            getData()
        }

    }

    fun getData() {
        //获取用户类
        val user = Repository.getUser()
        //用户名赋值
        username.text = user.user_name
        //个人简介赋值
        userIntroduce.text = user.user_introduce
        //获取头像并赋值
        val avatarUrl = ServiceCreator.avatar + user.user_ID + ".jpg" + "?" + (Math.random()*100).toInt()
        Glide.with(HNBApplication.context).load(avatarUrl).into(avatar)

        myRefresh.isRefreshing = false
    }
}