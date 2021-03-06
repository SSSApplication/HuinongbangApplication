package com.hnb.huinongbang.logic.network

import com.hnb.huinongbang.logic.model.GeneralResponse
import com.hnb.huinongbang.logic.model.OrderItemResponse
import com.hnb.huinongbang.logic.model.OrderResponse
import com.hnb.huinongbang.logic.model.UserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface UserService {
    //登录
    @POST("foremlogin")
    fun login(@Query("name") name: String, @Query("password") password: String): Call<UserResponse>

    //注册
    @GET("foremregister")
    fun register(@Query("phone") phone: String, @Query("password") password: String): Call<UserResponse>

    //更新个人信息
    @GET("foremupdate")
    fun updateMyInformation(
        @Query("user_name") user_name: String,
        @Query("user_nickname") user_nickname: String,
        @Query("user_sex") user_sex: String,
        @Query("user_birthday") user_birthday: String,
        @Query("user_address") user_address: String,
        @Query("user_introduce") user_introduce: String,
        @Query("phone") phone: String,
        @Query("password") password: String
    ): Call<UserResponse>

    //更新个人信息
    @GET("foremupdatePassword")
    fun changePassword(
        @Query("phone") phone: String,
        @Query("oldPassword") oldPassword: String,
        @Query("newPassword") newPassword: String
    ): Call<UserResponse>

    //实名认证
    @GET("foremuploadIdentity")
    fun identity(
        @Query("phone") phone: String,
        @Query("password") password: String,
        @Query("name") name: String,
        @Query("type") type: String,
        @Query("number") number: String
    ): Call<UserResponse>

    //获取购物车
    @GET("foremcarts")
    fun getCart(
        @Query("uid") uid: String,
        @Query("type") type: String
    ): Call<OrderItemResponse>

    //购买购物车
    @GET("forembuy")
    fun buyCart(
        @Query("oiid") oiid: Array<String>,
        @Query("type") type: String
    ): Call<OrderItemResponse>

    //获取订单
    @GET("forembought")
    fun getOrder(
        @Query("uid") uid: String,
        @Query("type") type: String
    ): Call<OrderResponse>

    //删除购物车
    @GET("foremdeleteCart")
    fun deleteCart(@Query("oiid") oiid: Int): Call<GeneralResponse>
}