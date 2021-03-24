package com.hnb.huinongbang.logic.network

import com.hnb.huinongbang.logic.model.*
import com.hnb.huinongbang.logic.network.HNBNetwork.await
import com.hnb.huinongbang.util.LogUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

//定义一个统一的网络数据源访问入口，对所有网络请求的API进行封装
object HNBNetwork {
    //封装user的网络请求
    private val userService = ServiceCreator.create<UserService>()
    suspend fun login(loginData: LoginData) = userService.login(loginData.name, loginData.password).await()
    suspend fun register(registerData: RegisterData) = userService.register(registerData.phone, registerData.password).await()
    suspend fun updateMyInformation(data: UpdateMyInformationData) = userService.updateMyInformation(
        data.user_name,
        data.user_nickname,
        data.user_sex,
        data.user_birthday,
        data.user_address,
        data.user_introduce,
        data.phone,
        data.password
    ).await()
    suspend fun changePassword(data: ChangePasswordData) = userService.changePassword(
        data.phone,
        data.oldPassword,
        data.newPassword
    ).await()
    suspend fun identity(data: IdentityData) = userService.identity(
        data.phone,
        data.password,
        data.name,
        data.type,
        data.number
    ).await()

    //封装Category的网络请求
    private val categoryService = ServiceCreator.create<CategoryService>()
    suspend fun categories(type: Int) = categoryService.categories(type).await()


    //封装Product的网络请求
    private val productService = ServiceCreator.create<ProductService>()
    private val buyService = ServiceCreator.create<BuyService>()
    suspend fun products(type: Int) = productService.products(type).await()
    suspend fun productsbycid(cid: Int) = productService.productsbycid(cid).await()
    suspend fun product(pid: Int) = productService.product(pid).await()
    suspend fun propertyValues(pid: Int) = productService.propertyValues(pid).await()
    suspend fun reviews(pid: Int) = productService.reviews(pid).await()
    suspend fun addCart(data: AddCartData) = productService.addCart(
        data.pid,
        data.type,
        data.uid,
        data.num
    ).await()
    suspend fun beforeCreateOrder(data: GetOrderItemData) = buyService.beforeCreateOrder(
        data.pid,
        data.type,
        data.uid,
        data.num
    ).await()
    suspend fun createOrder(data: CreateOrderData) = buyService.createOrder(
        data.uid,
        data.oiid,
        data.address,
        data.post,
        data.receiver,
        data.mobile,
        data.userMessage,
        data.type
    ).await()
    suspend fun payForDonation(data: PayForDonationData) = buyService.payForDonation(
        data.oid,
        data.uid,
        data.total
    ).await()

    //封装PlantingCategory的网络请求
    private val plantingCategoryService = ServiceCreator.create<PlantingCategoryService>()
    suspend fun plantingCategories() = plantingCategoryService.plantingCategories().await()
    //plantsNews的网络请求
    suspend fun plantsNews() = plantingCategoryService.plantsNews().await()
    //plantsNewsOfCategory的网络请求
    suspend fun plantsNewsOfCategory(classify:String,item:String) = plantingCategoryService.plantsNewsOfCategory(classify,item).await()

    //封装Doctor栏目的网络请求
    private val doctorService = ServiceCreator.create<DoctorService>()
    suspend fun doctorInformation() = doctorService.doctorInformation().await()
    suspend fun doctorComments(id:Int) = doctorService.doctorComments(id).await()


    //封装Policy的网络请求
    private val policyService = ServiceCreator.create<PolicyService>()
    suspend fun policys() = policyService.policys().await()
    suspend fun newpolicys() = policyService.newpolicys().await()

    //封装测试的网络请求
    private val testService = ServiceCreator.create<BuyService>()
    suspend fun test(oiid: Array<String>, type: Int) = testService.buy(oiid, type).await()


    //协程suspend
    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->

            enqueue(object : Callback<T> {

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    LogUtil.d("Test", body.toString())
                    LogUtil.d("HNBNetwork", "服务器返回成功")
                    if (body != null) continuation.resume(body) //服务器返回成功
                    else continuation.resumeWithException(
                        //服务器返回成功，但是值为空
                        RuntimeException("response body is null 服务器返回成功，但是值为空")
                    )
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    //服务器返回失败
                    LogUtil.d("HNBNetwork", "服务器返回失败")
                    continuation.resumeWithException(t)
                }
            })

        }
    }
}