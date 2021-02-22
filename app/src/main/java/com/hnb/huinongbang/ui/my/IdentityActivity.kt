package com.hnb.huinongbang.ui.my

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.hnb.huinongbang.HNBApplication
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.IdentityData
import com.hnb.huinongbang.util.ToastUtil
import kotlinx.android.synthetic.main.activity_identity.*

class IdentityActivity : AppCompatActivity() {

    val viewModel by lazy { ViewModelProviders.of(this).get(IdentityViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identity)

        //获取用户类
        val user = Repository.getUser()

        return_btn.setOnClickListener {
            onBackPressed()
        }
        //提交按钮
        submit.setOnClickListener {
            viewModel.submit(
                IdentityData(
                    user.user_phone,
                    user.user_password,
                    name.text.toString(),
                    type.text.toString(),
                    number.text.toString()
                )
            )
        }

        //监听提交结果
        viewModel.submitResult.observe(this, { result ->
            val user = result.getOrNull()
            if (user != null) {
                Repository.saveUser(user)
                val intent = Intent(HNBApplication.context, MyAuthenticationActivity::class.java)
                startActivity(intent)
            } else {
                ToastUtil.show("提交失败")
            }
        })
    }
}