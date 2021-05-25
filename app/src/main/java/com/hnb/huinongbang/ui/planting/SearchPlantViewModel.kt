package com.hnb.huinongbang.ui.planting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.PlantsNews

class SearchPlantViewModel : ViewModel() {
    var plantList = ArrayList<PlantsNews>()
    private val searchLiveData = MutableLiveData<String>()
    val searchResult = Transformations.switchMap(searchLiveData){ keyword ->
        Repository.plantSearch(keyword)
    }
    //外部调用函数
    fun search(keyword: String){
        searchLiveData.value = keyword
    }
}