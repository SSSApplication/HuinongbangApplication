package com.hnb.huinongbang.ui.Donate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.Category

class DonateViewModel : ViewModel() {

    var categoryList = ArrayList<Category>()
    private val typeLiveData = MutableLiveData<Int>()
    val categoriesLiveData = Transformations.switchMap(typeLiveData){ type ->
        Repository.categories(type)
    }
    fun categories(type: Int){
        typeLiveData.value = type
    }
}