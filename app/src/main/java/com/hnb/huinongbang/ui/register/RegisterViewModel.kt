package com.hnb.huinongbang.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.LoginData
import com.hnb.huinongbang.logic.model.RegisterData

class RegisterViewModel : ViewModel() {
    private val registerDataLiveData = MutableLiveData<RegisterData>()
    val registerLiveData = Transformations.switchMap(registerDataLiveData){ registerData ->
        Repository.register(registerData)
    }
    //外部调用函数
    fun register(registerData: RegisterData){
        registerDataLiveData.value = registerData
    }
}