package com.hnb.huinongbang.ui.my

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hnb.huinongbang.HNBApplication
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.User
import com.hnb.huinongbang.util.ToastUtil
import kotlinx.android.synthetic.main.activity_my_authentication.*

class MyAuthenticationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_authentication)

        return_btn.setOnClickListener {
            onBackPressed()
        }
        //获取当前用户信息
        val user = Repository.getUser()

        val identitySign = user.user_identitysign
        if(identitySign == 0){
            identityCheck.text = "未认证"
            doctorCheck.text = "未实名"
            poorCheck.text = "未实名"
        }else if(identitySign == 1){
            identityCheck.text = "已认证"
            when (user.user_doctorsign) {
                0 -> {
                    doctorCheck.text = "未认证"
                }
                1 -> {
                    doctorCheck.text = "已认证"
                }
                2 -> {
                    doctorCheck.text = "审核中"
                }
                3 -> {
                    doctorCheck.text = "未通过"
                }
                else -> {
                    doctorCheck.text = "错误"
                }
            }
            when (user.user_poorsign) {
                0 -> {
                    poorCheck.text = "未认证"
                }
                1 -> {
                    poorCheck.text = "已认证"
                }
                2 -> {
                    poorCheck.text = "审核中"
                }
                3 -> {
                    poorCheck.text = "未通过"
                }
                else -> {
                    poorCheck.text = "错误"
                }
            }
        }
        else if(identitySign == 2){
            identityCheck.text = "审核中"
            doctorCheck.text = "未实名"
            poorCheck.text = "未实名"
        }
        else if(identitySign == 3){
            identityCheck.text = "未通过"
            doctorCheck.text = "未实名"
            poorCheck.text = "未实名"
        }else{
            identityCheck.text = "错误"
            doctorCheck.text = "错误"
            poorCheck.text = "错误"
        }

        //下拉刷新
        myAuthenticationRefresh.setColorSchemeResources(R.color.colorPrimary)

        //设置点击事件监听器
        setClickListener(user)

    }

    //设置监听器函数
    fun setClickListener(user: User) {
        //实名认证
        identity.setOnClickListener {
            when (user.user_identitysign) {
                0 -> {
                    val intent = Intent(HNBApplication.context, IdentityActivity::class.java)
                    startActivity(intent)
                }
                1 -> {
                    ToastUtil.show("实名认证已认证，请前往官网个人中心确认")
                }
                2 -> {
                    ToastUtil.show("实名认证审核中，请耐心仍待")
                }
                3 -> {
                    ToastUtil.show("实名认证未通过，请前往官网个人中心重新认证")
                }
                else -> {
                    ToastUtil.show("数据出错，请尝试刷新页面或与客服取得联系")
                }
            }
        }

        //专家认证
        doctor.setOnClickListener {
            when (user.user_doctorsign) {
                0 -> {
                    ToastUtil.show("专家认证未认证，请前往官网个人中心认证")
                }
                1 -> {
                    ToastUtil.show("专家认证已认证，请前往官网个人中心确认")
                }
                2 -> {
                    ToastUtil.show("专家认证审核中，请耐心仍待")
                }
                3 -> {
                    ToastUtil.show("专家认证未通过，请前往官网个人中心重新认证")
                }
                else -> {
                    ToastUtil.show("数据出错，请尝试刷新页面或与客服取得联系")
                }
            }
        }

        //贫困认定
        poor.setOnClickListener {
            when (user.user_poorsign) {
                0 -> {
                    ToastUtil.show("贫困认定未认证，请前往官网个人中心认证")
                }
                1 -> {
                    ToastUtil.show("贫困认定已认证，请前往官网个人中心确认")
                }
                2 -> {
                    ToastUtil.show("贫困认定审核中，请耐心仍待")
                }
                3 -> {
                    ToastUtil.show("贫困认定未通过，请前往官网个人中心重新认证")
                }
                else -> {
                    ToastUtil.show("数据出错，请尝试刷新页面或与客服取得联系")
                }
            }
        }
    }
}