package com.hnb.huinongbang.logic.network

import com.hnb.huinongbang.logic.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {
    //获取所有产品
    @GET("foremproducts")
    fun products(@Query("type") type: Int): Call<ProductsResponse>
    //根据分类获取产品
    @GET("foremproductsbycid")
    fun productsbycid(@Query("cid") cid: Int): Call<ProductsResponse>
    //获取单个产品
    @GET("foremproduct")
    fun product(@Query("pid") pid: Int): Call<ProductResponse>
    //获取产品属性
    @GET("forempropertyValues")
    fun propertyValues(@Query("pid") pid: Int): Call<PropertyValueResponse>
    //获取产品评价
    @GET("foremreviews")
    fun reviews(@Query("pid") pid: Int): Call<ReviewResponse>
    //搜索
    @GET("foremsearch")
    fun search(
        @Query("keyword") keyword: String,
        @Query("type") type: String
    ): Call<ProductsResponse>
    //加入购物车
    @GET("foremaddCart")
    fun addCart(@Query("pid") pid: String,
                @Query("type") type: String,
                @Query("uid") uid: String,
                @Query("num") num: String
    ): Call<GeneralResponse>
}