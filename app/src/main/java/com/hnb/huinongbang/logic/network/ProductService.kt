package com.hnb.huinongbang.logic.network

import com.hnb.huinongbang.logic.model.ProductResponse
import com.hnb.huinongbang.logic.model.ProductsResponse
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
}