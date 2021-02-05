package com.hnb.huinongbang.logic.network

import com.hnb.huinongbang.logic.model.PolicyClassifyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PolicyClassifyService {
    @GET("names")
    fun policyclassify(@Query("type") type: Int): Call<PolicyClassifyResponse>
}