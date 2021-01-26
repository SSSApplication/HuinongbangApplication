package com.hnb.huinongbang.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hnb.huinongbang.logic.Repository
import com.hnb.huinongbang.logic.model.LoginData

class LoginViewModel : ViewModel() {
    private val loginDataLiveData = MutableLiveData<LoginData>()
    val loginLiveData = Transformations.switchMap(loginDataLiveData){loginData ->
        Repository.login(loginData)
    }
    //外部调用函数
    fun login(loginData: LoginData){
        loginDataLiveData.value =loginData
    }
}