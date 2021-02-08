package com.hnb.huinongbang.ui.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hnb.huinongbang.logic.Repository

class CategoryPageViewModel : ViewModel() {
    private val cidDataLiveData = MutableLiveData<Int>()
    val productsLiveData = Transformations.switchMap(cidDataLiveData){ cid ->
        Repository.productsbycid(cid)
    }
    //外部调用函数
    fun getProducts(cid: Int){
        cidDataLiveData.value = cid
    }
}