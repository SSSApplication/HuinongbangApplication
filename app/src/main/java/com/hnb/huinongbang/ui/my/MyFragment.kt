package com.hnb.huinongbang.ui.my

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.Repository
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
        //用户名赋值
        username.text = user.user_name
        //个人简介赋值
        userIntroduce.text = user.user_introduce

    }
}