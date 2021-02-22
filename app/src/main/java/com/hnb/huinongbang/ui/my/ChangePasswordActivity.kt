package com.hnb.huinongbang.ui.my

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.hnb.huinongbang.HNBApplication
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.ChangePasswordData
import com.hnb.huinongbang.util.ToastUtil
import kotlinx.android.synthetic.main.activity_change_password.*

class ChangePasswordActivity : AppCompatActivity() {

    val viewModel by lazy { ViewModelProviders.of(this).get(ChangePasswordViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        return_btn.setOnClickListener {
            onBackPressed()
        }
        //更改按钮
        change.setOnClickListener {
            if( oldPassword.text.toString().equals(newPassword.text.toString())){
                ToastUtil.show("新密码不能与旧密码一致")
            }else{
                viewModel.change(
                    ChangePasswordData(
                        userPhone.text.toString(),
                        oldPassword.text.toString(),
                        newPassword.text.toString()
                    )
                )
            }
        }

        //监听更改结果
        viewModel.changeResult.observe(this, { result ->
            val user = result.getOrNull()
            if (user != null) {
                Repository.saveUser(user)
                val intent = Intent(HNBApplication.context, MySecurityActivity::class.java)
                startActivity(intent)
            } else {
                ToastUtil.show("更改失败")
            }
        })
    }
}