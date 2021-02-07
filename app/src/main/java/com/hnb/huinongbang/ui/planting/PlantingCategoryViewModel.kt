package com.hnb.huinongbang.ui.planting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.Transformations
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.PlantingCategory
import com.hnb.huinongbang.logic.model.PlantsNews


class PlantingCategoryViewModel :ViewModel(){
    var plantingcategoryList = ArrayList<PlantingCategory>()
    private val typeLiveData = MutableLiveData<Int>()
    val plantingcategoriesLiveData = Transformations.switchMap(typeLiveData){ type ->
        Repository.plantingCategories(type)
    }


    var plantsNewsList = ArrayList<PlantsNews>()
    val plantsNewsLiveData = Transformations.switchMap(typeLiveData) { type ->
        Repository.plantsNews(type)
    }
    fun getdata(type: Int) {
        typeLiveData.value = type
    }
}