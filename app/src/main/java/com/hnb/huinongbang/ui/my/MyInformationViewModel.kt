package com.hnb.huinongbang.ui.my

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.LoginData
import com.hnb.huinongbang.logic.model.UpdateMyInformationData

class MyInformationViewModel : ViewModel() {
    private val updateLiveData = MutableLiveData<UpdateMyInformationData>()
    val updateResult = Transformations.switchMap(updateLiveData){ data ->
        Repository.updateMyInformation(data)
    }
    //外部调用函数
    fun update(data: UpdateMyInformationData){
        updateLiveData.value = data
    }
}