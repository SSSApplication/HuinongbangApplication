package com.hnb.huinongbang

import com.hnb.huinongbang.logic.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TestService {
    //注册
    @GET("forembuy")
    fun buy(@Query("oiid") oiid: Array<String>, @Query("type") type: Int): Call<UserResponse>
}