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
}