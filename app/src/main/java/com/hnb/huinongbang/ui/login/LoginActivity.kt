package com.hnb.huinongbang.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.hnb.huinongbang.BottomActivity
import com.hnb.huinongbang.HNBApplication.Companion.context
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.LoginData
import com.hnb.huinongbang.logic.model.OrderItemResponse
import com.hnb.huinongbang.logic.network.ServiceCreator
import com.hnb.huinongbang.logic.network.BuyService
import com.hnb.huinongbang.ui.register.RegisterActivity
import com.hnb.huinongbang.util.LogUtil
import com.hnb.huinongbang.util.ToastUtil
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    val viewModel by lazy { ViewModelProviders.of(this).get(LoginViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //        记住账号
        if (Repository.isUserSaved()){
            val user = Repository.getUser()
            username.setText(user.user_phone)
            //        记住密码功能
            if (Repository.isRemembered()){
                password.setText(user.user_password)
                rememberPass.isChecked = true
            }
        }

        //登录按钮
        loginBtn.setOnClickListener {
            val name = username.text.toString()
            val password = password.text.toString()
            val loginData = LoginData(name, password)
            viewModel.login(loginData)
            LogUtil.d("测试", "测试开始")
            val testService = ServiceCreator.create<BuyService>()
            LogUtil.d("测试", "测试开始1")
            testService.buy(arrayOf("70", "72"), 0).enqueue(object : Callback<OrderItemResponse> {
                override fun onResponse(call: Call<OrderItemResponse>, response: Response<OrderItemResponse>) {
                    val OrderItemResponse = response.body()
                    LogUtil.d("测试", "1")
                    LogUtil.d("测试", "${OrderItemResponse}")
                }

                override fun onFailure(call: Call<OrderItemResponse>, t: Throwable) {
                    t.printStackTrace()
                    LogUtil.d("测试", "测试开始2")
                }
            })
        }
        //监听登录结果
        viewModel.loginLiveData.observe(this, {result ->
            val user = result.getOrNull()
            if(user != null){
                Repository.saveUser(user)
                if(rememberPass.isChecked){
                    Repository.remember()
                }else{
                    Repository.unremember()
                }
                val intent = Intent(context, BottomActivity::class.java)
                startActivity(intent)
            }else{
                ToastUtil.show("账号或密码错误")
            }
        })

        //立即注册
        toRegister.setOnClickListener {
            //跳转至注册页面
            val intent = Intent(context, RegisterActivity::class.java)
            startActivity(intent)
        }
    }


}
