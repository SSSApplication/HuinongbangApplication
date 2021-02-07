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
            LogUtil.d("分" +
                    ".000000000000000000000000000000000000类模块", "获取分类成功，分类如下：${categoryResponse.data}")
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
    //通过id获取属性
    fun propertyValues(pid: Int) = fire(Dispatchers.IO) {
        val propertyValueResponse = HNBNetwork.propertyValues(pid)
        if (propertyValueResponse.code == 1) { //根据状态来处理
            LogUtil.d("属性模块", "获取属性成功，分类如下：${propertyValueResponse.data}")
            val propertyValues = propertyValueResponse.data
            Result.success(propertyValues)
        } else {
            LogUtil.d("属性模块", "获取属性失败，${propertyValueResponse.message}")
            Result.failure(RuntimeException("response status is ${propertyValueResponse.message}"))
        }
    }
    //通过id获取评价
    fun reviews(pid: Int) = fire(Dispatchers.IO) {
        val reviewsResponse = HNBNetwork.reviews(pid)
        if (reviewsResponse.code == 1) { //根据状态来处理
            LogUtil.d("评价模块", "获取评价成功，分类如下：${reviewsResponse.data}")
            val reviews = reviewsResponse.data
            Result.success(reviews)
        } else {
            LogUtil.d("评价模块", "获取评价失败，${reviewsResponse.message}")
            Result.failure(RuntimeException("response status is ${reviewsResponse.message}"))
        }
    }

    //种植模块获取所有分类
    fun plantingCategories(type: Int) = fire(Dispatchers.IO) {
        val plantingCategoryResponse = HNBNetwork.plantingCategories(type)
        if (plantingCategoryResponse.code == 1) { //根据状态来处理
            LogUtil.d("种植模块分类", "获取分类成功，分类如下：${plantingCategoryResponse.data}")
            val plantingCategories = plantingCategoryResponse.data
            Result.success(plantingCategories)
        } else {
            LogUtil.d("种植模块分类", "获取分类失败，${plantingCategoryResponse.message}")
            Result.failure(RuntimeException("response status is ${plantingCategoryResponse.message}"))
        }

    }
    //种植模块获取最新文章
    fun plantsNews(type: Int) = fire(Dispatchers.IO) {
        val plantsNewsResponse = HNBNetwork.plantsNews(type)
        if (plantsNewsResponse.code == 1) { //根据状态来处理
            LogUtil.d("种植模块最新文章", "获取最新文章成功，分类如下：${plantsNewsResponse.data}")
            val plantsNews = plantsNewsResponse.data
            Result.success(plantsNews)
        } else {
            LogUtil.d("种植模块最新文章", "获取最新文章失败，${plantsNewsResponse.message}")
            Result.failure(RuntimeException("response status is ${plantsNewsResponse.message}"))
        }

    }


    //种植模块根据分类获取文章
    fun plantsNewsOfCategory(classify:String,item:String) = fire(Dispatchers.IO) {
        val plantsNewsOfCategoryResponse = HNBNetwork.plantsNewsOfCategory(classify,item)

        if (plantsNewsOfCategoryResponse.code == 1) { //根据状态来处理
            LogUtil.d("种植模块根据分类获取文章", "根据分类获取文章成功，如下：${plantsNewsOfCategoryResponse.data}")
            val plantsNews = plantsNewsOfCategoryResponse.data
            Result.success(plantsNews)
        } else {
            LogUtil.d("根据分类获取文章", "根据分类获取文章失败，${plantsNewsOfCategoryResponse.message}")
            Result.failure(RuntimeException("response status is ${plantsNewsOfCategoryResponse.message}"))
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