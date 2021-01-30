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
    // Dispatchers.IO函数线程类型设置，里面的代码全在子线程运行
    //获取用户
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
    //获取所有分类
    fun categories(type: Int) = fire(Dispatchers.IO) {
        val categoryResponse = HNBNetwork.categories(type)
        if (categoryResponse.code == 1) { //根据状态来处理
            LogUtil.d("分类模块", "获取分类成功，分类如下：${categoryResponse.data}")
            val categories = categoryResponse.data
            Result.success(categories)
        } else {
            LogUtil.d("分类模块", "获取分类失败，${categoryResponse.message}")
            Result.failure(RuntimeException("response status is ${categoryResponse.message}"))
        }
    }
    //获取所有产品
    fun products(type: Int) = fire(Dispatchers.IO) {
        val productsResponse = HNBNetwork.products(type)
        if (productsResponse.code == 1) { //根据状态来处理
            LogUtil.d("产品模块", "获取产品成功，分类如下：${productsResponse.data}")
            val products = productsResponse.data
            Result.success(products)
        } else {
            LogUtil.d("产品模块", "获取产品失败，${productsResponse.message}")
            Result.failure(RuntimeException("response status is ${productsResponse.message}"))
        }
    }
    //通过分类获取产品
    fun productsbycid(cid: Int) = fire(Dispatchers.IO) {
        val productsResponse = HNBNetwork.productsbycid(cid)
        if (productsResponse.code == 1) { //根据状态来处理
            LogUtil.d("产品模块", "获取产品成功，分类如下：${productsResponse.data}")
            val products = productsResponse.data
            Result.success(products)
        } else {
            LogUtil.d("产品模块", "获取产品失败，${productsResponse.message}")
            Result.failure(RuntimeException("response status is ${productsResponse.message}"))
        }
    }
    //通过id获取单个产品
    fun product(pid: Int) = fire(Dispatchers.IO) {
        val productsResponse = HNBNetwork.product(pid)
        if (productsResponse.code == 1) { //根据状态来处理
            LogUtil.d("产品模块", "获取产品成功，分类如下：${productsResponse.data}")
            val product = productsResponse.data
            Result.success(product)
        } else {
            LogUtil.d("产品模块", "获取产品失败，${productsResponse.message}")
            Result.failure(RuntimeException("response status is ${productsResponse.message}"))
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