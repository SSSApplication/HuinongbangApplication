package com.hnb.huinongbang.logic.network

import com.hnb.huinongbang.logic.model.PolicyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PolicyService {
    //获取所有政策
    @GET("forempolicys")
    fun policy(@Query("type") type: Int): Call<PolicyResponse>
    //根据分类获取政策
    @GET("foremnewpolicys")
    fun policybyid(@Query("pid") pid: Int): Call<PolicyResponse>

}