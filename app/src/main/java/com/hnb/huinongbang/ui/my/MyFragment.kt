package com.hnb.huinongbang.ui.my

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.hnb.huinongbang.HNBApplication
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.User
import com.hnb.huinongbang.logic.network.ServiceCreator
import com.hnb.huinongbang.ui.common.CreateOrderActivity
import com.hnb.huinongbang.ui.login.LoginActivity
import com.hnb.huinongbang.util.ToastUtil
import kotlinx.android.synthetic.main.fragment_my.*

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

        //获取用户类
        val user = Repository.getUser()

        //获取头像并赋值数据
        getData(user)

        //下拉刷新
        myRefresh.setColorSchemeResources(R.color.colorPrimary)
        myRefresh.setOnRefreshListener {
            getData(user)
        }

        //设置点击事件监听器
        setClickListener(user)
    }

    fun getData(user: User) {

        //用户名赋值
        username.text = user.user_name
        //个人简介赋值
        userIntroduce.text = user.user_introduce
        //获取头像并赋值
        val avatarUrl = ServiceCreator.avatar + user.user_ID + ".jpg" + "?" + (Math.random()*100).toInt()
        Glide.with(HNBApplication.context).load(avatarUrl).into(avatar)

        //关闭刷新
        myRefresh.isRefreshing = false
    }

    //设置监听器函数
    fun setClickListener(user: User ) {
        //我的信息
        information.setOnClickListener {
            val intent = Intent(HNBApplication.context, MyInformationActivity::class.java)
            startActivity(intent)
        }

        //账号安全
        security.setOnClickListener {
            val intent = Intent(HNBApplication.context, MySecurityActivity::class.java)
            startActivity(intent)
        }

        //购物车
        shoppingCart.setOnClickListener {
            val intent = Intent(HNBApplication.context, CartActivity::class.java).apply {
                putExtra("type", 0)
            }
            startActivity(intent)
        }

        //计划清单
        addToList.setOnClickListener {
            val intent = Intent(HNBApplication.context, CartActivity::class.java).apply {
                putExtra("type", 1)
            }
            startActivity(intent)
        }

        //认证相关
        authentication.setOnClickListener {
            val intent = Intent(HNBApplication.context, MyAuthenticationActivity::class.java)
            startActivity(intent)
        }

        //捐赠物品
        donation.setOnClickListener {
            ToastUtil.show("请移步至官网进行该操作！")
        }

        //已申请捐赠订单
        donationOrder.setOnClickListener {
            val intent = Intent(HNBApplication.context, OrderActivity::class.java).apply {
                putExtra("type", 1)
            }
            startActivity(intent)
        }

        //购买物品
        sale.setOnClickListener {
            ToastUtil.show("请移步至官网进行该操作！")
        }

        //已购买售卖订单
        saleOrder.setOnClickListener {
            val intent = Intent(HNBApplication.context, OrderActivity::class.java).apply {
                putExtra("type", 0)
            }
            startActivity(intent)
        }

        //退出登录
        signOut.setOnClickListener {
            Repository.clearUser(Repository.getUser())
            val intent = Intent(HNBApplication.context, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}