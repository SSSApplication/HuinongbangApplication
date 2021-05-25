package com.hnb.huinongbang.logic.network

import com.hnb.huinongbang.logic.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PolicyService {
    //获取所有分类和对应政策
    @GET("forempolicys")
    fun policys(): Call<PolicyClassifyResponse>
    //根据分类获取政策
    @GET("foremnewpolicys")
    fun newpolicys(): Call<PolicyResponse>

    //搜索
    @GET("forempolicySearch")
    fun search(
        @Query("keyword") keyword: String
    ): Call<PoliciesResponse>

}