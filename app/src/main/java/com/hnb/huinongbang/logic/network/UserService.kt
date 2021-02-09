package com.hnb.huinongbang.logic.network

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
}