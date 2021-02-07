package com.hnb.huinongbang.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.hnb.huinongbang.BottomActivity
import com.hnb.huinongbang.HNBApplication
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.RegisterData
import com.hnb.huinongbang.ui.login.LoginActivity
import com.hnb.huinongbang.util.ToastUtil
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    val viewModel by lazy { ViewModelProviders.of(this).get(RegisterViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //注册按钮
        registerBtn.setOnClickListener {
            val phone = phone.text.toString()
            val password = registerPassword.text.toString()
            val registerData = RegisterData(phone, password)
            viewModel.register(registerData)
        }
        //监听注册结果
        viewModel.registerLiveData.observe(this, {result ->
            val user = result.getOrNull()
            if(user != null){
                Repository.saveUser(user)
                val intent = Intent(HNBApplication.context, BottomActivity::class.java)
                startActivity(intent)
            }else{
                ToastUtil.show("注册失败")
            }
        })
        //立即登陆
        toLogin.setOnClickListener {
            //跳转至注册页面
            val intent = Intent(HNBApplication.context, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}