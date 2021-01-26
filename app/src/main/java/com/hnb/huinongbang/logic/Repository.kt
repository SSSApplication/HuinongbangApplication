package com.hnb.huinongbang.logic

import androidx.lifecycle.liveData
import com.hnb.huinongbang.logic.dao.UserDAO
import com.hnb.huinongbang.logic.model.LoginData
import com.hnb.huinongbang.logic.model.User
import com.hnb.huinongbang.logic.network.HNBNetwork
import com.hnb.huinongbang.util.LogUtil
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

object Repository {

    //获取用户 Dispatchers.IO函数线程类型设置，里面的代码全在子线程运行
    fun login(loginData: LoginData) = fire(Dispatchers.IO) {
        val userResponse = HNBNetwork.login(loginData)
        if (userResponse.code == 1) { //根据状态来处理
            LogUtil.d("登录模块", "登录成功，用户名：${userResponse.data.user_name}")
            val user = userResponse.data
            Result.success(user)
        } else {
            LogUtil.d("登录模块", "登录失败，${userResponse.message}")
            Result.failure(RuntimeException("response status is ${userResponse.message}"))
        }
    }

    //简化函数
    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) = liveData<Result<T>>(context) {
        val result = try {
            block()
        } catch (e: Exception) {
            Result.failure<T>(e)
        }
        emit(result)
    }

    //    用户操作封装
    fun saveUser(user: User) = UserDAO.saveUser(user)
    fun getUser() = UserDAO.getUser()
    fun isUserSaved() = UserDAO.isUserSaved()
    //记住密码
    fun remember() = UserDAO.remember()
    fun unremember() = UserDAO.unremember()
    fun isRemembered() = UserDAO.isRemembered()
}