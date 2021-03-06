package com.hnb.huinongbang.logic

import androidx.lifecycle.liveData
import com.hnb.huinongbang.logic.dao.UserDAO
import com.hnb.huinongbang.logic.model.*
import com.hnb.huinongbang.logic.network.HNBNetwork
import com.hnb.huinongbang.util.LogUtil
import com.hnb.huinongbang.util.ToastUtil
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
    //注册用户
    fun register(registerData: RegisterData) = fire(Dispatchers.IO) {
        val response = HNBNetwork.register(registerData)
        if (response.code == 1) { //根据状态来处理
            LogUtil.d("注册模块", "注册成功，用户名：${response.data.user_name}")
            val user = response.data
            Result.success(user)
        } else {
            LogUtil.d("注册模块", "注册失败，${response.message}")
            Result.failure(RuntimeException("response status is ${response.message}"))
        }
    }
    //更改密码
    fun updateMyInformation( updateData: UpdateMyInformationData) = fire(Dispatchers.IO) {
        val response = HNBNetwork.updateMyInformation(updateData)
        if (response.code == 1) { //根据状态来处理
            LogUtil.d("我的信息", "更新成功")
            val user = response.data
            Result.success(user)
        } else {
            LogUtil.d("我的信息", "更新失败，${response.message}")
            Result.failure(RuntimeException("response status is ${response.message}"))
        }
    }
    //实名认证
    fun identity( submitData: IdentityData) = fire(Dispatchers.IO) {
        val response = HNBNetwork.identity(submitData)
        if (response.code == 1) { //根据状态来处理
            LogUtil.d("实名认证", "提交成功")
            val user = response.data
            Result.success(user)
        } else {
            LogUtil.d("实名认证", "提交失败，${response.message}")
            Result.failure(RuntimeException("response status is ${response.message}"))
        }
    }
    //删除购物车
    fun deleteCart( data: Int) = fire(Dispatchers.IO) {
        val response = HNBNetwork.deleteCart(data)
        if (response.code == 1) { //根据状态来处理
            LogUtil.d("删除购物车", "删除成功")
            val data = response.data
            Result.success(data)
        } else {
            LogUtil.d("删除购物车", "删除失败，${response.message}")
            Result.failure(RuntimeException("response status is ${response.message}"))
        }
    }
    //获取购物车
    fun getCart( data: GetCartData) = fire(Dispatchers.IO) {
        val response = HNBNetwork.getCart(data)
        if (response.code == 1) { //根据状态来处理
            LogUtil.d("获取购物车", "获取成功")
            val data = response.data
            Result.success(data)
        } else {
            LogUtil.d("获取购物车", "获取失败，${response.message}")
            Result.failure(RuntimeException("response status is ${response.message}"))
        }
    }
    //购买购物车
    fun buyCart( data: BuyCartData) = fire(Dispatchers.IO) {
        val response = HNBNetwork.buyCart(data)
        if (response.code == 1) { //根据状态来处理
            LogUtil.d("购买购物车", "获取返回成功")
            Result.success(response)
        } else {
            LogUtil.d("购买购物车", "获取返回失败，${response.message}")
            Result.failure(RuntimeException("response status is ${response.message}"))
        }
    }
    //获取订单
    fun getOrder( data: GetOrderData) = fire(Dispatchers.IO) {
        val response = HNBNetwork.getOrder(data)
        if (response.code == 1) { //根据状态来处理
            LogUtil.d("获取订单", "获取成功")
            val data = response.data
            Result.success(data)
        } else {
            LogUtil.d("获取订单", "获取失败，${response.message}")
            Result.failure(RuntimeException("response status is ${response.message}"))
        }
    }
    //更新用户信息
    fun changePassword( data: ChangePasswordData) = fire(Dispatchers.IO) {
        val response = HNBNetwork.changePassword(data)
        if (response.code == 1) { //根据状态来处理
            LogUtil.d("更改密码", "更改成功")
            val user = response.data
            Result.success(user)
        } else {
            LogUtil.d("更改密码", "更改失败，${response.message}")
            Result.failure(RuntimeException("response status is ${response.message}"))
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
    //搜索
    fun search(data: SearchData) = fire(Dispatchers.IO) {
        val response = HNBNetwork.search(data)
        if (response.code == 1) { //根据状态来处理
            LogUtil.d("搜索", "搜索成功：${response.data}")
            val products = response.data
            Result.success(products)
        } else {
            LogUtil.d("搜索", "搜索失败，${response.message}")
            Result.failure(RuntimeException("response status is ${response.message}"))
        }
    }
    //加入购物车(清单)
    fun addCart(data: AddCartData) = fire(Dispatchers.IO) {
        LogUtil.d("hhhhh", "${data}")
        val response = HNBNetwork.addCart(data)
        LogUtil.d("hhhhh1", "${data}")
        if (response.code == 1) { //根据状态来处理
            LogUtil.d("产品详情", "产品加入购物车成功，分类如下：${response.data}")
            val product = response.data
            Result.success(product)
        } else {
            LogUtil.d("产品详情", "产品加入购物车失败，${response.message}")
            Result.failure(RuntimeException("response status is ${response.message}"))
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
    //创建订单之前信息准备
    fun beforeCreateOrder(data: GetOrderItemData) = fire(Dispatchers.IO) {
        LogUtil.d("测试","开始传输2")
        val response = HNBNetwork.beforeCreateOrder(data)
        if (response.code == 1) { //根据状态来处理
            LogUtil.d("订单信息", "准备成功，${response.data}")
            val reviews = response
            Result.success(reviews)
        } else {
            LogUtil.d("订单信息", "准备失败，${response.message}")
            Result.failure(RuntimeException("response status is ${response.message}"))
        }
    }
    //创建订单
    fun createOrder(data: CreateOrderData) = fire(Dispatchers.IO) {
        val response = HNBNetwork.createOrder(data)
        if (response.code == 1) { //根据状态来处理
            LogUtil.d("订单信息", "创建成功，${response.data}")
            val reviews = response
            Result.success(reviews)
        } else {
            LogUtil.d("订单信息", "创建失败，${response.message}")
            Result.failure(RuntimeException("response status is ${response.message}"))
        }
    }
    //扣除慧农币
    fun payForDonation(data: PayForDonationData) = fire(Dispatchers.IO) {
        val response = HNBNetwork.payForDonation(data)
        if (response.code == 1) { //根据状态来处理
            LogUtil.d("扣除慧农币", "扣除成功，${response.data}")
            val reviews = response
            Result.success(reviews)
        } else {
            LogUtil.d("扣除慧农币", "扣除失败，${response.message}")
            Result.failure(RuntimeException("response status is ${response.message}"))
        }
    }

    //购物付款
    fun payForShopping(data: PayForShopping) = fire(Dispatchers.IO) {
        val response = HNBNetwork.payForShopping(data)
        if (response.code == 1) { //根据状态来处理
            LogUtil.d("购物付款", "付款成功，${response.data}")
            Result.success(response)
        } else {
            LogUtil.d("购物付款", "付款失败，${response.message}")
            Result.failure(RuntimeException("response status is ${response.message}"))
        }
    }

    //确认收货
    fun morderConfirmed(oid: String) = fire(Dispatchers.IO) {
        val response = HNBNetwork.morderConfirmed(oid)
        if (response.code == 1) { //根据状态来处理
            LogUtil.d("确认收货", "成功，${response.data}")
            Result.success(response)
        } else {
            LogUtil.d("确认收货", "失败，${response.message}")
            Result.failure(RuntimeException("response status is ${response.message}"))
        }
    }

    //评价
    fun addReview(data: AddReviewData) = fire(Dispatchers.IO) {
        val response = HNBNetwork.addReview(data)
        if (response.code == 1) { //根据状态来处理
            LogUtil.d("评价", "成功，${response.data}")
            Result.success(response)
        } else {
            LogUtil.d("评价", "失败，${response.message}")
            Result.failure(RuntimeException("response status is ${response.message}"))
        }
    }

    //种植模块获取所有分类
    fun plantingCategories() = fire(Dispatchers.IO) {
        val plantingCategoryResponse = HNBNetwork.plantingCategories()
        if (plantingCategoryResponse.code == 1) { //根据状态来处理
            LogUtil.d("种植模块分类", "获取分类成功，分类如下：${plantingCategoryResponse.data}")
            val plantingCategories = plantingCategoryResponse.data
            Result.success(plantingCategories)
        } else {
            Result.failure(RuntimeException("response status is ${plantingCategoryResponse.message}"))
        }

    }
    //种植模块获取最新文章
    fun plantsNews() = fire(Dispatchers.IO) {
        val plantsNewsResponse = HNBNetwork.plantsNews()
        if (plantsNewsResponse.code == 1) { //根据状态来处理
            LogUtil.d("种植模块最新文章", "获取最新文章成功，分类如下：${plantsNewsResponse.data}")
            val plantsNews = plantsNewsResponse.data
            Result.success(plantsNews)
        } else {
            Result.failure(RuntimeException("response status is ${plantsNewsResponse.message}"))
        }

    }
    //搜索
    fun plantSearch(keyword: String) = fire(Dispatchers.IO) {
        val response = HNBNetwork.plantSearch(keyword)
        if (response.code == 1) { //根据状态来处理
            LogUtil.d("搜索", "搜索成功：${response.data}")
            val plant = response.data
            Result.success(plant)
        } else {
            LogUtil.d("搜索", "搜索失败，${response.message}")
            Result.failure(RuntimeException("response status is ${response.message}"))
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
            Result.failure(RuntimeException("response status is ${plantsNewsOfCategoryResponse.message}"))
        }

    }

    //获取专家信息
    fun doctorInformation() = fire(Dispatchers.IO) {
        val doctorInformationResponse = HNBNetwork.doctorInformation()
        if (doctorInformationResponse.code == 1) { //根据状态来处理
            LogUtil.d("获取专家信息", "获取专家信息成功，如下：${doctorInformationResponse.data}")
            val doctorInformation = doctorInformationResponse.data
            Result.success(doctorInformation)
        } else {
            Result.failure(RuntimeException("获取专家信息失败response status is ${doctorInformationResponse.message}"))
        }

    }
    //根据·专家id获取专家评论链表
    fun doctorComments(id:Int) = fire(Dispatchers.IO) {
        LogUtil.d("获取专家评论链表", "获取专家评论链表成功，如下：")
        val doctorCommentsResponse = HNBNetwork.doctorComments(id)
         if (doctorCommentsResponse.code == 1) { //根据状态来处理
            LogUtil.d("获取专家评论链表", "获取专家评论链表成功，如下：${doctorCommentsResponse.data}")
            val doctorComments = doctorCommentsResponse.data
            Result.success(doctorComments)
        } else {
            Result.failure(RuntimeException("获取专家评论链表失败response status is ${doctorCommentsResponse.message}"))
        }

    }
    //专家模块评论回复功能
    fun doctorCommentBack(doctorCommentBack:DoctorCommentBack) = fire(Dispatchers.IO) {
        val doctorCommentBackResponse = HNBNetwork.doctorCommentBack(doctorCommentBack)
        if (doctorCommentBackResponse.code == 1) { //根据状态来处理
            val doctorCommentBack = doctorCommentBackResponse.data
            Result.success(doctorCommentBack)
        } else {
            Result.failure(RuntimeException("专家模块评论回复功能response status is ${doctorCommentBackResponse.message}"))
        }

    }



    //获取政策
    fun policys() = fire(Dispatchers.IO) {
        val policyClassifyResponse = HNBNetwork.policys()
        if (policyClassifyResponse.code == 1) { //根据状态来处理
            LogUtil.d("政策模块", "获取政策成功，分类如下：${policyClassifyResponse.data}")
            LogUtil.d("政策模块", "获取政策成功，分类如下：${policyClassifyResponse.data.toString().length}")
            LogUtil.d("政策模块", "获取政策成功，分类如下：${policyClassifyResponse.data.toString().substring(7000)}")
            val classifies = policyClassifyResponse.data
            Result.success(classifies)
        } else {
            LogUtil.d("政策模块", "获取政策失败，${policyClassifyResponse.message}")
            Result.failure(RuntimeException("response status is ${policyClassifyResponse.message}"))
        }
    }

    //获取最新10条政策
    fun newpolicys() = fire(Dispatchers.IO) {
        val policyResponse = HNBNetwork.newpolicys()
        if (policyResponse.code == 1) { //根据状态来处理
            LogUtil.d("政策模块", "分类获取政策成功，分类如下：${policyResponse.data}")
            val policys = policyResponse.data
            Result.success(policys)
        } else {
            LogUtil.d("政策模块", "分类获取政策失败，${policyResponse.message}")
            Result.failure(RuntimeException("response status is ${policyResponse.message}"))
        }
    }
    //搜索
    fun policySearch(keyword: String) = fire(Dispatchers.IO) {
        val response = HNBNetwork.policySearch(keyword)
        if (response.code == 1) { //根据状态来处理
            LogUtil.d("搜索", "搜索成功：${response.data}")
            val policy = response.data
            Result.success(policy)
        } else {
            LogUtil.d("搜索", "搜索失败，${response.message}")
            Result.failure(RuntimeException("response status is ${response.message}"))
        }
    }

    //测试的封装
    fun test(oiid: Array<String>, type: Int) = fire(Dispatchers.IO) {
        val userResponse = HNBNetwork.test(oiid, type)
        if (userResponse.code == 1) { //根据状态来处理
            LogUtil.d("HNBNetwork", "分类获取政策成功，分类如下：${userResponse}")
            Result.success(userResponse)
        } else {
            LogUtil.d("HNBNetwork", "分类获取政策失败，${userResponse}")
            Result.failure(RuntimeException("response status is ${userResponse}"))
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
    fun clearUser(user: User) = UserDAO.clearUser(user)
    fun getUser() = UserDAO.getUser()
    fun isUserSaved() = UserDAO.isUserSaved()
    //记住密码
    fun remember() = UserDAO.remember()
    fun unremember() = UserDAO.unremember()
    fun isRemembered() = UserDAO.isRemembered()
}