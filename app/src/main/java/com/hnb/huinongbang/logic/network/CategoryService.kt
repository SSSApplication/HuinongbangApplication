package com.hnb.huinongbang.logic.network

import com.hnb.huinongbang.logic.model.CategoryResponse
import com.hnb.huinongbang.logic.model.ProductResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryService{
    //登录
    @GET("foremcategories")
    fun categories(@Query("type") type: Int): Call<CategoryResponse>

    //通过分类id请求其下数据
    @GET("foremproductsbycid")
    fun getItemDataByCid(@Query("type") type: Int): Call<ProductResponse>
}