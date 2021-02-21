package com.hnb.huinongbang.logic.network

import com.hnb.huinongbang.logic.model.OrderItemResponse
import com.hnb.huinongbang.logic.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BuyService {
    //注册 测试内容 实际需要你自己写
    @GET("forembuy")
    fun buy(@Query("oiid") oiid: Array<String>, @Query("type") type: Int): Call<OrderItemResponse>
}