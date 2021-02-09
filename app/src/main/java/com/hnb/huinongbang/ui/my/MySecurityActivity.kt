package com.hnb.huinongbang.ui.my

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hnb.huinongbang.HNBApplication
import com.hnb.huinongbang.R
import com.hnb.huinongbang.util.ToastUtil
import kotlinx.android.synthetic.main.activity_my_security.*

class MySecurityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_security)

        //下拉刷新
        mySecurityRefresh.setColorSchemeResources(R.color.colorPrimary)

        //设置点击事件监听器
        setClickListener()
    }

    //设置监听器函数
    fun setClickListener() {
        //更改密码
        changePassword.setOnClickListener {
            val intent = Intent(HNBApplication.context, ChangePasswordActivity::class.java)
            startActivity(intent)
        }

        //更改手机
        changePhone.setOnClickListener {
            ToastUtil.show("请前往官网个人中心更改手机号")
        }
    }
}