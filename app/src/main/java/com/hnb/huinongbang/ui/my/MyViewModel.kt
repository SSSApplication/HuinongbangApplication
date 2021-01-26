package com.hnb.huinongbang.ui.my

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "这是我的模块"
    }
    val text: LiveData<String> = _text
}