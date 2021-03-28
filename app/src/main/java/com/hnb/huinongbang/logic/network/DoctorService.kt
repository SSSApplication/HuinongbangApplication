package com.hnb.huinongbang.logic.network


import com.hnb.huinongbang.logic.model.DoctorCommentBackResponse
import com.hnb.huinongbang.logic.model.DoctorCommentsResponse
import com.hnb.huinongbang.logic.model.DoctorInformationResponse
import com.hnb.huinongbang.logic.model.PlantsNewsOfCategoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Query

interface DoctorService {
    //点击专家问答按钮获得专家信息
    @GET("foremdoctors")
    fun doctorInformation(): Call<DoctorInformationResponse>

    //根据id获得该专家下的问答评论
    //根据分类获取文章
    @GET("foremdiscuss")
    fun doctorComments(@Query("id") id: Int): Call<DoctorCommentsResponse>


    //评论回复功能
    @GET("foremadddiscuss")
    fun doctorCommentBack(@Query("uid")uid:Int,@Query("DUserup_ID")DUserup_ID:Int,@Query("Discuss_root")Discuss_root:Int,@Query("Discuss_Values")Discuss_Values:String):Call<DoctorCommentBackResponse>

}