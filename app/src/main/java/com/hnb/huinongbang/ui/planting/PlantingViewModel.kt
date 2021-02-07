package com.hnb.huinongbang.ui.planting


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.LoginData
import com.hnb.huinongbang.logic.model.PlantingCategory
import com.hnb.huinongbang.logic.model.PlantsNews
import com.hnb.huinongbang.logic.model.PlantsNewsOfCategory
import com.hnb.huinongbang.util.LogUtil

class PlantingViewModel : ViewModel() {
    private val typeLiveData = MutableLiveData<Int>()

    //种植模块分类
    var plantingcategoryList = ArrayList<PlantingCategory>()
    val plantingcategoriesLiveData = Transformations.switchMap(typeLiveData) { type ->
        Repository.plantingCategories(type)
    }

    //最新文章
    var plantsNewsList = ArrayList<PlantsNews>()
    val plantsNewsLiveData = Transformations.switchMap(typeLiveData) { type ->
        Repository.plantsNews(type)
    }

    fun getdata(type: Int) {
        typeLiveData.value = type
    }


    //根据分类获取文章
    private val plantsNewsCategoryLiveData = MutableLiveData<PlantsNewsOfCategory>()
    var plant_Class: String = ""
    var plant_Name: String = ""
    var plantsNewsOfCategoryList = ArrayList<PlantsNews>()
    val plantsNewsOfCategoryLivepData =
        Transformations.switchMap(plantsNewsCategoryLiveData) { plantsNewsOfCategory ->

            Repository.plantsNewsOfCategory(
                plantsNewsOfCategory.plant_Class,
                plantsNewsOfCategory.plant_Name

            )
        }

    fun getPlantsNewsOfCategory(plantsNewsOfCategory: PlantsNewsOfCategory) {
        plantsNewsCategoryLiveData.value = plantsNewsOfCategory
    }

}