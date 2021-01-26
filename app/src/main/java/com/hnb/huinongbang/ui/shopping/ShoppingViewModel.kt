package com.hnb.huinongbang.ui.shopping

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoppingViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "这是购物模块"
    }
    val text: LiveData<String> = _text
}