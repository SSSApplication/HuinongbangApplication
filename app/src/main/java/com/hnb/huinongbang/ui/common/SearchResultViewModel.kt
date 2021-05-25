package com.hnb.huinongbang.ui.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.Product
import com.hnb.huinongbang.logic.model.SearchData

class SearchResultViewModel : ViewModel() {
    var productList = ArrayList<Product>()
    private val searchLiveData = MutableLiveData<SearchData>()
    val searchResult = Transformations.switchMap(searchLiveData){ data ->
        Repository.search(data)
    }
    //外部调用函数
    fun search(data: SearchData){
        searchLiveData.value = data
    }
}