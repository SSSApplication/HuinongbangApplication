package com.hnb.huinongbang.logic.network

import com.hnb.huinongbang.logic.model.PlantingCategoryResponse
import com.hnb.huinongbang.logic.model.PlantsNewsOfCategoryResponse
import com.hnb.huinongbang.logic.model.PlantsNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlantingCategoryService {
    //分类
    @GET("forempclassifys")
    fun plantingCategories(@Query("type") type: Int): Call<PlantingCategoryResponse>

    //最新10篇
    @GET("foremnewplants")
    fun plantsNews(@Query("type") type: Int): Call<PlantsNewsResponse>

    //根据分类获取文章
    @GET("foremplants")
    fun plantsNewsOfCategory(@Query("classify") classify: String, @Query("item") item: String): Call<PlantsNewsOfCategoryResponse>
}