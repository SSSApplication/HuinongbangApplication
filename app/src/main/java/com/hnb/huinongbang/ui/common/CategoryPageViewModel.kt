package com.hnb.huinongbang.ui.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.getItemDataByCid

class CategoryPageViewModel : ViewModel() {
    private val productsDataLiveData = MutableLiveData<getItemDataByCid>()
    val productsLiveData = Transformations.switchMap(productsDataLiveData){ productsData ->
        Repository.getItemDataByCid(productsData)
    }
    //外部调用函数
    fun getProducts(productsData: getItemDataByCid){
        productsDataLiveData.value = productsData
    }
}