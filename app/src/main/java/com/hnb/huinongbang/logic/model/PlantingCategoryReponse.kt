package com.hnb.huinongbang.logic.model

import java.util.*

//种植模块分类(粮食作物，水果等)
data class PlantingCategoryResponse(val code: Int, val data: List<PlantingCategory>, val message: String)

data class PlantingCategory(val names: String, //分类名称
                            val plant:ArrayList<String>//详细分类
)

//最新十篇文章的简介信息（放于首页）
data class PlantsNewsResponse(val code: Int, val data: List<PlantsNews>, val message: String)
data class PlantsNews(
        val plant_ID:Int,
        val plant_Name:String,
        val plant_Title:String,
        val plant_Values:String,
        val  puser_ID:User,
        val plant_Class:String,
        val plant_Number:Int,
        val plant_View:Int,
        val  plant_CreateTime: Date
)

//根据分类查询文章信息
data class PlantsNewsOfCategoryResponse(val code: Int, val data: List<PlantsNews>, val message: String)
data class PlantsNewsOfCategory(val plant_Class: String, //分类所属种类
                            val plant_Name:String//分类名字
)
