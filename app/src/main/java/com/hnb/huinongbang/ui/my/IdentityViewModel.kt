package com.hnb.huinongbang.ui.my

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.IdentityData

class IdentityViewModel : ViewModel() {
    private val submitLiveData = MutableLiveData<IdentityData>()
    val submitResult = Transformations.switchMap(submitLiveData){ data ->
        Repository.identity(data)
    }
    //外部调用函数
    fun submit(data: IdentityData){
        submitLiveData.value = data
    }
}