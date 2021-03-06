package com.hnb.huinongbang.logic.network

import com.hnb.huinongbang.logic.model.CreateOrderResponse
import com.hnb.huinongbang.logic.model.GeneralResponse
import com.hnb.huinongbang.logic.model.Order
import com.hnb.huinongbang.logic.model.OrderItemResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BuyService {
    @GET("forembuy")
    fun buy(@Query("oiid") oiid: Array<String>, @Query("type") type: Int): Call<OrderItemResponse>

    //创建订单必要信息获取
    @GET("forembuyone")
    fun beforeCreateOrder(
        @Query("pid") pid: String,
        @Query("type") type: String,
        @Query("uid") uid: String,
        @Query("num") num: String
    ): Call<OrderItemResponse>

    //创建订单
    @GET("foremcreateOrder")
    fun createOrder(
        @Query("uid") uid: String,
        @Query("oiid") oiid: Array<String>,
        @Query("address") address: String,
        @Query("post") post: String,
        @Query("receiver") receiver: String,
        @Query("mobile") mobile: String,
        @Query("userMessage") userMessage: String,
        @Query("type") type: String
    ): Call<CreateOrderResponse>

    //扣除慧农币
    @GET("foremmoneypayed")
    fun payForDonation(
        @Query("oid") oid: String,
        @Query("uid") uid: String,
        @Query("total") total: String
    ): Call<CreateOrderResponse>

    //购物付款
    @GET("forempayed")
    fun payForShopping(
        @Query("oid") oid: String,
        @Query("total") total: String
    ): Call<CreateOrderResponse>

    //确认收货
    @GET("foremorderConfirmed")
    fun morderConfirmed(
        @Query("oid") oid: String
    ): Call<GeneralResponse>

    //评价
    @GET("foremdoreview")
    fun review(
        @Query("oid") oid: String,
        @Query("pid") pid: String,
        @Query("uid") uid: String,
        @Query("content") content: String
    ): Call<GeneralResponse>
}