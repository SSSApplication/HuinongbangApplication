package com.hnb.huinongbang.ui.my

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.ChangePasswordData

class ChangePasswordViewModel : ViewModel() {
    private val changeLiveData = MutableLiveData<ChangePasswordData>()
    val changeResult = Transformations.switchMap(changeLiveData){ data ->
        Repository.changePassword(data)
    }
    //外部调用函数
    fun change(data: ChangePasswordData){
        changeLiveData.value = data
    }
}