package com.hnb.huinongbang.logic.network


import com.hnb.huinongbang.logic.model.DoctorInformationResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DoctorService {
    //点击专家问答按钮获得专家信息
    @GET("foremdoctors")
    fun doctorInformation(): Call<DoctorInformationResponse>
}