package com.hnb.huinongbang.ui.my

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.hnb.huinongbang.BottomActivity
import com.hnb.huinongbang.HNBApplication
import com.hnb.huinongbang.R
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.UpdateMyInformationData
import com.hnb.huinongbang.logic.model.User
import com.hnb.huinongbang.util.ToastUtil
import kotlinx.android.synthetic.main.activity_my_information.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class MyInformationActivity : AppCompatActivity() {

    val viewModel by lazy { ViewModelProviders.of(this).get(MyInformationViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_information)

        return_btn.setOnClickListener {
            onBackPressed()
        }
        //获取当前用户信息
        val user = Repository.getUser()

        //获取数据并赋值数据
        getData(user)

        //下拉刷新
        myInformationRefresh.setColorSchemeResources(R.color.colorPrimary)
        myInformationRefresh.setOnRefreshListener {
            getData(user)
        }

        //保存按钮
        save.setOnClickListener {
            viewModel.update(
                UpdateMyInformationData(
                    userName.text.toString(),
                    userNickName.text.toString(),
                    userSex.text.toString(),
                    userBirthdayYear.text.toString() + "-" + userBirthdayMonth.text.toString() + "-" + userBirthdayDay.text.toString(),
                    userAddress.text.toString(),
                    userIntroduce.text.toString(),
                    user.user_phone,
                    user.user_password
                )
            )
        }

        //监听保存结果
        viewModel.updateResult.observe(this, { result ->
            val user = result.getOrNull()
            if (user != null) {
                Repository.clearUser(Repository.getUser())
                Repository.saveUser(user)
                val intent = Intent(HNBApplication.context, BottomActivity::class.java)
                startActivity(intent)
            } else {
                ToastUtil.show("保存失败")
            }
        })

    }

    fun getData(user: User) {
        //显示个人相关信息
        userName.setText(user.user_name)
        userNickName.setText(user.user_nickname)
        userSex.setText(user.user_sex)
        userBirthdayYear.setText(user.getUser_birthday_year())
        userBirthdayMonth.setText(user.getUser_birthday_month())
        userBirthdayDay.setText(user.getUser_birthday_day())
        userAddress.setText(user.user_address)
        userIntroduce.setText(user.user_introduce)

        //关闭刷新
        myInformationRefresh.isRefreshing = false
    }
}